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

public class EventCausesCreationDAO extends AbstractDAO<EventCause> {


        private static final String INSERT_STATEMENT = "INSERT INTO public.\"EventCauses\" (\"eventId\", \"causeId\") VALUES (?, ?)";
        /**
         *  the basic attributes
         */
        private int eventID = -1;
        private int causeID = -1;


        /**
         * Creates a new EventCauses
         */
        public EventCausesCreationDAO(Connection con, EventCause eventCause) {
            super(con);
            this.eventID = eventCause.getEventId();
            this.causeID = eventCause.getCauseId();
        }

        @Override
        protected void doAccess() throws Exception {

            PreparedStatement stmtInsert = null;
            ResultSet rs = null;



            try {
                if(causeID>0&&eventID>0){
                    stmtInsert = con.prepareStatement(INSERT_STATEMENT);
                    stmtInsert.setInt(1, eventID);
                    stmtInsert.setInt(2, causeID);

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

