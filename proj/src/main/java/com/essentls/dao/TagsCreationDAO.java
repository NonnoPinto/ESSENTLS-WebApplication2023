package com.essentls.dao;

import com.essentls.resource.Tag;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Creates a new tag into the database
 *
 * @author Laura Pallante (laura.pallante@studenti.unipd.it
 * @version 1.00
 * @since 1.00
 */

public class TagsCreationDAO extends AbstractDAO<Tag> {

        /**
        *The SQL statement to be executed
         */
        private static final String STATEMENT = "INSERT INTO public.\"Tags\" (name) VALUES (?) RETURNING *";

        /**
         *  the tag name to be stored into the database
         */
        private final String name;


        /**
         * Creates a new object for storing a tag into the database.
         *
         * @param con    the connection to the database.
         * @param name   the tag name to be stored into the database
         */
        public TagsCreationDAO(Connection con, final String name) {
            super(con);
            this.name = name.replaceAll("[^a-zA-Z0-9]", "");
        }

        @Override
        protected void doAccess() throws Exception {

            PreparedStatement stmt = null;
            ResultSet rs = null;

            //the created tag
            Tag t = null;

            try {
                stmt = con.prepareStatement(STATEMENT);
                stmt.setString(1, name);

                rs = stmt.executeQuery();   //add to payment's table

                if (rs.next()) {
                    t = new Tag(rs.getString("name"));

                    LOGGER.info("Tag %s successfully added", t.getName());
                }

            } finally {
                if (rs != null)
                    rs.close();


                if (stmt != null)
                    stmt.close();
            }

            outputParam = t;


        }


    }

