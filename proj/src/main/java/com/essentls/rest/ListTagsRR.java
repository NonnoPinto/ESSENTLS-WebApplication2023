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
 * A REST resource for searching {@link tag}s .
 *
 * @author Laura Pallante
 * @version 1.00
 * @since 1.00
 */
public final class ListTagsRR extends AbstractRR {

    /**
     * Creates a new REST resource for searching {@code Student}s.
     *
     * @param req the HTTP request.
     * @param res the HTTP response.
     * @param con the connection to the database.
     */
    public ListTagsRR(final HttpServletRequest req, final HttpServletResponse res, Connection con) {
        super("LIST_TAGS", req, res, con);
    }


    @Override
    protected void doServe() throws IOException {

        List<Tag> el = null;
        Message m = null;

        try {

            LogContext.setResource("tags");

            // creates a new DAO for accessing the database and searches the Student(s)
            el = new TagsListDAO(con).access().getOutputParam();
            LOGGER.info("tag %s",el);

            if (el != null) {
                LOGGER.info("Tag(s) successfully retrieved");

                res.setStatus(HttpServletResponse.SC_OK);
                new ResourceList(el).toJSON(res.getOutputStream());
            } else { // it should not happen
                LOGGER.error("Fatal error while searching Tag(s).");

                m = new Message("Cannot search Tag(s): unexpected error.", "E5A1", null);
                res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                m.toJSON(res.getOutputStream());
            }
        } catch (IndexOutOfBoundsException | NumberFormatException ex) {
            LOGGER.warn("Cannot search Tag(s): wrong format for URI", ex);

            m = new Message("Cannot search Tag(s): wrong format for URI", "E4A7",
                    ex.getMessage());
            res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            m.toJSON(res.getOutputStream());
        } catch (SQLException ex) {
            LOGGER.error("Cannot search Tag(s): unexpected database error.", ex);

            m = new Message("Cannot search Tag(s): unexpected database error.", "E5A1", ex.getMessage());
            res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            m.toJSON(res.getOutputStream());
        }
    }


}
