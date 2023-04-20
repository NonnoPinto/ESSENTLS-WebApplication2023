package com.essentls.dao;

import com.essentls.resource.Payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminPaymentListDAO extends AbstractDAO<List<Payment>>{
    private static final String STATEMENT_USERS_LIST = "SELECT * FROM Payment;";

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
