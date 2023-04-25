package com.essentls.servlet;

import com.essentls.dao.UserEventsListDAO;
import com.essentls.dao.UserJoinedEventsListDAO;
import com.essentls.dao.UserProfileInfoDAO;
import com.essentls.resource.Event;
import com.essentls.resource.Message;
import com.essentls.resource.Tag;
import com.essentls.resource.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "UserEventsListServlet", urlPatterns = {"", "/joined-events"})
public final class UserEventsListServlet extends AbstractDatabaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //get the current session
        HttpSession session = req.getSession();
        
        //take the request uri
        LogContext.setIPAddress(req.getRemoteAddr());
        LogContext.setResource(req.getRequestURI());
        LogContext.setAction("RETRIVE EVENTS BY TIER AND TAGS");


        //get user of the current session
        long userId = -1;
        User user = null;
        Message m = null;
        try {
            userId = (long) session.getAttribute("sessionUserId");
            user = new UserProfileInfoDAO(getConnection(), userId).access().getOutputParam();
        }
        catch (NullPointerException e){
            LOGGER.error("Cannot search the User: id is not retrieved correctly.", e);

            m = new Message(true, "Cannot search the User: unexpected error while accessing the database.");

        }
        catch(SQLException e){
            LOGGER.error("Database error", e);
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
