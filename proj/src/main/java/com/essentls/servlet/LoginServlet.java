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


@WebServlet(name = "LoginServlet", value = "/login/")
public class LoginServlet extends AbstractDatabaseServlet {

    //public final static String LOGIN_JSP = "/jsp/login.jsp";



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
    
            LOGGER.info("user {} is trying to login",email);
    
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
                if (user == null){
                    LOGGER.info("User null %s",email);
                    //if not, tell it to the user, forward an error message
                    ErrorCode ec = ErrorCode.WRONG_CREDENTIALS;
                    res.setStatus(ec.getHTTPCode());
                    m = new Message(true, "Credentials are wrong");
                    req.setAttribute("message", m);
                    req.getRequestDispatcher("/jsp/login.jsp").forward(req, res);
                }
                else{
                    // activate a session to keep the user data
                    HttpSession session = req.getSession();
                    session.setAttribute("user", user);
                    session.setAttribute("tier", user.getTier());
                    LOGGER.info("the USER {} LOGGED IN",user.getEmail());
    //
                    // login credentials were correct: we redirect the user to the profile page
                    // now the session is active and its data can used to change the profile page
                    res.sendRedirect(req.getContextPath()+"/profile");
    
    //                    req.getRequestDispatcher("/jsp/user/home.jsp").forward(req, res);
                }
            }
        } catch (SQLException e){
            //something unexpected happened: we write it into the LOGGER
            //writeError(res, ErrorCode.INTERNAL_ERROR);
            LOGGER.error("stacktrace:", e);
        }
    }


}




