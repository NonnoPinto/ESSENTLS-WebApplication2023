package com.essentls.servlet;

import com.essentls.dao.*;
import com.essentls.resource.*;

import java.io.*;
import java.net.URL;
import java.sql.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Creates a new event and inserts it into the database.
 *
 * @author Francesco Marcato
 * @version 1.00
 * @since 1.00
 */
@WebServlet(name = "CreateEventServlet", urlPatterns = {"", "/create-event"})
@MultipartConfig
public final class CreateEventServlet extends AbstractDatabaseServlet {

    /**
     * A method that processes a file to retrieve its name without the preceding path.
     *
     * @param part a {@link Part} object that contains the file to be processed.
     * @return a {@link String} object that contains the name of the file.
     */
    private String getFileName(final Part part) {
        final String partHeader = part.getHeader("content-disposition");
        if (partHeader == null) {
            return null;
        }
        LOGGER.info("Part Header = {0}", partHeader);
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(
                        content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

    /**
     * Handles the HTTP {@code GET} method. Redirects the user to the event creation page.
    *
    * @param req a {@link HttpServletRequest} object that contains the request the client has made of the servlet.
    * @param res a {@link HttpServletResponse} object that contains the response the servlet sends to the client.
    *
    * @throws IOException if any error occurs in the client/server communication.
    */

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        LogContext.setIPAddress(req.getRemoteAddr());
        LogContext.setResource(req.getRequestURI());
        LogContext.setAction("CREATE EVENT");

        List<Cause> causes = null;
        try {
            causes = new CausesListDAO(getConnection(), -1, "").access().getOutputParam();
        }catch (SQLException sqle){
            LOGGER.info("Unexpected Database error: "+sqle.getMessage());
        }

        List<Tag> tags = null;
        try {
            tags = new TagsListDAO(getConnection(), "").access().getOutputParam();
        }catch (SQLException sqle){
            LOGGER.info("Unexpected Database error: "+sqle.getMessage());
        }

        req.setAttribute("causes", causes);
        req.setAttribute("tags", tags);
        req.getRequestDispatcher("/jsp/eventcreation.jsp").forward(req, res);
    }


    /**
     * Handles the HTTP {@code POST} method. Retrieves the parameters inserted by the user, creates a new event and
     * inserts it into the database.
     *
     * @param req a {@link HttpServletRequest} object that contains the request the client has made of the servlet.
     * @param res a {@link HttpServletResponse} object that contains the response the servlet sends to the client.
     *
     * @throws IOException if any error occurs in the client/server communication.
     */
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        LogContext.setIPAddress(req.getRemoteAddr());
        LogContext.setAction("CREATE EVENT");

        // request parameters
        String name = null;
        String description = null;
        float price = -1;
        int visibility = 0;
        JSONObject location = null;
        String city;
        String street;
        String number;
        int maxParticipantsInternational = -1;
        int maxParticipantVolunteer = -1;
        LocalDateTime eventStart = null;
        LocalDateTime eventEnd = null;
        LocalDateTime subscriptionStart = null;
        LocalDateTime subscriptionEnd = null;
        LocalDateTime withdrawalEnd = null;
        int maxWaitingList = -1;
        String[] attributes = null;
        String thumbnail = null;
        String poster = null;
        String country = null;

        // model
        Event e = null;
        Message m = null;

        //set datetime format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

        String contextFolder = req.getContextPath().replace("/","").replace("\\","");

        try {
            // retrieves the request parameters
            name = req.getParameter("name");
            description = req.getParameter("description");
            price = Float.parseFloat(req.getParameter("price"));
            visibility = Integer.parseInt(req.getParameter("visibility"));
            city= req.getParameter("city");
            street= req.getParameter("street");
            number= req.getParameter("number");
            country= req.getParameter("country");
            maxParticipantsInternational = Integer.parseInt(req.getParameter("maxParticipantsInternational"));
            maxParticipantVolunteer = Integer.parseInt(req.getParameter("maxParticipantsVolunteer"));
            eventStart = LocalDateTime.parse(req.getParameter("eventStart"), formatter);
            eventEnd = LocalDateTime.parse(req.getParameter("eventEnd"), formatter);
            subscriptionStart = LocalDateTime.parse(req.getParameter("subscriptionStart"), formatter);
            subscriptionEnd = LocalDateTime.parse(req.getParameter("subscriptionEnd"), formatter);
            withdrawalEnd = LocalDateTime.parse(req.getParameter("withdrawalEnd"), formatter);
            maxWaitingList = Integer.parseInt(req.getParameter("maxWaitingList"));
            attributes = req.getParameter("attributes").replace(", ",",").replace(" ,",",").split(",");

            // set the name of the event as the resource in the log context
            LogContext.setResource(req.getParameter("name"));

            location = new JSONObject();
            location.put("city", city);
            location.put("street", street);
            location.put("number", number);
            location.put("country", country);

            LOGGER.info("The location is:  \""+location.toString()+"\"");

            URL url = CreateEventServlet.class.getProtectionDomain().getCodeSource().getLocation();

            File file = new java.io.File(url.getFile());
            File parent = file.getParentFile();
            if(!(parent==null)) {
                while (!contextFolder.equals(parent.getName())){
                    parent = parent.getParentFile();
                }

                //create path
                final String path = (parent.getPath() + File.separator + "ESSENTLS_Cloud").replaceAll("%20", " ");
                final String relative_path = "ESSENTLS_Cloud";

                //create folder if it doesn't exist
                new File(path).mkdirs();

                //get file
                final Part posterPart = req.getPart("poster");
                final Part thumbnailPart = req.getPart("thumbnail");
                final String posterName = getFileName(posterPart);
                final String thumbnailName = getFileName(thumbnailPart);

                if(posterName==null || thumbnailName==null){
                    LOGGER.info("Poster or thumbnail not found");
                    throw new FileNotFoundException("Poster or thumbnail not found");
                }

                OutputStream out1 = null;
                InputStream filecontent = null;

                //save poster

                out1 = new FileOutputStream(new File(path + File.separator + posterName));
                filecontent = posterPart.getInputStream();

                int read = 0;
                final byte[] posterBytes = new byte[1024];
                final byte[] thumbnailBytes = new byte[1024];

                while ((read = filecontent.read(posterBytes)) != -1) {
                    out1.write(posterBytes, 0, read);
                }
                LOGGER.info("New file " + posterName + " created at " + path);

                poster = relative_path + File.separator + posterName;

                //save thumbnail

                OutputStream out = null;

                out = new FileOutputStream(new File(path + File.separator + thumbnailName));
                filecontent = thumbnailPart.getInputStream();

                read = 0;

                while ((read = filecontent.read(thumbnailBytes)) != -1) {
                    out.write(thumbnailBytes, 0, read);
                }
                LOGGER.info("New file " + thumbnailName + " created at " + path);

                poster = relative_path + File.separator + posterName;
                thumbnail = relative_path + File.separator + thumbnailName;
            }
            // creates a new event from the request parameters
            e = new Event(name, description, price, visibility, location, maxParticipantsInternational,
                    maxParticipantVolunteer, Timestamp.valueOf(eventStart), Timestamp.valueOf(eventEnd),
                    Timestamp.valueOf(subscriptionStart), Timestamp.valueOf(subscriptionEnd), Timestamp.valueOf(withdrawalEnd),
                    maxWaitingList, attributes, thumbnail, poster);


            // creates a new object for accessing the database and stores the event
            int eventID = new AdminCreateEventDAO(getConnection(), e).access().getOutputParam();

            List<Cause> causes = new ArrayList<>();
            try {
                causes = new CausesListDAO(getConnection(), -1, "").access().getOutputParam();
            }catch (SQLException sqle){
                LOGGER.info("Unexpected Database error: "+sqle.getMessage());
            }

            for (Cause cause:causes) {
                int causeId= cause.getId();
                EventCause ec= new EventCause(eventID, causeId);
                if (cause.getName().equals(req.getParameter("cs_"+causeId))){
                    new EventCausesCreationDAO(getConnection(), ec).access();
                }
            }

            List<Tag> tags = new ArrayList<>();
            try {
                tags = new TagsListDAO(getConnection(), "").access().getOutputParam();
            }catch (SQLException sqle){
                LOGGER.info("Unexpected Database error: "+sqle.getMessage());
            }

            for (Tag tag:tags) {
                String tagName= tag.getName();
                EventTag et= new EventTag(eventID, tagName);
                if (tag.getName().equals(req.getParameter("cs_"+tagName))){
                    new EventTagsCreationDAO(getConnection(), et).access();
                }
            }


            m = new Message(String.format("Event \""+e.getName()+"\" successfully created."));

            LOGGER.info("Event \""+e.getName()+"\" successfully created in the database.");

            req.getSession().setAttribute("organizer", eventID);

            res.sendRedirect(req.getContextPath()+"/payment?action=event&id="+eventID);

        } catch (NumberFormatException ex) {
            m = new Message(
                    "Cannot create the event. Invalid input parameters.",
                    "E100", ex.getMessage());

            LOGGER.error(
                    "Cannot create the event. Invalid input parameters.",
                    ex);
        } catch (SQLException ex) {
            
            m = new Message("Cannot create the event: unexpected error while accessing the database.", "E200",
                    ex.getMessage());

            LOGGER.error("Cannot create the event: unexpected error while accessing the database.", ex);
            
        } catch (FileNotFoundException fne) {
            LOGGER.info("You either did not specify a file to upload or are "
                    + "trying to upload a file to a protected or nonexistent "
                    + "location.");
            LOGGER.info("<br/> ERROR: " + fne.getMessage());

            LOGGER.info("Problems during file upload. Error: {0}", new Object[]{fne.getMessage()});
        }

    }

}
