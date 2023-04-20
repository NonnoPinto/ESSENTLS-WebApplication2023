package com.essentls.dao;

import com.essentls.resource.Event;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Gather events by their visibility.
 *
 * @author Mattia Maglie (mattia.maglie@studenti.unipd.it)
 * @version 1.00
 * @since 1.00
 */
public final class UserEventsListDAO extends AbstractDAO<List<Event>> {

    /**
     * The SQL statement to be executed
     */
    private static final String STATEMENT = "SELECT * FROM  public.\"Events\" WHERE Visibility <= ?";

    /**
     * The tier of the current user
     */
    private final int tier;

    /**
     * Creates a new object for gather events by tier.
     *
     * @param con    the connection to the database.
     * @param tier  the thier of the current user.
     */
    public UserEventsListDAO(final Connection con, final int tier) {
        super(con);
        this.tier = tier;
    }

    @Override
    public final void doAccess() throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        // the results of the search
        final List<Event> events = new ArrayList<Event>();

        try {
            pstmt = con.prepareStatement(STATEMENT);
            pstmt.setInt(1, this.tier);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                events.add(
                        new Event(
                                rs.getInt("Id"),
                                rs.getString("Name"),
                                rs.getString("Description"),
                                rs.getFloat("Price"),
                                rs.getInt("Visibility"),
                                rs.getString("Location"),
                                rs.getInt("MaxParticipantsInternational"),
                                rs.getInt("MaxParticipantsVolunteer"),
                                rs.getDate("EventStart"),
                                rs.getDate("EventEnd"),
                                rs.getDate("SubscriptionStart"),
                                rs.getDate("SubscriptionEnd"),
                                rs.getDate("WithdrawalEnd"),
                                rs.getInt("MaxWaitingList"),
                                rs.getString("Attributes"),
                                rs.getString("Thumbnail"),
                                rs.getString("Poster")
                        )
                );
            }

            LOGGER.info("Event(s) with visibility lower than tier %d successfully listed.", this.tier);
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
