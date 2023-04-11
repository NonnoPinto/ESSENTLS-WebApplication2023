package com.essentls.dao;

import com.essentls.resource.Participant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AdminRemoveParticipantDAO extends AbstractDAO<Participant>{

    private static final String STATEMENT_REMOVE_PARTICIPANT = "DELETE FROM participants WHERE userId = ? AND eventId = ?";

    private final Participant participant;

    public AdminRemoveParticipantDAO(final Connection con, final Participant participant) {
        super(con);
        this.participant = participant;
    }

    @Override
    public final void doAccess() throws SQLException {

        PreparedStatement stmt = null;

        try {

            stmt = con.prepareStatement(STATEMENT_REMOVE_PARTICIPANT);
            stmt.setLong(1, this.participant.getUserId());
            stmt.setLong(2, this.participant.getEventId());

            stmt.executeUpdate();

            LOGGER.info("Participant {} successfully removed.", this.participant.getUserId());

        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }

    }
}
