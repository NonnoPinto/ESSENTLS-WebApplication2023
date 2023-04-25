package com.essentls.rest;
import com.essentls.dao.TagsCreationDAO;
import com.essentls.dao.TagsListDAO;
import com.essentls.resource.Message;
import com.essentls.resource.ResourceList;
import com.essentls.resource.Tag;
import com.essentls.servlet.LogContext;
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
public class CreateTagRR extends AbstractRR{

    /**
     * Creates a new REST resource for creating {@code tag}s.
     *
     * @param req the HTTP request.
     * @param res the HTTP response.
     * @param con the connection to the database.
     */
    public CreateTagRR(final HttpServletRequest req, final HttpServletResponse res, Connection con) {
        super("CREATE_TAG", req, res, con);
    }


    @Override
    protected void doServe() throws IOException {
        Message m = null;
        Tag newtag = null;

        try {

            // parse the URI path to extract the name
            final Tag tag = Tag.fromJSON(req.getInputStream());
            String path = req.getRequestURI();
            path = path.substring(path.lastIndexOf("tags") + 5);
            LogContext.setResource(path);


            // creates a new DAO for accessing the database and creating a new tag
            newtag = new TagsCreationDAO(con,tag.getName()).access().getOutputParam();
            LOGGER.info("tag %s creation",req.getAttribute("name"));

            if (newtag != null) {
                LOGGER.info("Tag successfully created");

                res.setStatus(HttpServletResponse.SC_CREATED);
                newtag.toJSON(res.getOutputStream());
            } else { // it should not happen
                LOGGER.error("Fatal error while creating Tag.");

                m = new Message("Cannot create Tag: unexpected error.", "E5A1", null);
                res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                m.toJSON(res.getOutputStream());
            }
        } catch (IndexOutOfBoundsException | NumberFormatException ex) {
            LOGGER.warn("Cannot create Tag: wrong format for URI", ex);

            m = new Message("Cannot create Tag: wrong format for URI", "E4A7",
                    ex.getMessage());
            res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            m.toJSON(res.getOutputStream());
        } catch (SQLException ex) {
            LOGGER.error("Cannot create Tag: unexpected database error.", ex);

            m = new Message("Cannot create Tag: unexpected database error.", "E5A1", ex.getMessage());
            res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            m.toJSON(res.getOutputStream());
        }
    }
}
