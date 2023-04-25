package com.essentls.rest;

import com.essentls.resource.Cause;
import com.essentls.resource.Message;
import com.essentls.resource.ResourceList;

import com.essentls.servlet.LogContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.EOFException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import com.essentls.dao.CauseRemovalDAO;
import java.util.ArrayList;
import java.util.List;

/**
 * A REST resource for searching {@link Cause}s .
 *
 * @author Laura Pallante
 * @version 1.00
 * @since 1.00
 */
public class DeleteCauseRR extends AbstractRR{

    /**
     * Creates a new REST resource for deleting {@code Cause}s.
     *
     * @param req the HTTP request.
     * @param res the HTTP response.
     * @param con the connection to the database.
     */
    public DeleteCauseRR(final HttpServletRequest req, final HttpServletResponse res, Connection con) {
        super("DELETE_CAUSE", req, res, con);
    }


    @Override
    protected void doServe() throws IOException {
        Message m = null;
        Cause delCause;

        try {

            // parse the URI path to extract the name
            String path = req.getRequestURI();
            path = path.substring(path.lastIndexOf("id/") + 3);
            LogContext.setResource(path);


            // creates a new DAO for accessing the database and deleting a cause
            delCause=new CauseRemovalDAO(con, Long.parseLong(path)).access().getOutputParam();
            LOGGER.info("cause %s removal", path);

            if (delCause != null) {
                LOGGER.info("cause(s) successfully removed");

                res.setStatus(HttpServletResponse.SC_OK);
                delCause.toJSON(res.getOutputStream());
            } else { // it should not happen
                LOGGER.error("Fatal error while deleting Cause.");

                m = new Message("Cannot delete Cause: unexpected error.", "E5A1", null);
                res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                m.toJSON(res.getOutputStream());
            }
        } catch (IndexOutOfBoundsException | NumberFormatException ex) {
            LOGGER.warn("Cannot delete Cause: wrong format for URI", ex);

            m = new Message("Cannot delete Cause: wrong format for URI", "E4A7",
                    ex.getMessage());
            res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            m.toJSON(res.getOutputStream());
        } catch (SQLException ex) {
            LOGGER.error("Cannot delete Cause: unexpected database error.", ex);

            m = new Message("Cannot delete Cause: unexpected database error.", "E5A1", ex.getMessage());
            res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            m.toJSON(res.getOutputStream());
        }
    }
}
