package com.essentls.dao;

import com.essentls.resource.Event;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Gather an event by ID
 *
 * @author Mattia Maglie (mattia.maglie@studenti.unipd.it)
 * @version 1.00
 * @since 1.00
 */
public final class EventInfoDAO extends AbstractDAO<Event> {

    /**
     * The SQL statement to be executed
     */
    private static final String STATEMENT = "SELECT Id, Name, Description, Price, Visibility, Location, MaxParticipantsInternational, MaxParticipantsVolunteer, EventStart, EventEnd, SubscriptionStart, SubscriptionEnd, WithdrawalEnd, MaxWaitingList, Attributes, Thumbnail, Poster FROM Event WHERE Id = ?";

    /**
     * The id of the event
     */
    private final int id;

    /**
     * Creates a new object for gathering the event by id.
     *
     * @param con    the connection to the database.
     * @param tier  the thier of the current user.
     */
    public EventInfoDAO(final Connection con, final int id) {
        super(con);
        this.id = id;
    }

    @Override
    public final void doAccess() throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        // the results of the search
        final event = null;

        try {
            pstmt = con.prepareStatement(STATEMENT);
            pstmt.setInt(1, this.id);

            rs = pstmt.executeQuery();
            if(rs.next()){
                event = new Event(
                            rs.getInt("Id"),
                            rs.getString("Name"),
                            rs.getString("Description"),
                            rs.getFloat("Price"),
                            rs.getBoolean("Visibility"),
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

            LOGGER.info("Event with id %d successfully gathered.", this.id);
        } finally {
            if (rs != null) {
                rs.close();
            }

            if (pstmt != null) {
                pstmt.close();
            }

        }

        this.outputParam = event;
    }
}
