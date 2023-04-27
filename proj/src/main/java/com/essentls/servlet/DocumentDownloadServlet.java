package com.essentls.servlet;

import com.essentls.dao.AdminUsersListDAO;
import com.essentls.dao.DocumentDownloadDAO;
import com.essentls.resource.User;
import com.essentls.resource.Message;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.message.StringFormattedMessage;

public class DocumentDownloadServlet extends AbstractDatabaseServlet {

    /**
     * @param req  //request
     * @param res  //response
     * @throws ServletException    //exception servlet
     * @throws IOException     //exceptionIO
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
            res.sendError(HttpServletResponse.SC_NOT_FOUND);
        }


    }
}
