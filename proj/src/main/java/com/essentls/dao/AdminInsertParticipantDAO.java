package com.essentls.dao;

import com.essentls.resource.Participant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AdminInsertParticipantDAO extends AbstractDAO<Participant> {

    private static final String STATEMENT_INSERT_PARTICIPANT = "INSERT INTO participants (userId, eventId, role, date, attributeValues) VALUES (?, ?, ?, ?, ?)";

    private final Participant participant;

    public AdminInsertParticipantDAO(final Connection con, final Participant participant) {
        super(con);
        this.participant = participant;
    }

    @Override
    public final void doAccess() throws SQLException {

        PreparedStatement stmt = null;

        try {

            stmt = con.prepareStatement(STATEMENT_INSERT_PARTICIPANT);
            stmt.setLong(1, this.participant.getUserId());
            stmt.setLong(2, this.participant.getEventId());
            stmt.setString(3, this.participant.getRole());
            stmt.setDate(4, this.participant.getDate());
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
