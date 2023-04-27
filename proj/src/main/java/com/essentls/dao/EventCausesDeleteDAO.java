package com.essentls.dao;

import com.essentls.resource.EventCause;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Add a tag to the tag's table
 *
 * @author Alessandro Borsato (alessandro.borsato.1@studenti.unipd.it)
 * @version 1.00
 * @since 1.00
 */

public class EventCausesDeleteDAO extends AbstractDAO<EventCause> {

        private static final String DELETE_STATEMENT = "DELETE FROM public.\"EventCauses\" WHERE (\"eventId\"=?)";

        /**
         *  the basic attributes
         */
        private int eventID = -1;


        /**
         * Creates a new EventCauses
         */
        public EventCausesDeleteDAO(Connection con, int eventID) {
            super(con);
            this.eventID = eventID;
        }

        @Override
        protected void doAccess() throws Exception {

            PreparedStatement stmtDelete = null;
            ResultSet rs = null;



            try {
                if(eventID>0){
                    stmtDelete = con.prepareStatement(DELETE_STATEMENT);
                    stmtDelete.setInt(1, eventID);
                    stmtDelete.executeUpdate();

                }

            } finally {
                if (rs != null)
                    rs.close();


                if (stmtDelete != null)
                    stmtDelete.close();
            }
        }
    }

