package com.essentls.rest;

import com.essentls.dao.CausesCreationDAO;
import com.essentls.dao.TagsCreationDAO;
import com.essentls.dao.TagsListDAO;
import com.essentls.dao.UpdateCauseDAO;
import com.essentls.resource.Message;
import com.essentls.resource.ResourceList;
import com.essentls.resource.Tag;
import com.essentls.servlet.LogContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.essentls.resource.Cause;
import java.io.EOFException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * A REST resource for updating {@link Cause}s .
 *
 * @author Vaidas Lenartavicius
 * @version 1.00
 * @since 1.00
 */


public class UpdateCauseRR extends AbstractRR {
    /**
     * Creates a new REST resource for updating {@code Cause}s.
     *
     * @param req the HTTP request.
     * @param res the HTTP response.
     * @param con the connection to the database.
     */

    public UpdateCauseRR(final HttpServletRequest req, final HttpServletResponse res, Connection con) {
        super("UPDATE_CAUSE", req, res, con);
    }

    @Override
    protected void doServe() throws IOException {
        Message m = null;
        Cause newcause = null;

        try {
            //parse the URI path to extract the id
            String path = req.getRequestURI();
            path = path.substring(path.lastIndexOf("causes/id/") + 10);

            final int id = Integer.parseInt(path);

            LogContext.setResource(Integer.toString(id));

            final Cause cause = Cause.fromJSON(req.getInputStream());

            if(id != cause.getId()) {
                LOGGER.warn("Cause id mismatch: (URI request) %d != (cause resource) %d", id, cause.getId());

                m = new Message("Cannot update the cause: URI request and cause resource ids differ.", "E4A8",
                        String.format("Request URI id %d; cause resource id %d.", id, cause.getId()));
                res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                m.toJSON(res.getOutputStream());
                return;
            }

            newcause = new UpdateCauseDAO(con, cause).access().getOutputParam();

            if (newcause != null) {
                LOGGER.info("Cause successfully updated");

                res.setStatus(HttpServletResponse.SC_OK);
                newcause.toJSON(res.getOutputStream());
            } else {
                LOGGER.warn("Cause not found");

                m = new Message("Cannot update the cause: cause not found.", "E4A9",
                        String.format("Cause id %d not found.", id));
                res.setStatus(HttpServletResponse.SC_NOT_FOUND);
                m.toJSON(res.getOutputStream());
            }
        } catch (IndexOutOfBoundsException | NumberFormatException ex) {
            LOGGER.warn("Cannot update the cause: wrong format for URI /cause/id/{id}.", ex);

            m = new Message("Cannot update the cause: wrong format for URI /cause/id/{id}.", "E4A7",
                    ex.getMessage());
            res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            m.toJSON(res.getOutputStream());
        } catch (EOFException ex) {
            LOGGER.warn("Cannot update the cause: no Cause JSON object found in the request.", ex);

            m = new Message("Cannot update the cause: no Cause JSON object found in the request.", "E4A8",
                    ex.getMessage());
            res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            m.toJSON(res.getOutputStream());
        } catch (SQLException ex) {
                LOGGER.error("Cannot update the cause: unexpected database error.", ex);

                m = new Message("Cannot update the cause: unexpected database error.", "E5A1", ex.getMessage());
                res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                m.toJSON(res.getOutputStream());
        }
    }
}
