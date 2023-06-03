package com.essentls.servlet;

import com.essentls.dao.UserEditProfileDAO;
import com.essentls.dao.UserProfileInfoDAO;
import com.essentls.resource.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

/**
 * Servlet that handles the user changing his own profile information.
 *
 * @author Vaidas Lenartavicius
 * @version 1.00
 * @since 1.00
 */
@WebServlet(name = "EditUserProfileServlet", value = "/edit-profile")
public class EditUserProfileServlet extends AbstractDatabaseServlet {

    /**
     * Handles the HTTP {@code GET} method. Retrieves the user information from the database and redirects to the edit
     * profile page.
     *
     * @param req a {@link HttpServletRequest} object that contains the request the client has made of the servlet
     * @param res a {@link HttpServletResponse} object that contains the response the servlet sends to the client
     * @throws ServletException if the request for the GET could not be handled
     * @throws IOException if an input or output error is detected when the servlet handles the GET request
     */
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        //take the req uri
        LogContext.setIPAddress(req.getRemoteAddr());
        LogContext.setResource(req.getRequestURI());
        LogContext.setAction("EDIT_PROFILE");

        HttpSession session = req.getSession();
        LOGGER.info("session: %s", session);
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

            RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/editprofile.jsp");
            dispatcher.forward(req, res);

        } catch (SQLException e) {
            LOGGER.error("stacktrace:", e);
            throw new ServletException(e);
        }
    }

    /**
     * Handles the HTTP {@code POST} method. Retrieves the user information from the request and updates the database.
     *
     * @param req a {@link HttpServletRequest} object that contains the request the client has made of the servlet
     * @param res a {@link HttpServletResponse} object that contains the response the servlet sends to the client
     * @throws ServletException if the request for the POST could not be handled
     * @throws IOException if an input or output error is detected when the servlet handles the POST request
     */
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

        try {

            user = new UserProfileInfoDAO(getConnection(), userId).access().getOutputParam();
            req.setAttribute("user", user);

        } catch (SQLException e) {
            LOGGER.error("stacktrace:", e);
            throw new ServletException(e);
        }

        JSONObject homeCountryAddress = new JSONObject();
        JSONObject paduaAddress = new JSONObject();

        user.setName(req.getParameter("userName"));
        user.setSurname(req.getParameter("userSurname"));
        user.setSex(req.getParameter("userSex"));
        user.setDateOfBirth(Date.valueOf(req.getParameter("userDateOfBirth")));
        user.setNationality(req.getParameter("userNationality"));
        homeCountryAddress.put("street", req.getParameter("userHomeCountryAddress-street"));
        homeCountryAddress.put("number", req.getParameter("userHomeCountryAddress-number"));
        homeCountryAddress.put("city", req.getParameter("userHomeCountryAddress-city"));
        homeCountryAddress.put("zip", req.getParameter("userHomeCountryAddress-zip"));
        homeCountryAddress.put("country", req.getParameter("userHomeCountryAddress-country"));
        user.setHomeCountryAddress(homeCountryAddress);
        user.setHomeCountryUniversity(req.getParameter("userHomeCountryUniversity"));
        user.setPeriodOfStay(Integer.parseInt(req.getParameter("userPeriodOfStay")));
        user.setPhoneNumber(req.getParameter("userPhoneNumber"));
        paduaAddress.put("street", req.getParameter("userPaduaAddress-street"));
        paduaAddress.put("number", req.getParameter("userPaduaAddress-number"));
        paduaAddress.put("city", req.getParameter("userPaduaAddress-city"));
        paduaAddress.put("zip", req.getParameter("userPaduaAddress-zip"));
        paduaAddress.put("country", req.getParameter("userPaduaAddress-country"));
        user.setPaduaAddress(paduaAddress);
        user.setDietType(req.getParameter("userDietType"));
        user.setAllergies(req.getParameter("userAllergies").replace(", ",",").replace(" ,",",").split(","));

        try {
            new UserEditProfileDAO(getConnection(), user).access();
            req.setAttribute("Users", user);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/profile.jsp");
            dispatcher.forward(req, res);
        } catch (SQLException e) {
            LOGGER.error("stacktrace:", e);
            throw new ServletException(e);
        }

    }

}
