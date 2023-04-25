package com.essentls.servlet;

import com.essentls.dao.EventInfoDAO;
import com.essentls.dao.UserJoinsEventDAO;
import com.essentls.dao.UserProfileInfoDAO;
import com.essentls.resource.Event;
import com.essentls.resource.Participant;
import com.essentls.resource.Payment;
import com.essentls.resource.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;

@WebServlet(name = "JoinEventServlet", value = "/joinEvent")
public class JoinEventServlet extends AbstractDatabaseServlet{


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            if(session.getAttribute("transConn") != null) {
                Connection transConn = (Connection) session.getAttribute("transConn");
                if (request.getParameter("id") == null || session.getAttribute("userId") == null) {
                    request.getRequestDispatcher("/jsp/unauthorized.jsp").forward(request, response);
                } else {
                    long eventId = Long.parseLong(request.getParameter("id"));
                    long userId = (long) session.getAttribute("userId");
                    transConn.commit(); //Partecipation confirmed, event payed
                    Event event = new EventInfoDAO(getConnection(), eventId).access().getOutputParam();
                    String[] attributes = event.getAttributes();
                    request.setAttribute("event", event);
                    request.setAttribute("attributes", attributes);
                    request.getRequestDispatcher("/jsp/home.jsp").forward(request, response);
                }
           }
        }catch(SQLException e){
            throw new ServletException(e);
        }
    }
}
