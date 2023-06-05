package com.essentls.dao;

import java.sql.*;
import com.essentls.resource.User;

/**
 * Deletes a user from the database.
 *
 * @author Francesco Marcato
 * @version 1.00
 * @since 1.00
 */
public class AdminDeleteUserDAO extends AbstractDAO<User>{

	//This DAO is not used in the current version of the project since we figured out that the removal of a user by admin is currently not necessary
    /**
	 * The SQL statement to be executed
	 */
	private static final String STATEMENT = "DELETE FROM Users WHERE id = ?";   

    /**
	 * The id of the user
	 */
	private final int user_id;

    /**
     * Creates a new object for deleting a user
     *
     * @param con    the connection to the database.
     * @param _user   the user to delete
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
            pstmt.setInt(1, this.user_id);

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


	}
    
}
