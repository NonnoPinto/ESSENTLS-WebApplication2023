package com.essentls.servlet;

import com.essentls.dao.*;
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
 * Retrieves the list of participants for an event and redirects to the event participants page.
 *
 * @author Mattia Maglie
 * @version 1.00
 * @since 1.00
 */
@WebServlet(name = "EventParticipantsServlet", value = "/eventparticipants")
public class EventParticipantsServlet extends AbstractDatabaseServlet {

    /**
     * Handles the HTTP {@code GET} method. Retrieves the list of participants for an event and redirects to the event
     * participants page.
     *
     * @param request a {@link HttpServletRequest} object that contains the request the client has made of the servlet
     * @param response a {@link HttpServletResponse} object that contains the response the servlet sends to the client
     * @throws ServletException if the request for the GET could not be handled
     * @throws IOException if an input or output error is detected when the servlet handles the GET request
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        try {
            int eventId = Integer.parseInt(request.getParameter("id").trim());
            Event event = new EventInfoDAO(getConnection(), eventId).access().getOutputParam();
            if(event==null){
                request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
                return;
            }
            List<Participant> participants = new AdminParticipantsListDAO(getConnection(), eventId).access().getOutputParam();
            request.setAttribute("event", event);
            request.setAttribute("participants", participants);
            request.getRequestDispatcher("/jsp/eventparticipants.jsp").forward(request, response);
        } catch (SQLException e) {
            LOGGER.error("stacktrace:", e);
            throw new ServletException(e);
        }
    }

}






