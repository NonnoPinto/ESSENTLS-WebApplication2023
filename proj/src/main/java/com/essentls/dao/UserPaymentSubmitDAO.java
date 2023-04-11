package com.essentls.dao;

import com.essentls.resource.Payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;



public class UserPaymentSubmitDAO extends AbstractDAO<Payment> {
    private static final String STATEMENT = "INSERT INTO Payments (id, userId, eventId, method, amount, date, notes) VALUES (?, ?, ?, ?, ?, ?, ?)";

    private final Payment payment;

    public UserPaymentSubmitDAO(Connection con, final Payment payment) {
        super(con);
        this.payment = payment;
    }

    @Override
    protected void doAccess() throws Exception {

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(STATEMENT);
            stmt.setLong(1, payment.getId());
            stmt.setLong(2, payment.getUserId());
            stmt.setLong(3, payment.getEventId());
            stmt.setString(4, payment.getMethod());
            stmt.setFloat(5, payment.getAmount());
            stmt.setDate(6, payment.getDate());
            stmt.setString(7, payment.getNotes());

            stmt.executeUpdate();

            LOGGER.info("Payment %l successfully added to the payment's list.", payment.getId());
        } finally {
            if (stmt != null)
                stmt.close();
        }

        con.close();

    }


}
