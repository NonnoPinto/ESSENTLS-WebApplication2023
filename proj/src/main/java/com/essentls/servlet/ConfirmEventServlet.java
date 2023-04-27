package com.essentls.servlet;

import com.essentls.dao.*;
import com.essentls.resource.Event;
import com.essentls.resource.Participant;
import com.essentls.resource.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "ConfirmEventServlet", value = "/confirmEvent")
public class ConfirmEventServlet extends AbstractDatabaseServlet{

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            HttpSession session = request.getSession();
            if(request.getParameter("submitAttr") != null) {
                if (request.getParameter("eventId") != null && session.getAttribute("sessionUserId") != null){
                    int eventId = Integer.parseInt(request.getParameter("eventId"));
                    int userId = (int) session.getAttribute("sessionUserId");
                    Participant p = new ParticipantInfoDAO(getConnection(), userId, eventId).access().getOutputParam();
                    Event event = new EventInfoDAO(getConnection(), eventId).access().getOutputParam();

                    JSONObject newAttributes = new JSONObject();

                    for(String att: event.getAttributes()){
                        String value = request.getParameter("att_" + att.replace(" ",""));
                        if(value != null)
                            newAttributes.put(att, value.trim());
                    }

                    if(new ParticipantEditDAO(getConnection(), userId, eventId, newAttributes.toString()).access().getOutputParam()){
                        response.sendRedirect(request.getContextPath() + "/eventdetail?id=" + eventId);
                    }else{
                        throw new SQLException("Error during attributes edit");
                    }
                }
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    protected synchronized void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        Connection transConn = null;
        try {
            transConn = getConnection();
            transConn.setAutoCommit(false);
            if (request.getParameter("id") == null || session.getAttribute("sessionUserId") == null) {
                transConn.rollback();
                transConn.close();
                request.getRequestDispatcher("/jsp/unauthorized.jsp").forward(request, response);
            } else {
                int eventId = Integer.parseInt(request.getParameter("id"));
                int userId = (int) session.getAttribute("sessionUserId");
                boolean isOrganizer = (session.getAttribute("organizer") != null && (int)session.getAttribute("organizer") == eventId);
                Participant p = new ParticipantInfoDAO(transConn, userId, eventId).access().getOutputParam();
                if (p == null && (
                        session.getAttribute("event_" + eventId) == null ||
                                !session.getAttribute("event_" + eventId).equals("payed") ||
                                !this.startPartecipation(transConn, eventId, userId, isOrganizer))) {
                    transConn.rollback();
                    transConn.close();
                    throw new ServletException("Can't participate at the event");
                } else {
                    transConn.commit(); //Partecipation confirmed, event payed
                    transConn.close();
                    Event event = new EventInfoDAO(getConnection(), eventId).access().getOutputParam();

                    Map<String, String> attributes = new HashMap<>();
                    Map<String, String> attributeValues = new HashMap<>();
                    if(p!=null){
                        attributeValues = p.getAttributeMap();
                    }
                    String[] attributesKeys = event.getAttributes();

                    for(String att: attributesKeys){
                        attributes.put(att,attributeValues.get(att));
                    }

                    if (attributes.size() > 0) {
                        request.setAttribute("event", event);
                        request.setAttribute("attributes", attributes);
                        request.getRequestDispatcher("/jsp/askattributes.jsp").forward(request, response);
                    } else {
                        response.sendRedirect(request.getContextPath() + "/eventdetail?id=" + eventId);
                    }
                }
            }
        } catch (SQLException e) {
            try {
                if(transConn != null)
                    transConn.rollback();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            throw new ServletException(e);
        }
    }

    protected synchronized boolean startPartecipation(Connection transConn, int eventId, int userId, boolean isOrganizer) throws ServletException {
        try {
            Event event = new EventInfoDAO(transConn, eventId).access(false).getOutputParam();
            User user = new UserProfileInfoDAO(transConn, userId).access(false).getOutputParam();
            if (user == null || user.getTier() < event.getVisibility()) { //Auth check
                return false;
            } else {
                try {
                    Participant p = new Participant(user.getId(), eventId, (isOrganizer ? "Organizer" : null), new Timestamp(System.currentTimeMillis()), "{}", user);
                    p = new UserCheckEventParticipantDAO(transConn, p, (event.getPrice() > 0)).access(false).getOutputParam();
                    if (p != null) {
                        if ((new UserJoinsEventDAO(transConn, p)).access(false).getOutputParam()) {
                            return true;
                        } else {
                            throw new ServletException("Event was maybe full");
                        }
                    } else {
                        throw new ServletException("Trans error");
                    }
                } catch (SQLException e) {
                    LOGGER.error(e);
                    throw new ServletException(e);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new RuntimeException(e);
        }
    }
}
