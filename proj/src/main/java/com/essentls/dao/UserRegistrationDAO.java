package com.essentls.dao;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
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
public class UserRegistrationDAO extends AbstractDAO  {
    private static final String STATEMENT_REGISTRATION = "INSERT INTO public.\"Users\"(" +
            "email, password, \"cardID\", tier, \"registrationDate\", name, surname, sex, \"dateOfBirth\", " +
            "nationality, \"homeCountryAddress\", \"homeCountryUniversity\", \"periodOfStay\", \"phoneNumber\", " +
            "\"paduaAddress\", \"documentType\", \"documentNumber\", \"documentFile\", \"dietType\", allergies, " +
            "\"emailHash\", \"emailConfirmed\")" +
            "VALUES (?, ?, ?, 0, ?, ?, ?, CAST(? as gen), ?, ?, ?, ?, ?, ?, ?, CAST (? as identity), ?, ?, CAST (? as diet), ?, ?, ?);";


    private final User user;

    /**
     * Convert a JSONObject to a PGobject, format that can be recognized by the Postgres DB.
     */
    public PGobject jsonToPGobj(JSONObject j) throws java.sql.SQLException{
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
            //stmt.setObject(5, jsonToPGobj(this.event.getLocation()));

            pstmt.setObject(10,  jsonToPGobj(user.getHomeCountryAddress()));
            pstmt.setString(11, user.getHomeCountryUniversity());
            //pstmt.setInt(12, user.getPeriodOfStay());
            pstmt.setInt(12, 2);
            pstmt.setString(13, user.getPhoneNumber());
            pstmt.setObject(14, jsonToPGobj(user.getPaduaAddress()));
            pstmt.setString(15, user.getDocumentType());
            pstmt.setString(16, user.getDocumentNumber());
            pstmt.setString(17, user.getDocumentFile());
            pstmt.setString(18, user.getDietType());
            String[] allergies = user.getAllergies();
            Object[] values = Arrays.stream(allergies).map(i -> String.valueOf(i)).toArray();
            Array array = con.createArrayOf("text", values);
            pstmt.setArray(19, array);
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
