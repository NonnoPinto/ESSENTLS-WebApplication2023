package com.essentls.dao;

import com.essentls.resource.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class AdminUsersListDAO extends AbstractDAO<List<User>> {

    private static final String STATEMENT_USERS_LIST = "SELECT * FROM users WHERE (? IS NULL OR name = ?)";
    private  String name="";

    /**
     * Creates a new object for the updating of the tier of a user
     *
     * @param con    the connection to the database.
     * @param name  the name of the users we want to search
     */
    public AdminUsersListDAO(final Connection con, final String name)
    {
        super(con);
        this.name=name;
    }


    @Override
    public final void doAccess() throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        final List<User> users = new ArrayList<>();

        try {
            stmt = con.prepareStatement(STATEMENT_USERS_LIST);
            stmt.setString(1, this.name);
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
                                rs.getString("allergies"),
                                rs.getBoolean("emailConfirmed")));
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
