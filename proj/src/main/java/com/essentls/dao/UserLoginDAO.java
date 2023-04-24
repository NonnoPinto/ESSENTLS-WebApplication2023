package com.essentls.dao;

import com.essentls.resource.User;
import org.json.JSONObject;
import org.postgresql.util.PGobject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Authenticates the user in the database
 *
 * @author Andrea Campagnol (andrea.campagnol.1@studenti.unipd.it)
 * @version 1.00
 * @since 1.00
 */
public final class UserLoginDAO extends AbstractDAO<User> {
//AbstractDAO<User> return the user logged in

    /**
	 * The SQL statement to be executed
	 */
    private static final String STATEMENT_LOGIN = "SELECT * FROM \"Users\" WHERE LOWER(email)=? AND password=?;"; //md5() not secure?

	/**
	 * The email of the user to be authenticated
	 */
	private final String email;

	/**
	 * The password of the user to be authenticated
	 */
	private final String password;

    /**
	 * Creates a new object for loggin in a user by its email and password.
	 *
	 * @param con      the connection to the database.
	 * @param email    the email of the user to be authenticated.
	 * @param password the password of the user to be authenticated.
	 */
    public UserLoginDAO(final Connection con, final String email, final String password) {
        super(con);

        if (email == null || email.isEmpty()) {
			LOGGER.error("The email cannot be null or empty.");
			throw new NullPointerException("The email cannot be null or empty.");
		}

		this.email = email;

		if (password == null || password.isEmpty()) {
			LOGGER.error("The password cannot be null or empty.");
			throw new NullPointerException("The password cannot be null or empty.");
		}

		this.password = password;
    }

    @Override
    public void doAccess() throws SQLException {

        PreparedStatement stmnt = null;
        ResultSet rs = null;  // the results of the search
        User user = null; //user returned to outputParam if logged in 

        try {
            stmnt = con.prepareStatement(STATEMENT_LOGIN);
            stmnt.setString(1, this.email);
            stmnt.setString(2, this.password);
            rs = stmnt.executeQuery();

            if(rs.next()){

                user = new User(
                    rs.getLong("id"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("cardId"),
                    rs.getInt("tier"),
                    rs.getDate("registrationDate"),
                    rs.getString("name"),
                    rs.getString("surname"),
                    rs.getString("sex"),
                    rs.getDate("dateOfBirth"),
                    rs.getString("nationality"),
                    new JSONObject(rs.getObject("homeCountryAddress", PGobject.class)),
                    rs.getString("homeCountryUniversity"),
                    rs.getInt("periodOfStay"),
                    rs.getString("phoneNumber"),
                    new JSONObject (rs.getObject("paduaAddress", PGobject.class)),
                    rs.getString("documentType"),
                    rs.getString("documentNumber"),
                    rs.getString("documentFile"),
                    rs.getString("dietType"),
                    (String[]) rs.getArray("allergies").getArray(),
                    rs.getString("emailhash"),
                    rs.getBoolean("emailConfirmed")
                ); 

                LOGGER.info("User logged in {}.", user.getEmail());

            }else{

                LOGGER.error("error logging in the user {}.", this.email);

            }

        } finally {
            if (rs != null) {
                rs.close();
            }

            if (stmnt != null) {
                stmnt.close();
            }

            this.outputParam = user; 
            con.close();
        }

    }
    
}