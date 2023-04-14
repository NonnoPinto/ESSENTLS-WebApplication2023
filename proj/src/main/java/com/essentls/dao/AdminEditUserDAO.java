package com.essentls.dao;

import java.sql.*;
import com.essentls.resource.User;

public class AdminEditUserDAO extends AbstractDAO<User>{
    
    /**
	 * The SQL statement to be executed
	 */
	private static final String STATEMENT = "UPDATE Users SET id WHERE ID = ?,  Email = ?,  "+
    "Password = md5(?),  CardID = ?,  Tier = ?,  RegistrationDate = ?,  Name = ?,  Surname = ?, "+
    "Sex = ?,  DOB = ?,  Nationality = ?,  HomeCountryAddress = ?,  HomeCountryUniversity = ?, "+
    "PeriodOfStay = ?,  PhoneNumber = ?,  PadovaAddress = ?,  DocumentType = ?,  DocumentNumber = ?, "+
    "DocumentFile = ?,  DietType = ?,  Allergies = ?,  EmailHash = ?,  EmailConfirmed = ?";   

    /**
	 * The all the user attributes
	 */
	private final long id;
    private final String email;
    private final String password;
    private final int cardId;
    private final int tier;
    private final Date registrationDate;
    private final String name;
    private final String surname;
    private final String sex;
    private final Date dateOfBirth;
    private final String nationality;
    private final String homeCountryAddress;
    private final String homeCountryUniversity;
    private final String periodOfStay;
    private final int phoneNumber;
    private final String paduaAddress;
    private final String documentType;
    private final String documentNumber;
    private final String documentFile;
    private final String dietType;
    private final String allergies;
    private final boolean emailConfirmed;

    /**
     * Creates a new object for editing an User
     *
     * @param con    the connection to the database.
     * @param event   the user to delete
     */
    public AdminEditUserDAO(final Connection con, long id, String email, String password, int cardId, int tier, Date registrationDate, String name,
            String surname, String sex, Date dateOfBirth, String nationality, String homeCountryAddress,
            String homeCountryUniversity, String periodOfStay, int phoneNumber, String paduaAddress,
            String documentType, String documentNumber, String documentFile, String dietType, String allergies, boolean emailConfirmed) {
        
        super(con);
        this.id = id;
        this.email = email;
        this.password = password;
        this.cardId = cardId;
        this.tier = tier;
        this.registrationDate = registrationDate;
        this.name = name;
        this.surname = surname;
        this.sex = sex;
        this.dateOfBirth = dateOfBirth;
        this.nationality = nationality;
        this.homeCountryAddress = homeCountryAddress;
        this.homeCountryUniversity = homeCountryUniversity;
        this.periodOfStay = periodOfStay;
        this.phoneNumber = phoneNumber;
        this.paduaAddress = paduaAddress;
        this.documentType = documentType;
        this.documentNumber = documentNumber;
        this.documentFile = documentFile;
        this.dietType = dietType;
        this.allergies = allergies;
        this.emailConfirmed = emailConfirmed;
    }
    
    @Override
	protected final void doAccess() throws SQLException {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = con.prepareStatement(STATEMENT);
            pstmt.setLong(1, this.id);
            pstmt.setString(2, this.email);
            pstmt.setString(3, this.password);
            pstmt.setInt(4, this.cardId);
            pstmt.setInt(5, this.tier);
            pstmt.setDate(6, this.registrationDate);
            pstmt.setString(7, this.name);
            pstmt.setString(8, this.surname);
            pstmt.setString(9, this.sex);
            pstmt.setDate(10, this.dateOfBirth);
            pstmt.setString(11, this.nationality);
            pstmt.setString(12, this.homeCountryAddress);
            pstmt.setString(13, this.homeCountryUniversity);
            pstmt.setString(14, this.periodOfStay);
            pstmt.setInt(15, this.phoneNumber);
            pstmt.setString(16, this.paduaAddress);
            pstmt.setString(17, this.documentType);
            pstmt.setString(18, this.documentNumber);
            pstmt.setString(19, this.documentFile);
            pstmt.setString(20, this.dietType);
            pstmt.setString(21, this.allergies);
            pstmt.setBoolean(22, this.emailConfirmed);

			rs = pstmt.executeQuery();

			LOGGER.info("User %d successfully edited in the database.", this.id);

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
