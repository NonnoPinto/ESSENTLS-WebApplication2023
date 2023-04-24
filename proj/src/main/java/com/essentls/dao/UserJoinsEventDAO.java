package com.essentls.dao;

import java.sql.*;

import com.essentls.resource.Participant;


/**
 * Let a User join an Event
 *
 * @author Vittorio Cardillo (vittorio.cardillo@studenti.unipd.it)
 * @version 1.00
 * @since 1.00
 */
public class UserJoinsEventDAO extends AbstractDAO<Boolean> {
    private static final String STATEMENT_JOIN_EVENT = "INSERT INTO public.\"Participants\" (\"userId\", \"eventId\", role, date, \"attributeValues\") VALUES (?, ?, ?, ?, ?)";
    private static final String STATEMENT_GET_PARTICIPANTS = "SELECT * FROM public.\"Participants\" INNER JOIN public.\"Users\" ON public.\"Participants\".\"userId\" = public.\"Users\".\"id\" WHERE \"eventId\" = ?";
    private static final String STATEMENT_GET_EVENT = "SELECT * FROM public.\"Events\" WHERE \"id\" = ?";

    private final Participant participant;

    public UserJoinsEventDAO(Connection con, Participant participant) throws SQLException {
        super(con);

        if (participant == null) {
            LOGGER.error("The participant cannot be null.");
            throw new NullPointerException("The participant cannot be null.");
        }
        con.setAutoCommit(false);
        this.participant = participant;
    }

    @Override
    protected void doAccess() throws Exception {

        PreparedStatement pstmt_participants = null;
        PreparedStatement pstmt_event = null;
        PreparedStatement pstmt_joinEvent = null;

        ResultSet rs_participants = null;
        ResultSet rs_event = null;
        this.outputParam = false;

        int maxParticipantsInternational = 0;
        int maxParticipantsVolunteer = 0;
        int maxWaitingList = 0;
        int waitingList = 0;
        int internationals = 0;
        int volunteers = 0;
        boolean alreadyPartecipating = false;

        try {
            pstmt_event = con.prepareStatement(STATEMENT_GET_EVENT);
            pstmt_event.setLong(1, participant.getEventId());
            rs_event = pstmt_event.executeQuery();
            if(rs_event.next()){
                maxParticipantsInternational = rs_event.getInt("maxParticipantsInternational");
                maxParticipantsVolunteer = rs_event.getInt("maxParticipantsVolunteer");
                maxWaitingList = rs_event.getInt("maxWaitingList");
            }

            pstmt_participants = con.prepareStatement(STATEMENT_GET_PARTICIPANTS);
            pstmt_participants.setLong(1, participant.getEventId());
            rs_participants = pstmt_participants.executeQuery();

            while (rs_participants.next()) {
                if(rs_participants.getLong("userId") == participant.getUserId()){
                    alreadyPartecipating = true;
                }
                if(rs_participants.getString("role").equals("WaitingList"))
                    waitingList++;
                else if(rs_participants.getString("role").equals("Organizer") || rs_participants.getString("role").equals("Volunteer"))
                    volunteers++;
                else
                    internationals++;
            }

            if(participant.getTier() > 1) { //Is a Volunteer
                if(volunteers < maxParticipantsVolunteer){
                    participant.setRole("Volunteer");
                }
            }else{
                if(internationals < maxParticipantsInternational){
                    participant.setRole("Participant");
                } else if (waitingList < maxWaitingList) {
                    participant.setRole("WaitingList");
                }
            }

            if(participant.getRole() != null && !alreadyPartecipating){
                pstmt_joinEvent = con.prepareStatement(STATEMENT_JOIN_EVENT);
                pstmt_joinEvent.setLong(1, participant.getUserId());
                pstmt_joinEvent.setLong(2, participant.getEventId());
                pstmt_joinEvent.setObject(3, participant.getRole(), Types.OTHER);
                pstmt_joinEvent.setTimestamp(4, participant.getDate());
                pstmt_joinEvent.setObject(5, participant.getAttributeValues(), Types.OTHER);

                if(!pstmt_joinEvent.execute()){
                    if(pstmt_joinEvent.getUpdateCount() > 0){
                        this.outputParam = true;
                    }
                }
            }else{
                this.outputParam = false;
            }
            con.commit();

            LOGGER.info("User %l successfully join event %l.", participant.getUserId(), participant.getEventId());

        } finally {
            if (pstmt_event != null)
                pstmt_event.close();
            if (pstmt_participants != null)
                pstmt_participants.close();
            if (pstmt_joinEvent != null)
                pstmt_joinEvent.close();
        }
        
    }


}
