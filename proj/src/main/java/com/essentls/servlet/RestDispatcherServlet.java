package com.essentls.servlet;


import com.essentls.rest.*;
import com.essentls.resource.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.OutputStream;


public final class RestDispatcherServlet extends AbstractDatabaseServlet {

    /**
     * The JSON UTF-8 MIME media type
     */
    private static final String JSON_UTF_8_MEDIA_TYPE = "application/json; charset=utf-8";

    @Override
    protected void service(final HttpServletRequest req, final HttpServletResponse res) throws IOException {

        LogContext.setIPAddress(req.getRemoteAddr());

        final OutputStream out = res.getOutputStream();

        try {

            // if the requested resource was a tag, delegate its processing and return
            if (processTag(req, res)) {
                return;
            }


            // if none of the above process methods succeeds, it means an unknown resource has been requested
            LOGGER.warn("Unknown resource requested: %s.", req.getRequestURI());

            final Message m = new Message("Unknown resource requested.", "E4A6",
                    String.format("Requested resource is %s.", req.getRequestURI()));
            res.setStatus(HttpServletResponse.SC_NOT_FOUND);
            res.setContentType(JSON_UTF_8_MEDIA_TYPE);
            m.toJSON(out);
        } catch (Throwable t) {
            LOGGER.error("Unexpected error while processing the REST resource.", t);

            final Message m = new Message("Unexpected error.", "E5A1", t.getMessage());
            res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            m.toJSON(out);
        } finally {

            // ensure to always flush and close the output stream
            if (out != null) {
                out.flush();
                out.close();
            }

            LogContext.removeIPAddress();
        }
    }


    /**
     * Checks whether the request
     * @param req the HTTP request.
     * @param res the HTTP response.
     *
     * @return {@code true} if the request was for an {@code Employee}; {@code false} otherwise.
     *
     * @throws Exception if any error occurs.
     */
    private boolean processTag(final HttpServletRequest req, final HttpServletResponse res) throws Exception {

        final String method = req.getMethod();

        String path = req.getRequestURI();
        Message m = null;

        // the requested resource was not a tag
        if (path.lastIndexOf("rest/tags/") <= 0) {
            return false;
        }

        // strip everything until after the /employee
        path = path.substring(path.lastIndexOf("tags/") + 5);

        // I can have multiple paths. Split on "/"
        String[] splitted_path = path.split("/");



        // the request URI is: /tags
        // if method GET, list tags
        if (path.length() == 0) {

            switch (method) {
                case "GET":
                    new ListTagsRR(req, res, getConnection(), "").serve();
                    break;
                case "POST":
                    new CreateTagRR(req, res, getConnection()).serve();
                    break;
                default:
                    LOGGER.warn("Unsupported operation for URI /tags: %s.", method);

                    m = new Message("Unsupported operation for URI /tags.", "E4A5",
                            String.format("Requested operation %s.", method));
                    res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
                    m.toJSON(res.getOutputStream());
                    break;

            }
        } else {
            // the request URI is: /tags/name or /tags/subtag

            //TODO make RegEx to check if tagname only contains alphanumerical characters

            switch (method) {
                case "GET":
                    new ListTagsRR(req, res, getConnection(), path).serve();
                    break;
                case "DELETE":
                    new DeleteTagRR(req, res, getConnection()).serve();
                    break;
                default:
                    LOGGER.warn("Unsupported operation for URI /tags/{name}: %s.", method);

                    m = new Message("Unsupported operation for URI /tags/{name}.", "E4A5",
                            String.format("Requested operation %s.", method));
                    res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
                    m.toJSON(res.getOutputStream());
                    break;
            }


        }

        return true;

    }
}
