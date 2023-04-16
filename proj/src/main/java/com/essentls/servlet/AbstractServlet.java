package com.essentls.servlet;

import com.essentls.utils.ErrorCode;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;

/**
 * Abstract class of Servlets
 *
 */
public class AbstractServlet extends HttpServlet {

    Logger logger;

    //override the init method: here you should put the initialization of your servlet
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        logger = LogManager.getLogger(this.getClass());
    }


    //override destroy method: here you should put the behaviour of your servlet when destroyed
    @Override
    public void destroy(){
        super.destroy();
    }

    public void writeError(HttpServletResponse res, ErrorCode ec) throws IOException {
        res.setStatus(ec.getHTTPCode());
        res.getWriter().write(ec.toJSON().toString());
    }
}

