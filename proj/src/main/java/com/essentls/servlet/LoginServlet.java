package com.essentls.servlet;

import com.essentls.dao.*;
import com.essentls.resource.*;
import com.essentls.utils.ErrorCode;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Logs in the user.
 *
 * @author Andrea Campagnol
 * @version 1.00
 * @since 1.00
 */
@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends AbstractDatabaseServlet {

    /**
     * Handles the HTTP {@code GET} method. If the user is not logged in, redirects to the login page, otherwise
     * redirects to the home page.
     *
     * @param req a {@link HttpServletRequest} object that contains the request the client has made of the servlet
     * @param res a {@link HttpServletResponse} object that contains the response the servlet sends to the client
     * @throws ServletException if the request for the GET could not be handled
     * @throws IOException if an input or output error is detected when the servlet handles the GET request
     */
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        LogContext.setIPAddress(req.getRemoteAddr());
        LogContext.setResource(req.getRequestURI());
        LogContext.setAction("LOGIN");

        HttpSession session = req.getSession();
        LOGGER.info("session %s:", session);
        if (session.getAttribute("sessionUserId") == null){
            req.getRequestDispatcher("/jsp/login.jsp").forward(req, res);
        }
        else{
            res.sendRedirect(req.getContextPath() + "/home");
        }
    }

    /**
     * Handles the HTTP {@code POST} method. Authenticates the user and redirects to the home page if successful,
     * otherwise redirects to the login page.
     *
     * @param req a {@link HttpServletRequest} object that contains the request the client has made of the servlet
     * @param res a {@link HttpServletResponse} object that contains the response the servlet sends to the client
     * @throws ServletException if the request for the POST could not be handled
     * @throws IOException if an input or output error is detected when the servlet handles the POST request
     */
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        //take the request uri
        LogContext.setIPAddress(req.getRemoteAddr());
        LogContext.setResource(req.getRequestURI());
        LogContext.setAction("LOGIN");
    
        User user = null;
        Message m = null;
    
        try {
            //take from the request, the parameters (email and password)
            String email = req.getParameter("email");
            String password = req.getParameter("password");
    
            LOGGER.info("user %s is trying to login",email);
    
            if (email == null || email.equals("")) {
                //the email is null (was not set on the parameters) or an empty string
                //notify this to the user
                ErrorCode ec = ErrorCode.EMAIL_MISSING;
                res.setStatus(ec.getHTTPCode());
                m = new Message(true, "missing email");
    
                //we used jsp for the login page: thus we forward the request
                req.setAttribute("message", m);
                req.getRequestDispatcher("/jsp/login.jsp").forward(req, res);
    
            } else if (password == null || password.equals("")) {
                //the password was empty
                ErrorCode ec = ErrorCode.PASSWORD_MISSING;
                res.setStatus(ec.getHTTPCode());
                m = new Message(true, "missing password");
                req.setAttribute("message", m);
                req.getRequestDispatcher("/jsp/login.jsp").forward(req, res);
    
            } else{
                //try to authenticate the user
                email = email.toLowerCase();
                LOGGER.info("User %s",email);
                // try to find the user in the database
                user = new UserLoginDAO(getConnection(),email, password).access().getOutputParam();

    
                //the UserLoginDAO will tell us if the email exists and the password
                //matches
                if (user == null) {
                    LOGGER.info("User null %s", email);
                    //if not, tell it to the user, forward an error message
                    ErrorCode ec = ErrorCode.WRONG_CREDENTIALS;
                    res.setStatus(ec.getHTTPCode());
                    m = new Message(true, "Credentials are wrong");
                    req.setAttribute("message", m);
                    req.getRequestDispatcher("/jsp/login.jsp").forward(req, res);
                }else if(!user.getEmailConfirmed()){
                    m = new Message(true, "Mail must be confirmed");
                    req.setAttribute("message", m);
                    req.getRequestDispatcher("/jsp/login.jsp").forward(req, res);
                } else{
                    // activate a session to keep the user data
                    HttpSession session = req.getSession();
                    session.setAttribute("sessionUserId", user.getId());
                    session.setAttribute("sessionUserTier", user.getTier());
                    LOGGER.info("the USER %s LOGGED IN",user.getEmail());

                    // login credentials were correct: we redirect the user to the profile page
                    // now the session is active and its data can used to change the profile page
                    res.sendRedirect(req.getContextPath()+"/home");
    
                }
            }
        } catch (SQLException e){
            //something unexpected happened: we write it into the LOGGER
            LOGGER.error("stacktrace:", e);
            throw new ServletException(e);
        }
    }


}




