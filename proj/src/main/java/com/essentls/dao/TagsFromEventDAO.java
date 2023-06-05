package com.essentls.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class TagsFromEventDAO extends AbstractDAO<ArrayList<String>> {

    private static final String STATEMENT_TAGS_LIST = "SELECT * FROM \"EventTags\" WHERE \"EventTags\".\"eventId\" = ?";

    private int eventID = -1;

    public TagsFromEventDAO(Connection con, int eventId) {
        super(con);
        this.eventID = eventId;
    }

    @Override
    protected void doAccess() throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        //the results of the search by a given eventId
        final ArrayList<String> tags = new ArrayList<String>();

        try {
            pstmt = con.prepareStatement(STATEMENT_TAGS_LIST);
            pstmt.setInt(1, eventID);

            rs = pstmt.executeQuery();

            while (rs.next()){
                tags.add(rs.getString("tag"));
            }

            LOGGER.info("%d Tag(s) successfully listed for eventId: %d.", tags.size(), eventID);

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
        }

        this.outputParam = tags;

    }
}
