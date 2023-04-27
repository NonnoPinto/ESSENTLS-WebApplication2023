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
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;


@WebServlet(name = "EmailConfirmationServlet", value = "/email-confirmation")
public class EmailConfirmationServlet extends AbstractDatabaseServlet {
    
    //public final static String LOGIN_JSP = "/jsp/login.jsp";
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
            //writeError(res, ErrorCode.INTERNAL_ERROR);
            LOGGER.error("stacktrace:", e);
        }
        
        //req.getRequestDispatcher("/").forward(req, res);
    }
}
