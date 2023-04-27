package com.essentls.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.essentls.resource.Event;


/**
 * Return the list of Events joined by a User
 *
 * @author Vittorio Cardillo (vittorio.cardillo@studenti.unipd.it)
 * @version 1.00
 * @since 1.00
 */
public class JoinedEventsListDAO extends AbstractDAO<List<Event>> {
    private static final String STATEMENT_JOINED_EVENTS = "SELECT e.id, e.name, e.\"startDate\" from public.\"Participants\" AS p INNER JOIN public.\"Events\" AS e " +
    "on p.\"eventId\" = e.id where p.\"userId\" = ?;";

    private final int userId;

    public JoinedEventsListDAO(Connection con, int userId) {
        super(con);
        this.userId = userId;
    }

    @Override
    protected void doAccess() throws Exception {
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        final List<Event> joinedEvents = new ArrayList<Event>();

        try {
            pstmt = con.prepareStatement(STATEMENT_JOINED_EVENTS);
            pstmt.setInt(1, userId);
        
            rs = pstmt.executeQuery();

            while (rs.next())
                joinedEvents.add(new Event(rs.getInt("id"), rs.getString("name"), rs.getTimestamp("date")));
        
            LOGGER.info("Event(s) joined by user %d successfully listed.", userId);
            
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
        }

        this.outputParam = joinedEvents;
    }

    
}
