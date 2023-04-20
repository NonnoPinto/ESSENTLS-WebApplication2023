package com.essentls.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.essentls.resource.Participant;


/**
 * Let a User join an Event
 *
 * @author Vittorio Cardillo (vittorio.cardillo@studenti.unipd.it)
 * @version 1.00
 * @since 1.00
 */
public class UserJoinsEventDAO extends AbstractDAO {
    private static final String STATEMENT_JOIN_EVENT = "INSERT INTO  public.\"Participants\" (\"userId\", \"eventId\", role, date, \"attributeValues\") VALUES (?, ?, ?, ?, ?)";

    private final Participant participant;

    public UserJoinsEventDAO(Connection con, Participant participant) {
        super(con);

        if (participant == null) {
            LOGGER.error("The participant cannot be null.");
            throw new NullPointerException("The participant cannot be null.");
        }

        this.participant = participant;
    }

    @Override
    protected void doAccess() throws Exception {

        PreparedStatement pstmt = null;

        try {
            pstmt = con.prepareStatement(STATEMENT_JOIN_EVENT);
            pstmt.setLong(1, participant.getUserId());
            pstmt.setLong(2, participant.getEventId());
            pstmt.setString(3, participant.getRole());
            pstmt.setDate(4, participant.getDate());

            pstmt.execute();

            LOGGER.info("User %l successfully join event %l.", participant.getUserId(), participant.getEventId());

        } finally {
            if (pstmt != null)
                pstmt.close();
        }
        
    }


}
