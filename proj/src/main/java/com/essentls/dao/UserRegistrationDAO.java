package com.essentls.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.essentls.resource.User;



/**
 * Add a new User to the database with tier 0
 *
 * @author Vittorio Cardillo (vittorio.cardillo@studenti.unipd.it)
 * @version 1.00
 * @since 1.00
 */
public class UserRegistrationDAO extends AbstractDAO  {
    private static final String STATEMENT_REGISTRATION = "insert into public.\"Users\"(email, password, cardID, tier, date, name, " +
                                "surname, sex, date2, nationality, homeCountryAddress, homeCountryUniversity, periodOfStay, " +
                                "phoneNumber, paduaAddress, documentType, documentNumber, documentFile, dietType, allergies, emailHash, emailConfirmed) " +
                                "values (?, ?, ?, 0, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

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
    public void doAccess() throws SQLException {
        
        PreparedStatement pstmt = null;

        try {
            pstmt = con.prepareStatement(STATEMENT_REGISTRATION);
            pstmt.setString(1, user.getEmail());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getCardId());
            pstmt.setDate(4, user.getRegistrationDate());
            pstmt.setString(5, user.getName());
            pstmt.setString(6, user.getSurname());
            pstmt.setString(7, user.getSex());
            pstmt.setDate(8, user.getDateOfBirth());
            pstmt.setString(9, user.getNationality());
            pstmt.setString(10, user.getHomeCountryAddress());
            pstmt.setString(11, user.getHomeCountryUniversity());
            pstmt.setString(12, user.getPeriodOfStay());
            pstmt.setString(13, user.getPhoneNumber());
            pstmt.setString(14, user.getPaduaAddress());
            pstmt.setString(15, user.getDocumentType());
            pstmt.setString(16, user.getDocumentNumber());
            pstmt.setString(17, user.getDocumentFile());
            pstmt.setObject(18, user.getDietType());
            pstmt.setObject(19, user.getAllergies());
            pstmt.setString(20, user.getEmailHash());
            pstmt.setBoolean(21, user.getEmailConfirmed());

            pstmt.execute();

            LOGGER.info("User %l successfully registered", user.getId());

        } finally {
            if (pstmt != null)
                pstmt.close();
        }
    }
}
