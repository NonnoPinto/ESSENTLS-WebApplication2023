package com.essentls.rest;

import com.essentls.resource.*;
import com.essentls.dao.*;
import com.essentls.servlet.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.EOFException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

/**
 * A REST resource for searching {@link Cause}s .
 *
 * @author Laura Pallante
 * @version 1.00
 * @since 1.00
 */
public final class ListCausesRR extends AbstractRR {

    String subCause;
    long id=-1;
    /**
     * Creates a new REST resource for searching {@code Cause}s.
     *
     * @param req the HTTP request.
     * @param res the HTTP response.
     * @param con the connection to the database.
     */
    public ListCausesRR(final HttpServletRequest req, final HttpServletResponse res, Connection con, long id, String subCause) {
        super("LIST_CAUSES", req, res, con);
        this.subCause = subCause;
        this.id=id;
    }


    @Override
    protected void doServe() throws IOException {

        List<Cause> el = null;
        Message m = null;

        try {

            LogContext.setResource("causes");

            // creates a new DAO for accessing the database and searches the Student(s)
            el = new CausesListDAO(con, id, subCause).access().getOutputParam();
            LOGGER.info("cause %s",el);

            if (el != null) {
                LOGGER.info("Cause(s) successfully retrieved");

                res.setStatus(HttpServletResponse.SC_OK);
                new ResourceList(el).toJSON(res.getOutputStream());
            } else { // it should not happen
                LOGGER.error("Fatal error while searching Cause(s).");

                m = new Message("Cannot search Cause(s): unexpected error.", "E5A1", null);
                res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                m.toJSON(res.getOutputStream());
            }
        } catch (IndexOutOfBoundsException | NumberFormatException ex) {
            LOGGER.warn("Cannot search Cause(s): wrong format for URI", ex);

            m = new Message("Cannot search Cause(s): wrong format for URI", "E4A7",
                    ex.getMessage());
            res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            m.toJSON(res.getOutputStream());
        } catch (SQLException ex) {
            LOGGER.error("Cannot search Cause(s): unexpected database error.", ex);

            m = new Message("Cannot search Cause(s): unexpected database error.", "E5A1", ex.getMessage());
            res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            m.toJSON(res.getOutputStream());
        }
    }


}
