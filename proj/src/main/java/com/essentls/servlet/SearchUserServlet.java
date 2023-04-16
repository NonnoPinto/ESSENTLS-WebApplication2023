package com.essentls.servlet;


import com.essentls.dao.AdminUsersListDAO;
import com.essentls.resource.User;
import com.essentls.resource.Message;

//eventualmente spostare logContext in resources

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.message.StringFormattedMessage;

import javax.swing.*;

public final class SearchUserServlet extends AbstractDatabaseServlet{

    /**
    @param req
    @param res
    @throws ServletException
    @throws IOException
    */

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        LogContext.setIPAddress(req.getRemoteAddr());
        LogContext.setAction("SEARCH USER");

        //request parameter
        String name="";

        //model
        List<User> ul= null;
        Message m = null;

        try{
            //retrieves the request parameter
            name = req.getParameter("userName");

            //creates a new object for accessing the database and searching the users
            ul= new AdminUsersListDAO(getConnection(),name).access().getOutputParam();

            m= new Message("Users succesfully searched");

            LOGGER.info("Users succesfully searched by name %s.", name);

        }catch (SQLException ex){
            m=new Message("Cannot search for users. Unexpected error while accessing the database", "E200", ex.getMessage());

            LOGGER.error("Cannot search for users. Unexpected error while accessing the database", ex);
        }


        try{
            //stores the users list and the message as a request attribute
            req.setAttribute("userList",ul);
            req.setAttribute("message",m);

            //forward the control to the userlist-form JSP

            req.getRequestDispatcher("/jsp/userlist-form.jsp").forward(req,res);

        }catch (Exception ex){
            LOGGER.error(new StringFormattedMessage("Unable to send response when creating user %s", name), ex);
            throw ex;
        }finally {
            LogContext.removeIPAddress();
            LogContext.removeAction();
            LogContext.removeUser();
        }
    }
}
