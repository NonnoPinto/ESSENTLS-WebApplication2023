package com.essentls.dao;

import java.sql.*;
import com.essentls.resource.User;

public class AdminDeleteUserDAO extends AbstractDAO<User>{

    /**
	 * The SQL statement to be executed
	 */
	private static final String STATEMENT = "DELETE FROM Users WHERE id = ?";   

    /**
	 * The id of the user
	 */
	private final long user_id;

    /**
     * Creates a new object for deleting an User
     *
     * @param con    the connection to the database.
     * @param event   the user to delete
     */
    public AdminDeleteUserDAO(final Connection con, User _user) {
        super(con);
        this.user_id = _user.getId();
    }



    @Override
	protected final void doAccess() throws SQLException {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = con.prepareStatement(STATEMENT);
            pstmt.setLong(1, this.user_id);

			rs = pstmt.executeQuery();

			LOGGER.info("User %d successfully deleted from the database.", this.user_id);

		} finally {
			if (rs != null) {
				rs.close();
			}

			if (pstmt != null) {
				pstmt.close();
			}

		}
        con.close();

	}
    
}
