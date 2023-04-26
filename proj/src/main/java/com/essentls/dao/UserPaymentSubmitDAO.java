package com.essentls.dao;

import com.essentls.resource.Payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

/**
 * Add a user payment to the payment's table
 *
 * @author Laura Pallante (laura.pallante@studenti.unipd.it
 * @version 1.00
 * @since 1.00
 */

public class UserPaymentSubmitDAO extends AbstractDAO<Boolean> {

    /**
     * The SQL statement to be executed
     */
    private static final String STATEMENT = "INSERT INTO public.\"Payments\" (\"userId\", \"eventId\", \"method\", \"amount\", \"date\", \"notes\") VALUES (?, ?, ?, ?, ?, ?)";

    /**
     * The payment that must be added
     */
    private final Payment payment;


    /**
     * Creates a new object for payment
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
        this.outputParam = false;

        try {
            stmt = con.prepareStatement(STATEMENT);
            stmt.setLong(1, payment.getUserId());
            stmt.setLong(2, payment.getEventId());
            stmt.setObject(3, payment.getMethod(), Types.OTHER);
            stmt.setFloat(4, payment.getAmount());
            stmt.setDate(5, payment.getDate());
            stmt.setString(6, payment.getNotes());

            this.outputParam = (stmt.executeUpdate() == 1);

            LOGGER.info("Payment %d successfully added to the payment's list.", payment.getId());
        } finally {
            if (stmt != null)
                stmt.close();
        }


    }


}
