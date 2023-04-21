package com.essentls.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.essentls.dao.EventsFromTagAndTierDAO;
import com.essentls.dao.TagsListDAO;
import com.essentls.dao.UserEventsListDAO;
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

@WebServlet(name = "HomeServlet", urlPatterns = {"", "/home"})
public final class HomeServlet extends AbstractDatabaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //get the current session
        HttpSession session = req.getSession();
        
        //take the request uri
        LogContext.setIPAddress(req.getRemoteAddr());
        LogContext.setResource(req.getRequestURI());
        LogContext.setAction("RETRIVE EVENTS BY TIER AND TAGS");

        //get user of the current session
        long userId = (long)session.getAttribute("userId");

        User user = null;

        Message m = null;

        try {

            user = new UserProfileInfoDAO(getConnection(), userId).access().getOutputParam();

        } catch (SQLException e) {

            LOGGER.error("Cannot search the User: unexpected error while accessing the database.", e);

            m = new Message(true, "Cannot search the User: unexpected error while accessing the database.");

        }

        //authentication check
        if(user == null){
            req.getRequestDispatcher("/jsp/unauthorized.jsp").forward(req, resp);
        }
        else {

            int userTier = user.getTier();

            String filterTag = req.getParameter("tag");

            List<Event> events = null;

            List<Tag> tags = null;

            try {

                if (filterTag != null)
                    events = new EventsFromTagAndTierDAO(getConnection(), new Tag(filterTag.trim()), userTier).access().getOutputParam();
                else
                    events = new UserEventsListDAO(getConnection(), userTier).access().getOutputParam();

                LOGGER.info("Events successfully retrieved by tier %d.", userTier);

                m = new Message("Events successfully searched.");

            } catch (SQLException e) {
                
                LOGGER.error("Cannot search for events: unexpected error while accessing the database.", e);

                m = new Message(true, "Cannot search for events: unexpected error while accessing the database.");
            }

            try {

                tags = new TagsListDAO(getConnection()).access().getOutputParam();

                LOGGER.info("Tags successfully retrieved in the home");

            } catch (SQLException e) {
                
                LOGGER.error("Cannot search for tags: unexpected error while accessing the database.", e);

                m = new Message(true, "Cannot search for tags: unexpected error while accessing the database.");
            }

            try {
                req.setAttribute("events", events);
                req.setAttribute("tags", tags);
                req.setAttribute("message", m);
                req.getRequestDispatcher("/jsp/home.jsp").forward(req, resp);
            } catch (Exception e) {
                
                LOGGER.error("Cannot forward the request searching events by tier %d", userTier);
                throw e;
            }
        }

    }
    
}
