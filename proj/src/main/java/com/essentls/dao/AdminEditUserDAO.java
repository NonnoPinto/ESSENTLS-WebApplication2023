package com.essentls.dao;

import java.sql.*;
import com.essentls.resource.User;

public class AdminEditUserDAO extends AbstractDAO<User>{
    
    /**
	 * The SQL statement to be executed
	 */
	private static final String STATEMENT = "UPDATE public.\"Users\" SET Email = ?,  "+
    " CardID = ?,  Tier = ?,  RegistrationDate = ?,  Name = ?,  Surname = ?, "+
    "Sex = CAST(? as gen),  DateOfBirth = ?,  Nationality = ?,  HomeCountryAddress = NULL,  HomeCountryUniversity = ?, "+
    "PeriodOfStay = ?,  PhoneNumber = ?,  PaduaAddress = NULL,  DocumentType = ?,  DocumentNumber = ?, "+
    "DocumentFile = ?,  DietType = NULL,  Allergies = NULL,  EmailHash = ?,  EmailConfirmed = ? WHERE id = ?";

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

		try {
			pstmt = con.prepareStatement(STATEMENT);
            pstmt.setString(1, this.user.getEmail());
            //pstmt.setString(2, this.user.getPassword());
            pstmt.setString(2, this.user.getCardId());
            pstmt.setInt(3, this.user.getTier());
            pstmt.setDate(4, this.user.getRegistrationDate());
            pstmt.setString(5, this.user.getName());
            pstmt.setString(6, this.user.getSurname());
            pstmt.setString(7, this.user.getSex());
            pstmt.setDate(8, this.user.getDateOfBirth());
            pstmt.setString(9, this.user.getNationality());
            //pstmt.setString(10, this.user.getHomeCountryAddress());
            pstmt.setString(10, this.user.getHomeCountryUniversity());
            pstmt.setString(11, this.user.getPeriodOfStay());
            pstmt.setString(12, this.user.getPhoneNumber());
            //pstmt.setString(13, this.user.getPaduaAddress());
            pstmt.setString(13, this.user.getDocumentType());
            pstmt.setString(14, this.user.getDocumentNumber());
            pstmt.setString(15, this.user.getDocumentFile());
            //pstmt.setString(18, this.user.getDietType());
            //pstmt.setString(19, this.user.getAllergies());
            pstmt.setString(16, this.user.getEmailHash());
            pstmt.setBoolean(17, this.user.getEmailConfirmed());
            pstmt.setLong(18, this.user.getId());

			pstmt.executeUpdate();

			LOGGER.info("User %d successfully edited in the database.", this.user.getId());

		} catch (SQLException e) {
            LOGGER.error("Error while editing user %d in the database.", this.user.getId(), e);
        }
        finally {
			if (pstmt != null) {
				pstmt.close();
			}

		}
        con.close();

	}

}
