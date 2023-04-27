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
    private final Participant participant;

    public UserJoinsEventDAO(Connection con, Participant participant) throws SQLException {
        super(con);

        if (participant == null) {
            LOGGER.error("The participant cannot be null.");
            throw new NullPointerException("The participant cannot be null.");
        }
        this.participant = participant;
    }

    @Override
    protected void doAccess() throws Exception {

        PreparedStatement pstmt_joinEvent = null;

        this.outputParam = false;

        try {
            if(participant.getRole() != null){
                pstmt_joinEvent = con.prepareStatement(STATEMENT_JOIN_EVENT);
                pstmt_joinEvent.setInt(1, participant.getUserId());
                pstmt_joinEvent.setInt(2, participant.getEventId());
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

            LOGGER.info("User %d successfully join event %d.", participant.getUserId(), participant.getEventId());

        } finally {
            if (pstmt_joinEvent != null)
                pstmt_joinEvent.close();
        }
        
    }


}
