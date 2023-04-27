package com.essentls.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.essentls.resource.Cause;
import com.essentls.resource.Event;
import com.essentls.resource.Tag;
import org.json.JSONObject;

/**
 * Return the list of Events corresponding to a tag in [Home Format]
 *
 * @author Vittorio Cardillo (vittorio.cardillo@studenti.unipd.it)
 * @version 1.00
 * @since 1.00
 */
public class EventsFromTagAndTierDAO extends AbstractDAO<List<Event>> {

    private static final String STATEMENT_JOINED_EVENTS = "SELECT * FROM public.\"Events\" " +
            "WHERE (? = '' OR id IN (SELECT \"eventId\" FROM \"EventTags\" WHERE tag = ?)) " +
            "AND (? = -1 OR id IN (SELECT \"eventId\" FROM \"EventCauses\" WHERE \"causeId\" = ?)) " +
            "AND visibility <= ? " +
            "AND (LOWER(name) LIKE ? OR LOWER(description) LIKE ? OR " +
            "((LOWER(location#>>'{zip}') LIKE ?) OR (LOWER(location#>>'{number}') LIKE ?) OR (LOWER(location#>>'{country}') LIKE ?) OR " +
            "(LOWER(location#>>'{city}') LIKE ?) OR (LOWER(location#>>'{street}') LIKE ?)))";


    private final Tag tag;
    private final int tier;
    private final Cause cause;
    private final String srch;


    public EventsFromTagAndTierDAO(Connection con, Tag tag, int tier, Cause cause,String srch) {
        super(con);
        this.tag = tag;
        this.cause=cause;
        this.srch=srch;

        if(tier < 0){
           this.tier = 0; 
        } else if (tier > 4) {
            this.tier = 4;
        } else{
            this.tier = tier;
        }
    }

    @Override
    protected void doAccess() throws Exception {
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        final List<Event> events = new ArrayList<Event>();

        try {
            pstmt = con.prepareStatement(STATEMENT_JOINED_EVENTS);
            pstmt.setString(1, tag.getName());
            pstmt.setString(2, tag.getName());
            pstmt.setInt(3, cause.getId());
            pstmt.setInt(4, cause.getId());
            pstmt.setInt(5, tier);
            pstmt.setString(6,"%" + srch + "%");
            pstmt.setString(7,"%" + srch + "%");
            pstmt.setString(8,"%" + srch + "%");
            pstmt.setString(9,"%" + srch + "%");
            pstmt.setString(10,"%" + srch + "%");
            pstmt.setString(11,"%" + srch + "%");
            pstmt.setString(12,"%" + srch + "%");



            rs = pstmt.executeQuery();

            while (rs.next()) {
                events.add(new Event(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getFloat("price"),
                        new JSONObject(rs.getObject("location").toString()),
                        rs.getTimestamp("subscriptionEnd")));

}
        
            LOGGER.info("%d Events(s) successfully listed for tag %s and tier %d.", events.size(), tag.getName(), tier);
            
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
        }

        this.outputParam = events;
    }

}
