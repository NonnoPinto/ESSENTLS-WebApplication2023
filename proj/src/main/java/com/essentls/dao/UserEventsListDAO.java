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
    private static final String STATEMENT = "SELECT * FROM public.\"Events\" LEFT JOIN (SELECT \"eventId\", string_agg(tag, ';') AS tags " +
            "FROM \"EventTags\" GROUP BY \"eventId\") AS tb_tags ON \"Events\".id = tb_tags.\"eventId\" " +
            "LEFT JOIN (SELECT \"eventId\", string_agg((" +
            "SELECT name FROM public.\"Causes\" WHERE id = \"causeId\")" +
            ", ';') AS causes FROM \"EventCauses\" GROUP BY \"eventId\") AS tb_causes " +
            "ON \"Events\".id = tb_causes.\"eventId\" "+
            "WHERE Visibility <= ?";

    /**
     * The tier of the current user
     */
    private final int tier;

    /**
     * Creates a new object for gather events by tier.
     *
     * @param con    the connection to the database.
     * @param tier  the tier of the current user.
     */
    public UserEventsListDAO(final Connection con, final int tier) {
        super(con);
        if(tier < 0){
            this.tier = 0; 
         } else if (tier > 4) {
             this.tier = 4;
         } else{
             this.tier = tier;
         }
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

                events.add(
                        new Event(
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
                                (String[]) rs.getArray("Attributes").getArray(),
                                rs.getString("Thumbnail"),
                                rs.getString("Poster"),
                                tags,
                                causes
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
