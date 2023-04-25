package com.essentls.dao;

import com.essentls.resource.User;
import org.json.JSONObject;
import org.postgresql.util.PGobject;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class UserAssociationFormSubmitDAO extends AbstractDAO<User> {


    /**
     * Creates a new DAO object.
     *
     * @param con the connection to be used for accessing the database.
     */

    private static final String STATEMENT = "UPDATE public.\"Users\" SET " +
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
    public PGobject stringArrayToPGobj(String[] s) throws java.sql.SQLException{

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



    @Override
    protected void doAccess() throws Exception {
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(STATEMENT);
            stmt.setString(1, user.getCardId());
            stmt.setString(2, user.getName());
            stmt.setString(3, user.getSurname());
            stmt.setString(4, user.getSex());
            stmt.setDate(5, user.getDateOfBirth());
            stmt.setString(6, user.getNationality());
            stmt.setObject(7, jsonToPGobj(user.getHomeCountryAddress()));
            stmt.setString(8, user.getHomeCountryUniversity());
            stmt.setInt(9, user.getPeriodOfStay());
            stmt.setString(10, user.getPhoneNumber());
            stmt.setObject(11, jsonToPGobj(user.getPaduaAddress()));
            stmt.setString(12, user.getDocumentType());
            stmt.setString(13, user.getDocumentNumber());
            stmt.setString(14, user.getDocumentFile());
            stmt.setString(15, user.getDietType());
            stmt.setObject(16, user.getAllergies());

            stmt.executeUpdate();   // the update

            LOGGER.info("%s's User Association Form successfully updated.", this.user.getEmail());

        } finally {
            if (stmt != null)
                stmt.close();
        }


    }
}
