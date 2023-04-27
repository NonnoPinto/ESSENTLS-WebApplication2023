package com.essentls.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.essentls.resource.Cause;


/**
 * Return the list of Tags
 *
 * @author Laura Pallante (laura.pallante@studenti.unipd.it
 * @version 1.00
 * @since 1.00
 */
public class CausesListDAO extends AbstractDAO<List<Cause>> {

    private static final String STATEMENT_CAUSE_LIST = "SELECT * from public.\"Causes\"" +
                                                        " WHERE name LIKE ?" +
                                                        " AND (? = -1 OR id = ?);";

    String subCause;
    int id;

    public CausesListDAO(Connection con, int id, String subCause) {
        super(con);
        this.subCause = subCause;
        this.id=id;
    }

    @Override
    protected void doAccess() throws Exception {

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        final List<Cause> causes = new ArrayList<Cause>();

        try {
            //TODO separate id and tag
            pstmt = con.prepareStatement(STATEMENT_CAUSE_LIST);
            pstmt.setString(1, "%" + subCause + "%");
            pstmt.setInt(2, id);
            pstmt.setInt(3, id);



            rs = pstmt.executeQuery();

            while (rs.next())
                causes.add(new Cause(rs.getInt("id"),rs.getString("name")));

            LOGGER.info("%d Cause(s) successfully listed.", causes.size());

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
