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
        String city;
        String street;
        String number;
        int maxPartecipantsInternational = -1;
        int maxPartecipantVolunteer = -1;
        LocalDateTime eventStart = null;
        LocalDateTime eventEnd = null;
        LocalDateTime subscriptionStart = null;
        LocalDateTime subscriptionEnd = null;
        LocalDateTime withdrawalEnd = null;
        int maxWaitingList = -1;
        String[] attributes = null;
        String thumbnail = null;
        String poster = null;

        // model
        Event e = null;
        Message m = null;

        //set datetime format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

        try {
            // retrieves the request parameters
            name = req.getParameter("name");
            description = req.getParameter("description");
            price = Float.parseFloat(req.getParameter("price"));
            visibility = Integer.parseInt(req.getParameter("visibility"));
            city= req.getParameter("city");
            street= req.getParameter("street");
            number= req.getParameter("number");
            maxPartecipantsInternational = Integer.parseInt(req.getParameter("maxParticipantsInternational"));
            maxPartecipantVolunteer = Integer.parseInt(req.getParameter("maxParticipantsVolunteer"));
            eventStart = LocalDateTime.parse(req.getParameter("eventStart"), formatter);
            eventEnd = LocalDateTime.parse(req.getParameter("eventEnd"), formatter);
            subscriptionStart = LocalDateTime.parse(req.getParameter("subscriptionStart"), formatter);
            subscriptionEnd = LocalDateTime.parse(req.getParameter("subscriptionEnd"), formatter);
            withdrawalEnd = LocalDateTime.parse(req.getParameter("withdrawalEnd"), formatter);
            maxWaitingList = Integer.parseInt(req.getParameter("maxWaitingList"));
            attributes = req.getParameter("attributes").replace(", ",",").split(",");
            thumbnail = req.getParameter("thumbnail");
            poster = req.getParameter("poster");

            // set the name of the event as the resource in the log context
            LogContext.setResource(req.getParameter("name"));

            location = new JSONObject();
            location = location.put("city", city);
            location = location.put("street", street);
            location = location.put("number", number);

            LOGGER.info("The location is:  \""+location.toString()+"\"");

            // creates a new event from the request parameters
            e = new Event(name, description, price, visibility, location, maxPartecipantsInternational,
                maxPartecipantVolunteer, Timestamp.valueOf(eventStart), Timestamp.valueOf(eventEnd),
                Timestamp.valueOf(subscriptionStart), Timestamp.valueOf(subscriptionEnd), Timestamp.valueOf(withdrawalEnd),
                maxWaitingList, attributes, thumbnail, poster);

            // creates a new object for accessing the database and stores the event
            new AdminCreateEventDAO(getConnection(), e).access();

            m = new Message(String.format("Event \""+e.getName()+"\" successfully created."));

            LOGGER.info("Event \""+e.getName()+"\" successfully created in the database.");

            req.setAttribute("message", m);
            req.getRequestDispatcher("/jsp/eventcreation.jsp").forward(req, res);

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
