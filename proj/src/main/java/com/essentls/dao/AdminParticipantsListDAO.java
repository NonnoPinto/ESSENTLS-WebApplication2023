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

/**
 * Lists all the participants (to an event) in the database.
 *
 * @author Vaidas Lenartavicius
 * @version 1.00
 * @since 1.00
 */
public class AdminParticipantsListDAO extends AbstractDAO<List<Participant>> {

    /**
     * The SQL statement to be executed
     */
    private static final String STATEMENT_PARTICIPANTS_LIST = "SELECT * FROM public.\"Participants\" INNER JOIN public.\"Users\" ON public.\"Participants\".\"userId\" = public.\"Users\".\"id\" WHERE \"eventId\" = ?";

    /**
     * The id of the event
     */
    private final int eventId;

    /**
     * Creates a new object for listing all the participant to an event.
     *
     * @param con the connection to the database.
     * @param eventId the id of the event
     */
    public AdminParticipantsListDAO(final Connection con, final int eventId) {
        super(con);
        this.eventId = eventId;
    }

    @Override
    public final void doAccess() throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;

        //the results of the search
        final List<Participant> participants = new ArrayList<>();

        try {
            stmt = con.prepareStatement(STATEMENT_PARTICIPANTS_LIST);
            stmt.setInt(1, this.eventId);

            rs = stmt.executeQuery();

            while (rs.next()) {
                int partecipantTier = rs.getInt("tier");
                if(partecipantTier < 0){
                    partecipantTier = 0;
                } else if(partecipantTier > 4){
                    partecipantTier = 4;
                }
                participants.add(
                        new Participant(
                                rs.getInt("userId"),
                                rs.getInt("eventId"),
                                rs.getString("role"),
                                rs.getTimestamp("date"),
                                rs.getString("attributeValues"),
                                new User(
                                        rs.getInt("id"),
                                        rs.getString("email"),
                                        rs.getString("password"),
                                        rs.getString("cardId"),
                                        partecipantTier,
                                        rs.getDate("registrationDate"),
                                        rs.getString("name"),
                                        rs.getString("surname"),
                                        rs.getString("sex"),
                                        rs.getDate("dateOfBirth"),
                                        rs.getString("nationality"),
                                        new JSONObject(rs.getObject("homeCountryAddress", PGobject.class)),
                                        rs.getString("homeCountryUniversity"),
                                        rs.getInt("periodOfStay"),
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
