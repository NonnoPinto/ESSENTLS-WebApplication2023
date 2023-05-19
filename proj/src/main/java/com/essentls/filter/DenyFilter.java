package com.essentls.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;

/**
 * @author Mattia Maglie
 * @since  1.0
 * @version 1.0
 * deny filter
 */
public class DenyFilter extends AbstractFilter {

    /**
     * A LOGGER available for all the subclasses.
     */
    final static Logger logger = LogManager.getLogger(DenyFilter.class);

    /**
     * deny filter
     * @param req the request
     * @param res the response
     * @param chain the filter chain
     * @throws IOException if an error occurs while writing to the response
     * @throws ServletException if an error occurs while processing the request
     */
    @Override
    public void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {

        HttpSession session = req.getSession(false);

        res.sendRedirect(req.getContextPath() + "/unauthorized"); // Not logged in, show login page.

    }

}
