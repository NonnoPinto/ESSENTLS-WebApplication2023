package com.essentls.servlet;

import com.essentls.dao.UserJoinedEventsListDAO;
import com.essentls.dao.UserProfileInfoDAO;
import com.essentls.resource.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Lists all the events a user has joined.
 *
 * @author Mattia Maglie
 * @version 1.00
 * @since 1.00
 */
@WebServlet(name = "UserEventsListServlet", value = "/joined-events")
public final class UserEventsListServlet extends AbstractDatabaseServlet {

    /**
     * Handles the HTTP {@code GET} method. Retrieves all the events a user has joined through the participant table
     * and passes them to the joined events page.
     * @param req a {@link HttpServletRequest} object that contains the request the client has made of the servlet
     * @param resp a {@link HttpServletResponse} object that contains the response the servlet sends to the client
     * @throws ServletException if the request for the GET could not be handled
     * @throws IOException if an input or output error is detected when the servlet handles the GET request
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //get the current session
        HttpSession session = req.getSession();
        
        //take the request uri
        LogContext.setIPAddress(req.getRemoteAddr());
        LogContext.setResource(req.getRequestURI());
        LogContext.setAction("RETRIEVE EVENTS BY TIER AND TAGS");


        //get user of the current session
        int userId = -1;
        User user = null;
        Message m = null;
        
        try {
            userId = (int) session.getAttribute("sessionUserId");
        }
        catch (NullPointerException e){

            LOGGER.error("Cannot search the User: id is not retrieved correctly.", e);

            m = new Message(true, "Cannot search the User: unexpected error while accessing the database.");
        }

        try {
            user = new UserProfileInfoDAO(getConnection(), userId).access().getOutputParam();
        }
        catch(SQLException e){

            LOGGER.error("Cannot search the User: unexpected error while accessing the database.", e);

            m = new Message(true, "Cannot search the User: unexpected error while accessing the database.");
            
        }

        //authentication check
        if(user == null){
            req.getRequestDispatcher("/jsp/unauthorized.jsp").forward(req, resp);
        }
        else {

            List<Event> events = null;

            List<Tag> tags = null;

            try {
                events = new UserJoinedEventsListDAO(getConnection(), user.getId()).access().getOutputParam();

                LOGGER.info("Events successfully retrieved for user %d", user.getId());

                m = new Message("Events successfully searched.");

            } catch (SQLException e) {

                LOGGER.error("Cannot search for events: unexpected error while accessing the database.", e);

                m = new Message(true, "Cannot search for events: unexpected error while accessing the database.");
                throw new ServletException(e);

            }

            try {
                req.setAttribute("events", events);
                req.setAttribute("tags", tags);
                req.setAttribute("message", m);
                req.getRequestDispatcher("/jsp/joinedevents.jsp").forward(req, resp);
            } catch (Exception e) {

                LOGGER.error("Cannot forward the request searching events by user %d", user.getId());
                
                throw e;
            }
        }

    }
    
}
