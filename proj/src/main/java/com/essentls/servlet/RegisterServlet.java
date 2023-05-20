package com.essentls.servlet;

import com.essentls.dao.*;
import com.essentls.resource.*;
import com.essentls.mail.MailManager;

import com.essentls.utils.ErrorCode;
import jakarta.mail.MessagingException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.message.StringFormattedMessage;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

/**
 * Handles the registration of a new user.
 *
 * @author Alessandro Borsato
 * @version 1.00
 * @since 1.00
 */
@WebServlet(name = "RegisterServlet", value = "/register")
public class RegisterServlet extends AbstractDatabaseServlet {

    /**
     * Handles the HTTP {@code GET} method. Redirects to the registration page.
     *
     * @param req a {@link HttpServletRequest} object that contains the request the client has made of the servlet.
     * @param res a {@link HttpServletResponse} object that contains the response the servlet sends to the client.
     * @throws ServletException if the request for the GET could not be handled.
     * @throws IOException if an input or output error is detected when the servlet handles the GET request.
     */
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        //take the request uri
        LogContext.setIPAddress(req.getRemoteAddr());
        LogContext.setResource(req.getRequestURI());
        LogContext.setAction("REGISTER");

        req.getRequestDispatcher("/jsp/register.jsp").forward(req, res);
    }

    /**
     * Handles the HTTP {@code POST} method. Registers a new user and redirects to the home page if successful,
     * otherwise redirects to the registration page.
     * @param req a {@link HttpServletRequest} object that contains the request the client has made of the servlet.
     * @param res
     * @throws ServletException
     * @throws IOException
     */
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        //take the request uri
        LogContext.setIPAddress(req.getRemoteAddr());
        LogContext.setResource(req.getRequestURI());
        LogContext.setAction("REGISTER");
    
        User user = null;
        Message m = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        try {
            int id = 0;
            String email = req.getParameter("email").toLowerCase();
            String password = req.getParameter("password");
            String passwordRepeated = req.getParameter("rpassword");
            Integer tier = 0;
            String emailHash = email.hashCode()+"";
            Boolean emailConfirmed = false;

            if (email == null || email.equals("")) {
                ErrorCode ec = ErrorCode.EMAIL_MISSING;
                res.setStatus(ec.getHTTPCode());
                m = new Message(true, "Email field cannot be empty.");
    
                req.setAttribute("message", m);
                req.getRequestDispatcher("/jsp/register.jsp").forward(req, res);
    
            } else if (password == null || password.equals("")) {
                ErrorCode ec = ErrorCode.PASSWORD_MISSING;
                res.setStatus(ec.getHTTPCode());
                m = new Message(true, "The password is missing.");
                req.setAttribute("message", m);
                req.getRequestDispatcher("/jsp/register.jsp").forward(req, res);

            } else if (!password.equals(passwordRepeated)) {
                ErrorCode ec = ErrorCode.WRONG_INTERVALS;
                res.setStatus(ec.getHTTPCode());
                m = new Message(true, "The two passwords must coincide.");
                req.setAttribute("message", m);
                req.getRequestDispatcher("/jsp/register.jsp").forward(req, res);
            } else {
                String password_sha1 = DigestUtils.sha1Hex(password);
                emailConfirmed = true; //Forcing confirmation until mail works
                user= new User(id,email,password_sha1,null,tier,null,null,null,null,
                        null,null,null,null,0,
                        null,null,null,null,null,
                        null,null,emailHash,emailConfirmed);

                //uncomment when smtp service is set
                // sendCreationConfirmationEmail(user);
                //LOGGER.info("Creation confirmation email for user %s successfully sent.", user.getEmail());

                //m = new Message(String.format("user %s successfully created and confirmation email successfully sent.", user.getEmail()));
                // try to find the user in the database
                LOGGER.info("user %s is trying to register",email);
                new UserRegistrationDAO(getConnection(), user).doAccess();

            }

        } catch (NumberFormatException ex) {
            m = new Message(
                    "Cannot create the user. Invalid input parameters.",
                    "E100", ex.getMessage());

            LOGGER.error(
                    "Cannot create the user. Invalid input parameters.",
                    ex);
//        } catch (ParseException ex) {
//            m = new Message(String.format("Unable to set the date.",
//                    user.getEmail()));
//
//            LOGGER.warn(new StringFormattedMessage(
//                    "User %s successfully created but unable to send confirmation email.", user.getEmail()), ex);
        /*
        } catch (MessagingException ex) {
            m = new Message(String.format("User %s successfully created but unable to send confirmation email.",
                    user.getEmail()));
            LOGGER.warn(new StringFormattedMessage(
                    "User %s successfully created but unable to send confirmation email.", user.getEmail()), ex);
            throw new ServletException(ex);
        */

        } catch (final SQLException e) {
            LOGGER.error("Exception SQL happened.", e);
            m = new Message(true, "Is not possible to register this user. Please try to fill the form again, if problem persist contact us!");
            req.setAttribute("message", m);
            req.getRequestDispatcher("/jsp/register.jsp").forward(req, res);
//        } catch (MessagingException e) {
//            throw new RuntimeException(e);
        }
        try{
            //stores the users list and the message as a request attribute
            req.setAttribute("user", user);
            req.setAttribute("message", m);

            //forward the control to the userlist-form JSP

            req.getRequestDispatcher("/jsp/register-result.jsp").forward(req,res);

        }catch (Exception ex){
            LOGGER.error(new StringFormattedMessage("Unable to send response when creating user"), ex);
            throw ex;
        }finally {
            LogContext.removeIPAddress();
            LogContext.removeAction();
            LogContext.removeUser();
        }
    }

    /**
     * Sends a confirmation email to the user with an url containing the hash for the email verification.
     * The url will be something like: http://localhost:8080/proj-1.0//email-confirmation/hashMail=1234567890
     *
     * This method is currently unused, but ready for deployment when the SMTP service is set.
     *
     * @param u the user to send the email to
     * @throws MessagingException if an error occurs while sending the email
     */
    private void sendCreationConfirmationEmail(User u) throws MessagingException {

        final StringBuilder sb = new StringBuilder();
        sb.append(String.format("<p>Welcome %s,</p>%n", u.getSurname(), u.getName()));
        sb.append(String.format("<p>Your account for ESN - Erasmus Student Network Padua has been created.</p>%n"));
        sb.append(String.format("<p>Please, verify your mail by clicking the link below.</p>%n"));
        sb.append(String.format("<p><a href=\"%s\">Verify your mail</a></p>%n", "http://localhost:8080/proj-1.0/email-confirmation/hashMail=" + u.getEmailHash()));
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




