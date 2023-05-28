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

/**
 * Lists all the users in the database.
 *
 * @author Alessandro Borsato
 * @version 1.00
 * @since 1.00
 */
public class AdminUsersListDAO extends AbstractDAO<List<User>> {

    /**
     * The SQL statement to be executed
     */
    private static final String STATEMENT_USERS_LIST = "SELECT id, email, password, \"cardID\", tier, \"registrationDate\", " +
            "name, surname, sex, \"dateOfBirth\", nationality, \"homeCountryAddress\", " +
            "\"homeCountryUniversity\", \"periodOfStay\", \"phoneNumber\", \"paduaAddress\", " +
            "\"documentType\", \"documentNumber\", \"documentFile\", \"dietType\", allergies, " +
            "\"emailHash\", \"emailConfirmed\" FROM public.\"Users\"" +
            " WHERE (? IS NULL OR name ILIKE ?)" +
            " AND (? IS NULL OR surname ILIKE ?)" +
            " AND (? = -1 OR id = ?)" +
            " AND (? IS NULL OR LOWER(\"cardID\") = LOWER(?))" +
            " AND (? IS NULL OR LOWER(email) = LOWER(?))";

    /**
     * The name of the user
     */
    private String name;

    /**
     * The surname of the user
     */
    private String surname;

    /**
     * The id of the user
     */
    private int id;

    /**
     * The cardId of the user
     */
    private String cardId;

    /**
     * The email of the user
     */
    private String email;

    /**
     * Creates a new object for listing all the users
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

        //the results of the search
        final List<User> users = new ArrayList<>();

        try {
            stmt = con.prepareStatement(STATEMENT_USERS_LIST);
            //search by name
            stmt.setString(1, name);
            stmt.setString(2, "%" + name  + "%");

            //search by surname
            stmt.setString(3, surname);
            stmt.setString(4, "%" + surname  + "%");

            //search by id
            stmt.setInt(5, id);
            stmt.setInt(6, id);

            //search by cardID
            stmt.setString(7, cardId);
            stmt.setString(8, cardId);

            //search by email
            stmt.setString(9, email);
            stmt.setString(10, email);

            rs = stmt.executeQuery();

            while (rs.next()) {
                int userTier = rs.getInt("tier");
                if(userTier < 0){
                    userTier = 0;
                } else if(userTier > 4){
                    userTier = 4;
                }
                JSONObject homeCountryAddress = null;
                JSONObject paduaAddress = null;
                String[] allergies = null;
                try{
                    paduaAddress=new JSONObject(rs.getObject("paduaAddress", PGobject.class).getValue());
                } catch (Exception e){
                    LOGGER.error("Error while parsing paduaAddress for user with id: " + rs.getInt("id"));
                }
                try{
                    homeCountryAddress=new JSONObject(rs.getObject("homeCountryAddress", PGobject.class).getValue());
                } catch (Exception e){
                    LOGGER.error("Error while parsing homeCountryAddress for user with id: " + rs.getInt("id"));
                }
                try{
                    allergies=(String[]) rs.getArray("allergies").getArray();
                } catch (Exception e){
                    LOGGER.error("Error while parsing allergies for user with id: " + rs.getInt("id"));
                }
                users.add(
                        new User(
                                rs.getInt("id"),
                                rs.getString("email"),
                                rs.getString("password"),
                                rs.getString("cardId"),
                                userTier,
                                rs.getDate("registrationDate"),
                                rs.getString("name"),
                                rs.getString("surname"),
                                rs.getString("sex"),
                                rs.getDate("dateOfBirth"),
                                rs.getString("nationality"),
                                homeCountryAddress,
                                rs.getString("homeCountryUniversity"),
                                rs.getInt("periodOfStay"),
                                rs.getString("phoneNumber"),
                                paduaAddress,
                                rs.getString("documentType"),
                                rs.getString("documentNumber"),
                                rs.getString("documentFile"),
                                rs.getString("dietType"),
                                allergies,
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
