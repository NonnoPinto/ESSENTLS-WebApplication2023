package com.essentls.dao;

import com.essentls.resource.Event;
import org.json.JSONObject;
import org.postgresql.util.PGobject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Admin Create Event DAO, to create an event
 *
 * @author Matteo VIllani (matteo.villani@studenti.unipd.it)
 * @version 1.00
 * @since 1.00
 */

public class AdminCreateEventDAO extends AbstractDAO<Event>{
    /**
     * The SQL statement to be executed
     */
    private static final String STATEMENT = "INSERT INTO \"Events\" (name, description, price, visibility, location, \"maxParticipantsInternational\", \"maxParticipantsVolunteer\", \"eventStart\", \"eventEnd\", \"subscriptionStart\",  \"subscriptionEnd\", \"withdrawalEnd\", \"maxWaitingList\", attributes, thumbnail, poster) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";


    /**
     * The event to be created
     */
    private final Event event;

    /**
     * Convert a JSONObject to a PGobject, format that can be recognized by the Postgres DB.
     */
    public PGobject jsonToPGobj(JSONObject j) throws java.sql.SQLException, NullPointerException{
        if(j==null){
            return null;
        }
        PGobject pgobj = new PGobject();
        pgobj.setType("json");
        pgobj.setValue(j.toString());
        return pgobj;
    }

    /**
     * Convert a JSONObject to a PGobject, format that can be recognized by the Postgres DB.
     */
    public PGobject stringArrayToPGobj(String[] s) throws java.sql.SQLException, NullPointerException{

        PGobject pgobj = new PGobject();
        pgobj.setType("text[]");

        //return empty if so
        if(s.length ==0){
            pgobj.setValue("");
            return pgobj;
        }

        //String[] to String
        String text ="{" + "\"" + s[0];
        for(int i=1; i<s.length; i++){
            text += "\", ";
            text += "\"";
            text += s[i];
        }
        text += "\"}";
        //set value of PGObject
        pgobj.setValue(text);
        return pgobj;
    }


    /**
     * Creates a new object.
     *
     * @param con    the connection to the database.
     * @param event   the event to create.
     */
    public AdminCreateEventDAO(final Connection con, final Event event){
        super(con);
        this.event = event;
    }

    @Override
    public final void doAccess() throws SQLException{
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try{
            stmt = con.prepareStatement(STATEMENT);
            //stmt.setInt(1, this.event.getId());
            stmt.setString(1, this.event.getName());
            stmt.setString(2, this.event.getDescription());
            stmt.setFloat(3, this.event.getPrice());
            stmt.setInt(4, this.event.getVisibility());
            stmt.setObject(5, jsonToPGobj(this.event.getLocation()));
            stmt.setInt(6, this.event.getMaxParticipantsInternational());
            stmt.setInt(7, this.event.getMaxParticipantsVolunteer());
            stmt.setTimestamp(8, this.event.getEventStart());
            stmt.setTimestamp(9, this.event.getEventEnd());
            stmt.setTimestamp(10, this.event.getSubscriptionStart());
            stmt.setTimestamp(11, this.event.getSubscriptionEnd());
            stmt.setTimestamp(12, this.event.getWithdrawalEnd());
            stmt.setInt(13, this.event.getMaxWaitingList());
            stmt.setObject(14, stringArrayToPGobj(this.event.getAttributes()));
            stmt.setString(15, this.event.getThumbnail());
            stmt.setString(16, this.event.getPoster());

            stmt.executeUpdate();
            //rs = stmt.executeQuery();

            LOGGER.info("Event {} successfully created.", this.event.getId());

        }finally {
            if (stmt != null) {
                stmt.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
    }
}