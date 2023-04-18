package com.essentls.dao;

import com.essentls.resource.Participant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class AdminParticipantsListDAO extends AbstractDAO<List<Participant>> {

    private static final String STATEMENT_PARTICIPANTS_LIST = "SELECT * FROM public.\"Participants\" WHERE eventId = ?";

    private final long eventId;

    public AdminParticipantsListDAO(final Connection con, final long eventId) {
        super(con);
        this.eventId = eventId;
    }

    @Override
    public final void doAccess() throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        final List<Participant> participants = new ArrayList<>();

        try {
            stmt = con.prepareStatement(STATEMENT_PARTICIPANTS_LIST);
            stmt.setLong(1, this.eventId);

            rs = stmt.executeQuery();

            while (rs.next()) {
                participants.add(
                        new Participant(
                                rs.getLong("userId"),
                                rs.getLong("eventId"),
                                rs.getString("role"),
                                rs.getDate("date"),
                                rs.getString("attributeValues")));
            }

            LOGGER.info("Participants list for event {} successfully listed.", this.eventId);


        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }

        this.outputParam = participants;
    }
}
