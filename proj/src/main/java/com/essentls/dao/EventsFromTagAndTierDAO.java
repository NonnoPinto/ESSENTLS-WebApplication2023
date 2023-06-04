package com.essentls.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.essentls.resource.Cause;
import com.essentls.resource.Event;
import com.essentls.resource.Tag;
import org.json.JSONObject;

/**
 * Searches events by their tag, cause, tier, name, description, location
 *
 * @author Vittorio Cardillo (vittorio.cardillo@studenti.unipd.it)
 * @version 1.00
 * @since 1.00
 */
public class EventsFromTagAndTierDAO extends AbstractDAO<List<Event>> {

    /**
     * The SQL statement to be executed
     */
    private static final String STATEMENT_JOINED_EVENTS = "SELECT * FROM public.\"Events\" " +
            "LEFT JOIN (SELECT \"eventId\", string_agg(tag, ';') AS tags FROM \"EventTags\" GROUP BY \"eventId\") AS tb_tags ON \"Events\".id = tb_tags.\"eventId\" " +
            "LEFT JOIN (SELECT \"eventId\", string_agg((" +
            "SELECT name FROM public.\"Causes\" WHERE id = \"causeId\")" +
            ", ';') AS causes FROM \"EventCauses\" GROUP BY \"eventId\") AS tb_causes " +
            "ON \"Events\".id = tb_causes.\"eventId\" "+
            "WHERE (? = '' OR id IN (SELECT \"eventId\" FROM \"EventTags\" WHERE tag = ?)) " +
            "AND (? = -1 OR id IN (SELECT \"eventId\" FROM \"EventCauses\" WHERE \"causeId\" = ?)) " +
            "AND visibility <= ? " +
            "AND (LOWER(name) LIKE ? OR LOWER(description) LIKE ? OR " +
            "((LOWER(location#>>'{zip}') LIKE ?) OR (LOWER(location#>>'{number}') LIKE ?) OR (LOWER(location#>>'{country}') LIKE ?) OR " +
            "(LOWER(location#>>'{city}') LIKE ?) OR (LOWER(location#>>'{street}') LIKE ?)))";


    /**
     * The tag of the event
     */
    private final Tag tag;

    /**
     * The tier of the user executing the query
     */
    private final int tier;

    /**
     * The cause of the event
     */
    private final Cause cause;

    /**
     * The name or description or location of the event
     */
    private final String srch;

    /**
     * Creates a new object for searching events by tag, cause, user's tier, name, description, location.
     *
     * @param con    the connection to the database.
     * @param tag   the tag of the event
     * @param tier  the tier of the user executing the query
     * @param cause the cause of the event
     * @param srch  the name or description or location of the event
     */
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

        //the results of the search
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

                List<String> tags = new ArrayList<>();

                String db_tags = rs.getString("tags");
                if(db_tags != null){
                    tags.addAll(Arrays.stream(db_tags.split(";")).toList());
                }

                List<String> causes = new ArrayList<>();

                String db_causes = rs.getString("causes");
                if(db_causes != null){
                    causes.addAll(Arrays.stream(db_causes.split(";")).toList());
                }

                events.add(new Event(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getFloat("price"),
                        new JSONObject(rs.getObject("location").toString()),
                        rs.getTimestamp("eventStart"),
                        rs.getTimestamp("eventEnd"),
                        rs.getTimestamp("subscriptionEnd"),
                        rs.getString("thumbnail"),
                        tags,
                        causes
                        ));

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
