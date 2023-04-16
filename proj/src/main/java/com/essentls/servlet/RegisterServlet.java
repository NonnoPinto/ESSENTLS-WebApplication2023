package com.essentls.servlet;

import com.essentls.dao.*;
import com.essentls.mail.MailManager;
import com.essentls.resource.*;
import com.essentls.utils.ErrorCode;


import jakarta.mail.MessagingException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.message.StringFormattedMessage;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

@WebServlet(name = "RegisterServlet", value = "/register/")
public class RegisterServlet extends AbstractDatabaseServlet {

    //public final static String REGISTER_JSP = "/jsp/register.jsp";



    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        //take the request uri
        LogContext.setIPAddress(req.getRemoteAddr());
        LogContext.setResource(req.getRequestURI());
        LogContext.setAction("REGISTER");
    
        User user = null;
        Message m = null;
    
        try {
            //take from the request, the parameters (email and password)
            String email = req.getParameter("email");
            String password = req.getParameter("password");
    
            LOGGER.info("user {} is trying to register",email);
    
            if (email == null || email.equals("")) {
                //the email is null (was not set on the parameters) or an empty string
                //notify this to the user
                ErrorCode ec = ErrorCode.EMAIL_MISSING;
                res.setStatus(ec.getHTTPCode());
                m = new Message(true, "missing email");
    
                //we used jsp for the register page: thus we forward the request
                req.setAttribute("message", m);
                req.getRequestDispatcher("/jsp/register.jsp").forward(req, res);
    
            } else if (password == null || password.equals("")) {
                //the password was empty
                ErrorCode ec = ErrorCode.PASSWORD_MISSING;
                res.setStatus(ec.getHTTPCode());
                m = new Message(true, "missing password");
                req.setAttribute("message", m);
                req.getRequestDispatcher("/jsp/register.jsp").forward(req, res);
    
            } else{
                //the email and password are not null, we can try to register the user and set the value email confirmation to false until the user confirms the email
                //TODO: email confirmation
                //TODO: user registration

                email = email.toLowerCase();
                LOGGER.info("User registration email %s",email);




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

    private void sendCreationConfirmationEmail(User u) throws MessagingException {

		final StringBuilder sb = new StringBuilder();
        //TODO: test this
		sb.append(String.format("<p>Dear %s,</p>%n", u.getSurname(), u.getName()));
		sb.append(String.format("<p>Your account for ESN - Erasmus Student Network Padua has been successfully created as follows:</p>%n"));
		sb.append(String.format("<ul>%n"));
		sb.append(String.format("<li><b>surname</b>: %s</li>%n", u.getSurname()));
		sb.append(String.format("<li><b>age</b>: %d</li>%n", u.getAge()));
		sb.append(String.format("</ul>%n"));
		sb.append(String.format("<p>You can modify those informations in your profile page!</p>%n"));
		sb.append(String.format("<p>Best regards,<br>The ESN Padua Team</p>%n"));
		sb.append(String.format("<p>Remember, to fully enrol you must visit our office!</p>%n"));
		sb.append(String.format("<p>MUST: bring with yourself a valid document.</p>%n"));
        //TODO: add office address, opening hours and phone number
        //TODO: write this message according to the ESN style

		MailManager.sendMail(e.getEmail(), String.format("User %s successfully created.", e.getId()),
				sb.toString(), "text/html;charset=UTF-8");

	}

}




