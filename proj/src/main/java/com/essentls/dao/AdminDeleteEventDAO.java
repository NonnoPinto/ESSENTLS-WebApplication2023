package com.essentls.dao;
import com.essentls.resource.Event;
import java.sql.*;

public class AdminDeleteEventDAO extends AbstractDAO<Event>{
    /**
     * The SQL statement to be executed
     */
    private static final String STATEMENT = "DELETE * FROM Event WHERE Id = ?";

    /**
     * The event to be deleted
     */
    private final Event event;

    /**
     * Creates a new object for gather info about user.
     *
     * @param con    the connection to the database.
     * @param event   the event to delete
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

            LOGGER.info("Event %s successfully deleted.", this.event.getId());

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
