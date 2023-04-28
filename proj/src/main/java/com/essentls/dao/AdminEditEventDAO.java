package com.essentls.dao;

import com.essentls.resource.Event;
import org.json.JSONObject;
import org.postgresql.util.PGobject;

import java.sql.*;

/**
 * Updates an event into the database
 *
 * @author Matteo VIllani (matteo.villani@studenti.unipd.it)
 * @version 1.00
 * @since 1.00
 */
public class AdminEditEventDAO extends AbstractDAO<Event>{

    /**
     * The SQL statement to be executed
     */
    private static final String STATEMENT = "UPDATE \"Events\" SET id=?, name=?, description=?, price=?, visibility=?, location=?, \"maxParticipantsInternational\"=?, \"maxParticipantsVolunteer\"=?, \"eventStart\"=?, \"eventEnd\"=?, \"subscriptionStart\"=?,  \"subscriptionEnd\"=?, \"withdrawalEnd\"=?, \"maxWaitingList\"=?, attributes=?, thumbnail=?, poster=? WHERE id = ?";

    /**
     * The event to be updated in the database
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
     * Creates a new object to update an event.
     *
     * @param con    the connection to the database.
     * @param event   the event to be updated in the database.
     */
    public AdminEditEventDAO(final Connection con, final Event event){

        super(con);
        this.event = event;
    }


    @Override
    public final void doAccess() throws SQLException{

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try{

            stmt = con.prepareStatement(STATEMENT);
            stmt.setInt(1, this.event.getId());
            stmt.setString(2, this.event.getName());
            stmt.setString(3, this.event.getDescription());
            stmt.setFloat(4, this.event.getPrice());
            stmt.setInt(5, this.event.getVisibility());
            stmt.setObject(6, jsonToPGobj(this.event.getLocation()));
            stmt.setInt(7, this.event.getMaxParticipantsInternational());
            stmt.setInt(8, this.event.getMaxParticipantsVolunteer());
            stmt.setTimestamp(9, this.event.getEventStart());
            stmt.setTimestamp(10, this.event.getEventEnd());
            stmt.setTimestamp(11, this.event.getSubscriptionStart());
            stmt.setTimestamp(12, this.event.getSubscriptionEnd());
            stmt.setTimestamp(13, this.event.getWithdrawalEnd());
            stmt.setInt(14, this.event.getMaxWaitingList());
            stmt.setObject(15, stringArrayToPGobj(this.event.getAttributes()));
            stmt.setString(16, this.event.getThumbnail());
            stmt.setString(17, this.event.getPoster());
            stmt.setInt(18, this.event.getId());

            stmt.executeUpdate();

            LOGGER.info("Details of event {} successifully changed.", this.event.getId());
        }finally{
            if(rs != null){
                rs.close();
            }
            if(stmt != null){
                stmt.close();
            }
        }
    }
}