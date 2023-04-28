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
 * @version 1.0
 * @since 1.0
 * Filter for active volunteers.
 * This filter checks if the user is logged in and if he is an active volunteer.
 * If not, it redirects to the unauthorized page.
 * If the user is logged in and is an active volunteer, it continues the request.
 */
public class ActiveVolunteerFilter extends AbstractFilter {

    final static Logger logger = LogManager.getLogger(ActiveVolunteerFilter.class);

    /**
     * Checks if the user is logged in and if he is an active volunteer.
     * If not, it redirects to the unauthorized page.
     * If the user is logged in and is an active volunteer, it continues the request.
     * @param req the request
     * @param res the response
     * @param chain the filter chain
     * @throws IOException if an error occurs while writing to the response
     * @throws ServletException if an error occurs while processing the request
     */
    @Override
    public void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {

        HttpSession session = req.getSession(false);
        String unauthorizedURI = req.getContextPath() + "/unauthorized";

        boolean loggedIn = session != null && session.getAttribute("sessionUserId") != null;
        int tier = -1;
        if(session.getAttribute("sessionUserTier") != null) {
            tier = (int) session.getAttribute("sessionUserTier");
        }

        if (loggedIn && tier > 2) {
            res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
            res.setHeader("Pragma", "no-cache"); // HTTP 1.0.
            chain.doFilter(req, res); // User is logged in, just continue request.
        } else {
            res.sendRedirect(unauthorizedURI); // Not logged in, show login page.
        }

    }

}
