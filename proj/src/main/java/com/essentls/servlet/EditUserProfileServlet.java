package com.essentls.servlet;

import com.essentls.dao.AdminEditUserDAO;
import com.essentls.dao.AdminUsersListDAO;
import com.essentls.dao.UserEditProfileDAO;
import com.essentls.dao.UserProfileInfoDAO;
import com.essentls.resource.Message;
import com.essentls.resource.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.core.jackson.Log4jJsonObjectMapper;
import org.apache.logging.log4j.message.StringFormattedMessage;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;

@WebServlet(name = "EditUserProfileServlet", value = "/edit-profile")
public class EditUserProfileServlet extends AbstractDatabaseServlet {

    public final static String USER_SESSION_KEY = "edit-profile";

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        //take the req uri
        LogContext.setIPAddress(req.getRemoteAddr());
        LogContext.setResource(req.getRequestURI());
        LogContext.setAction("EDIT_PROFILE");

        HttpSession session = req.getSession();
        LOGGER.info("session: %s", session);
        int userId=-1;
        try{
            userId = (int) session.getAttribute("sessionUserId"); // retrieve the user id string from session
            LOGGER.info("userId: %s", userId);
        } catch (NullPointerException e) {
            LOGGER.error("stacktrace:", e);
        }

        User user = null;
        LOGGER.info("UserId: %s", userId);




        try {

            user = new UserProfileInfoDAO(getConnection(), userId).access().getOutputParam();
            req.setAttribute("user", user);

            // req parameters
            int id = -1;
            String email = null;
            String password = null;
            String cardId = null;
            int tier = user.getTier();
            Date registrationDate = null;
            String name = null;
            String surname = null;
            String sex = null;
            Date dateOfBirth = null;
            String nationality = null;
            JSONObject homeCountryAddress = user.getHomeCountryAddress();
            String homeCountryUniversity = null;
            int periodOfStay = 0;
            String phoneNumber = null;
            JSONObject paduaAddress = user.getPaduaAddress();
            String documentType = null;
            String documentNumber = null;
            String documentFile = null;
            String dietType = null;
            String[] allergies = null;
            String emailHash = user.getEmailHash();
            boolean emailConfirmed = user.getEmailConfirmed();

            // model
            User u = null;
            Message m = null;

            //set datetime format
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

            try{
                //retrieves the req parameters
                id = Integer.parseInt(req.getParameter("userId"));
                email = req.getParameter("userEmail");
                password = req.getParameter("userPassword");
                cardId = req.getParameter("userCardId");
                //tier will be removed
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
//                homeCountryAddress = new JSONObject()
//                        .put("street", req.getParameter("userHomeCountryAddress-street"))
//                        .put("number", req.getParameter("userHomeCountryAddress-number"))
//                        .put("city", req.getParameter("userHomeCountryAddress-city"))
//                        .put("zip", req.getParameter("userHomeCountryAddress-zip"))
//                        .put("country", req.getParameter("userHomeCountryAddress-country"));
                homeCountryUniversity = req.getParameter("userHomeCountryUniversity");
                periodOfStay = Integer.parseInt(req.getParameter("userPeriodOfStay"));
                phoneNumber = req.getParameter("userPhoneNumber");
//                paduaAddress = new JSONObject()
//                        .put("street", req.getParameter("userPaduaAddress-street"))
//                        .put("number", req.getParameter("userPaduaAddress-number"))
//                        .put("city", req.getParameter("userPaduaAddress-city"))
//                        .put("zip", req.getParameter("userPaduaAddress-zip"))
//                        .put("country", req.getParameter("userPaduaAddress-country"));
                documentType = req.getParameter("userDocumentType");
                documentNumber = req.getParameter("userDocumentNumber");
                documentFile = req.getParameter("userDocumentFile");
                dietType = req.getParameter("userDietType");
                allergies = req.getParameter("userAllergies").replace(", ",",").split(",");
//                emailHash = req.getParameter("userEmailHash");

                //emailConfirmed will be removed

                //creates a new user from the req parameters
                u = new User(
                        id, email, password, cardId, tier, registrationDate, name, surname, sex, dateOfBirth,
                        nationality, homeCountryAddress, homeCountryUniversity, periodOfStay, phoneNumber, paduaAddress, documentType,
                        documentNumber, documentFile, dietType, allergies, emailHash, emailConfirmed);
                //creates a new object for accessing the database and updates the user
                new UserEditProfileDAO(getConnection(), u).access();

                m = new Message(String.format("User %d successfully updated.", u.getId()));

                LOGGER.info("User %d successfully updated.", u.getId());


            } catch (NumberFormatException e) {
                m = new Message("Invalid req parameters.");
                LOGGER.error("Invalid req parameters.", e);
            } catch (SQLException e) {
                m = new Message("Database access error.");
                LOGGER.error("Database access error.",e);
            }




            if(user == null){
                req.getRequestDispatcher("/jsp/unauthorized.jsp").forward(req, res);
            } else {
                LOGGER.info("User : %s", user);
                LOGGER.info("User tier: %s", user.getName());
            }
            req.getRequestDispatcher("/jsp/editprofile.jsp").forward(req, res);
        } catch (SQLException e) {
            LOGGER.error("stacktrace:", e);
        } catch (NullPointerException e) {
            LOGGER.error("stacktrace:", e);
        }




    }

}
