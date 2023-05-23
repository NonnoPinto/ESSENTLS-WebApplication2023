package com.essentls.dao;

import com.essentls.resource.Cause;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CausesFromEventDAO extends AbstractDAO<ArrayList<Integer>> {

    private static final String STATEMENT_CAUSE_LIST = "SELECT * FROM \"EventCauses\" LEFT JOIN \"Causes\" ON \"EventCauses\".\"causeId\" = \"Causes\".id WHERE \"EventCauses\".\"eventId\" = ?";

    private int eventID = -1;

    public CausesFromEventDAO(Connection con, int eventId) {
        super(con);
        this.eventID = eventId;
    }

    @Override
    protected void doAccess() throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        //the results of the search by a given eventId
        final ArrayList<Integer> causes = new ArrayList<Integer>();

        try {
            pstmt = con.prepareStatement(STATEMENT_CAUSE_LIST);
            pstmt.setInt(1, eventID);

            rs = pstmt.executeQuery();

            while (rs.next()){
                causes.add(rs.getInt("id"));
            }

            LOGGER.info("%d Cause(s) successfully listed for eventId: %d.", causes.size(), eventID);

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
        }

        this.outputParam = causes;

    }
}
