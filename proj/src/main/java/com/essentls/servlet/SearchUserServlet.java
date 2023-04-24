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
    @param req  //request
    @param res  //response
    @throws ServletException    //exception servlet
    @throws IOException     //exceptionIO
    */

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        LogContext.setIPAddress(req.getRemoteAddr());
        LogContext.setAction("SEARCH USER");

        //request parameter
        String name=null;
        String surname=null;
        Long id;
        String cardId=null;
        String email=null;
        //model
        List<User> ul= null;
        Message m = null;

        try{
            //creates a user with the  request parameter
            if(!(req.getParameter("userId").equals(""))){
                id=Long.parseLong(req.getParameter("userId"));
            }else{
                id= (long) -1;
            }

            if(!(req.getParameter("userName").equals(""))){
                name=req.getParameter("userName");
            }

            if(!(req.getParameter("userSurname").equals(""))){
                surname=req.getParameter("useSurname");
            }

            if(!(req.getParameter("userCardId").equals(""))){
                cardId=req.getParameter("userCardId");
            }

            if(!(req.getParameter("userEmail").equals(""))){
                email=req.getParameter("userEmail");
            }

            User user = new User(id, email,null, cardId,0,null, name, surname,
                    null, null,null,null,null,0,
                    null,null,null,null,null,null,
                    null, null, true);

            //creates a new object for accessing the database and searching the users
            ul= new AdminUsersListDAO(getConnection(),user).access().getOutputParam();

            m= new Message("Users succesfully searched");


            LOGGER.info("Users succesfully searched by " + name + id);

        }catch (SQLException ex){
            m=new Message("Cannot search for users. Unexpected error while accessing the database", "E200", ex.getMessage());

            LOGGER.error("Cannot search for users. Unexpected error while accessing the database", ex);
        }


        try{
            //stores the users list and the message as a request attribute
            req.setAttribute("userList",ul);
            req.setAttribute("message",m);

            //forward the control to the userlist-form JSP

            req.getRequestDispatcher("/jsp/userlist-results.jsp").forward(req,res);

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
