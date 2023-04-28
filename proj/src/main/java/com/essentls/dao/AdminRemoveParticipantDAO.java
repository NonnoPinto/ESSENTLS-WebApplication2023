package com.essentls.dao;

import com.essentls.resource.Participant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


/**
 * Deletes a participant from the database.
 *
 * @author Vaidas Lenartavicius
 * @version 1.00
 * @since 1.00
 */
public class AdminRemoveParticipantDAO extends AbstractDAO<Participant>{

    /**
     * The SQL statement to be executed
     */
    private static final String STATEMENT_REMOVE_PARTICIPANT = "DELETE FROM participants WHERE userId = ? AND eventId = ?";

    /**
     * The participant to be deleted
     */
    private final Participant participant;

    /**
     * Creates a new object for deleting a participant.
     *
     * @param con the connection to the database.
     * @param participant the participant to be deleted.
     */
    public AdminRemoveParticipantDAO(final Connection con, final Participant participant) {
        super(con);
        this.participant = participant;
    }

    @Override
    public final void doAccess() throws SQLException {

        PreparedStatement stmt = null;

        try {

            stmt = con.prepareStatement(STATEMENT_REMOVE_PARTICIPANT);
            stmt.setInt(1, this.participant.getUserId());
            stmt.setInt(2, this.participant.getEventId());

            stmt.executeUpdate();

            LOGGER.info("Participant {} successfully removed.", this.participant.getUserId());

        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }

    }
}
