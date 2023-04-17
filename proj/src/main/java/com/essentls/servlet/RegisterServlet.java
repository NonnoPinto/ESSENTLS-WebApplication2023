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
            //take from the request the parameters
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            Integer tier = 0;
            Date date = LocalDate.now(); //registration date
            String name = req.getParameter("first_name");
            String surname = req.getParameter("last_name");
            String sex = req.getParameter("sex");
            Date date2 = req.getParameter("birth-date"); //date of birth
            String nationality = req.getParameter("nationality");
            String homeCountryAddress = req.getParameter("home-country-address");
            String homeCountryUniversity = req.getParameter("home-country-university");
            String periodOfStay = req.getParameter("period-of-stay");
            Integer phoneNumber = req.getParameter("phone-number");
            String paduaAddress = req.getParameter("padua-address");
            String documentType = req.getParameter("document-type");
            String documentNumber = req.getParameter("document-number");
            String documentFile = req.getParameter("document-file");
            String dietType = req.getParameter("diet-type");
            String allergies = req.getParameter("allergies");
            String emailConfirmed = false;

            user= new User(email, password, tier, date, name, surname, sex, date2, nationality, homeCountryAddress, homeCountryUniversity, periodOfStay, phoneNumber, paduaAddress, documentType, documentNumber, documentFile, dietType, allergies, emailConfirmed);
            
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
                
                email = email.toLowerCase();
                LOGGER.info("User is about to be registered %s",email);
                sendCreationConfirmationEmail(user);
                // try to find the user in the database
                user = new UserLoginDAO(getConnection(),email, password).access().getOutputParam();

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
		sb.append(String.format("<p>Welcome %s,</p>%n", u.getSurname(), u.getName()));
		sb.append(String.format("<p>Your account for ESN - Erasmus Student Network Padua has been created.</p>%n"));
		sb.append(String.format("<p>Please, verify your mail by clicking the link below.</p>%n"));
        sb.append(String.format("<p><a href=\"%s\">Verify your mail</a></p>%n", "http://localhost:8080/essentls/verify?email=" + u.getEmail()));
		sb.append(String.format("<p>Best regards,<br>The ESN Padua Team</p>%n"));
		sb.append(String.format("<p>Remember, to fully enrol you must visit our office!</p>%n"));
		sb.append(String.format("<p>You can find our office at , Padua. We are opened 6.30-8.30 PM on Monday, Tuesday and Thursday!</p>%n"));
		sb.append(String.format("<a href=\"https://g.page/esnpadova\" target=\"_blank\" rel=\"noopener noreferral nofollow\" data-saferedirecturl=\"https://www.google.com/url?q=https://g.page/esnpadova&amp;source=gmail&amp;ust=1681817791169000&amp;usg=AOvVaw01EBgGYLow4nqz6_FdOcJ-\">Via Galileo Galilei, 42</a>%n"));
		sb.append(String.format("<p>We are opened 6.30-8.30 PM on Monday, Tuesday and Thursday!</p>%n"));
		sb.append(String.format("<p>MUST: bring with yourself a valid document.</p>%n"));
        sb.append(String.format(""));

		MailManager.sendMail(u.getEmail(), String.format("User %s successfully created.", u.getId()),
				sb.toString(), "text/html;charset=UTF-8");

	}

}




