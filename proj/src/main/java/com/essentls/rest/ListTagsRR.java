package com.essentls.rest;

import com.essentls.resource.*;
import com.essentls.dao.*;
import com.essentls.servlet.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import java.util.List;

/**
 * A REST resource for listing {@link Tag}s .
 *
 * @author Laura Pallante
 * @version 1.00
 * @since 1.00
 */
public final class ListTagsRR extends AbstractRR {

    /**
     * The sub-string to search inside Tag names. Leave it "" to search all Tags.
     */
    String subTag;

    /**
     * Creates a new REST resource for listing {@link Tag}s.
     *
     * @param req a {@link HttpServletRequest} object that contains the request the client has made of the servlet.
     * @param res a {@link HttpServletResponse} object that contains the response the servlet sends to the client.
     * @param con a {@link Connection} object that represents a connection to the database.
     * @param subTag the {@code String} to search inside {@link Tag} names. Leave it "" to list all {@link Tag}s.
     */
    public ListTagsRR(final HttpServletRequest req, final HttpServletResponse res, Connection con, String subTag) {
        super("LIST_TAGS", req, res, con);
        this.subTag = subTag;
    }

    /**
     * Lists {@link Tag}s and returns them in the response.
     * @throws IOException if an I/O error occurs while writing the response.
     */
    @Override
    protected void doServe() throws IOException {

        List<Tag> el = null;
        Message m = null;

        try {

            LogContext.setResource("tags");

            // creates a new DAO for accessing the database and searches the Student(s)
            el = new TagsListDAO(con, subTag).access().getOutputParam();
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
