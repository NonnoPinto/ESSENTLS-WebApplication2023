package com.essentls.dao;

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

public class TagsRemovalDAO extends AbstractDAO<Tag> {

private static final String STATEMENT = "DELETE FROM public.\"Tags\" WHERE name = ? RETURNING *";
    /**
     * The payment that must be added
     */
    private final String name;


    /**
     * Creates a new object for the updating of the tier of a user
     *
     * @param con    the connection to the database.
     * @param name  the payment that must be added
     */
    public TagsRemovalDAO(Connection con, final String name) {
        super(con);
        this.name = name;
    }

    @Override
    protected void doAccess() throws Exception {

        PreparedStatement stmt = null;
        ResultSet rs = null;

        Tag t = null;

        try {
            stmt = con.prepareStatement(STATEMENT);
            stmt.setString(1, name);

            rs = stmt.executeQuery();   //add to payment's table

            if (rs.next()) {
                t = new Tag(rs.getString("name"));

                LOGGER.info("Tag %s successfully deleted", t.getName());
            }
        } finally {
            if (rs != null)
                rs.close();


            if (stmt != null)
                stmt.close();
        }

        outputParam = t;

        con.close();

    }


}

