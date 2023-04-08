package com.essentls.dao;

import com.essentls.resource.User;
import com.essentls.resource.Payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Gather payments related to a user.
 *
 * @author Andrea Campagnol (andrea.campagnol.1@studenti.unipd.it)
 * @version 1.00
 * @since 1.00
 */
public final class UserPaymentsListDAO extends AbstractDAO<List<Payment>> {

    /**
     * The SQL statement to be executed
     */
    private static final String STATEMENT = "SELECT * FROM Payment WHERE userID = ?";

    /**
     * The user that made the payments
     */
    private final User user;

    /**
     * Creates a new object for gather payments by user.
     *
     * @param con    the connection to the database.
     * @param user   the user that made the payments.
     */
    public UserPaymentsListDAO(final Connection con, final User user) {
        super(con);
        this.user = user;
    }

    @Override
    public final void doAccess() throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        final List<Payment> payments = new ArrayList<Payment>();

        try {
            stmt = con.prepareStatement(STATEMENT);
            stmt.setLong(1, this.user.getId());

            rs = stmt.executeQuery();

            while (rs.next()) {
                payments.add(
                        new Payment(
                                rs.getLong("id"),
                                rs.getLong("userId"),
                                rs.getLong("eventId"),
                                rs.getString("method"),
                                rs.getFloat("amount"),
                                rs.getDate("date"),
                                rs.getString("notes")
                        )
                );
            }

            LOGGER.info("Pyament(s) related to user %s successfully listed.", this.user.getEmail());

        } finally {
            if (rs != null) {
                rs.close();
            }

            if (stmt != null) {
                stmt.close();
            }

        }

        this.outputParam = payments;
        con.close();
    }
}
