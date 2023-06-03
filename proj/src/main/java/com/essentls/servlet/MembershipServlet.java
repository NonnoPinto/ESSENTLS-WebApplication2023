package com.essentls.servlet;

import com.essentls.dao.UploadUserDocumentDAO;
import com.essentls.dao.UserMembershipDAO;
import com.essentls.dao.UserProfileInfoDAO;
import com.essentls.resource.Message;
import com.essentls.resource.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import org.apache.logging.log4j.message.StringFormattedMessage;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Edits a user into the database in the instance where the user is making a membership request.
 *
 * @author Alessandro Borsato
 * @version 1.00
 * @since 1.00
 */
@MultipartConfig
@WebServlet(name = "MembershipServlet", value = "/membership")
public class MembershipServlet extends AbstractDatabaseServlet {

    /**
     * Handles the HTTP {@code GET} method. Retrieves the session user tier and redirects to the membership form
     * submission page if the user is tier 0, otherwise redirects to the profile page.
     *
     * @param req a {@link HttpServletRequest} object that contains the request the client has made of the servlet.
     * @param res a {@link HttpServletResponse} object that contains the response the servlet sends to the client.
     * @throws ServletException if the request for the GET could not be handled.
     * @throws IOException if any error occurs in the client/server communication.
     */

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        //take the request uri
        LogContext.setIPAddress(req.getRemoteAddr());
        LogContext.setResource(req.getRequestURI());
        LogContext.setAction("MEMBERSHIP");

        HttpSession session = req.getSession();
        int userId = -1;
        try {
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

            int userTier = user.getTier();

            if(userTier > 0) {
                res.sendRedirect(req.getContextPath() + "/profile");
            }else {
                if(user.getCardId() != null && user.getCardId().trim().length() > 0)
                    req.getRequestDispatcher("/jsp/membership-result.jsp").forward(req,res);
                else
                    req.getRequestDispatcher("/jsp/membership.jsp").forward(req, res);
            }

        } catch (SQLException e) {
            LOGGER.error("stacktrace:", e);
            throw new ServletException(e);
        }
    }

    /**
     * Handles the HTTP {@code POST} method. Retrieves the session user id and tier, and the membership form data.
     * If the user is tier 0, the membership request is submitted to the database, otherwise the user is redirected
     * to the profile page.
     *
     * @param req a {@link HttpServletRequest} object that contains the request the client has made of the servlet.
     * @param res a {@link HttpServletResponse} object that contains the response the servlet sends to the client.
     * @throws ServletException if the request for the POST could not be handled.
     * @throws IOException if any error occurs in the client/server communication.
     */
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
            int userTier = (int) req.getSession().getAttribute("sessionUserTier");

            if(userTier > 0){
                res.sendRedirect(req.getContextPath() + "/profile");
            }else {
                // String cardId = req.getParameter("card-id");
                String cardId = "A0000"; // PLACEHOLDER
                Integer tier = 0;
                java.util.Date utilDate = new java.util.Date();
                java.sql.Date registrationDate = new java.sql.Date(utilDate.getTime());//registration date
                String name = req.getParameter("first_name");
                String surname = req.getParameter("last_name");
                String sex = req.getParameter("sex");
                java.sql.Date date2 = (!req.getParameter("birth-date").isEmpty()) ? new java.sql.Date(format.parse(req.getParameter("birth-date")).getTime()) : null;//registration date
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
                String dietType = req.getParameter("diet-type");
                String[] allergies = req.getParameter("allergies").split(",");
                //String emailHash = email.hashCode()+"";
                Boolean emailConfirmed = true;

                Part documentBytesPart = req.getPart("document-bytes");
                byte[] documentBytes = documentBytesPart.getInputStream().readAllBytes();

                user = new User(id, null, null, null, tier, registrationDate, name, surname, sex, date2,
                        nationality, homeCountryAddress, homeCountryUniversity, periodOfStay, phoneNumber, paduaAddress,
                        documentType, documentNumber, null, dietType, allergies, null, emailConfirmed);

                // try to find the user in the database
                LOGGER.info("user %s is trying to membership", user.getEmail());
                if(new UserMembershipDAO(getConnection(), user).access().getOutputParam() != null) {
                    req.getSession().setAttribute("sessionUserTier", 1);
                }

                if(documentBytes.length > 0) {
                    user.setDocumentBytes(documentBytes);
                    new UploadUserDocumentDAO(getConnection(), user).access().getOutputParam();
                    user.setDocumentBytes(null);
                }
            }

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




