package com.essentls.dao;

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
    private static final String STATEMENT_LOGIN = "SELECT * FROM User WHERE email=? AND password=md5(?);"; //md5() not secure?

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

        if (email == null || email.isBlank()) {
			LOGGER.error("The email cannot be null or empty.");
			throw new NullPointerException("The email cannot be null or empty.");
		}

		this.email = email;

		if (password == null || password.isBlank()) {
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
                            rs.getInt("Id"),
                            rs.getString("Email"),
                            rs.getString("Password"),
                            rs.getInt("CardID"),
                            rs.getInt("Tier"),
                            rs.getDate("RegistrationDate"),
                            rs.getString("Name"),
                            rs.getString("Surname"),
                            rs.getstring("Sex"),
                            rs.getDate("DOB"),
                            rs.getString("Nationality"),
                            rs.getString("HomeCountryAddress"),
                            rs.getString("HomeCountryUniversity"),
                            rs.getString("PeriodOfStay"),
                            rs.getString("PhoneNumber"),
                            rs.getString("PadovaAddress"),
                            rs.getString("DocumentType"),
                            rs.getstring("DocumentNumber"),
                            rs.getString("DocumentFile"),
                            rs.getString("DietType"),
                            rs.getString("Allergies"),
                            rs.getString("EmailHash"),
                            rs.getString("EmailConfirmed")
                ); 

                LOGGER.info("User logged in %s.", user.getEmail());

            }else{

                LOGGER.error("error logging in the user %s.", user.getEmail());

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