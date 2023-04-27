package com.essentls.dao;

import com.essentls.resource.Tag;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Add a tag to the tag's table
 *
 * @author Francesco Marcato (francesco.marcato.2@studenti.unipd.it)
 * @version 1.00
 * @since 1.00
 */

public class EventCausesCreationDAO extends AbstractDAO<Tag> {

        private static final String STATEMENT = "INSERT INTO public.\"EventCauses\" (\"eventId\", \"causeId\") VALUES (?, ?)";
        /**
         *  the basic attributes
         */
        private int eventID = -1;
        private int causeID = -1;


        /**
         * Creates a new EventCauses
         */
        public EventCausesCreationDAO(Connection con, int _eventID, int _causesID) {
            super(con);
            this.eventID = _eventID;
            this.causeID = _causesID;
        }

        @Override
        protected void doAccess() throws Exception {

            PreparedStatement stmt = null;
            ResultSet rs = null;

            Tag t = null;

            try {
                stmt = con.prepareStatement(STATEMENT);
                stmt.setInt(1, eventID);
                stmt.setInt(2, causeID);

                stmt.executeQuery();

            } finally {
                if (rs != null)
                    rs.close();


                if (stmt != null)
                    stmt.close();
            }
        }
    }

