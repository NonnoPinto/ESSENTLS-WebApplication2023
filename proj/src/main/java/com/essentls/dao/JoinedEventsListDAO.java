package com.essentls.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.essentls.resource.Event;

public class JoinedEventsListDAO extends AbstractDAO<List<Event>> {
    private static final String STATEMENT_JOINED_EVENTS = "SELECT e.id, e.name, e.startDate from data.participant AS p INNER JOIN data.event AS e " +
    "on p.eventId = e.id where p.userId = ?;";

    private final long userId;

    public JoinedEventsListDAO(Connection con, long userId) {
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
            pstmt.setLong(1, userId);
        
            rs = pstmt.executeQuery();

            while (rs.next())
                joinedEvents.add(new Event(rs.getLong("id"), rs.getString("name"), rs.getDate("date")));
        
            LOGGER.info("Event(s) joined by user %l successfully listed.", userId);
            
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
