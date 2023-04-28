package com.essentls.filter;

import com.essentls.utils.ErrorCode;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 * @author Mattia Maglie
 * @version 1.0
 * @since 1.0
 * Abstract class for filters.
 */

public class AbstractFilter extends HttpFilter {

    /**
     * A LOGGER available for all the subclasses.
     */
    Logger logger;

    /**
     * Creates an abstract filter.
     * If you have any <init-param> in web.xml, then you could get them
     * here by config.getInitParameter("name") and assign it as field.
     */
    @Override
    public void init(FilterConfig config) throws ServletException {
        logger = LogManager.getLogger(this.getClass());

    }

    /**
     * Destroys the filter.
     */
    @Override
    public void destroy() {
        // If you have assigned any expensive resources as field of
        // this Filter class, then you could clean/close them here.
    }

    /**
     * Writes an error to the response.
     * @param res the response to write to
     * @param ec the error code
     * @throws IOException if an error occurs while writing to the response
     */
    public void writeError(HttpServletResponse res, ErrorCode ec) throws IOException {
        res.setStatus(ec.getHTTPCode());
        res.getWriter().write(ec.toJSON().toString());
    }
}
