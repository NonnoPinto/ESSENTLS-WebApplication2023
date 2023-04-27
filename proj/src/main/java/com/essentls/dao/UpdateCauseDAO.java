package com.essentls.dao;

import com.essentls.resource.Cause;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * A DAO for updating a {@link Cause}.
 *
 * @author Vaidas Lenartavicius
 * @version 1.00
 * @since 1.00
 */

public class UpdateCauseDAO extends AbstractDAO<Cause>{

    private static final String STATEMENT_UPDATE_CAUSE = "UPDATE public.\"Causes\" SET name = ? WHERE id = ? RETURNING *";

    final Cause cause;

    /**
     * Creates a new DAO for updating a {@code Cause}.
     *
     * @param con the connection to the database.
     * @param cause the {@code Cause} to update.
     */
    public UpdateCauseDAO(final Connection con, final Cause cause) {
        super(con);
        if(cause == null) {
            LOGGER.error("Cause cannot be null");
            throw new IllegalArgumentException("Cause cannot be null");
        }
        this.cause = cause;
    }

    @Override
    protected void doAccess() throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        Cause c = null;

        try{
            pstmt = con.prepareStatement(STATEMENT_UPDATE_CAUSE);
            pstmt.setString(1, cause.getName());
            pstmt.setInt(2, cause.getId());

            rs = pstmt.executeQuery();

            if(rs.next()) {
                c = new Cause(rs.getInt("id"), rs.getString("name"));

                LOGGER.info("Cause updated: %s", c);
            }

        } finally {
            if(rs != null) {
                rs.close();
            }
            if(pstmt != null) {
                pstmt.close();
            }
        }

        outputParam = c;
    }
}
