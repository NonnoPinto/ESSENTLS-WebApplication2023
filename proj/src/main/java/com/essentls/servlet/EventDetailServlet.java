package com.essentls.servlet;

import com.essentls.dao.*;
import com.essentls.resource.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "EventDetailServlet", value = "/eventdetail")
public class EventDetailServlet extends AbstractDatabaseServlet {

    public final static String USER_SESSION_KEY = "user";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //PrintWriter out = response.getWriter();

        // write the HTML page
        /*out.printf("test: " + Integer.getInteger(request.getParameter("id").trim()));
        out.flush();
        out.close();*/
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
                boolean currentIsPartecipating = false;
                for (Participant p : participants) {
                    if (!p.getRole().equals("WaitingList")) {
                        if(p.getUserId() == user.getId())
                            currentIsPartecipating = true;
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
                request.setAttribute("currentIsPartecipating", currentIsPartecipating);
                request.getRequestDispatcher("/jsp/eventdetail.jsp").forward(request, response);
            }
        } catch (Exception e) {
            LOGGER.error("stacktrace:", e);
            throw new ServletException(e);
        }

    }

}






