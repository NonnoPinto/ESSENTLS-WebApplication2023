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
            //take from the request the parameters
            long id = 0;
            String email = req.getParameter("email").toLowerCase();
            String password = req.getParameter("password");
            String passwordRepeated = req.getParameter("rpassword");
            String cardId = req.getParameter("card-id");
            Integer tier = 0;
            java.util.Date utilDate = new java.util.Date();
            java.sql.Date registrationDate = new java.sql.Date(utilDate.getTime());//registration date
            String name = req.getParameter("first_name");
            String surname = req.getParameter("last_name");
            String sex = req.getParameter("sex");
            java.sql.Date date2 = new java.sql.Date(format.parse(req.getParameter("birth-date")).getTime());//registration date
            String nationality = req.getParameter("nationality");
            //String homeCountryAddress = req.getParameter("home-country-address");
            JSONObject homeCountryAddress = new JSONObject();
            String homeCountryAddressProvince = req.getParameter("home-country-address-province");
            String homeCountryAddressCity = req.getParameter("home-country-address-city");
            String homeCountryAddressStreet = req.getParameter("home-country-address-street");
            String homeCountryUniversity = req.getParameter("home-country-university");
            homeCountryAddress = homeCountryAddress.put("province", homeCountryAddressProvince);
            homeCountryAddress = homeCountryAddress.put("city", homeCountryAddressCity);
            homeCountryAddress = homeCountryAddress.put("street",  homeCountryAddressStreet);
            int periodOfStay = Integer.parseInt(req.getParameter("period-of-stay"));
            String phoneNumber = req.getParameter("phone-number");
            JSONObject paduaAddress = new JSONObject();
            String paduaAddressProvince = req.getParameter("padua-address-province");
            String paduaAddressCity = req.getParameter("padua-address-city");
            String paduaAddressStreet = req.getParameter("padua-address-street");
            paduaAddress = paduaAddress.put("city", paduaAddressCity);
            paduaAddress = paduaAddress.put("street",  paduaAddressStreet);
            paduaAddress = paduaAddress.put("province", paduaAddressProvince);
            String documentType = req.getParameter("document-type");
            String documentNumber = req.getParameter("document-number");
            String documentFile = req.getParameter("document-file");
            String dietType = req.getParameter("diet-type");
            String[] allergies = req.getParameter("allergies").split(",");
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
            user= new User(id, email, password, cardId, tier, registrationDate, name, surname, sex, date2,
                    nationality, homeCountryAddress, homeCountryUniversity, periodOfStay, phoneNumber, paduaAddress,
                    documentType, documentNumber, documentFile, dietType, allergies, emailHash, emailConfirmed);


            //sendCreationConfirmationEmail(user);
            //LOGGER.info("Creation confirmation email for user %s successfully sent.", user.getEmail());

            m = new Message(String.format("user %s successfully created and confirmation email successfully sent.",
                    user.getEmail()));
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
        } catch (ParseException ex) {
            m = new Message(String.format("Unable to set the date.",
                    user.getEmail()));

            LOGGER.warn(new StringFormattedMessage(
                    "User %s successfully created but unable to send confirmation email.", user.getEmail()), ex);
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




