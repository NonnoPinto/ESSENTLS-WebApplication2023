package com.essentls.dao;

import java.sql.*;
import com.essentls.resource.User;

/**
 * Upload in the database the document of a user
 *
 * @author Vaidas Lenartavicius
 * @version 1.00
 * @since 1.00
 */
public class UploadUserDocumentDAO  extends AbstractDAO<User>{

    /**
     * The SQL statement to be executed
     */
    private static final String STATEMENT = "UPDATE public.\"Users\" SET \"documentBytes\" = ? WHERE id = ?";


    /**
     * The user whose document is to be uploaded
     */
    private final User user;

    /**
     * Creates a new object for editing a user in the database
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
