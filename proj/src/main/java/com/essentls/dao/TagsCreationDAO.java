package com.essentls.dao;

import com.essentls.resource.Tag;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/**
 * Add a tag to the tag's table
 *
 * @author Laura Pallante (laura.pallante@studenti.unipd.it
 * @version 1.00
 * @since 1.00
 */

public class TagsCreationDAO extends AbstractDAO<Tag> {

        private static final String STATEMENT = "INSERT INTO Tags (name) VALUES (?)";
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
        public TagsCreationDAO(Connection con, final String name) {
            super(con);
            this.name = name;
        }

        @Override
        protected void doAccess() throws Exception {

            PreparedStatement stmt = null;

            try {
                stmt = con.prepareStatement(STATEMENT);
                stmt.setString(1, name);

                stmt.executeUpdate();   //add to payment's table

                LOGGER.info("Tag %l successfully added", name);
            } finally {
                if (stmt != null)
                    stmt.close();
            }

            con.close();

        }


    }

