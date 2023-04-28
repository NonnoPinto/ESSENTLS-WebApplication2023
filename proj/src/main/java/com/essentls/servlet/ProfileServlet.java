package com.essentls.servlet;

import com.essentls.dao.UserProfileInfoDAO;
import com.essentls.resource.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Displays the profile page of the user.
 *
 * @author Md Imran Faruck Talukder
 * @version 1.00
 * @since 1.00
 */
@WebServlet(name = "ProfileServlet", value = "/profile")
public class ProfileServlet extends AbstractDatabaseServlet {

    /**
     * Handles the HTTP {@code GET} method. Passes the user attribute to the profile page and redirects the user.
     *
     * @param request a {@link HttpServletRequest} object that contains the request the client has made of the servlet
     * @param response a {@link HttpServletResponse} object that contains the response the servlet sends to the client
     * @throws ServletException if the request for the GET could not be handled
     * @throws IOException if an input or output error is detected when the servlet handles the GET request
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //take the request uri
        LogContext.setIPAddress(request.getRemoteAddr());
        LogContext.setResource(request.getRequestURI());
        LogContext.setAction("PROFILE");

        HttpSession session = request.getSession();
        LOGGER.info("session: %s", session);
        int userId=-1;
        try{
            userId = (int) session.getAttribute("sessionUserId"); // retrieve the user id string from session
        } catch (NullPointerException e) {
            LOGGER.error("stacktrace:", e);

        }

        User user = null;
        LOGGER.info("UserId: %s", userId);

        
        try {

            user = new UserProfileInfoDAO(getConnection(), userId).access().getOutputParam();
            request.setAttribute("Users", user);
            if(user == null){
                request.getRequestDispatcher("/jsp/unauthorized.jsp").forward(request, response);
            } else {
                LOGGER.info("The Login User tier: %s", user.getTier());
            }
            request.getRequestDispatcher("/jsp/profile.jsp").forward(request, response);
        } catch (SQLException e) {
            LOGGER.error("stacktrace:", e);
        } catch (NullPointerException e) {
            LOGGER.error("stacktrace:", e);
        }
    }
}
