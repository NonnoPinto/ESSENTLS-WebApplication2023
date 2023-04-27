package com.essentls.servlet;

import com.essentls.dao.*;
import com.essentls.resource.*;

import java.io.InputStream;
import java.sql.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import org.apache.logging.log4j.message.StringFormattedMessage;
import org.json.JSONObject;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
@MultipartConfig
public class EditUserServlet extends AbstractDatabaseServlet {

    /**
     * Creates a new event into the database.
     *
     * @param req the HTTP request from the client.
     * @param res the HTTP response from the server.
     *
     * @throws IOException if any error occurs in the client/server communication.
     */

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

        LogContext.setIPAddress(req.getRemoteAddr());
        LogContext.setAction("EDIT USER");

        // request parameters
        int id = -1;
        String email = null;
        String password = null;
        String cardId = null;
        int tier = 0;
        Date registrationDate = null;
        String name = null;
        String surname = null;
        String sex = null;
        Date dateOfBirth = null;
        String nationality = null;
        JSONObject homeCountryAddress = null;
        String homeCountryUniversity = null;
        int periodOfStay = 0;
        String phoneNumber = null;
        JSONObject paduaAddress = null;
        String documentType = null;
        String documentNumber = null;
        String documentFile = null;
        String dietType = null;
        String[] allergies = null;
        String emailHash = null;
        boolean emailConfirmed = false;

        // model
        User u = null;
        Message m = null;

        //set datetime format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

        try{
            //retrieves the request parameters
            id = Integer.parseInt(req.getParameter("userId"));
            email = req.getParameter("userEmail");
            password = req.getParameter("userPassword");
            cardId = req.getParameter("userCardId");
            try {
            tier = Integer.parseInt(req.getParameter("userTier"));}
            catch (Exception e){
                tier = 0;
            }
            try {
            registrationDate = Date.valueOf(req.getParameter("userRegistrationDate"));}
            catch (Exception e){
                registrationDate = null;
            }
            name = req.getParameter("userName");
            surname = req.getParameter("userSurname");
            sex = req.getParameter("userSex");
            try {
            dateOfBirth = Date.valueOf(req.getParameter("userDateOfBirth"));}
            catch (Exception e){
                dateOfBirth = null;
            }
            nationality = req.getParameter("userNationality");
            homeCountryAddress = new JSONObject()
                    .put("street", req.getParameter("userHomeCountryAddress-street"))
                    .put("number", req.getParameter("userHomeCountryAddress-number"))
                    .put("city", req.getParameter("userHomeCountryAddress-city"))
                    .put("zip", req.getParameter("userHomeCountryAddress-zip"))
                    .put("country", req.getParameter("userHomeCountryAddress-country"));
            homeCountryUniversity = req.getParameter("userHomeCountryUniversity");
            periodOfStay = Integer.parseInt(req.getParameter("userPeriodOfStay"));
            phoneNumber = req.getParameter("userPhoneNumber");
            paduaAddress = new JSONObject()
                    .put("street", req.getParameter("userPaduaAddress-street"))
                    .put("number", req.getParameter("userPaduaAddress-number"))
                    .put("city", req.getParameter("userPaduaAddress-city"))
                    .put("zip", req.getParameter("userPaduaAddress-zip"))
                    .put("country", req.getParameter("userPaduaAddress-country"));
            documentType = req.getParameter("userDocumentType");
            documentNumber = req.getParameter("userDocumentNumber");
            documentFile = req.getParameter("userDocumentFile");
            dietType = req.getParameter("userDietType");
            allergies = req.getParameter("userAllergies").replace(", ",",").split(",");
            emailHash = req.getParameter("userEmailHash");


            Part documentBytesPart = req.getPart("userDocumentBytes");
            byte[] documentBytes = null;

            if (documentBytesPart != null) {

                InputStream documentBytesStream = documentBytesPart.getInputStream();

                documentBytes = new byte[10*1024*1024];

                documentBytesStream.read(documentBytes);
            }



            try {
            emailConfirmed = Boolean.parseBoolean(req.getParameter("userEmailConfirmed"));}
            catch (Exception e){
                emailConfirmed = false;
            }

            //creates a new user from the request parameters
            u = new User(
                    id, email, password, cardId, tier, registrationDate, name, surname, sex, dateOfBirth,
                    nationality, homeCountryAddress, homeCountryUniversity, periodOfStay, phoneNumber, paduaAddress, documentType,
                    documentNumber, documentFile, dietType, allergies, emailHash, emailConfirmed);
            //creates a new object for accessing the database and updates the user
            new AdminEditUserDAO(getConnection(), u).access();

            m = new Message(String.format("User %d successfully updated.", u.getId()));

            LOGGER.info("User %d successfully updated.", u.getId());

            u = new AdminUsersListDAO(getConnection(), u).access().getOutputParam().get(0);

            if(documentBytes != null) {
                u.setDocumentBytes(documentBytes);
            	new UploadUserDocumentDAO(getConnection(), u).access();
                u.setDocumentBytes(null);
            }

        } catch (NumberFormatException e) {
            m = new Message("Invalid request parameters.");
            LOGGER.error("Invalid request parameters.", e);
        } catch (SQLException e) {
            m = new Message("Database access error.");
            LOGGER.error("Database access error.",e);
        }

        try{
            //stores the users list and the message as a request attribute
            req.setAttribute("user", u);
            req.setAttribute("message", m);

            //forward the control to the userlist-form JSP

            req.getRequestDispatcher("/jsp/edituser-result.jsp").forward(req,res);

        }catch (Exception ex){
            LOGGER.error(new StringFormattedMessage("Unable to send response when creating user"), ex);
            throw ex;
        }finally {
            LogContext.removeIPAddress();
            LogContext.removeAction();
            LogContext.removeUser();
        }

    }

}
