package com.essentls.dao;

import com.essentls.resource.Participant;
import com.essentls.resource.User;
import org.json.JSONObject;
import org.postgresql.util.PGobject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Return participant from DB
 *
 * @author Mattia Maglie (mattia.maglie@studenti.unipd.it)
 * @version 1.00
 * @since 1.00
 */

public final class ParticipantInfoDAO extends AbstractDAO<Participant>{

    /**
     * The user to get info about
     */
//    private final User user;

    /**
     * The SQL statement to be executed
     */
    private final String STATEMENT = "SELECT * FROM public.\"Participants\" INNER JOIN public.\"Users\" ON public.\"Participants\".\"userId\" = public.\"Users\".\"id\" WHERE public.\"Users\".\"id\" = ? AND public.\"Participants\".\"eventId\" = ?;";

    /**
     * UserID
     * TODO: decide how to get this data
     */
//    private int infoID; //final id?
    private final int userId;
    private final int eventId;
    /**
     * Creates a new object for gather info about user.
     *
     * @param con    the connection to the database.
     * @param id      the user that made the payments.
     */
    public ParticipantInfoDAO(final Connection con, final int userId, final int eventId) {
        super(con);
        this.userId = userId;
        this.eventId = eventId;
    }


    @Override
    protected void doAccess() throws Exception {

        PreparedStatement stmnt = null;
        ResultSet rs = null;
        Participant myUser = null;

        this.outputParam = null;

        try {
            stmnt = con.prepareStatement(STATEMENT);
//            stmnt.setInt(1, infoID);
            stmnt.setInt(1, this.userId);
            stmnt.setInt(2, this.eventId);

            rs = stmnt.executeQuery();

            if (rs.next()) {
                JSONObject paduaAddress = new JSONObject();
                if (!(rs.getObject("paduaAddress") == null)) {
                    paduaAddress = new JSONObject(rs.getObject("paduaAddress", PGobject.class));
                }
                JSONObject homeCountryAddress = new JSONObject();
                if (!(rs.getObject("homeCountryAddress") == null)) {
                    homeCountryAddress = new JSONObject(rs.getObject("homeCountryAddress", PGobject.class));
                }
                String[] allergies = {};
                if (!(rs.getArray("allergies") == null)) {
                    allergies = (String[]) rs.getArray("allergies").getArray();
                }

                myUser = new Participant(
                        this.userId,
                        this.eventId,
                        rs.getString("role"),
                        rs.getTimestamp("date"),
                        rs.getString("attributeValues"),
                        new User(
                                rs.getInt("id"),
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
                                homeCountryAddress,
                                rs.getString("homeCountryUniversity"),
                                rs.getInt("periodOfStay"),
                                rs.getString("phoneNumber"),
                                paduaAddress,
                                rs.getString("documentType"),
                                rs.getString("documentNumber"),
                                rs.getString("documentFile"),
                                rs.getString("dietType"),
                                allergies,
                                rs.getString("emailhash"),
                                rs.getBoolean("emailConfirmed")
                        )
                );

                this.outputParam = myUser;
                LOGGER.info("Info about participant %s successfully passed.", this.userId);
            }
        }catch(SQLException e){
            LOGGER.error("SQL Exception", e);
        } finally {
            if (rs != null) {
                rs.close();
            }

            if (stmnt != null) {
                stmnt.close();
            }

        }
    }
    
}
