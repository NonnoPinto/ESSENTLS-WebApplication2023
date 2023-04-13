package com.essentls.dao;

import com.essentls.resource.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class AdminUsersListDAO extends AbstractDAO<List<User>> {

    private static final String STATEMENT_USERS_LIST = "SELECT * FROM users";

    public AdminUsersListDAO(final Connection con) {
        super(con);
    }

    @Override
    public final void doAccess() throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        final List<User> users = new ArrayList<>();

        try {
            stmt = con.prepareStatement(STATEMENT_USERS_LIST);

            rs = stmt.executeQuery();

            while (rs.next()) {
                users.add(
                        new User(
                                rs.getLong("id"),
                                rs.getString("email"),
                                rs.getString("password"),
                                rs.getInt("cardId"),
                                rs.getInt("tier"),
                                rs.getDate("date"),
                                rs.getString("name"),
                                rs.getString("surname"),
                                rs.getString("sex"),
                                rs.getDate("date2"),
                                rs.getString("nationality"),
                                rs.getString("homeCountryAddress,"),
                                rs.getString("homeCountryUniversity"),
                                rs.getString("periodOfStay"),
                                rs.getInt("phoneNumber"),
                                rs.getString("paduaAddress,"),
                                rs.getString("documentType"),
                                rs.getString("documentNumber"),
                                rs.getString("documentFile"),
                                rs.getString("dietType"),
                                rs.getString("allergies")));
            }

            LOGGER.info("Users list successfully listed.");

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }

        this.outputParam = users;
    }

}
