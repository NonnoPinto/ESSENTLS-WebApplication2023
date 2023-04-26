package com.essentls.servlet;

import com.essentls.dao.EventsFromTagAndTierDAO;
import com.essentls.dao.TagsListDAO;
import com.essentls.dao.UserEventsListDAO;
import com.essentls.dao.UserProfileInfoDAO;
import com.essentls.resource.Event;
import com.essentls.resource.Message;
import com.essentls.resource.Tag;
import com.essentls.resource.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "IndexServlet", urlPatterns = {"", "/index"})
public final class IndexServlet extends AbstractDatabaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //get the current session
        HttpSession session = req.getSession();

        //get user of the current session
        int userId = -1;
        User user = null;
        Message m = null;
        try {
            if(session.getAttribute("sessionUserId") != null) {
                userId = (int) session.getAttribute("sessionUserId");
                user = new UserProfileInfoDAO(getConnection(), userId).access().getOutputParam();
            }
        }
        catch (NullPointerException e){
            LOGGER.error("Cannot search the User: id is not retrieved correctly.", e);

            m = new Message(true, "Cannot search the User: unexpected error while accessing the database.");

        }
        catch (SQLException e) {
            LOGGER.error("Cannot search the User: unexpected error while accessing the database.", e);

            m = new Message(true, "Cannot search the User: unexpected error while accessing the database.");

        }
        //authentication check
        if(user != null){
            resp.sendRedirect(req.getContextPath() + "/home");
        }
        else {
            try {
                req.getRequestDispatcher("/jsp/index.jsp").forward(req, resp);
            } catch (Exception e) {
                throw e;
            }
        }

    }
    
}
