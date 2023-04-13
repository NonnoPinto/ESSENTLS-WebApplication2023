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
            stmt.setLong(1, this.user.getId());

            stmt.executeUpdate();   // the update

            LOGGER.info("%s's User Association Form successfully updated.", this.user.getEmail());

        } finally {
            if (stmt != null)
                stmt.close();
        }

        con.close();

    }
}
