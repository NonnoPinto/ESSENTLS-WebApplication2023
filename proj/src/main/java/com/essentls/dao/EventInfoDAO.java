package com.essentls.dao;

import com.essentls.resource.Event;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Searches events by their id.
 *
 * @author Mattia Maglie (mattia.maglie@studenti.unipd.it)
 * @version 1.00
 * @since 1.00
 */
public final class EventInfoDAO extends AbstractDAO<Event> {

    /**
     * The SQL statement to be executed
     */
    private static final String STATEMENT_EVENT = "SELECT * FROM public.\"Events\" " +
            "LEFT JOIN (SELECT \"eventId\", string_agg(tag, ';') AS tags FROM \"EventTags\" GROUP BY \"eventId\") AS tb_tags " +
            "ON \"Events\".id = tb_tags.\"eventId\" " +
            "LEFT JOIN (SELECT \"eventId\", string_agg((" +
                "SELECT name FROM public.\"Causes\" WHERE id = \"causeId\")" +
            ", ';') AS causes FROM \"EventCauses\" GROUP BY \"eventId\") AS tb_causes " +
            "ON \"Events\".id = tb_causes.\"eventId\" WHERE id = ?";

    /**
     * The id of the event
     */
    private final int id;

    /**
     * Creates a new object for gathering the event by id.
     *
     * @param con    the connection to the database.
     * @param id     the id of the event.
     */
    public EventInfoDAO(final Connection con, final int id) {
        super(con);
        this.id = id;
    }

    @Override
    public final void doAccess() throws SQLException {

        PreparedStatement pstmt = null;
        PreparedStatement pstmt_tags = null;
        ResultSet rs = null;
        ResultSet rs_tags = null;

        // the results of the search
        Event event = null;

        try {
            pstmt = con.prepareStatement(STATEMENT_EVENT);
            pstmt.setInt(1, this.id);

            rs = pstmt.executeQuery();
            if(rs.next()){

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


                event = new Event(
                            rs.getInt("Id"),
                            rs.getString("Name"),
                            rs.getString("Description"),
                            rs.getFloat("Price"),
                            rs.getInt("Visibility"),
                            new JSONObject(rs.getObject("Location").toString()),
                            rs.getInt("MaxParticipantsInternational"),
                            rs.getInt("MaxParticipantsVolunteer"),
                            rs.getTimestamp("EventStart"),
                            rs.getTimestamp("EventEnd"),
                            rs.getTimestamp("SubscriptionStart"),
                            rs.getTimestamp("SubscriptionEnd"),
                            rs.getTimestamp("WithdrawalEnd"),
                            rs.getInt("MaxWaitingList"),
                            (String[]) rs.getArray("attributes").getArray(),
                            rs.getString("Thumbnail"),
                            rs.getString("Poster"),
                            tags,
                            causes
                        );

            };

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
