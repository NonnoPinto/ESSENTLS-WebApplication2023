package com.essentls.servlet;

import com.essentls.dao.AdminUsersListDAO;
import com.essentls.resource.User;
import com.essentls.resource.Message;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.message.StringFormattedMessage;

public class SelectUserByIDServlet extends AbstractDatabaseServlet {

     /**
     @param req  //request
     @param res  //response
     @throws ServletException    //exception servlet
     @throws IOException     //exceptionIO
     */

     public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
         LogContext.setIPAddress(req.getRemoteAddr());
         LogContext.setAction("SELECT USER BY ID");

         //request parameter
         Long id;

         //model
         User u= null;
         Message m = null;

         try {
                //creates a user with the request parameter
                if(!(req.getParameter("userId").equals(""))){
                    id=Long.parseLong(req.getParameter("userId"));
                }else{
                    throw new NumberFormatException();
                }

                User user = new User(id, null, null, null, 0, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null,
                null, null, true);

                //creates a connection to the database
                try (Connection con = getConnection()) {
                    //creates a new object for the search of the user
                    AdminUsersListDAO dao = new AdminUsersListDAO(con, user);
                    //searches the user
                    dao.doAccess();
                    //gets the user
                    u = new AdminUsersListDAO(getConnection(),user).access().getOutputParam().get(0);;
                }
         } catch (SQLException e) {
             m = new Message("Database error");
             LOGGER.error(new StringFormattedMessage("Database error: %s", e.getMessage()));
         } catch (NumberFormatException e) {
             m = new Message("Invalid ID");
             LOGGER.error(new StringFormattedMessage("Invalid ID: %s", e.getMessage()));
         } catch (ArrayIndexOutOfBoundsException e) {
             m = new Message("User not found");
             LOGGER.error(new StringFormattedMessage("User not found: %s", e.getMessage()));
         }

         try{
             //stores the user in the request
             req.setAttribute("user", u);
             req.setAttribute("message", m);

             //forward the control to the edituser-form JSP
             req.getRequestDispatcher("/jsp/edituser-form.jsp").forward(req, res);
         } catch(Exception e) {
             LOGGER.error(new StringFormattedMessage("Unable to send response when selecting user by ID %s", e.getMessage()));
             throw e;
         } finally {
             LogContext.removeIPAddress();
             LogContext.removeAction();
             LogContext.removeUser();
         }

     }
}
