package com.essentls.rest;

import com.essentls.dao.CausesCreationDAO;
import com.essentls.resource.Message;
import com.essentls.servlet.LogContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.essentls.resource.Cause;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * A REST resource for creating {@link Cause}s .
 *
 * @author Laura Pallante
 * @version 1.00
 * @since 1.00
 */
public class CreateCauseRR extends AbstractRR{

    /**
     * Creates a new REST resource for creating {@link Cause}s.
     *
     * @param req a {@link HttpServletRequest} object that contains the request the client has made of the servlet
     * @param res a {@link HttpServletResponse} object that contains the response the servlet sends to the client
     * @param con a {@link Connection} object that represents a connection to the database
     */
    public CreateCauseRR(final HttpServletRequest req, final HttpServletResponse res, Connection con) {
        super("CREATE_CAUSE", req, res, con);
    }


    /**
     * Creates a new {@link Cause} and returns it in the response.
     *
     * @throws IOException if an I/O error occurs while writing the response
     */
    @Override
    protected void doServe() throws IOException {
        Message m = null;
        Cause newcause = null;

        try {

            // parse the URI path to extract the name
            final Cause cause = Cause.fromJSON(req.getInputStream());
            String path = req.getRequestURI();
            path = path.substring(path.lastIndexOf("causes") + 7);
            LogContext.setResource(path);


            // creates a new DAO for accessing the database and creating a new cause
            newcause = new CausesCreationDAO(con, cause.getName()).access().getOutputParam();
            LOGGER.info("cause %s creation", cause.toString());

            if (newcause != null) {
                LOGGER.info("Cause successfully created");

                res.setStatus(HttpServletResponse.SC_CREATED);
                newcause.toJSON(res.getOutputStream());
            } else { // it should not happen
                LOGGER.error("Fatal error while creating Cause.");

                m = new Message("Cannot create Cause: unexpected error.", "E5A1", null);
                res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                m.toJSON(res.getOutputStream());
            }
        } catch (IndexOutOfBoundsException | NumberFormatException ex) {
            LOGGER.warn("Cannot create Cause: wrong format for URI", ex);

            m = new Message("Cannot create Cause: wrong format for URI", "E4A7",
                    ex.getMessage());
            res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            m.toJSON(res.getOutputStream());
        } catch (SQLException ex) {
            LOGGER.error("Cannot create Cause: unexpected database error.", ex);

            m = new Message("Cannot create Cause: unexpected database error.", "E5A1", ex.getMessage());
            res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            m.toJSON(res.getOutputStream());
        }
    }
}
