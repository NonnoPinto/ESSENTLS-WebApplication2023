package com.essentls.dao;

import java.sql.*;
import java.util.Arrays;

import com.essentls.resource.User;
import org.json.JSONObject;
import org.postgresql.util.PGobject;

/**
 * User edit profile DAO, to modify mail and pass
 *
 * @author Giovanni Zago (giovanni.zago.3@studenti.unipd.it)
 * @version 1.00
 * @since 1.00
 */

public final class UserEditProfileDAO extends AbstractDAO<User> {

    /**
     * Creates a new object for gather info about user.
     *
     * @param con    the connection to the database.
     * @param user   the user that made the payments.
     */

    /**
     * The SQL statement to be executed
     */
    private static final String STATEMENT = "UPDATE public.\"Users\" SET "+
            "name = ?,  surname = ?, "+
            "sex = CAST(? as gen),  \"dateOfBirth\" = ?,  nationality = ?, " +
            "\"homeCountryAddress\" = ?,  \"homeCountryUniversity\" = ?, "+
            "\"periodOfStay\" = ?,  \"phoneNumber\" = ?, \"paduaAddress\" = ?, "+
            "\"dietType\" = CAST (? as diet),  allergies = ? WHERE id = ?;";


    private final User user;

    /**
     * Convert a JSONObject to a PGobject, format that can be recognized by the Postgres DB.
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
     * Convert a JSONObject to a PGobject, format that can be recognized by the Postgres DB.
     */
    public PGobject stringArrayToPGobj(String[] s) throws java.sql.SQLException, NullPointerException{

        PGobject pgobj = new PGobject();
        pgobj.setType("text[]");

        //return empty if so
        if(s.length ==0){
            pgobj.setValue("");
            return pgobj;
        }

        //String[] to String
        String text ="{" + "\"" + s[0];
        for(int i=1; i<s.length; i++){
            text += "\", ";
            text += "\"";
            text += s[i];
        }
        text += "\"}";
        //set value of PGObject
        pgobj.setValue(text);
        return pgobj;
    }


    public UserEditProfileDAO(final Connection con, final User user) {
        super(con);
        this.user = user;
    }


    @Override
    protected void doAccess() throws Exception {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {

            stmt = con.prepareStatement(STATEMENT);

            stmt.setString(1, this.user.getName());
            stmt.setString(2, this.user.getSurname());
            stmt.setString(3, this.user.getSex());
            stmt.setDate(4, this.user.getDateOfBirth());
            stmt.setString(5, this.user.getNationality());
            stmt.setObject(6, jsonToPGobj(this.user.getHomeCountryAddress()));
            stmt.setString(7, this.user.getHomeCountryUniversity());
            stmt.setInt(8, this.user.getPeriodOfStay());
            stmt.setString(9, this.user.getPhoneNumber());
            stmt.setObject(10, jsonToPGobj(this.user.getPaduaAddress()));
            stmt.setString(11, this.user.getDietType());
            stmt.setArray(12, con.createArrayOf("text", Arrays.stream(this.user.getAllergies()).map(i -> String.valueOf(i)).toArray()));
            stmt.setInt(13, this.user.getId());


            stmt.executeUpdate();


            LOGGER.info("Profile successfully changed. %s", this.user.getEmail());

        } catch (SQLException e) {
            LOGGER.error("Error while editing user %d in the database. %s", this.user.getId(), e);
        }
        finally {
            if (stmt != null) {
                stmt.close();
            }

        }

    }

}
