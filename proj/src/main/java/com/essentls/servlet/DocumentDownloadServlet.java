package com.essentls.servlet;

import com.essentls.dao.DocumentDownloadDAO;
import com.essentls.resource.Message;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.message.StringFormattedMessage;

/**
 * Downloads a user's document.
 *
 * @author Vaidas Lenartavicius
 * @version 1.00
 * @since 1.00
 */
@WebServlet(name = "DocumentDownloadServlet", value = "/download-user-document")
public class DocumentDownloadServlet extends AbstractDatabaseServlet {

    /**
     * Handles the HTTP {@code POST} method. Downloads a user's document.
     *
     * @param req a {@link HttpServletRequest} object that contains the request the client has made of the servlet
     * @param res a {@link HttpServletResponse} object that contains the response the servlet sends to the client
     * @throws ServletException if the request for the POST could not be handled
     * @throws IOException if an input or output error is detected when the servlet handles the POST request
     */
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        LogContext.setIPAddress(req.getRemoteAddr());
        LogContext.setAction("DOWNLOAD USER DOCUMENT");

        long id = 0;
        byte[] document = null;
        Message m = null;

        try {
            if(!(req.getParameter("userId").equals(""))){
                id=Long.parseLong(req.getParameter("userId"));
            }else{
                throw new NumberFormatException();
            }


            document = new DocumentDownloadDAO(getConnection(), id).access().getOutputParam();

            m= new Message("Document succesfully downloaded");

            LOGGER.info("Document of user %d succesfully downloaded", id);

        } catch (SQLException e) {
            m = new Message("Database error");
            LOGGER.error(new StringFormattedMessage("Database error: %s", e.getMessage()));
        } catch (NumberFormatException e) {
            m = new Message("Invalid ID");
            LOGGER.error(new StringFormattedMessage("Invalid ID: %s", e.getMessage()));
        }

        if (document != null) {
            res.setContentType("application/pdf");
            res.setHeader("Content-Disposition", "attachment; filename=" + id + "_Document.pdf");
            res.setContentLength(document.length);
            res.getOutputStream().write(document);
            res.getOutputStream().close();
        } else {
            res.sendRedirect(req.getContextPath() + "/error"); // redirect to 404 page
            //res.sendError(HttpServletResponse.SC_NOT_FOUND);
        }


    }
}
