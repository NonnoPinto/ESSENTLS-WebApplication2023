package com.essentls.dao;

import com.essentls.resource.Payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Lists all the payments in the database.
 *
 * @author Giovanni Zago
 * @version 1.00
 * @since 1.00
 */
public class AdminPaymentListDAO extends AbstractDAO<List<Payment>>{
    /**
     * The SQL statement to be executed
     */
    private static final String STATEMENT_USERS_LIST = "SELECT * FROM public.\"Payments\";";

    /**
     * Creates a new object for listing all the payments.
     *
     * @param con the connection to the database.
     */
    public AdminPaymentListDAO(final Connection con) {
        super(con);
    }

    @Override
    public final void doAccess() throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        final List<Payment> users = new ArrayList<>();

        try {
            stmt = con.prepareStatement(STATEMENT_USERS_LIST);

            rs = stmt.executeQuery();

            while (rs.next()) {
                users.add(
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

            LOGGER.info("Payment list successfully listed.");

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }

        this.outputParam = users;
    }
}
