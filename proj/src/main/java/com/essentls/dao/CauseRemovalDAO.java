package com.essentls.dao;

import com.essentls.resource.Cause;
import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Deletes a cause from the database
 *
 * @author Laura Pallante (laura.pallante@studenti.unipd.it)
 * @version 1.00
 * @since 1.00
 */

public class CauseRemovalDAO extends AbstractDAO<Cause> {

    /**
     * The SQL statement to be executed
     */
    private static final String STATEMENT = "DELETE FROM public.\"Causes\"" +
                                            " WHERE id = ? RETURNING *";

    /**
     * The id of the cause to be deleted
     */
    private int id=-1;


    /**
     * Creates a new object for deleting a cause.
     *
     * @param con    the connection to the database.
     * @param id   the id of the cause.
     *
     */
    public CauseRemovalDAO(Connection con, final int id) {
        super(con);
        this.id=id;

    }

    @Override
    protected void doAccess() throws Exception {

        PreparedStatement stmt = null;
        ResultSet rs = null;

        //the deleted cause
        Cause c = null;

        try {
            stmt = con.prepareStatement(STATEMENT);
            stmt.setInt(1, id);


            rs = stmt.executeQuery();

            if (rs.next()) {
                c = new Cause(this.id,null);

                LOGGER.info("Cause %d successfully deleted", c.getId());
            }
        } finally {
            if (rs != null)
                rs.close();


            if (stmt != null)
                stmt.close();
        }

        outputParam = c;

        con.close();

    }


}

