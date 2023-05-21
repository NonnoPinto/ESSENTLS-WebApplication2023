package com.essentls.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.essentls.dao.*;
import com.essentls.resource.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Retrieves the list of events visible to the user and redirects to the home page.
 *
 * @author Vittorio Cardillo
 * @version 1.00
 * @since 1.00
 */
@WebServlet(name = "HomeServlet", value = "/home")
public final class HomeServlet extends AbstractDatabaseServlet {

    /**
     * Handles the HTTP {@code GET} method. Retrieves the list of events visible to the user and redirects to the home
     * page.
     *
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
            if (filterTag == null) filterTag = "";
            else filterTag = filterTag.trim();


            int id = -1;
            String filterCause = req.getParameter("cause");
            if (!(filterCause == null || "".equals(filterCause))) id = Integer.parseInt(filterCause);


            String filterSrch=req.getParameter("srch");
            if(filterSrch == null) filterSrch="";
            else filterSrch=filterSrch.trim();

            List<Event> events = new ArrayList<>();

            List<Tag> tags = null;
            List<Cause> causes = null;
            Cause cause = null;
            Map<Integer,Boolean> isOrganizer = new HashMap<>();

            try {

                if (true) //tag name is "" if not filtered, "<filter>" otherwise. cause id is -1 if not filtered, <id> otherwise, cause name is always "". search is "" if not filtered, "<filter>" otherwise
                    events = new EventsFromTagAndTierDAO(getConnection(), new Tag(filterTag), userTier,new Cause(id,""),filterSrch.toLowerCase()).access().getOutputParam();

                else
                    events = new UserEventsListDAO(getConnection(), userTier).access().getOutputParam();

                for(Event e: events){
                    List<Participant> participantsList = new AdminParticipantsListDAO(getConnection(), e.getId()).access().getOutputParam();
                    isOrganizer.put(e.getId(), false);
                    for(Participant p: participantsList){
                        if(p.getRole().equals("Organizer")){
                            isOrganizer.put(e.getId(), p.getUserId() == userId);
                        }
                    }
                }

                LOGGER.info("Events successfully retrieved by tier %d.", userTier);

                m = new Message("Events successfully searched.");

            } catch (SQLException e) {
                
                LOGGER.error("Cannot search for events: unexpected error while accessing the database.", e);

                m = new Message(true, "Cannot search for events: unexpected error while accessing the database.");
                throw new ServletException(e);
            }

            try {

                tags = new TagsListDAO(getConnection(), "").access().getOutputParam();
                causes = new CausesListDAO(getConnection(),-1,"").access().getOutputParam();

                if (id == -1)
                    cause = new Cause(-1, "");
                else
                    cause = new CausesListDAO(getConnection(),id,"").access().getOutputParam().get(0);

                LOGGER.info("Tags and causes successfully retrieved in the home");

            } catch (SQLException e) {
                
                LOGGER.error("Cannot search for tags and causes: unexpected error while accessing the database.", e);

                m = new Message(true, "Cannot search for tags and causes: unexpected error while accessing the database.");
            }

            try {
                req.setAttribute("tag", filterTag);
                req.setAttribute("events", events);
                req.setAttribute("tags", tags);
                req.setAttribute("cause", cause);
                req.setAttribute("srch", filterSrch);
                req.setAttribute("causes", causes);
                req.setAttribute("message", m);
                req.setAttribute("isOrganizer", isOrganizer);
                req.getRequestDispatcher("/jsp/home.jsp").forward(req, resp);
            } catch (Exception e) {
                
                LOGGER.error("Cannot forward the request searching events by tier %d", userTier);
                throw e;
            }
        }

    }
    
}
