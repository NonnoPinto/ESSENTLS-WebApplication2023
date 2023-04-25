package com.essentls.dao;
import com.essentls.resource.User;
import org.json.JSONObject;
import org.postgresql.util.PGobject;

import java.sql.*;

/**
 * User profile info DAO, to get infos about any user
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
    private final String STATEMENT = "SELECT * FROM public.\"Users\" WHERE id = ?;";

    /**
     * UserID
     * TODO: decide how to get this data
     */
//    private long infoID; //final id?
    private final long id;
    /**
     * Creates a new object for gather info about user.
     *
     * @param con    the connection to the database.
     * @param id      the user that made the payments.
     */
    public UserProfileInfoDAO(final Connection con, final long id) {
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
//            stmnt.setLong(1, infoID);
            stmnt.setLong(1, id);

            rs = stmnt.executeQuery();

            if (rs.next()) {
                JSONObject paduaAddress=new JSONObject();
                if(!(rs.getObject("paduaAddress")==null)){
                    paduaAddress=new JSONObject(rs.getObject("paduaAddress", PGobject.class));
                }
                JSONObject homeCountryAddress= new JSONObject();
                if(!(rs.getObject("homeCountryAddress")==null)){
                    homeCountryAddress=new JSONObject(rs.getObject("homeCountryAddress", PGobject.class));
                }
                String[] allergies= {};
                if(!(rs.getArray("allergies")==null)){
                    allergies= (String[]) rs.getArray("allergies").getArray();
                }

                myUser = new User(
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
