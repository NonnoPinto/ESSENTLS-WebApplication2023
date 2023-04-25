package com.essentls.servlet;

import com.essentls.dao.*;
import com.essentls.resource.*;
import com.essentls.mail.MailManager;

import com.essentls.utils.ErrorCode;
import jakarta.mail.MessagingException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.message.StringFormattedMessage;
import org.json.JSONObject;


import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

@WebServlet(name = "RegisterServlet", value = "/register")
public class RegisterServlet extends AbstractDatabaseServlet {

    //public final static String REGISTER_JSP = "/jsp/register.jsp";


    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        //take the request uri
        LogContext.setIPAddress(req.getRemoteAddr());
        LogContext.setResource(req.getRequestURI());
        LogContext.setAction("REGISTER");

        req.getRequestDispatcher("/jsp/register.jsp").forward(req, res);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        //take the request uri
        LogContext.setIPAddress(req.getRemoteAddr());
        LogContext.setResource(req.getRequestURI());
        LogContext.setAction("REGISTER");
    
        User user = null;
        Message m = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        try {
            long id = 0;
            String email = req.getParameter("email").toLowerCase();
            String password = req.getParameter("password");
            String passwordRepeated = req.getParameter("rpassword");
//            String cardId = req.getParameter("card-id");
            Integer tier = 0;
//            java.util.Date utilDate = new java.util.Date();
//            java.sql.Date registrationDate = new java.sql.Date(utilDate.getTime());//registration date
//            String name = req.getParameter("first_name");
//            String surname = req.getParameter("last_name");
//            String sex = req.getParameter("sex");
//            java.sql.Date date2 = (!req.getParameter("birth-date").isEmpty()) ? new java.sql.Date(format.parse(req.getParameter("birth-date")).getTime()): null;//registration date
//            String nationality = req.getParameter("nationality");
//            String homeCountryUniversity = req.getParameter("home-country-university");
//            JSONObject homeCountryAddress = new JSONObject();
//            String homeCountryAddressStreet = req.getParameter("padua-address-street");
//            String homeCountryAddressNumber = req.getParameter("padua-address-number");
//            String homeCountryAddressCity = req.getParameter("padua-address-city");
//            String homeCountryAddressZip = req.getParameter("padua-address-zip");
//            String homeCountryAddressCountry = req.getParameter("padua-address-country");
//            homeCountryAddress = homeCountryAddress.put("street", homeCountryAddressStreet);
//            homeCountryAddress = homeCountryAddress.put("number", homeCountryAddressNumber);
//            homeCountryAddress = homeCountryAddress.put("city", homeCountryAddressCity);
//            homeCountryAddress = homeCountryAddress.put("zip", homeCountryAddressZip);
//            homeCountryAddress = homeCountryAddress.put("country", homeCountryAddressCountry);
//            int periodOfStay = Integer.parseInt(req.getParameter("period-of-stay"));
//            String phoneNumber = req.getParameter("phone-number");
//            JSONObject paduaAddress = new JSONObject();
//            String paduaAddressStreet = req.getParameter("padua-address-street");
//            String paduaAddressNumber = req.getParameter("padua-address-number");
//            String paduaAddressCity = req.getParameter("padua-address-city");
//            String paduaAddressZip = req.getParameter("padua-address-zip");
//            String paduaAddressCountry = req.getParameter("padua-address-country");
//            paduaAddress = paduaAddress.put("street", paduaAddressStreet);
//            paduaAddress = paduaAddress.put("number", paduaAddressNumber);
//            paduaAddress = paduaAddress.put("city", paduaAddressCity);
//            paduaAddress = paduaAddress.put("zip", paduaAddressZip);
//            paduaAddress = paduaAddress.put("country", paduaAddressCountry);
//            String documentType = req.getParameter("document-type");
//            String documentNumber = req.getParameter("document-number");
//            String documentFile = req.getParameter("document-file");
//            String dietType = req.getParameter("diet-type");
//            String[] allergies = req.getParameter("allergies").split(",");
            String emailHash = email.hashCode()+"";//TODO: hashme
            Boolean emailConfirmed = false;

            if (email == null || email.equals("")) {
                ErrorCode ec = ErrorCode.EMAIL_MISSING;
                res.setStatus(ec.getHTTPCode());
                m = new Message(true, "Email field cannot be empty.");
    
                req.setAttribute("message", m);
                req.getRequestDispatcher("/jsp/register.jsp").forward(req, res);
    
            } else if (password == null || password.equals("")) {
                ErrorCode ec = ErrorCode.PASSWORD_MISSING;
                res.setStatus(ec.getHTTPCode());
                m = new Message(true, "The password is missing.");
                req.setAttribute("message", m);
                req.getRequestDispatcher("/jsp/register.jsp").forward(req, res);

            } else if (!password.equals(passwordRepeated)) {
                ErrorCode ec = ErrorCode.WRONG_INTERVALS;
                res.setStatus(ec.getHTTPCode());
                m = new Message(true, "The two passwords must coincide.");
                req.setAttribute("message", m);
                req.getRequestDispatcher("/jsp/register.jsp").forward(req, res);
            } else {
//            user= new User(id, email, password, cardId, tier, registrationDate, name, surname, sex, date2,
//                    nationality, homeCountryAddress, homeCountryUniversity, periodOfStay, phoneNumber, paduaAddress,
//                    documentType, documentNumber, documentFile, dietType, allergies, emailHash, emailConfirmed);
                user= new User(id,email,password,null,tier,null,null,null,null,
                        null,null,null,null,0,
                        null,null,null,null,null,
                        null,null,emailHash,emailConfirmed);


                //uncomment when smtp service is set
            //sendCreationConfirmationEmail(user);
            //LOGGER.info("Creation confirmation email for user %s successfully sent.", user.getEmail());

            //m = new Message(String.format("user %s successfully created and confirmation email successfully sent.", user.getEmail()));
            // try to find the user in the database
            LOGGER.info("user %s is trying to register",email);
            new UserRegistrationDAO(getConnection(), user).doAccess();

            }

        } catch (NumberFormatException ex) {
            m = new Message(
                    "Cannot create the user. Invalid input parameters.",
                    "E100", ex.getMessage());

            LOGGER.error(
                    "Cannot create the user. Invalid input parameters.",
                    ex);
//        } catch (ParseException ex) {
//            m = new Message(String.format("Unable to set the date.",
//                    user.getEmail()));
//
//            LOGGER.warn(new StringFormattedMessage(
//                    "User %s successfully created but unable to send confirmation email.", user.getEmail()), ex);
        /*
        } catch (MessagingException ex) {
            m = new Message(String.format("User %s successfully created but unable to send confirmation email.",
                    user.getEmail()));
            LOGGER.warn(new StringFormattedMessage(
                    "User %s successfully created but unable to send confirmation email.", user.getEmail()), ex);
            throw new ServletException(ex);
        */

        } catch (final SQLException e) {
            LOGGER.error("Exception SQL happened.", e);
            m = new Message(true, "Is not possible to register this user. Please try to fill the form again, if problem persist contact us!");
            req.setAttribute("message", m);
            req.getRequestDispatcher("/jsp/register.jsp").forward(req, res);
        }
    }

    private void sendCreationConfirmationEmail(User u) throws MessagingException {

        final StringBuilder sb = new StringBuilder();
        //TODO: test this
        sb.append(String.format("<p>Welcome %s,</p>%n", u.getSurname(), u.getName()));
        sb.append(String.format("<p>Your account for ESN - Erasmus Student Network Padua has been created.</p>%n"));
        sb.append(String.format("<p>Please, verify your mail by clicking the link below.</p>%n"));
        sb.append(String.format("<p><a href=\"%s\">Verify your mail</a></p>%n", "http://localhost:8080/essentls/verify?email=" + u.getEmail()));
        sb.append(String.format("<p>Best regards,<br>The ESN Padua Team</p>%n"));
        sb.append(String.format("<p>Remember, to fully enrol you must visit our office!</p>%n"));
        sb.append(String.format("<p>You can find our office at , Padua. We are opened 6.30-8.30 PM on Monday, Tuesday and Thursday!</p>%n"));
        sb.append(String.format("<a href=\"https://g.page/esnpadova\" target=\"_blank\" rel=\"noopener noreferral nofollow\" data-saferedirecturl=\"https://www.google.com/url?q=https://g.page/esnpadova&amp;source=gmail&amp;ust=1681817791169000&amp;usg=AOvVaw01EBgGYLow4nqz6_FdOcJ-\">Via Galileo Galilei, 42</a>%n"));
        sb.append(String.format("<p>We are opened 6.30-8.30 PM on Monday, Tuesday and Thursday!</p>%n"));
        sb.append(String.format("<p>MUST: bring with yourself a valid document.</p>%n"));
        sb.append(String.format(""));

        MailManager.sendMail(u.getEmail(), String.format("User %s successfully created.", u.getId()),
                sb.toString(), "text/html;charset=UTF-8");

    }
}




