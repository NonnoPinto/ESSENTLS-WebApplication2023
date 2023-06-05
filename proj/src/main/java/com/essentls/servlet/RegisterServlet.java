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
                emailConfirmed = false;
                user= new User(id,email,password_sha1,null,tier,null,null,null,null,
                        null,null,null,null,0,
                        null,null,null,null,null,
                        null,null,emailHash,emailConfirmed);


                String contextFolder = req.getContextPath().replace("/","").replace("\\","");

                //uncomment when smtp service is set
                sendCreationConfirmationEmail(user, contextFolder);
                LOGGER.info("Creation confirmation email for user %s successfully sent.", user.getEmail());

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
        } catch (MessagingException e) {
            throw new RuntimeException(e);
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
     * The url will be something like: http://localhost:8080/proj-1.0/email-confirmation/hashMail=1234567890
     *
     * This method is currently unused, but ready for deployment when the SMTP service is set.
     *
     * @param u the user to send the email to
     * @throws MessagingException if an error occurs while sending the email
     */
    private void sendCreationConfirmationEmail(User u, String contextFolder) throws MessagingException {


        final StringBuilder sb = new StringBuilder();
        sb.append(String.format("<p>Welcome %s,</p>%n", u.getEmail()));
        sb.append(String.format("<p>Your account for ESN - Erasmus Student Network Padua has been created.</p>%n"));
        sb.append(String.format("<p>Please, verify your mail by clicking the link below.</p>%n"));
        sb.append(String.format("<p><a href=\"%s\" style=\"background-color: darkblue; padding: 8px; border-radius: 5px; color: white; display: block\">Verify your mail</a></p>%n", "http://localhost:8080/"+contextFolder+"/email-confirmation?hashMail=" + u.getEmailHash()));
        sb.append(String.format("<p>Remember, to fully enrol you must visit our office!</p>%n"));
        sb.append(String.format("<p>You can find our office at , Padua. We are opened 6.30-8.30 PM on Monday, Tuesday and Thursday!</p>%n"));
        sb.append(String.format("<p>MUST: bring with yourself a valid document.</p>%n"));
        sb.append(String.format("<p style=\"line-height:1.38;margin-top:0pt;margin-bottom:0pt\"><span style=\"font-size:10pt;font-family:arial;color:rgb(34,34,34);background-color:rgb(255,255,255);font-variant-numeric:normal;font-variant-east-asian:normal;font-variant-alternates:normal;vertical-align:baseline;white-space:pre-wrap\">——</span></p>"));
        sb.append(String.format("<p style=\"line-height:1.38;margin-top:0pt;margin-bottom:0pt\"><b>ESN Padova</b></p>%n"));
        sb.append(String.format("<a style=\"line-height:1.38;margin-top:0pt;margin-bottom:0pt\" href=\"https://g.page/esnpadova\" target=\"_blank\" rel=\"noopener noreferral nofollow\" data-saferedirecturl=\"https://www.google.com/url?q=https://g.page/esnpadova&amp;source=gmail&amp;ust=1681817791169000&amp;usg=AOvVaw01EBgGYLow4nqz6_FdOcJ-\">Via Galileo Galilei, 42</a>%n"));
        sb.append(String.format("<p style=\"line-height:1.38;margin-top:0pt;margin-bottom:0pt\">35123 - Padova, Italy</p>%n"));
        sb.append(String.format("<a style=\"line-height:1.38;margin-top:0pt;margin-bottom:0pt\" href=\"https://padova.esn.it\" target=\"_blank\" rel=\"noopener noreferral nofollow\" data-saferedirecturl=\"https://www.google.com/url?q=https://padova.esn.it&source=gmail&ust=1686070309478000&usg=AOvVaw3JEzvzF2XYlnc6FbaV-Q3S\">https://padova.esn.it</a>%n"));
        sb.append(String.format("<p style=\"line-height:1.38;margin-top:0pt;margin-bottom:0pt\"><span style=\"font-size:10pt;font-family:arial;color:rgb(34,34,34);background-color:rgb(255,255,255);font-variant-numeric:normal;font-variant-east-asian:normal;font-variant-alternates:normal;vertical-align:baseline;white-space:pre-wrap\"><span style=\"border:none;display:inline-block;height:63px;width:124px\"><img src=\"https://lh5.googleusercontent.com/rfn7FSvdlcXGFnqn1Y5dSw3TyA3gDJk3DonL9MLGa5b6MqGVEtRTzdJdpO-XDSH9xii1cb2vRSEPGD7lGNK_nBo8gx4zFBe3qXns8Ra8aLIgqhLOfNceoKCtBhT06PYBOPPlc02ridY0ryBB-gMvGw\" style=\"margin-left:0px;margin-top:0px\" data-bit=\"iit\" width=\"124.0\" height=\"63.0\"></span></span></p>"));
        sb.append(String.format("<a href=\"https://www.facebook.com/esnpadova/\" style=\"text-decoration-line:none\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?q=https://www.facebook.com/esnpadova/&amp;source=gmail&amp;ust=1686070309478000&amp;usg=AOvVaw0pHXIYZc3phu21FWyHciSp\"><span style=\"font-size:11pt;font-family:arial;color:rgb(17,85,204);background-color:rgb(255,255,255);font-variant-numeric:normal;font-variant-east-asian:normal;font-variant-alternates:normal;text-decoration-line:underline;vertical-align:baseline;white-space:pre-wrap\"><span style=\"border:none;display:inline-block;height:20px;width:20px\"><img src=\"https://lh4.googleusercontent.com/iSKCFy2Hyg3qLJ3gzTM5etsZKexq1uKSFCebk6Dgt2e-R0juFoua9OQaKr7fsqOI0Fhyu6S_xlu4YsUshGdFOWxOodSuUFvFS9sKOYIErzL5id_yElCNO5XddO_Nadlhqc1kv74ZfwXgdEDg2wxRUA\" style=\"margin-left:0px;margin-top:0px\" data-bit=\"iit\" width=\"20.0\" height=\"20.0\"></span></span></a>"));
        sb.append(String.format("<span style=\"font-size:12pt;font-family:lato,sans-serif;font-variant-numeric:normal;font-variant-east-asian:normal;font-variant-alternates:normal;vertical-align:baseline;white-space:pre-wrap\"> </span>"));
        sb.append(String.format("<a href=\"https://www.instagram.com/esnpadova/\" style=\"text-decoration-line:none\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?q=https://www.instagram.com/esnpadova/&amp;source=gmail&amp;ust=1686070309478000&amp;usg=AOvVaw1vmam9_cueZB2oKX756xfw\"><span style=\"font-size:11pt;font-family:arial;color:rgb(17,85,204);background-color:rgb(255,255,255);font-variant-numeric:normal;font-variant-east-asian:normal;font-variant-alternates:normal;text-decoration-line:underline;vertical-align:baseline;white-space:pre-wrap\"><span style=\"border:none;display:inline-block;height:20px;width:20px\"><img src=\"https://lh5.googleusercontent.com/Iy2PoP4LOoGdWdqKoEiWYa8z1vzFeY5jJohTX4BfRaF8u0LbL4YJkW4qyhK2TMwfhWbEaGYuJ00GBKyQ2N5SLeWf4uE-Sk_WdvkbjAvrmBdco_CDZT3BQr4-dEeP-hlhEShizvSIIDxfC2Gc1XWd-w\" style=\"margin-left:0px;margin-top:0px\" data-bit=\"iit\" width=\"20.0\" height=\"20.0\"></span></span></a>"));
        sb.append(String.format("<span style=\"font-size:12pt;font-family:lato,sans-serif;font-variant-numeric:normal;font-variant-east-asian:normal;font-variant-alternates:normal;vertical-align:baseline;white-space:pre-wrap\"> </span>"));
        sb.append(String.format("<a href=\"https://www.youtube.com/channel/UChLwc2hf9SnE4tvOq8vWUEA\" style=\"text-decoration-line:none\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?q=https://www.youtube.com/channel/UChLwc2hf9SnE4tvOq8vWUEA&amp;source=gmail&amp;ust=1686070309478000&amp;usg=AOvVaw2WGKCYv_F7oxYgeUo7Bv2b\"><span style=\"font-size:11pt;font-family:arial;color:rgb(17,85,204);background-color:rgb(255,255,255);font-variant-numeric:normal;font-variant-east-asian:normal;font-variant-alternates:normal;text-decoration-line:underline;vertical-align:baseline;white-space:pre-wrap\"><span style=\"border:none;display:inline-block;height:20px;width:20px\"><img src=\"https://lh5.googleusercontent.com/eDMFkRQFmIGTBdKHNYdv0bqRXgA8sVVd3H15ZJbFgfsX36BXjVVwhAoYQ4HMCyy1FDmOGTIaHNFqhXhsG9VMuECrMZIPpK2VlcEG7yt9AmRX2GDglLI75aT28G59iWxfQoF1_8KlJ5G1GNixWRdDMw\" style=\"margin-left:0px;margin-top:0px\" data-bit=\"iit\" width=\"20.0\" height=\"20.0\"></span></span></a>"));
        sb.append(String.format("<span style=\"font-size:11pt;font-family:arial;font-variant-numeric:normal;font-variant-east-asian:normal;font-variant-alternates:normal;vertical-align:baseline;white-space:pre-wrap\">&nbsp;</span>"));
        sb.append(String.format("<a href=\"https://it.linkedin.com/company/esnpadova\" style=\"text-decoration-line:none\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?q=https://it.linkedin.com/company/esnpadova&amp;source=gmail&amp;ust=1686070309479000&amp;usg=AOvVaw0bR89x_GNxpuJkwTvK7seM\"><span style=\"font-size:11pt;font-family:arial;color:rgb(17,85,204);background-color:rgb(255,255,255);font-variant-numeric:normal;font-variant-east-asian:normal;font-variant-alternates:normal;text-decoration-line:underline;vertical-align:baseline;white-space:pre-wrap\"><span style=\"border:none;display:inline-block;height:20px;width:20px\"><img src=\"https://lh6.googleusercontent.com/0XrVqX-jMJFZ8YRrYrEKt9zymbpwjHKPihCNsUkQFgufowTVB9a2wmN5ygdtKk3FXV-TAHjMVIbk48E-kH5w0-H8srJrUUyNPLKRqF2MV9c7BM94bh5zqMQpaBB_NY02RTxdPBNG6ZOD9GSDUAyLog\" style=\"margin-left:0px;margin-top:0px\" data-bit=\"iit\" width=\"20.0\" height=\"20.0\"></span></span></a>"));

        sb.append(String.format(""));

        MailManager.sendMail(u.getEmail(), String.format("Welcome to ESN %s", u.getEmail()),
                sb.toString(), "text/html;charset=UTF-8");

    }
}




