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
    private static final String STATEMENT = "SELECT * FROM public.\"Payments\" WHERE \"userId\" = ?;";

    /**
     * The user that made the payments
     */
//    private final User user;
    private final int userId;

    /**
     * Creates a new object for gather payments by user.
     *
     * @param con    the connection to the database.
     * @param userId   the user that made the payments.
     */
    public UserPaymentsListDAO(final Connection con, final int userId) {
        super(con);
//        this.user = user;
        this.userId = userId;
    }

    @Override
    public final void doAccess() throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        final List<Payment> payments = new ArrayList<>();

        try {
            stmt = con.prepareStatement(STATEMENT);
            stmt.setInt(1, userId);

            rs = stmt.executeQuery();

            while (rs.next()) {
                payments.add(
                        new Payment(
                                rs.getInt("id"),
                                rs.getInt("userId"),
                                rs.getInt("eventId"),
                                rs.getString("method"),
                                rs.getFloat("amount"),
                                rs.getDate("date"),
                                rs.getString("notes")
                        )
                );
            }

            LOGGER.info("Payment(s) related to user %s successfully listed.");

        } finally {
            if (rs != null) {
                rs.close();
            }

            if (stmt != null) {
                stmt.close();
            }

        }

        this.outputParam = payments;
    }
}
