package com.essentls.dao;

import com.essentls.resource.Participant;

import java.sql.*;


/**
 * Checks if user can join the event
 *
 * @author Mattia Maglie (mattia.maglie@studenti.unipd.it)
 * @version 1.00
 * @since 1.00
 */
public class UserCheckEventParticipantDAO extends AbstractDAO<Participant> {
    private static final String STATEMENT_GET_PARTICIPANTS = "SELECT * FROM public.\"Participants\" INNER JOIN public.\"Users\" ON public.\"Participants\".\"userId\" = public.\"Users\".\"id\" WHERE \"eventId\" = ?";
    private static final String STATEMENT_GET_EVENT = "SELECT * FROM public.\"Events\" WHERE \"id\" = ?";

    private final Participant participant;
    private final boolean alwaysWaitingList;

    public UserCheckEventParticipantDAO(Connection con, Participant participant, boolean alwaysWaitingList) throws SQLException {
        super(con);

        if (participant == null) {
            LOGGER.error("The participant cannot be null.");
            throw new NullPointerException("The participant cannot be null.");
        }
        this.participant = participant;
        this.alwaysWaitingList = alwaysWaitingList;

    }

    @Override
    protected void doAccess() throws Exception {

        PreparedStatement pstmt_participants = null;
        PreparedStatement pstmt_event = null;

        ResultSet rs_participants = null;
        ResultSet rs_event = null;
        this.outputParam = null;

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
                else if(rs_participants.getString("role").equals("Organizer"))
                    volunteers++;
                else
                    internationals++;
            }

            if(participant.getTier() > 1) { //Is a Volunteer
                if(volunteers < maxParticipantsVolunteer){
                    participant.setRole("Volunteer");
                }else if(this.alwaysWaitingList){ //The user has payed so always put in waitingList
                    participant.setRole("WaitingList");
                }
            }else{
                if(internationals < maxParticipantsInternational){
                    participant.setRole("Participant");
                } else if (waitingList < maxWaitingList || this.alwaysWaitingList) { //The user has payed so always put in waitingList
                    participant.setRole("WaitingList");
                }
            }

            if(participant.getRole() != null && !alreadyPartecipating){
                this.outputParam = participant;
            }else{
                this.outputParam = null;
            }

            LOGGER.info("User %d checks event %d.", participant.getUserId(), participant.getEventId());

        } finally {
            if (pstmt_event != null)
                pstmt_event.close();
            if (pstmt_participants != null)
                pstmt_participants.close();
        }
        
    }


}
