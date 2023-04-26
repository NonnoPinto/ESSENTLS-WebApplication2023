package com.essentls.servlet;

import com.essentls.dao.EventInfoDAO;
import com.essentls.dao.UserProfileInfoDAO;
import com.essentls.resource.Event;
import com.essentls.resource.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ProfileServlet", value = "/profile")
public class ProfileServlet extends AbstractDatabaseServlet {
    public final static String USER_SESSION_KEY = "user";

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //take the request uri
        LogContext.setIPAddress(request.getRemoteAddr());
        LogContext.setResource(request.getRequestURI());
        LogContext.setAction("PROFILE");

        HttpSession session = request.getSession();
        LOGGER.info("session: %s", session);
        long userId=-1;
        try{
            userId = (long) session.getAttribute("sessionUserId"); // retrieve the user id string from session
        } catch (NullPointerException e) {
            LOGGER.error("stacktrace:", e);

        }

        User user = null;
        LOGGER.info("UserId: %s", userId);

        
        try {

            user = new UserProfileInfoDAO(getConnection(), userId).access().getOutputParam();
            request.setAttribute("Users", user);
            if(user == null){
                request.getRequestDispatcher("/jsp/unauthorized.jsp").forward(request, response);
            } else {
                //Passing data to Payment List page
                //session.setAttribute("Users", user);
                //session.setAttribute("tier", user.getTier());
                //session.setAttribute("id", user.getId());
                LOGGER.info("The Login User tier: %s", user.getTier());
            }
            request.getRequestDispatcher("/jsp/profile.jsp").forward(request, response);
        } catch (SQLException e) {
            LOGGER.error("stacktrace:", e);
        } catch (NullPointerException e) {
            LOGGER.error("stacktrace:", e);
        }




    }
}
