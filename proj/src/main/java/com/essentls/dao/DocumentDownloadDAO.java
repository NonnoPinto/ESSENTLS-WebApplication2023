package com.essentls.dao;

import com.essentls.resource.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DocumentDownloadDAO extends AbstractDAO<byte[]>{

    /**
     * The SQL statement to be executed
     */
    private static final String STATEMENT = "SELECT \"documentBytes\" FROM public.\"Users\" WHERE id = ?";
    long id;

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
