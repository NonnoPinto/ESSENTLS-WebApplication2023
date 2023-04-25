package com.essentls.dao;

import com.essentls.resource.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * After the initial payment, the user's tier is updated from 0 to 1
 *
 * @author Laura Pallante (laura.pallante@studenti.unipd.it
 * @version 1.00
 * @since 1.00
 */

public class UserUpgradeTierDAO extends AbstractDAO<User> {
    /**
     * The SQL statement to be executed
     */
    private static final String STATEMENT = "UPDATE Users SET tier = 1 WHERE tier = 0 AND id = ?";

    /**
     * The user whose tier must be updated
     */
    private final User user;


    /**
     * Creates a new object for the updating of the tier of a user
     *
     * @param con    the connection to the database.
     * @param user  the user whose tier must be updated
     */
    public UserUpgradeTierDAO(Connection con, final User user) {
        super(con);
        this.user = user;
    }

    @Override
    protected void doAccess() throws Exception {

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(STATEMENT);
            stmt.setLong(1, this.user.getId());

            stmt.executeUpdate();   // the update

            LOGGER.info("%s's tier successfully updated.", this.user.getEmail());

        } finally {
            if (stmt != null)
                stmt.close();
        }


    }


}
