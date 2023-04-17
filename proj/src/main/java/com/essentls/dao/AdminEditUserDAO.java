package com.essentls.dao;

import java.sql.*;
import com.essentls.resource.User;

public class AdminEditUserDAO extends AbstractDAO<User>{
    
    /**
	 * The SQL statement to be executed
	 */
	private static final String STATEMENT = "UPDATE Users SET id WHERE ID = ?,  Email = ?,  "+
    "Password = ?,  CardID = ?,  Tier = ?,  RegistrationDate = ?,  Name = ?,  Surname = ?, "+
    "Sex = ?,  DOB = ?,  Nationality = ?,  HomeCountryAddress = ?,  HomeCountryUniversity = ?, "+
    "PeriodOfStay = ?,  PhoneNumber = ?,  PadovaAddress = ?,  DocumentType = ?,  DocumentNumber = ?, "+
    "DocumentFile = ?,  DietType = ?,  Allergies = ?,  EmailHash = ?,  EmailConfirmed = ?";   

    /**
	 * The all the user attributes
	 */
	private final User user;

    /**
     * Creates a new object for editing an User
     *
     * @param con    the connection to the database.
     * @param user   the user to edit
     */
    public AdminEditUserDAO(final Connection con, User user) {
        
        super(con);
        this.user = user;
    }
    
    @Override
	protected final void doAccess() throws SQLException {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = con.prepareStatement(STATEMENT);
            pstmt.setLong(1, this.user.getId());
            pstmt.setString(2, this.user.getEmail());
            pstmt.setString(3, this.user.getPassword());
            pstmt.setString(4, this.user.getCardId());
            pstmt.setInt(5, this.user.getTier());
            pstmt.setDate(6, this.user.getRegistrationDate());
            pstmt.setString(7, this.user.getName());
            pstmt.setString(8, this.user.getSurname());
            pstmt.setString(9, this.user.getSex());
            pstmt.setDate(10, this.user.getDateOfBirth());
            pstmt.setString(11, this.user.getNationality());
            pstmt.setString(12, this.user.getHomeCountryAddress());
            pstmt.setString(13, this.user.getHomeCountryUniversity());
            pstmt.setString(14, this.user.getPeriodOfStay());
            pstmt.setString(15, this.user.getPhoneNumber());
            pstmt.setString(16, this.user.getPaduaAddress());
            pstmt.setString(17, this.user.getDocumentType());
            pstmt.setString(18, this.user.getDocumentNumber());
            pstmt.setString(19, this.user.getDocumentFile());
            pstmt.setString(20, this.user.getDietType());
            pstmt.setString(21, this.user.getAllergies());
            pstmt.setBoolean(22, this.user.getEmailConfirmed());

			rs = pstmt.executeQuery();

			LOGGER.info("User %d successfully edited in the database.", this.user.getId());

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
