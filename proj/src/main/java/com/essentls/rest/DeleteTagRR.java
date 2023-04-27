package com.essentls.rest;
import com.essentls.dao.TagsCreationDAO;
import com.essentls.dao.TagsListDAO;
import com.essentls.dao.TagsRemovalDAO;
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
 * A REST resource for searching {@link Tag}s .
 *
 * @author Laura Pallante
 * @version 1.00
 * @since 1.00
 */
public class DeleteTagRR extends AbstractRR{

    /**
     * Creates a new REST resource for deleting {@code tag}s.
     *
     * @param req the HTTP request.
     * @param res the HTTP response.
     * @param con the connection to the database.
     */
    public DeleteTagRR(final HttpServletRequest req, final HttpServletResponse res, Connection con) {
        super("DELETE_TAG", req, res, con);
    }


    @Override
    protected void doServe() throws IOException {
        Message m = null;
        Tag deltag;

        try {

            // parse the URI path to extract the name
            String path = req.getRequestURI();
            path = path.substring(path.lastIndexOf("tags") + 5).replace("%20"," ");
            LogContext.setResource(path);


            // creates a new DAO for accessing the database and creating a new tag
            deltag=new TagsRemovalDAO(con,path).access().getOutputParam();
            LOGGER.info("tag %s removal", path);

            if (deltag != null) {
                LOGGER.info("Tag(s) successfully removed");

                res.setStatus(HttpServletResponse.SC_OK);
                deltag.toJSON(res.getOutputStream());
            } else { // it should not happen
                LOGGER.error("Fatal error while deleting Tag.");

                m = new Message("Cannot delete Tag: unexpected error.", "E5A1", null);
                res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                m.toJSON(res.getOutputStream());
            }
        } catch (IndexOutOfBoundsException | NumberFormatException ex) {
            LOGGER.warn("Cannot delete Tag: wrong format for URI", ex);

            m = new Message("Cannot delete Tag: wrong format for URI", "E4A7",
                    ex.getMessage());
            res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            m.toJSON(res.getOutputStream());
        } catch (SQLException ex) {
            if ("23503".equals(ex.getSQLState())) {
                LOGGER.warn("Cannot delete the tag: other resources depend on it.");

                m = new Message("Cannot delete the tag: other resources depend on it.", "E5A4", ex.getMessage());
                res.setStatus(HttpServletResponse.SC_CONFLICT);
                m.toJSON(res.getOutputStream());
            } else {
                LOGGER.error("Cannot delete Tag: unexpected database error.", ex);

                m = new Message("Cannot delete Tag: unexpected database error.", "E5A1", ex.getMessage());
                res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                m.toJSON(res.getOutputStream());
            }
        }
    }
}
