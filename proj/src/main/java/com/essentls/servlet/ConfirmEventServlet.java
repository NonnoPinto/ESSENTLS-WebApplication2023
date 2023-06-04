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


/**
 * Processes the event join request of a user to make sure there is availability in the event and then inserts the
 * participant in the database.
 *
 * @author Mattia Maglie
 * @version 1.00
 * @since 1.00
 */

@WebServlet(name = "ConfirmEventServlet", value = "/confirmEvent")
public class ConfirmEventServlet extends AbstractDatabaseServlet{

    /**
     * Handles the HTTP {@code POST} method. Once the user participation is confirmed
     * retrieves the event id , the user id and the values of the attributes from the request and uses the
     * {@link ParticipantEditDAO} to add these attribute values to the participant in the database.
     *
     * @param request  a {@link HttpServletRequest} object that contains the request the client has made of the servlet
     * @param response a {@link HttpServletResponse} object that contains the response the servlet sends to the client
     *
     * @throws ServletException if the request for the POST could not be handled
     * @throws IOException      if an input or output error is detected when the servlet handles the POST request
     */

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

    /**
     * Handles the HTTP {@code GET} method. Checks if the eventual payment is confirmed and then calls the
     * {@Link #startParticipation(Connection, int, int, boolean)} method to try to insert the participant in the database.
     *
     * @param request  a {@link HttpServletRequest} object that contains the request the client has made of the servlet
     * @param response a {@link HttpServletResponse} object that contains the response the servlet sends to the client
     *
     * @throws ServletException if the request for the GET could not be handled
     * @throws IOException      if an input or output error is detected when the servlet handles the GET request
     */
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
                                !this.startParticipation(transConn, eventId, userId, isOrganizer))) {
                    transConn.rollback();
                    transConn.close();
                    throw new ServletException("Can't participate at the event");
                } else {
                    transConn.commit(); //Participation confirmed, event payed
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

    /**
     * Checks if there is availability in the event and then inserts the participant in the database.
     *
     * @param transConn a {@link Connection} object that contains the connection to the database
     * @param eventId a {@code int} that contains the id of the event
     * @param userId a {@code int} that contains the id of the user
     * @param isOrganizer a {@code boolean} that contains the value of the organizer flag
     * @return a {@code boolean} that contains the value of the operation result
     *
     * @throws ServletException if the request for the GET could not be handled
     */
    protected synchronized boolean startParticipation(Connection transConn, int eventId, int userId, boolean isOrganizer) throws ServletException {
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
