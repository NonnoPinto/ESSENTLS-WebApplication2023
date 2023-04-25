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

            // if the requested resource was a tag or a cause, delegate its processing and return
            if (processTag(req, res) || processCause(req, res)) {
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
     *
     * @param req the HTTP request.
     * @param res the HTTP response.
     * @return {@code true} if the request was for a {@code Tag}; {@code false} otherwise.
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

        // strip everything until after the /tags/ part
        path = path.substring(path.lastIndexOf("rest/tags/") + 10);

        // if the path is empty, the request URI is: /tags/
        if (path.isEmpty()) {

            switch (method) {
                case "GET" ->
                    // get all tags
                        new ListTagsRR(req, res, getConnection(), "").serve();
                case "POST" ->
                    // create a new tag
                        new CreateTagRR(req, res, getConnection()).serve();
                default -> {
                    // method not allowed
                    LOGGER.warn("Unsupported method for URI /tags/: %s.", method);

                    m = new Message("Method not allowed.", "E4A2", String.format("Method %s is not allowed.", method));
                    res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
                    m.toJSON(res.getOutputStream());
                }
            }
        } else {
            // the request URI is: /tags/name or /tags/subtag

            switch (method) {
                case "GET" ->
                    // get a tag
                        new ListTagsRR(req, res, getConnection(), path).serve();
                case "DELETE" ->
                    // delete a tag
                        new DeleteTagRR(req, res, getConnection()).serve();
                default -> {
                    // method not allowed
                    LOGGER.warn("Unsupported method for URI /tags/name/ or /tags/subtag/: %s.", method);

                    m = new Message("Method not allowed.", "E4A2", String.format("Method %s is not allowed.", method));
                    res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
                    m.toJSON(res.getOutputStream());
                }
            }
        }

        return true;
    }


    /**
     * Checks whether the request
     *
     * @param req the HTTP request.
     * @param res the HTTP response.
     * @return {@code true} if the request was for a {@code Cause}; {@code false} otherwise.
     * @throws Exception if any error occurs.
     */
    private boolean processCause(final HttpServletRequest req, final HttpServletResponse res) throws Exception {

        final String method = req.getMethod();
        String path = req.getRequestURI();
        Message m = null;

        // the requested resource was not a cause
        if (path.lastIndexOf("rest/causes/") <= 0) {
            return false;
        }

        // strip everything until after the /causes/ part
        path = path.substring(path.lastIndexOf("rest/causes/") + 12);

        // if the path is empty, the request URI is: /causes/
        if (path.isEmpty()) {

            switch (method) {
                case "GET" ->
                    // get all causes
                        new ListCausesRR(req, res, getConnection(), -1, "").serve();
                case "POST" ->
                    // create a new cause
                        new CreateCauseRR(req, res, getConnection()).serve();
                default -> {
                    // method not allowed
                    LOGGER.warn("Unsupported method for URI /causes/: %s.", method);

                    m = new Message("Method not allowed.", "E4A2", String.format("Method %s is not allowed.", method));
                    res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
                    m.toJSON(res.getOutputStream());
                }
            }
            //the request URI is: /causes/id/causeId
        } else if (path.contains("id/")) {
            // strip everything until after the /id/ part
            path = path.substring(path.lastIndexOf("id/") + 3);

            if (!path.isEmpty()) {
                switch (method) {
                    case "GET" ->
                        // get a cause
                            new ListCausesRR(req, res, getConnection(), Integer.parseInt(path), "").serve();
                    case "DELETE" ->
                        // delete a cause
                            new DeleteCauseRR(req, res, getConnection()).serve();
                    default -> {
                        // method not allowed
                        LOGGER.warn("Unsupported method for URI /causes/id/: %s.", method);

                        m = new Message("Method not allowed.", "E4A2", String.format("Method %s is not allowed.", method));
                        res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
                        m.toJSON(res.getOutputStream());
                    }
                }
            } else {
                //operation not allowed
                LOGGER.warn("Unsupported operation for URI /causes/id/: %s.", method);

                m = new Message("Operation not allowed.", "E4A3", String.format("Operation not allowed."));
                res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
                m.toJSON(res.getOutputStream());

            }
            //the request URI is: /causes/srch/name or /causes/srch/subcause
        } else if (path.contains("srch/")) {
            // strip everything until after the /srch/ part
            path = path.substring(path.lastIndexOf("srch/") + 5);

            switch (method) {
                case "GET" ->
                    // get a cause
                        new ListCausesRR(req, res, getConnection(), -1, path).serve();
                default -> {
                    // method not allowed
                    LOGGER.warn("Unsupported method for URI /causes/srch/: %s.", method);

                    m = new Message("Method not allowed.", "E4A2", String.format("Method %s is not allowed.", method));
                    res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
                    m.toJSON(res.getOutputStream());
                }
            }

        } else {
            //unknown resourse requested
            LOGGER.warn("Unknown resource requested: %s.", path);

            m = new Message("Unknown resource.", "E4A1", String.format("Resource %s is unknown.", path));
            res.setStatus(HttpServletResponse.SC_NOT_FOUND);
            m.toJSON(res.getOutputStream());
        }
        return true;
    }

}