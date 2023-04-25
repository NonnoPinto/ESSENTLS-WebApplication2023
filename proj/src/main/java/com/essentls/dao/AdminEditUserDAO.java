package com.essentls.dao;

import java.sql.*;
import java.util.Arrays;

import com.essentls.resource.User;
import org.json.JSONObject;
import org.postgresql.util.PGobject;

public class AdminEditUserDAO extends AbstractDAO<User>{
    
    /**
	 * The SQL statement to be executed
	 */
	private static final String STATEMENT = "UPDATE public.\"Users\" SET email = ?, "+
    "password = ?, \"cardID\" = ?, tier = ?,  \"registrationDate\" = ?,  name = ?,  surname = ?, "+
    "sex = CAST(? as gen),  \"dateOfBirth\" = ?,  nationality = ?,  \"homeCountryAddress\" = ?,  \"homeCountryUniversity\" = ?, "+
    "\"periodOfStay\" = ?,  \"phoneNumber\" = ?,  \"paduaAddress\" = ?,  \"documentType\" = CAST (? as identity),  \"documentNumber\" = ?, "+
    "\"documentFile\" = ?,  \"dietType\" = CAST (? as diet),  allergies = ?,  \"emailHash\" = ?,  \"emailConfirmed\" = ? WHERE id = ?";

    /**
	 * The all the user attributes
	 */
	private final User user;

    public PGobject jsonToPGobj(JSONObject j) throws java.sql.SQLException{
        PGobject pgobj = new PGobject();
        pgobj.setType("json");
        pgobj.setValue(j.toString());
        return pgobj;
    }

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
            pstmt.setString(2, this.user.getPassword());
            pstmt.setString(3, this.user.getCardId());
            pstmt.setInt(4, this.user.getTier());
            pstmt.setDate(5, this.user.getRegistrationDate());
            pstmt.setString(6, this.user.getName());
            pstmt.setString(7, this.user.getSurname());
            pstmt.setString(8, this.user.getSex());
            pstmt.setDate(9, this.user.getDateOfBirth());
            pstmt.setString(10, this.user.getNationality());
            pstmt.setObject(11, jsonToPGobj(this.user.getHomeCountryAddress()));
            pstmt.setString(12, this.user.getHomeCountryUniversity());
            pstmt.setInt(13, this.user.getPeriodOfStay());
            pstmt.setString(14, this.user.getPhoneNumber());
            pstmt.setObject(15, jsonToPGobj(this.user.getPaduaAddress()));
            pstmt.setString(16, this.user.getDocumentType());
            pstmt.setString(17, this.user.getDocumentNumber());
            pstmt.setString(18, this.user.getDocumentFile());
            pstmt.setString(19, this.user.getDietType());
            pstmt.setArray(20, con.createArrayOf("text", Arrays.stream(this.user.getAllergies()).map(i -> String.valueOf(i)).toArray()));
            pstmt.setString(21, this.user.getEmailHash());
            pstmt.setBoolean(22, this.user.getEmailConfirmed());
            pstmt.setLong(23, this.user.getId());

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

	}

}
