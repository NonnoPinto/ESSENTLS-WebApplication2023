package com.essentls.servlet;

import com.essentls.dao.*;
import com.essentls.resource.*;
import java.sql.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet(name = "CreateEventServlet", urlPatterns = {"", "/create-event"})
public final class CreateEventServlet extends AbstractDatabaseServlet {

    /**
     * Creates a new event into the database.
    *
    * @param req the HTTP request from the client.
    * @param res the HTTP response from the server.
    *
    * @throws IOException if any error occurs in the client/server communication.
    */

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        LogContext.setIPAddress(req.getRemoteAddr());
        LogContext.setResource(req.getRequestURI());
        LogContext.setAction("CREATE EVENT");

        req.getRequestDispatcher("/jsp/eventcreation.jsp").forward(req, res);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        LogContext.setIPAddress(req.getRemoteAddr());
        LogContext.setAction("CREATE EVENT");

        // request parameters
        String name = null;
        String description = null;
        float price = -1;
        int visibility = 0;
        JSONObject location = null;
        int maxPartecipantsInternational = -1;
        int maxPartecipantVolunteer = -1;
        LocalDateTime eventStart = null;
        LocalDateTime eventEnd = null;
        LocalDateTime subscriptionStart = null;
        LocalDateTime subscriptionEnd = null;
        LocalDateTime withdrawalEnd = null;
        int maxWaitingList = -1;
        String attributes = null;
        String thumbnail = null;
        String poster = null;

        // model
        Event e = null;
        Message m = null;

        //set datetime format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        try {
            // retrieves the request parameters
            name = req.getParameter("name");
            description = req.getParameter("description");
            price = Float.parseFloat(req.getParameter("price"));
            visibility = Integer.parseInt(req.getParameter("visibility"));
            location = new JSONObject(req.getParameter("location"));
            maxPartecipantsInternational = Integer.parseInt(req.getParameter("maxParticipantsInternational"));
            maxPartecipantVolunteer = Integer.parseInt(req.getParameter("maxParticipantsVolunteer"));
            eventStart = LocalDateTime.parse(req.getParameter("eventStart"), formatter);
            eventEnd = LocalDateTime.parse(req.getParameter("eventEnd"), formatter);
            subscriptionStart = LocalDateTime.parse(req.getParameter("subscriptionStart"), formatter);
            subscriptionEnd = LocalDateTime.parse(req.getParameter("subscriptionEnd"), formatter);
            withdrawalEnd = LocalDateTime.parse(req.getParameter("withdrawalEnd"), formatter);
            maxWaitingList = Integer.parseInt(req.getParameter("maxWaitingList"));
            attributes = req.getParameter("attributes");
            thumbnail = req.getParameter("thumbnail");
            poster = req.getParameter("poster");

            // set the name of the event as the resource in the log context
            LogContext.setResource(req.getParameter("name"));

            // creates a new event from the request parameters
            e = new Event(name, description, price, visibility, location, maxPartecipantsInternational,
                maxPartecipantVolunteer, java.sql.Date.valueOf(eventStart.toLocalDate()), 
                java.sql.Date.valueOf(eventEnd.toLocalDate()), java.sql.Date.valueOf(subscriptionStart.toLocalDate()),
                java.sql.Date.valueOf(subscriptionEnd.toLocalDate()), java.sql.Date.valueOf(withdrawalEnd.toLocalDate()), 
                 maxWaitingList, attributes, thumbnail, poster);

            // creates a new object for accessing the database and stores the event
            new AdminCreateEventDAO(getConnection(), e).access();

            m = new Message(String.format("Event %d successfully created.", name));

            LOGGER.info("Event %d successfully created in the database.", name);

        } catch (NumberFormatException ex) {
            m = new Message(
                    "Cannot create the event. Invalid input parameters.",
                    "E100", ex.getMessage());

            LOGGER.error(
                    "Cannot create the event. Invalid input parameters.",
                    ex);
        } catch (SQLException ex) {
            
            m = new Message("Cannot create the event: unexpected error while accessing the database.", "E200",
                    ex.getMessage());

            LOGGER.error("Cannot create the event: unexpected error while accessing the database.", ex);
            
        }

    }

}
