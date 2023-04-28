package com.essentls.servlet;

import com.essentls.dao.*;
import com.essentls.resource.*;
import com.essentls.utils.ErrorCode;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Servlet that handles the email confirmation.
 *
 * @author Andrea Campagnol
 * @version 1.00
 * @since 1.00
 */

@WebServlet(name = "EmailConfirmationServlet", value = "/email-confirmation")
public class EmailConfirmationServlet extends AbstractDatabaseServlet {

    /**
     * Handles the HTTP {@code GET} method. Retrieves the hash from the request and checks if it matches with the one
     * stored in the database. If it does, the user is verified and redirected to the login page, otherwise an error
     * message is displayed.
     *
     * @param req a {@link HttpServletRequest} object that contains the request the client has made of the servlet
     * @param res a {@link HttpServletResponse} object that contains the response the servlet sends to the client
     * @throws ServletException if the request for the GET could not be handled
     * @throws IOException if an input or output error is detected when the servlet handles the GET request
     */
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        LogContext.setIPAddress(req.getRemoteAddr());
        LogContext.setResource(req.getRequestURI());
        LogContext.setAction("EMAIL CONFIRMATION");

        String uri = req.getPathInfo();
        Message m = null;

        String hash = uri.split("=")[1];

        LOGGER.info("Uri %s",hash);

        try{
            boolean verified = new EmailConfirmationDAO(getConnection(),hash).access().getOutputParam();
            if (!verified){
                LOGGER.info("verification FAILED.");
                ErrorCode ec = ErrorCode.NOT_VERIFIED;
                res.setStatus(ec.getHTTPCode());
                m = new Message(true, "User not verified or already verified");
                req.setAttribute("message", m);
                req.getRequestDispatcher("/jsp/mailconfirmation.jsp").forward(req, res);
            }
            else{
                LOGGER.info("hashCode has a match");
                LOGGER.info("trying to set user status to verified");
                boolean status_updated = new UpdateConfirmedStatusDAO(getConnection(),hash).access().getOutputParam();

                if (!status_updated){
                    LOGGER.info("Updating status FAILED.");
                    ErrorCode ec = ErrorCode.NOT_VERIFIED;
                    res.setStatus(ec.getHTTPCode());
                    m = new Message(true, "User status not updated");
                    req.setAttribute("message", m);
                }
                else{
                    LOGGER.info("Updating status COMPLETED.");
                    m = new Message("User status Updated");
                    req.setAttribute("message", m);
                }
                req.getRequestDispatcher("/jsp/mailconfirmation.jsp").forward(req, res);
            }
        }
        catch (SQLException e){
            //something unexpected happened: we write it into the LOGGER
            LOGGER.error("stacktrace:", e);
        }
    }
}
