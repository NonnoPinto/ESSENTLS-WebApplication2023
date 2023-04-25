package com.essentls.dao;

import com.essentls.resource.Payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Add a user payment to the payment's table
 *
 * @author Laura Pallante (laura.pallante@studenti.unipd.it
 * @version 1.00
 * @since 1.00
 */

public class UserPaymentSubmitDAO extends AbstractDAO<Payment> {

    /**
     * The SQL statement to be executed
     */
    private static final String STATEMENT = "INSERT INTO Payments (id, userId, eventId, method, amount, date, notes) VALUES (?, ?, ?, ?, ?, ?, ?)";

    /**
     * The payment that must be added
     */
    private final Payment payment;


    /**
     * Creates a new object for the updating of the tier of a user
     *
     * @param con    the connection to the database.
     * @param payment  the payment that must be added
     */
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

            stmt.executeUpdate();   //add to payment's table

            LOGGER.info("Payment %l successfully added to the payment's list.", payment.getId());
        } finally {
            if (stmt != null)
                stmt.close();
        }


    }


}
