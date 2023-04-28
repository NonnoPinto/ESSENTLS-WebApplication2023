package com.essentls.dao;
import com.essentls.resource.Event;
import java.sql.*;

/**
 * Deletes an event from the database
 *
 * @author Giovanni Zago
 * @version 1.00
 * @since 1.00
 */
public class AdminDeleteEventDAO extends AbstractDAO<Event>{

    //TODO: need to create servlet and jsp page for admin event removal functionality and update statement with new database format
    /**
     * The SQL statement to be executed
     */
    private static final String STATEMENT = "DELETE * FROM Event WHERE Id = ?";

    /**
     * The event to be deleted
     */
    private final Event event;

    /**
     * Creates a new object for deleting an event
     *
     * @param con    the connection to the database.
     * @param _event   the event to delete
     */
    public AdminDeleteEventDAO(final Connection con, Event _event) {
        super(con);
        this.event = _event;
    }

    @Override
    protected void doAccess() throws Exception {
        PreparedStatement stmnt = null;
        ResultSet rs = null;

        try {
            stmnt = con.prepareStatement(STATEMENT);
            stmnt.setInt(1, this.event.getId());

            rs = stmnt.executeQuery();

            LOGGER.info("Event %s successfully deleted from the database.", this.event.getId());

        }   finally {
            if (rs != null) {
                rs.close();
            }

            if (stmnt != null) {
                stmnt.close();
            }
        }
    }
}