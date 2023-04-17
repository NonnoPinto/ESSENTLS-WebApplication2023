package com.essentls.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.essentls.dao.UserEventsListDAO;
import com.essentls.resource.Event;
import com.essentls.resource.Message;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "HomeServlet", value = "/home")
public final class HomeServlet extends AbstractDatabaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //get the current session
        HttpSession session = req.getSession();
        
        //take the request uri
        LogContext.setIPAddress(req.getRemoteAddr());
        LogContext.setResource(req.getRequestURI());
        LogContext.setAction("LOGIN");

        //get user tier from session or set the default value
        Object o = session.getAttribute("tier");
        int userTier = 0;

        if (o != null) {
            userTier = (int) o;
        }

        Message m = null;

        List<Event> events = null;

        try {

            events = new UserEventsListDAO(getConnection(), userTier).access().getOutputParam();

            LOGGER.info("Events successfully retrieved by tier %d.", userTier);

            m = new Message("Events successfully searched.");

        } catch (SQLException e) {
            
            LOGGER.error("Cannot search for events: unexpected error while accessing the database.", e);

            m = new Message(true, "Cannot search for events: unexpected error while accessing the database.");
        }

        try {
            req.setAttribute("events", events);
            req.setAttribute("message", m);
            req.getRequestDispatcher("/jsp/home.jsp").forward(req, resp);
        } catch (Exception e) {
            
            LOGGER.error("Cannot forward the request searching events by tier %d", userTier);
            throw e;
        }
        

    }
    
}
