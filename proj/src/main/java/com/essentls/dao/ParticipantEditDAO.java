package com.essentls.dao;

import com.essentls.resource.User;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;

/**
 * Participant edit attributes DAO
 *
 * @author Mattia Maglie (mattia.maglie@studenti.unipd.it)
 * @version 1.00
 * @since 1.00
 */

public final class ParticipantEditDAO extends AbstractDAO<Boolean> {

    /**
     * new mail and pass
     */
    private final int userId;
    private final int eventId;
    private final String attributeValues;

    /**
     * The SQL statement to be executed
     */
    private static final String STATEMENT = "UPDATE public.\"Participants\" SET \"attributeValues\" = ? WHERE \"userId\" = ? AND \"eventId\" = ?";


    /**
     * Creates a new object for gather info about user.
     *
     * @param con    the connection to the database.
     * @param user   the user that made the payments.
     */
    public ParticipantEditDAO(final Connection con, int userId, int eventId, String attributeValues) {
        super(con);
        this.userId = userId;
        this.eventId = eventId;
        this.attributeValues = attributeValues;
    }

    @Override
    protected void doAccess() throws Exception {
        PreparedStatement stmnt = null;
        ResultSet rs = null;

        this.outputParam = false;

        try {
            stmnt = con.prepareStatement(STATEMENT);
            stmnt.setObject(1, this.attributeValues, Types.OTHER);
            stmnt.setInt(2, this.userId);
            stmnt.setInt(3, this.eventId);

            this.outputParam = (stmnt.executeUpdate() == 1);


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
