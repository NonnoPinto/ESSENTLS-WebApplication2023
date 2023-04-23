package com.essentls.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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

    private static final String STATEMENT_JOINED_EVENTS = "SELECT e.id, e.name, e.description, e.price, e.location, e.\"subscriptionEnd\" " + 
        "FROM public.\"EventTags\" AS et INNER JOIN public.\"Events\" AS e ON et.\"eventId\" = e.id " +
        "WHERE et.\"tagId\" = ? AND e.visibility <= ?;";

    private final Tag tag;
    private final int tier;

    public EventsFromTagAndTierDAO(Connection con, Tag tag, int tier) {
        super(con);
        this.tag = tag;
        this.tier = tier;
    }

    @Override
    protected void doAccess() throws Exception {
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        final List<Event> events = new ArrayList<Event>();

        try {
            pstmt = con.prepareStatement(STATEMENT_JOINED_EVENTS);
            pstmt.setString(1, tag.getName());
            pstmt.setInt(2, tier);
        
            rs = pstmt.executeQuery();

            while (rs.next()) {
                events.add(new Event(
                        rs.getLong("id"),
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
