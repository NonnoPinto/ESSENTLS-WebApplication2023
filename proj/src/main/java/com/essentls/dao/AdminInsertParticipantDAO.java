package com.essentls.dao;

import com.essentls.resource.Participant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Creates a new participant into the database.
 *
 * @author Vaidas Lenartavicius
 * @version 1.00
 * @since 1.00
 */
public class AdminInsertParticipantDAO extends AbstractDAO<Participant> {

    /**
     * The SQL statement to be executed
     */
    private static final String STATEMENT_INSERT_PARTICIPANT = "INSERT INTO participants (userId, eventId, role, date, attributeValues) VALUES (?, ?, ?, ?, ?)";

    /**
     * The participant to be stored into the database
     */
    private final Participant participant;

    /**
     * Creates a new object for storing a participant into the database.
     *
     * @param con the connection to the database.
     * @param participant the participant to be stored into the database.
     */
    public AdminInsertParticipantDAO(final Connection con, final Participant participant) {
        super(con);
        this.participant = participant;
    }

    @Override
    public final void doAccess() throws SQLException {

        PreparedStatement stmt = null;

        try {

            stmt = con.prepareStatement(STATEMENT_INSERT_PARTICIPANT);
            stmt.setInt(1, this.participant.getUserId());
            stmt.setInt(2, this.participant.getEventId());
            stmt.setString(3, this.participant.getRole());
            stmt.setTimestamp(4, this.participant.getDate());
            stmt.setString(5, this.participant.getAttributeValues());

            stmt.executeUpdate();

            LOGGER.info("Participant {} successfully inserted.", this.participant.getUserId());

        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }

    }
}
