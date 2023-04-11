package com.essentls.dao;

import java.sql.*;

import com.essentls.resource.User;

/**
 * User edit profile DAO, to modify mail and pass
 *
 * @author Giovanni Zago (giovanni.zago.3@studenti.unipd.it)
 * @version 1.00
 * @since 1.00
 */

public final class UserEditProfileDAO extends AbstractDAO<User> {
    
    /**
     * Creates a new object for gather info about user.
     *
     * @param con    the connection to the database.
     * @param user   the user that made the payments.
     */
    public UserEditProfileDAO(final Connection con, final User user) {
        super(con);
        this.user = user;
    }

    /**
     * The SQL statement to be executed
     */
    private static final String STATEMENT = "SELECT Mail, Password FROM User WHERE userID = ?";

    /**
     * The user who wants to change mail ans pass
     */
    private final User user;

    /**
     * new mail and pass
     */
    //TODO: how do we get the new mail and pass?
    /* private final String newMail;
    private final String newPassword; */

    @Override
    protected void doAccess() throws Exception {
        PreparedStatement stmnt = null;
        ResultSet rs = null;
        User myUser = null;

        try {
            stmnt = con.prepareStatement(STATEMENT);
            stmnt.setLong(1, this.user.getId());

            // Initialize mail and pass with old ones
            //TODO: set functions
            /* this.user.email = newMail;
            this.user.password = newPassword; */

            rs = stmnt.executeQuery();

            LOGGER.info("Mail and password of user %s successfully changed.", this.user.getEmail());

        }   finally {
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
