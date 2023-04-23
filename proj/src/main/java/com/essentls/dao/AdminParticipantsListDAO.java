package com.essentls.dao;

import com.essentls.resource.Participant;
import com.essentls.resource.User;
import org.json.JSONObject;
import org.postgresql.util.PGobject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class AdminParticipantsListDAO extends AbstractDAO<List<Participant>> {

    private static final String STATEMENT_PARTICIPANTS_LIST = "SELECT * FROM public.\"Participants\" INNER JOIN public.\"Users\" ON public.\"Participants\".\"userId\" = public.\"Users\".\"id\" WHERE \"eventId\" = ?";

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
                                rs.getString("attributeValues"),
                                new User(
                                        rs.getLong("id"),
                                        rs.getString("email"),
                                        rs.getString("password"),
                                        rs.getString("cardId"),
                                        rs.getInt("tier"),
                                        rs.getDate("registrationDate"),
                                        rs.getString("name"),
                                        rs.getString("surname"),
                                        rs.getString("sex"),
                                        rs.getDate("dateOfBirth"),
                                        rs.getString("nationality"),
                                        new JSONObject(rs.getObject("homeCountryAddress", PGobject.class)),
                                        rs.getString("homeCountryUniversity"),
                                        rs.getString("periodOfStay"),
                                        rs.getString("phoneNumber"),
                                        new JSONObject (rs.getObject("paduaAddress", PGobject.class)),
                                        rs.getString("documentType"),
                                        rs.getString("documentNumber"),
                                        rs.getString("documentFile"),
                                        rs.getString("dietType"),
                                        (String[]) rs.getArray("allergies").getArray(),
                                        rs.getString("emailHash"),
                                        rs.getBoolean("emailConfirmed")
                                )
                        )
                );
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
