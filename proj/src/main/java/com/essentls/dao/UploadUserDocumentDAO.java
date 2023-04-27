package com.essentls.dao;

import java.sql.*;
import java.util.Arrays;
import com.essentls.resource.User;


public class UploadUserDocumentDAO  extends AbstractDAO<User>{

    private static final String STATEMENT = "UPDATE public.\"Users\" SET \"documentBytes\" = ? WHERE id = ?";

    private final User user;
    /**
     * Creates a new object for editing an User
     *
     * @param con    the connection to the database.
     * @param user   the user whose document is to be uploaded
     */

    public UploadUserDocumentDAO(final Connection con, User user) {
        super(con);
        this.user = user;
    }

    @Override
    protected final void doAccess() throws SQLException {

            PreparedStatement pstmt = null;
            try {
                if (user.getDocumentBytes() != null) {
                    pstmt = con.prepareStatement(STATEMENT);
                    pstmt.setBytes(1, user.getDocumentBytes());
                    pstmt.setInt(2, user.getId());
                    pstmt.executeUpdate();
                    LOGGER.info("Document successfully uploaded for user %d", user.getId());
                } else {
                    LOGGER.info("Document not uploaded for user %d", user.getId());
                }
            } finally {
                if (pstmt != null) {
                    pstmt.close();
                }
            }
    }

}
