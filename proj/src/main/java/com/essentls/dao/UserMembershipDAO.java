package com.essentls.dao;

import com.essentls.resource.User;
import org.json.JSONObject;
import org.postgresql.util.PGobject;

import java.sql.*;
import java.util.Arrays;

/**
 * After the initial payment, the user's tier is updated from 0 to 1
 *
 * @author Alessandro Borsato (alessandro.borsato.1@studenti.unipd.it)
 * @version 1.00
 * @since 1.00
 */

public class UserMembershipDAO extends AbstractDAO<User> {
    /**
     * The SQL statement to be executed
     */
    private static final String STATEMENT_UPDATE_USER = "UPDATE public.\"Users\" SET "+
            " \"cardID\" = ?, tier = 1,  \"registrationDate\" = ?,  name = ?,  surname = ?, "+
            "sex = CAST(? as gen),  \"dateOfBirth\" = ?,  nationality = ?,  \"homeCountryAddress\" = ?,  \"homeCountryUniversity\" = ?, "+
            "\"periodOfStay\" = ?,  \"phoneNumber\" = ?,  \"paduaAddress\" = ?,  \"documentType\" = CAST (? as identity),  \"documentNumber\" = ?, "+
            "\"documentFile\" = ?,  \"dietType\" = CAST (? as diet),  allergies = ? WHERE id = ? RETURNING *";


    /**
     * The user whose tier and infos must be updated
     */
    private final User user;


    /**
     * Creates a new object for the updating of the tier of a user
     *
     * @param con    the connection to the database.
     * @param user  the user whose tier must be updated
     */
    public UserMembershipDAO(Connection con, final User user) {
        super(con);
        this.user = user;
    }
    public PGobject jsonToPGobj(JSONObject j) throws java.sql.SQLException, NullPointerException{
        if(j==null){
            return null;
        }
        PGobject pgobj = new PGobject();
        pgobj.setType("json");
        pgobj.setValue(j.toString());
        return pgobj;
    }


    @Override
    public void doAccess() throws SQLException {
        User newUser = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        String[] allergies = user.getAllergies();
        Array arrayAllergies = null;
        if(allergies!=null){
            Object[] values = (allergies.length>0)? Arrays.stream(allergies).map(i -> String.valueOf(i)).toArray(): null;
            arrayAllergies = con.createArrayOf("text", values);
        }
        try {
            pstmt = con.prepareStatement(STATEMENT_UPDATE_USER);
            pstmt.setString(1, user.getCardId());
            pstmt.setDate(2, user.getRegistrationDate());
            pstmt.setString(3, user.getName());
            pstmt.setString(4, user.getSurname());
            pstmt.setString(5, user.getSex());
            pstmt.setDate(6, user.getDateOfBirth());
            pstmt.setString(7, user.getNationality());
            pstmt.setObject(8,  jsonToPGobj(user.getHomeCountryAddress()));
            pstmt.setString(9, user.getHomeCountryUniversity());
            pstmt.setInt(10, user.getPeriodOfStay());
            pstmt.setString(11, user.getPhoneNumber());
            pstmt.setObject(12, jsonToPGobj(user.getPaduaAddress()));
            pstmt.setString(13, user.getDocumentType());
            pstmt.setString(14, user.getDocumentNumber());
            pstmt.setString(15, user.getDocumentFile());
            pstmt.setString(16, user.getDietType());
            pstmt.setArray(17, arrayAllergies);
            pstmt.setInt(18, user.getId());

            rs= pstmt.executeQuery();

            if(rs.next()){
                newUser= new User(
                        rs.getInt("id"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("cardId"),
                        1,
                        rs.getDate("registrationDate"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("sex"),
                        rs.getDate("dateOfBirth"),
                        rs.getString("nationality"),
                        new JSONObject(rs.getObject("homeCountryAddress", PGobject.class)),
                        rs.getString("homeCountryUniversity"),
                        rs.getInt("periodOfStay"),
                        rs.getString("phoneNumber"),
                        new JSONObject (rs.getObject("paduaAddress", PGobject.class)),
                        rs.getString("documentType"),
                        rs.getString("documentNumber"),
                        rs.getString("documentFile"),
                        rs.getString("dietType"),
                        (String[]) rs.getArray("allergies").getArray(),
                        rs.getString("emailHash"),
                        rs.getBoolean("emailConfirmed")
                );
                LOGGER.info("User %s membership successfully completed.", user.getId());
            }



        } finally {
            if (pstmt != null)
                pstmt.close();
            if (rs != null)
                rs.close();
        }
        this.outputParam = newUser;

    }


}
