package com.essentls.servlet;

import com.essentls.dao.*;
import com.essentls.resource.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

/**
 * Retrieves the details of an event from the database and redirects to the event detail page.
 *
 * @author Mattia Maglie
 * @version 1.00
 * @since 1.00
 */
@WebServlet(name = "EventDetailServlet", value = "/eventdetail")
public class EventDetailServlet extends AbstractDatabaseServlet {

    /**
     * Handles the HTTP {@code GET} method. Retrieves the details of an event from the database and redirects to the
     * event detail page.
     *
     * @param request a {@link HttpServletRequest} object that contains the request the client has made of the servlet
     * @param response a {@link HttpServletResponse} object that contains the response the servlet sends to the client
     * @throws ServletException if the request for the GET could not be handled
     * @throws IOException if an input or output error is detected when the servlet handles the GET request
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Integer eventId = Integer.parseInt(request.getParameter("id").trim());
        try {
            Event e = new EventInfoDAO(getConnection(),eventId).access().getOutputParam();
            HttpSession session = request.getSession();
            int userId = -1;
            if(session.getAttribute("sessionUserId") != null)
                userId = (int)session.getAttribute("sessionUserId");
            User user = new UserProfileInfoDAO(getConnection(), userId).access().getOutputParam();
            if(user == null || user.getTier() < e.getVisibility()){ //Auth check
                request.getRequestDispatcher("/jsp/unauthorized.jsp").forward(request, response);
            }else {
                List<Participant> participants = new AdminParticipantsListDAO(getConnection(), eventId).access().getOutputParam();
                int nParticipants = 0;
                int nWaiting = 0;
                boolean currentIsWaiting = false;
                boolean currentIsParticipating = false;
                boolean canEditEvent = false;

                //Tier 4 can always edit event
                if (user.getTier() == 4){
                    canEditEvent = true;
                }

                for (Participant p : participants) {
                    if (!p.getRole().equals("WaitingList")) {
                        if(p.getUserId() == user.getId()){
                            currentIsParticipating = true;
                            //if tier < 4 can edit only if is organizer
                            if (!canEditEvent && user.getTier() >= 2 && p.getRole().equals("Organizer")){
                                canEditEvent = true;
                            }
                        }
                        nParticipants++;
                    } else {
                        if(p.getUserId() == user.getId())
                            currentIsWaiting = true;
                        nWaiting++;
                    }
                }
                request.setAttribute("event", e);
                request.setAttribute("nParticipants", nParticipants);
                request.setAttribute("nWaiting", nWaiting);
                request.setAttribute("currentIsWaiting", currentIsWaiting);
                request.setAttribute("currentIsParticipating", currentIsParticipating);
                request.setAttribute("canEditEvent", canEditEvent);
                request.getRequestDispatcher("/jsp/eventdetail.jsp").forward(request, response);
            }
        } catch (Exception e) {
            LOGGER.error("stacktrace:", e);
            throw new ServletException(e);
        }

    }

}






