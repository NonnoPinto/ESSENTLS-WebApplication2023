package com.essentls.dao;

import com.essentls.resource.Event;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class AdminEventsListDAO extends AbstractDAO<List<Event>> {
    private static final String STATEMENT_EVENTS_LIST = "SELECT * FROM events";
    //TODO: we have to paginate the results

    public AdminEventsListDAO(final Connection con) {
        super(con);
    }

    @Override
    public final void doAccess() throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        final List<Event> events = new ArrayList<>();

        try {
            stmt = con.prepareStatement(STATEMENT_EVENTS_LIST);

            rs = stmt.executeQuery();

            while (rs.next()) {
                events.add(
                        new Event(
                                rs.getLong("id"),
                                rs.getString("name"),
                                rs.getString("description"),
                                rs.getFloat("price"),
                                rs.getInt("visibility"),
                                (JSONObject) rs.getObject("location"),
                                rs.getInt("maxParticipantsInternational"),
                                rs.getInt("maxParticipantsVolunteer"),
                                rs.getTimestamp("eventStart"),
                                rs.getTimestamp("eventEnd"),
                                rs.getTimestamp("subscriptionStart"),
                                rs.getTimestamp("subscriptionEnd"),
                                rs.getTimestamp("withdrawalEnd"),
                                rs.getInt("maxWaitingList"),
                                (String[]) rs.getArray("attributes").getArray(),
                                rs.getString("thumbnail"),
                                rs.getString("poster")));
            }

            LOGGER.info("Events list successfully listed.");


        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }

        this.outputParam = events;
    } 
}
