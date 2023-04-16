package com.essentls.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.essentls.resource.User;



/**
 * Add a new User to the database with tier 0
 *
 * @author Vittorio Cardillo (vittorio.cardillo@studenti.unipd.it)
 * @version 1.00
 * @since 1.00
 */
public class UserRegistrationDAO extends AbstractDAO  {
    private static final String STATEMENT_REGISTRATION = "insert into data.user(email, password, tier, date, name, " + 
                                "surname, sex, date2, nationality, homeCountryAddress, homeCountryUniversity, periodOfStay, " + 
                                "phoneNumber, paduaAddress, documentType, documentNumber, documentFile, dietType, allergies) " +
                                "values (?, md5(?), 0, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    //19 parameters
    private final User user;

    public UserRegistrationDAO(Connection con, User user) {
        super(con);

        if (user == null) {
            LOGGER.error("The user cannot be null.");
            throw new NullPointerException("The user cannot be null.");
        }
        this.user = user;
    }

    @Override
    protected void doAccess() throws Exception {
        
        PreparedStatement pstmt = null;

        try {
            pstmt = con.prepareStatement(STATEMENT_REGISTRATION);
            pstmt.setString(1, user.getEmail());
            pstmt.setString(2, user.getPassword());
            pstmt.setDate(3, user.getRegistrationDate());
            pstmt.setString(4, user.getName());
            pstmt.setString(5, user.getSurname());
            pstmt.setString(6, user.getSex());
            pstmt.setDate(7, user.getDateOfBirth());
            pstmt.setString(8, user.getNationality());
            pstmt.setString(9, user.getHomeCountryAddress());
            pstmt.setString(10, user.getHomeCountryUniversity());
            pstmt.setString(11, user.getPeriodOfStay());
            pstmt.setInt(12, user.getPhoneNumber());
            pstmt.setString(13, user.getPaduaAddress());
            pstmt.setString(14, user.getDocumentType());
            pstmt.setString(15, user.getDocumentNumber());
            pstmt.setString(16, user.getDocumentFile());
            pstmt.setString(17, user.getDietType());
            pstmt.setString(18, user.getAllergies());

            pstmt.execute();

            LOGGER.info("User %l successfully registered", user.getId());

        } finally {
            if (pstmt != null)
                pstmt.close();
        }
    }
}
