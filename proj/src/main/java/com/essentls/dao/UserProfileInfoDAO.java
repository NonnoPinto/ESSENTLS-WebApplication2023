package com.essentls.dao;
import com.essentls.resource.User;

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
    private final User user;

    /**
     * The SQL statement to be executed
     */
    private final String STATEMENT = "SELECT * FROM User WHERE userID = ?";

    /**
     * UserID
     * TODO: decide how to get this data
     */
    private long infoID; //final id?

    /**
     * Creates a new object for gather info about user.
     *
     * @param con    the connection to the database.
     * @param user   the user that made the payments.
     */
    public UserProfileInfoDAO(final Connection con, final User user) {
        super(con);
        this.user = user;
    }

    @Override
    protected void doAccess() throws Exception {

        PreparedStatement stmnt = null;
        ResultSet rs = null;
        User myUser = null;

        try {
            stmnt = con.prepareStatement(STATEMENT);
            stmnt.setLong(1, infoID);

            rs = stmnt.executeQuery();

            if (rs.next()) {
                myUser = new User(infoID,
                rs.getString("email"),
                rs.getString("password"),
                rs.getInt("cardId"),
                rs.getInt("tier"),
                rs.getDate("registrationDate"),
                rs.getString("name"),
                rs.getString("surname"),
                rs.getString("sex"),
                rs.getDate("dateOfBirth"),
                rs.getString("nationality"),
                rs.getString("homeCountryAddress"),
                rs.getString("homeCountryUniversity"),
                rs.getString("periodOfStay"),
                rs.getInt("phoneNumber"),
                rs.getString("padovaAddress"),
                rs.getString("documentType"),
                rs.getString("documentNumber"),
                rs.getString("documentFile"),
                rs.getString("dietType"),
                rs.getString("allergies"),
                rs.getBoolean("emailConfirmed"));
            }

            LOGGER.info("Info about user %s successfully passed.", this.user.getEmail());

        } finally {
            if (rs != null) {
                rs.close();
            }

            if (stmnt != null) {
                stmnt.close();
            }

        }

        this.outputParam = user;
        con.close();
    }
    
}
