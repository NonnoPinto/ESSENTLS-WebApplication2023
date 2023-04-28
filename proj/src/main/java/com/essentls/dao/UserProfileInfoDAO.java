package com.essentls.dao;
import com.essentls.resource.User;
import org.json.JSONObject;
import org.postgresql.util.PGobject;

import java.sql.*;

/**
 * User profile info DAO, to get infos about a user
 *
 * @author Giovanni Zago (giovanni.zago.3@studenti.unipd.it)
 * @version 1.00
 * @since 1.00
 */

public final class UserProfileInfoDAO extends AbstractDAO<User>{

    /**
     * The user to get info about
     */
//    private final User user;

    /**
     * The SQL statement to be executed
     */
    private final String STATEMENT = "SELECT id, email, password, \"cardID\", tier, \"registrationDate\", " +
                                    "name, surname, sex, \"dateOfBirth\", nationality, \"homeCountryAddress\", " +
                                    "\"homeCountryUniversity\", \"periodOfStay\", \"phoneNumber\", \"paduaAddress\", " +
                                    "\"documentType\", \"documentNumber\", \"documentFile\", \"dietType\", allergies, " +
                                    "\"emailHash\", \"emailConfirmed\" FROM public.\"Users\"" +
                                    " WHERE id = ?;";


//    private int infoID; //final id?
    /**
     * the user ID
     */
    private final int id;

    /**
     * Creates a new object for gather info about user.
     *
     * @param con    the connection to the database.
     * @param id      the id of the user.
     */
    public UserProfileInfoDAO(final Connection con, final int id) {
        super(con);
//        this.user = user;
        this.id = id;
    }


    @Override
    protected void doAccess() throws Exception {

        PreparedStatement stmnt = null;
        ResultSet rs = null;
        User myUser = null;

        try {
            stmnt = con.prepareStatement(STATEMENT);
//            stmnt.setInt(1, infoID);
            stmnt.setInt(1, id);

            rs = stmnt.executeQuery();

            if (rs.next()) {
                JSONObject homeCountryAddress = null;
                JSONObject paduaAddress = null;
                String[] allergies = null;
                try{
                    paduaAddress=new JSONObject(rs.getObject("paduaAddress", PGobject.class).getValue());
                } catch (Exception e){
                    LOGGER.error("Error while parsing paduaAddress for user with id: " + rs.getInt("id"));
                }
                try{
                    homeCountryAddress=new JSONObject(rs.getObject("homeCountryAddress", PGobject.class).getValue());
                } catch (Exception e){
                    LOGGER.error("Error while parsing homeCountryAddress for user with id: " + rs.getInt("id"));
                }
                try{
                    allergies=(String[]) rs.getArray("allergies").getArray();
                } catch (Exception e){
                    LOGGER.error("Error while parsing allergies for user with id: " + rs.getInt("id"));
                }

                myUser = new User(
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
//                (String[]) rs.getArray("allergies").getArray(),
                allergies,
                rs.getString("emailhash"),
                rs.getBoolean("emailConfirmed"));
            }

//            LOGGER.info("Info about user %s successfully passed.", this.user.getEmail());
            LOGGER.info("Info about user %s successfully passed.", id);

        } finally {
            if (rs != null) {
                rs.close();
            }

            if (stmnt != null) {
                stmnt.close();
            }

        }

//        this.outputParam = user;
        this.outputParam = myUser;
    }
    
}
