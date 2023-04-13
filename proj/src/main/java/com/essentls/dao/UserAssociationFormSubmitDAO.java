package com.essentls.dao;

import com.essentls.resource.User;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class UserAssociationFormSubmitDAO extends AbstractDAO<User> {


    /**
     * Creates a new DAO object.
     *
     * @param con the connection to be used for accessing the database.
     */

    private static final String STATEMENT = "UPDATE Users SET " +
            "cardID = ?, " +
            "name = ?, " +
            "surname = ?, " +
            "sex = ?, " +
            "dateOfBirth = ?, " +
            "nationality = ?, " +
            "homeCountryAddress = ?, " +
            "homeCountryUniversity = ?, " +
            "periodOfStay = ?, " +
            "phoneNumber = ?, " +
            "paduaAddress = ?, " +
            "documentType = ?, " +
            "documentNumber = ?, " +
            "documentFile = ?, " +
            "dietType = ?, " +
            "allergies = ? " +
            "WHERE id = ? ";

    private final User user;

    public UserAssociationFormSubmitDAO(Connection con, final User user) {
        super(con);
        this.user = user;
    }


    @Override
    protected void doAccess() throws Exception {
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(STATEMENT);
            stmt.setInt(1, user.getCardId());
            stmt.setString(2, user.getName());
            stmt.setString(3, user.getSurname());
            stmt.setString(4, user.getSex());
            stmt.setDate(5, user.getDateOfBirth());
            stmt.setString(6, user.getNationality());
            stmt.setString(7, user.getHomeCountryAddress());
            stmt.setString(8, user.getHomeCountryUniversity());
            stmt.setString(9, user.getPeriodOfStay());
            stmt.setInt(10, user.getPhoneNumber());
            stmt.setString(11, user.getPaduaAddress());
            stmt.setString(12, user.getDocumentType());
            stmt.setString(13, user.getDocumentNumber());
            stmt.setString(14, user.getDocumentFile());
            stmt.setString(15, user.getDietType());
            stmt.setString(16, user.getAllergies());

            stmt.executeUpdate();   // the update

            LOGGER.info("%s's User Association Form successfully updated.", this.user.getEmail());

        } finally {
            if (stmt != null)
                stmt.close();
        }

        con.close();

    }
}
