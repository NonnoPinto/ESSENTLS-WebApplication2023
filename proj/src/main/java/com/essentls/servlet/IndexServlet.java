package com.essentls.servlet;

import com.essentls.dao.UserProfileInfoDAO;
import com.essentls.resource.Message;
import com.essentls.resource.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Redirects to the index page.
 *
 * @author Mattia Maglie
 * @version 1.00
 * @since 1.00
 */
@WebServlet(name = "IndexServlet", urlPatterns = {"", "/index"})
public final class IndexServlet extends AbstractDatabaseServlet {

    /**
     * Handles the HTTP {@code GET} method. Redirects to the index page.
     *
     * @param req a {@code HttpServletRequest} object that contains the request the client has made of the servlet
     * @param resp a {@code HttpServletResponse} object that contains the response the servlet sends to the client
     * @throws ServletException if the request for the GET could not be handled
     * @throws IOException if an input or output error is detected when the servlet handles the GET request
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //get the current session
        HttpSession session = req.getSession();

        //get user of the current session
        int userId = -1;
        User user = null;
        Message m = null;
        try {
            if(session.getAttribute("sessionUserId") != null) {
                userId = (int) session.getAttribute("sessionUserId");
                user = new UserProfileInfoDAO(getConnection(), userId).access().getOutputParam();
            }
        }
        catch (NullPointerException e){
            LOGGER.error("Cannot search the User: id is not retrieved correctly.", e);

            m = new Message(true, "Cannot search the User: unexpected error while accessing the database.");

        }
        catch (SQLException e) {
            LOGGER.error("Cannot search the User: unexpected error while accessing the database.", e);

            m = new Message(true, "Cannot search the User: unexpected error while accessing the database.");

        }
        //authentication check
        if(user != null){
            resp.sendRedirect(req.getContextPath() + "/home");
        }
        else {
            try {
                req.getRequestDispatcher("/jsp/index.jsp").forward(req, resp);
            } catch (Exception e) {
                throw e;
            }
        }

    }
    
}
