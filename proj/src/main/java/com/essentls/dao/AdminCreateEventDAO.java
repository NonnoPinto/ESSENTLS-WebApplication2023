package com.essentls.dao;

import com.essentls.resource.Event;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
    private static final String STATEMENT = "INSERT INTO Events (id, 
                                                                name, 
                                                                description, 
                                                                price, 
                                                                visibility, 
                                                                location, 
                                                                maxParticipantsInternational, 
                                                                maxParticipantsVolunteer, 
                                                                eventStart, 
                                                                eventEnd,
                                                                subscriptionStart, 
                                                                subscriptionEnd,
                                                                withdrawalEnd,
                                                                maxWaitingList,
                                                                attributes,
                                                                thumbnail,
                                                                poster)
                                             VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";


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

        try{

            stmt = con.prepareStatement(STATEMENT);
            stmt.setLong(1, this.event.getId());
            stmt.setString(2, this.event.getName());
            stmt.setString(3, this.event.getDescription());
            stmt.setFloat(4, this.event.getPrice());
            stmt.setBoolean(5, this.event.isVisibility());
            stmt.setString(6, this.event.getLocation());
            stmt.setInt(7, this.event.getMaxParticipantsInternational());
            stmt.setInt(8, this.event.getMaxParticipantsVolunteer());
            stmt.setDate(9, this.event.getEventStart());
            stmt.setDate(10, this.event.getEventEnd());
            stmt.setDate(11, this.event.getSubscriptionStart());
            stmt.setDate(12, this.event.getSubscriptionEnd());
            stmt.setDate(13, this.event.getWithdrawalEnd());
            stmt.setInt(14, this.event.getMaxWaitingList());
            stmt.setString(15, this.event.getAttributes());
            stmt.setString(16, this.event.getThumbnail());
            stmt.setString(17, this.event.getPoster());

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