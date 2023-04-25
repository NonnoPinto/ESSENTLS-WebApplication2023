package com.essentls.dao;

import com.essentls.resource.Cause;
import com.essentls.resource.Tag;

import java.sql.*;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Add a tag to the tag's table
 *
 * @author Laura Pallante (laura.pallante@studenti.unipd.it
 * @version 1.00
 * @since 1.00
 */

public class CauseRemovalDAO extends AbstractDAO<Cause> {

    private static final String STATEMENT = "DELETE FROM public.\"Causes\"" +
                                            " WHERE id = ? RETURNING *";
    private long id=-1;


    /**
     * Creates a new object for the removing of a cause.
     *
     * @param con    the connection to the database.
     * @param id   the id of the cause to remove
     *
     */
    public CauseRemovalDAO(Connection con, final long id) {
        super(con);
        this.id=id;

    }

    @Override
    protected void doAccess() throws Exception {

        PreparedStatement stmt = null;
        ResultSet rs = null;

        Cause c = null;

        try {
            stmt = con.prepareStatement(STATEMENT);
            stmt.setLong(1, id);


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

