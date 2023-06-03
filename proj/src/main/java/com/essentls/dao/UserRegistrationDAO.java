package com.essentls.dao;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;

import com.essentls.resource.User;
import org.json.JSONObject;
import org.postgresql.util.PGobject;


/**
 * Add a new User to the database with tier 0
 *
 * @author Vittorio Cardillo (vittorio.cardillo@studenti.unipd.it)
 * @version 1.00
 * @since 1.00
 */
public class UserRegistrationDAO extends AbstractDAO<User>  {

    /**
     * The SQL statement to be executed
     */
    private static final String STATEMENT_REGISTRATION = "INSERT INTO public.\"Users\"(" +
            "email, password, \"cardID\", tier, \"registrationDate\", name, surname, sex, \"dateOfBirth\", " +
            "nationality, \"homeCountryAddress\", \"homeCountryUniversity\", \"periodOfStay\", \"phoneNumber\", " +
            "\"paduaAddress\", \"documentType\", \"documentNumber\", \"documentFile\", \"dietType\", allergies, " +
            "\"emailHash\", \"emailConfirmed\")" +
            "VALUES (?, ?, ?, 0, ?, ?, ?, CAST(? as gen), ?, ?, ?, ?, ?, ?, ?, CAST (? as identity), ?, ?, CAST (? as diet), ?, ?, ?);";


    /**
     * The user to be registered
     */
    private final User user;

    /**
     * Convert a JSONObject to a PGobject, format that can be recognized by the Postgres DB.
     *
     * @param j  the JSONObject to be converted
     * @return  the converted PGobject
     * @throws SQLException an error during an interaction with the database
     * @throws NullPointerException try to access to a null object
     */
    public PGobject jsonToPGobj(JSONObject j) throws java.sql.SQLException, NullPointerException{
        if(j==null){
            return null;
        }
        PGobject pgobj = new PGobject();
        pgobj.setType("json");
        pgobj.setValue(j.toString());
        return pgobj;
    }

    /**
     * Creates a new object for registering the user.
     *
     * @param con    the connection to the database.
     * @param user   the user to be registered.
     */
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
        String[] allergies = user.getAllergies();
        Array arrayAllergies = null;
        if(allergies!=null){
            Object[] values = (allergies.length>0)? Arrays.stream(allergies).map(i -> String.valueOf(i)).toArray(): null;
            arrayAllergies = con.createArrayOf("text", values);
        }
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
            pstmt.setObject(10,  jsonToPGobj(user.getHomeCountryAddress()));
            pstmt.setString(11, user.getHomeCountryUniversity());
            pstmt.setInt(12, user.getPeriodOfStay());
            //pstmt.setInt(12, 2);
            pstmt.setString(13, user.getPhoneNumber());
            pstmt.setObject(14, jsonToPGobj(user.getPaduaAddress()));
            pstmt.setString(15, user.getDocumentType());
            pstmt.setString(16, user.getDocumentNumber());
            pstmt.setString(17, user.getDocumentFile());
            pstmt.setString(18, user.getDietType());
            pstmt.setArray(19, arrayAllergies);
            pstmt.setString(20, user.getEmailHash());
            pstmt.setBoolean(21, user.getEmailConfirmed());

            pstmt.execute();

            LOGGER.info("User %s successfully registered", user.getEmail());

        } finally {
            if (pstmt != null)
                pstmt.close();
        }
    }
}
