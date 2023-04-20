package com.essentls.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.essentls.resource.Tag;

/**
 * Return the list of Tags
 *
 * @author Vittorio Cardillo (vittorio.cardillo@studenti.unipd.it)
 * @version 1.00
 * @since 1.00
 */
public class TagsListDAO extends AbstractDAO<List<Tag>> {

    private static final String STATEMENT_TAG_LIST = "SELECT * from public.\"Tags\"";

    public TagsListDAO(Connection con) {
        super(con);
    }

    @Override
    protected void doAccess() throws Exception {
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        final List<Tag> tags = new ArrayList<Tag>();

        try {
            pstmt = con.prepareStatement(STATEMENT_TAG_LIST);
        
            rs = pstmt.executeQuery();

            while (rs.next())
                tags.add(new Tag(rs.getString("name")));
        
            LOGGER.info("%d Tag(s) successfully listed.", tags.size());
            
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
