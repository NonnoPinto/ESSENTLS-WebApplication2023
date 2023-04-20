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
public final class UpdateConfirmedStatusDAO extends AbstractDAO<Boolean> {

    /**
	 * The SQL statement to be executed to set user status as verified
	 */
    private static final String STATEMENT_UPDATE_STATUS = "UPDATE public.\"Users\" SET \"emailConfirmed\" = ? WHERE \"emailHash\" = ?";

	/**
	 * The hashCode of the user to be verified
	 */
	private final String hashCode;

     /**
	 * Creates a new object for loggin in a user by its email and password.
	 *
	 * @param con      the connection to the database.
	 * @param hashCode    the hashCode of the user to be verified.
	 */
    public UpdateConfirmedStatusDAO(final Connection con, final String hash) {
        super(con);
        this.hashCode = hash;
    }

    @Override
    public void doAccess() throws SQLException {

        PreparedStatement stmnt = null;
        ResultSet rs = null;  // the results of the search
        boolean verified = false; //user returned to outputParam if verified 
        User user = null; //user to verify 

        try {
            stmnt = con.prepareStatement(STATEMENT_UPDATE_STATUS);
            stmnt.setBoolean(1, true);
            stmnt.setString(2, this.hashCode);
            int result = stmnt.executeUpdate();

            if(result == 1){

                /* 
                user = new User(
                    //rs.getLong("id"),
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
                    rs.getString("homeCountryAddress"),
                    rs.getString("homeCountryUniversity"),
                    rs.getString("periodOfStay"),
                    rs.getString("phoneNumber"),
                    rs.getString("paduaAddress"),
                    rs.getString("documentType"),
                    rs.getString("documentNumber"),
                    rs.getString("documentFile"),
                    rs.getString("dietType"),
                    rs.getString("allergies"),
                    //rs.getString("emailhash"),
                    rs.getBoolean("emailConfirmed")
                ); 
                */
 
               LOGGER.info("User status updated.");
                verified = true;
               //call insert bool true on verified


            }else{

                LOGGER.error("Error updating status");
                verified = false;
            }

        } finally {
            if (rs != null) {
                rs.close();
            }

            if (stmnt != null) {
                stmnt.close();
            }

            this.outputParam = verified; 
            con.close();
        }

    }
    
}

