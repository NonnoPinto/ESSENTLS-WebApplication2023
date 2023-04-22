package com.essentls.dao;

import com.essentls.resource.Event;
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
    private static final String STATEMENT = "INSERT INTO \"Events\" (name, description, price, visibility, location, \"maxParticipantsInternational\", \"maxParticipantsVolunteer\", \"eventStart\", \"eventEnd\", \"subscriptionStart\",  \"subscriptionEnd\", \"withdrawalEnd\", \"maxWaitingList\", attributes, thumbnail, poster) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING name";


    /**
     * The event to be created
     */
    private final Event event;


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

        PGobject location_JSON = new PGobject();
        location_JSON.setType("json");
        location_JSON.setValue(this.event.getLocation().toString());
        PGobject pg_attributes = new PGobject();
        pg_attributes.setType("text[]");
        String text = this.event.getAttributes();
        //String[] array = text.split(",");
        pg_attributes.setValue(this.event.getAttributes());



        try{
            stmt = con.prepareStatement(STATEMENT);
            //stmt.setLong(1, this.event.getId());
            stmt.setString(1, this.event.getName());
            stmt.setString(2, this.event.getDescription());
            stmt.setFloat(3, this.event.getPrice());
            stmt.setInt(4, this.event.getVisibility());
            stmt.setObject(5, location_JSON);
            stmt.setInt(6, this.event.getMaxParticipantsInternational());
            stmt.setInt(7, this.event.getMaxParticipantsVolunteer());
            stmt.setDate(8, this.event.getEventStart());
            stmt.setDate(9, this.event.getEventEnd());
            stmt.setDate(10, this.event.getSubscriptionStart());
            stmt.setDate(11, this.event.getSubscriptionEnd());
            stmt.setDate(12, this.event.getWithdrawalEnd());
            stmt.setInt(13, this.event.getMaxWaitingList());
            stmt.setObject(14, pg_attributes);
            stmt.setString(15, this.event.getThumbnail());
            stmt.setString(16, this.event.getPoster());

            stmt.executeUpdate();
            rs = stmt.executeQuery();

            LOGGER.info("Event {} successfully created.", this.event.getId());

        }finally{
            if(stmt != null){
                stmt.close();
            }
            if(rs != null){
                rs.close();
            }
        }
        con.close();
    }
}