package com.essentls.dao;

import com.essentls.resource.User;
import com.oracle.wls.shaded.org.apache.xpath.operations.Bool;

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
public final class EmailConfirmationDAO extends AbstractDAO<Boolean> {

    /**
	 * The SQL statement to be executed to check the user hashCode
	 */
    private static final String STATEMENT_CHECK = "SELECT * FROM \"Users\" WHERE \"emailHash\"=?;"; 

	/**
	 * The hashCode of the user to be to_be_verified
	 */
	private final String hashCode;

     /**
	 * Creates a new object for loggin in a user by its email and password.
	 *
	 * @param con      the connection to the database.
	 * @param hashCode    the hashCode of the user to be to_be_verified.
	 */
    public EmailConfirmationDAO(final Connection con, final String hashCode) {
        super(con);
        this.hashCode = hashCode;
    }

    @Override
    public void doAccess() throws SQLException {

        PreparedStatement stmnt = null;
        ResultSet rs = null;  // the results of the search
        boolean to_be_verified = false; //user returned to outputParam if to_be_verified 
        User user = null; //user to verify 

        try {
            stmnt = con.prepareStatement(STATEMENT_CHECK);
            stmnt.setString(1, this.hashCode);
            rs = stmnt.executeQuery();

            if(rs.next()){
 
               LOGGER.info("User %s matches the hashCode.", rs.getString("email"));
               LOGGER.info("User %s is trying to verify the email.", rs.getString("email"));
               if (rs.getBoolean("emailConfirmed")){
                    to_be_verified = false;
                    LOGGER.info("User has NOT to be verified, emailConfirmed was TRUE");
               }
               else{
                    to_be_verified = true;
                    LOGGER.info("User has to be verified, emailConfirmed was FALSE");
               }

            }else{

                LOGGER.error("No user matching hashCode: %s.", this.hashCode);
                to_be_verified = false;
            }

        } finally {
            if (rs != null) {
                rs.close();
            }

            if (stmnt != null) {
                stmnt.close();
            }

            this.outputParam = to_be_verified; 
            con.close();
        }

    }
    
}
