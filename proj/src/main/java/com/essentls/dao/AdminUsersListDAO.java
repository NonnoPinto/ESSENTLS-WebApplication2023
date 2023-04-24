package com.essentls.dao;

import com.essentls.resource.User;
import org.json.JSONObject;
import org.postgresql.util.PGobject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class AdminUsersListDAO extends AbstractDAO<List<User>> {

    private static final String STATEMENT_USERS_LIST = "SELECT * FROM public.\"Users\"" +
                                                        " WHERE (? IS NULL OR name = ?)" +
                                                        " AND (? IS NULL OR surname = ?)" +
                                                        " AND (? = -1 OR id = ?)" +
                                                        " AND (? IS NULL OR \"cardID\" = ?)" +
                                                        " AND (? IS NULL OR email = ?)";

    private String name;
    private String surname;
    private Long id;
    private String cardId;
    private String email;

    /**
     * Creates a new object for the updating of the tier of a user
     *
     * @param con    the connection to the database.
     * @param user  the user to search for
     */
    public AdminUsersListDAO(final Connection con, final User user)
    {
        super(con);
        this.name=user.getName();
        this.surname=user.getSurname();
        this.id=user.getId();
        this.cardId=user.getCardId();
        this.email=user.getEmail();

    }


    @Override
    public final void doAccess() throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        final List<User> users = new ArrayList<>();

        try {
            stmt = con.prepareStatement(STATEMENT_USERS_LIST);
            //search by name
            stmt.setString(1, name);
            stmt.setString(2, name);

            //search by surname
            stmt.setString(3, surname);
            stmt.setString(4, surname);

            //search by id
            stmt.setLong(5, id);
            stmt.setLong(6, id);

            //search by cardID
            stmt.setString(7, cardId);
            stmt.setString(8, cardId);

            //search by email
            stmt.setString(9, email);
            stmt.setString(10, email);

            rs = stmt.executeQuery();

            while (rs.next()) {
                users.add(
                        new User(
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
                                new JSONObject(((PGobject)rs.getObject("homeCountryAddress")).getValue()),
                                rs.getString("homeCountryUniversity"),
                                rs.getInt("periodOfStay"),
                                rs.getString("phoneNumber"),
                                new JSONObject(((PGobject)rs.getObject("paduaAddress")).getValue()),
                                rs.getString("documentType"),
                                rs.getString("documentNumber"),
                                rs.getString("documentFile"),
                                rs.getString("dietType"),
                                (String[]) rs.getArray("allergies").getArray(),
                                rs.getString("emailHash"),
                                rs.getBoolean("emailConfirmed")));
            }

            LOGGER.info("Users list successfully listed. Found %d users.", users.size());

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
