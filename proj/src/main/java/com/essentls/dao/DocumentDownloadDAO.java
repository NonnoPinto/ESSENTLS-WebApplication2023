package com.essentls.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Retrieves documents file from the database.
 *
 * @author Vaidas Lenartavicius
 * @version 1.00
 * @since 1.00
 */
public class DocumentDownloadDAO extends AbstractDAO<byte[]>{

    /**
     * The SQL statement to be executed
     */
    private static final String STATEMENT = "SELECT \"documentBytes\" FROM public.\"Users\" WHERE id = ?";

    /**
     * The id of the user
     */
    long id;

    /**
     * Creates a new object for retrieving the document of the user.
     *
     * @param con the connection to the database.
     * @param id the id of the user
     */
    public DocumentDownloadDAO(final Connection con, long id) {
        super(con);
        this.id = id;
    }

    @Override
    protected final void doAccess() throws Exception {
        PreparedStatement stmnt = null;
        ResultSet rs = null;
        byte[] documentBytes = null;

        try {
            stmnt = con.prepareStatement(STATEMENT);
            stmnt.setLong(1, id);
            rs = stmnt.executeQuery();
            if(rs.next()){
                documentBytes = rs.getBytes("documentBytes");
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmnt != null) {
                stmnt.close();
            }
        }

        this.outputParam = documentBytes;
    }
}
