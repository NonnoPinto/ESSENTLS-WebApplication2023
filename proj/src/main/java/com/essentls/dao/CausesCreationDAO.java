package com.essentls.dao;

import com.essentls.resource.Cause;
import com.essentls.resource.Tag;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Add a cause to the cause table
 *
 * @author Laura Pallante (laura.pallante@studenti.unipd.it
 * @version 1.00
 * @since 1.00
 */

public class CausesCreationDAO extends AbstractDAO<Cause> {

    private static final String STATEMENT = "INSERT INTO public.\"Causes\" (name) VALUES (?) RETURNING *";
    /**
     *  the cause name
     */
    private final String name;


    /**
     * Creates a new tag for events.
     *
     * @param con    the connection to the database.
     * @param name   the cause name
     */
    public CausesCreationDAO(Connection con, final String name) {
        super(con);
        this.name = name;
    }

    @Override
    protected void doAccess() throws Exception {

        PreparedStatement stmt = null;
        ResultSet rs = null;

        Cause c = null;

        try {
            stmt = con.prepareStatement(STATEMENT);
            stmt.setString(1, name);

            rs = stmt.executeQuery();   //add to causes' table

            if (rs.next()) {
                c = new Cause(rs.getLong("id"),rs.getString("name"));

                LOGGER.info("Cause %s successfully added", c.getName());
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

