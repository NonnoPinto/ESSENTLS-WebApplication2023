package com.essentls.dao;

import com.essentls.resource.EventTag;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Inserts an event-cause relation into the database
 *
 * @author Alessandro Borsato (alessandro.borsato.1@studenti.unipd.it)
 * @version 1.00
 * @since 1.00
 */

public class EventTagsCreationDAO extends AbstractDAO<EventTag> {

        /**
        * The SQL statement to be executed
        */
        private static final String INSERT_STATEMENT = "INSERT INTO public.\"EventTags\" (\"eventId\", \"tag\") VALUES (?, ?)";

        /**
         *  the basic attributes
         */
        private int eventID = -1;
        private String tagName = "";


    /**
     * Creates a new object for storing an event-cause relation into the database.
     *
     * @param con the connection to the database.
     * @param eventTagEventTag the event-cause relation to be stored into the database.
     */
        public EventTagsCreationDAO(Connection con, EventTag eventTagEventTag) {
            super(con);
            this.eventID = eventTagEventTag.getEventId();
            this.tagName = eventTagEventTag.getTagName();
        }

        @Override
        protected void doAccess() throws Exception {

            PreparedStatement stmtInsert = null;
            ResultSet rs = null;



            try {
                if(tagName!=""&&eventID>0){
                    stmtInsert = con.prepareStatement(INSERT_STATEMENT);
                    stmtInsert.setInt(1, eventID);
                    stmtInsert.setString(2, tagName);

                    stmtInsert.executeUpdate();
                }

            } finally {
                if (rs != null)
                    rs.close();


                if (stmtInsert != null)
                    stmtInsert.close();
            }
        }
    }

