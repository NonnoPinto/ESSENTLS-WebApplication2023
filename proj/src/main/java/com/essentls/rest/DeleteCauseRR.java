package com.essentls.rest;

import com.essentls.resource.Cause;
import com.essentls.resource.Message;

import com.essentls.servlet.LogContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import com.essentls.dao.CauseRemovalDAO;

/**
 * A REST resource for deleting {@link Cause}s .
 *
 * @author Laura Pallante
 * @version 1.00
 * @since 1.00
 */
public class DeleteCauseRR extends AbstractRR{

    /**
     * Creates a new REST resource for deleting {@link Cause}s.
     * @param req a {@link HttpServletRequest} object that contains the request the client has made of the servlet.
     * @param res a {@link HttpServletResponse} object that contains the response the servlet sends to the client.
     * @param con a {@link Connection} object that represents a connection to the database.
     */
    public DeleteCauseRR(final HttpServletRequest req, final HttpServletResponse res, Connection con) {
        super("DELETE_CAUSE", req, res, con);
    }

    /**
     * Deletes a {@link Cause} and returns it in the response.
     *
     * @throws IOException if an I/O error occurs while writing the response.
     */
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
            delCause=new CauseRemovalDAO(con, Integer.parseInt(path)).access().getOutputParam();
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
            if ("23503".equals(ex.getSQLState())) {
                LOGGER.warn("Cannot delete the cause: other resources depend on it.");

                m = new Message("Cannot delete the cause: other resources depend on it.", "E5A4", ex.getMessage());
                res.setStatus(HttpServletResponse.SC_CONFLICT);
                m.toJSON(res.getOutputStream());
            } else {
                LOGGER.error("Cannot delete Cause: unexpected database error.", ex);

                m = new Message("Cannot delete Cause: unexpected database error.", "E5A1", ex.getMessage());
                res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                m.toJSON(res.getOutputStream());
            }
        }
    }
}
