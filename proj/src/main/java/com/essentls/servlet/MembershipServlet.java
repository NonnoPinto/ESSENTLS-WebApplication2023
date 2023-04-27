package com.essentls.servlet;

import com.essentls.dao.UserMembershipDAO;
import com.essentls.resource.Message;
import com.essentls.resource.User;
import com.essentls.utils.ErrorCode;
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

@WebServlet(name = "MembershipServlet", value = "/membership")
public class MembershipServlet extends AbstractDatabaseServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        //take the request uri
        LogContext.setIPAddress(req.getRemoteAddr());
        LogContext.setResource(req.getRequestURI());
        LogContext.setAction("MEMBERSHIP");

        req.getRequestDispatcher("/jsp/membership.jsp").forward(req, res);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        //take the request uri
        LogContext.setIPAddress(req.getRemoteAddr());
        LogContext.setResource(req.getRequestURI());
        LogContext.setAction("MEMBERSHIP");
    
        User user = null;
        Message m = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        try {
            int id = (int) req.getSession().getAttribute("sessionUserId");
//            String email = req.getParameter("email").toLowerCase();
//            String password = req.getParameter("password");
//            String passwordRepeated = req.getParameter("rpassword");
            String cardId = req.getParameter("card-id");
            Integer tier = 0;
            java.util.Date utilDate = new java.util.Date();
            java.sql.Date registrationDate = new java.sql.Date(utilDate.getTime());//registration date
            String name = req.getParameter("first_name");
            String surname = req.getParameter("last_name");
            String sex = req.getParameter("sex");
            java.sql.Date date2 = (!req.getParameter("birth-date").isEmpty()) ? new java.sql.Date(format.parse(req.getParameter("birth-date")).getTime()): null;//registration date
            String nationality = req.getParameter("nationality");
            String homeCountryUniversity = req.getParameter("home-country-university");
            JSONObject homeCountryAddress = new JSONObject();
            String homeCountryAddressStreet = req.getParameter("padua-address-street");
            String homeCountryAddressNumber = req.getParameter("padua-address-number");
            String homeCountryAddressCity = req.getParameter("padua-address-city");
            String homeCountryAddressZip = req.getParameter("padua-address-zip");
            String homeCountryAddressCountry = req.getParameter("padua-address-country");
            homeCountryAddress = homeCountryAddress.put("street", homeCountryAddressStreet);
            homeCountryAddress = homeCountryAddress.put("number", homeCountryAddressNumber);
            homeCountryAddress = homeCountryAddress.put("city", homeCountryAddressCity);
            homeCountryAddress = homeCountryAddress.put("zip", homeCountryAddressZip);
            homeCountryAddress = homeCountryAddress.put("country", homeCountryAddressCountry);
            int periodOfStay = Integer.parseInt(req.getParameter("period-of-stay"));
            String phoneNumber = req.getParameter("phone-number");
            JSONObject paduaAddress = new JSONObject();
            String paduaAddressStreet = req.getParameter("padua-address-street");
            String paduaAddressNumber = req.getParameter("padua-address-number");
            String paduaAddressCity = req.getParameter("padua-address-city");
            String paduaAddressZip = req.getParameter("padua-address-zip");
            String paduaAddressCountry = req.getParameter("padua-address-country");
            paduaAddress = paduaAddress.put("street", paduaAddressStreet);
            paduaAddress = paduaAddress.put("number", paduaAddressNumber);
            paduaAddress = paduaAddress.put("city", paduaAddressCity);
            paduaAddress = paduaAddress.put("zip", paduaAddressZip);
            paduaAddress = paduaAddress.put("country", paduaAddressCountry);
            String documentType = req.getParameter("document-type");
            String documentNumber = req.getParameter("document-number");
            String documentFile = req.getParameter("document-file");
            String dietType = req.getParameter("diet-type");
            String[] allergies = req.getParameter("allergies").split(",");
            //String emailHash = email.hashCode()+"";//TODO: hashme
            Boolean emailConfirmed = false;

            user= new User(id, null, null, cardId, tier, registrationDate, name, surname, sex, date2,
                    nationality, homeCountryAddress, homeCountryUniversity, periodOfStay, phoneNumber, paduaAddress,
                    documentType, documentNumber, documentFile, dietType, allergies, null, emailConfirmed);

            // try to find the user in the database
            LOGGER.info("user %s is trying to membership",user.getEmail());
            new UserMembershipDAO(getConnection(), user).doAccess();

        } catch (NumberFormatException ex) {
            m = new Message(
                    "Cannot create the user. Invalid input parameters.",
                    "E100", ex.getMessage());

            LOGGER.error(
                    "Cannot create the user. Invalid input parameters.",
                    ex);

        } catch (final SQLException e) {
            LOGGER.error("Exception SQL happened.", e);
            m = new Message(true, "Is not possible to membership this user. Please try to fill the form again, if problem persist contact us!");
            req.setAttribute("message", m);
            req.getRequestDispatcher("/jsp/membership.jsp").forward(req, res);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        try{
            //stores the users list and the message as a request attribute
            req.setAttribute("user", user);
            req.setAttribute("message", m);

            //forward the control to the userlist-form JSP

            req.getRequestDispatcher("/jsp/membership-result.jsp").forward(req,res);

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




