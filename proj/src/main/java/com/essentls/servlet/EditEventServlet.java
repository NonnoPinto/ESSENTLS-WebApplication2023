package com.essentls.servlet;

import com.essentls.dao.*;
import com.essentls.resource.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Retrieves an event from the database and redirects the user to the event edit page. Saves the changes input by the
 * user to the event in the database.
 *
 * @author Francesco Marcato
 * @version 1.00
 * @since 1.00
 */
@WebServlet(name = "EditEventServlet", urlPatterns = {"", "/editEvent"})
@MultipartConfig
public final class EditEventServlet extends AbstractDatabaseServlet {

    /**
     * A method that processes a file to retrieve its name without the preceding path.
     *
     * @param part a {@link Part} object that contains the file to be processed.
     * @return a {@link String} object that contains the name of the file.
     */
    private String getFileName(final Part part) {
        final String partHeader = part.getHeader("content-disposition");
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
     * Handles the HTTP {@code GET} method. Retrieves the event from the database and redirects the user to the event
     * edit page.
     *
     * @param req a {@link HttpServletRequest} object that contains the request the client has made of the servlet.
     * @param res a {@link HttpServletResponse} object that contains the response the servlet sends to the client.
     *
     * @throws ServletException if the request for the GET could not be handled.
     * @throws IOException if an input or output error is detected when the servlet handles the GET request.
     */
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        LogContext.setIPAddress(req.getRemoteAddr());
        LogContext.setResource(req.getRequestURI());
        LogContext.setResource(req.getRequestURI());
        LogContext.setAction("EDIT EVENT");

        Integer eventId = Integer.parseInt(req.getParameter("id").trim());

        HttpSession session = req.getSession();
        session.setAttribute("sessionEventId", eventId);

        try {
            Event e = new EventInfoDAO(getConnection(),eventId).access().getOutputParam();
            if(e==null){
                req.getRequestDispatcher("/jsp/error.jsp").forward(req, res);
                return;
            }
            ArrayList<Integer> eventCauses = null;
            ArrayList<String> eventTags = null;
            int userId = -1;
            List<Cause> causes = null;
            List<Tag> tags = null;
            if(session.getAttribute("sessionUserId") != null)
                userId = (int)session.getAttribute("sessionUserId");
            User user = new UserProfileInfoDAO(getConnection(), userId).access().getOutputParam();
            List<Participant> participants = new AdminParticipantsListDAO(getConnection(), eventId).access().getOutputParam();
            boolean canEditEvent = false;

            if (user.getTier() == 4){
                canEditEvent = true;
            } else {
                for (Participant p : participants) {
                    if(p.getUserId() == user.getId()){
                        //if tier < 4 can edit only if is organizer
                        if (!canEditEvent && user.getTier() >= 2 && p.getRole().equals("Organizer")){
                            canEditEvent = true;
                        }
                        break;
                    }
                }
            }

            String contextFolder = req.getContextPath().replace("/","").replace("\\","");


            if(user == null || !canEditEvent){ //Auth check
                req.getRequestDispatcher("/jsp/unauthorized.jsp").forward(req, res);
            }else {
                req.setAttribute("event", e);

                try {
                    causes = new CausesListDAO(getConnection(), -1, "").access().getOutputParam();
                    eventCauses = new CausesFromEventDAO(getConnection(), eventId).access().getOutputParam();
                }catch (SQLException sqle){
                    LOGGER.info("Unexpected Database error: "+sqle.getMessage());
                }
                eventTags = new ArrayList<String>();
                try {
                    tags = new TagsListDAO(getConnection(), "").access().getOutputParam();
                    eventTags = new TagsFromEventDAO(getConnection(), eventId).access().getOutputParam();
                }catch (SQLException sqle){
                    LOGGER.info("Unexpected Database error: "+sqle.getMessage());
                }

                req.setAttribute("causes", causes);
                req.setAttribute("listCauses", eventCauses);
                req.setAttribute("tags", tags);
                req.setAttribute("event_tags", String.join(", ", eventTags));
                req.getRequestDispatcher("/jsp/editevent.jsp").forward(req, res);
            }
        } catch (Exception e) {
            LOGGER.error("stacktrace:", e);
            throw new ServletException(e);
        }


    }

    /**
     * Handles the HTTP {@code POST} method. Saves the changes input by the user to the event in the database.
     *
     * @param req a {@link HttpServletRequest} object that contains the request the client has made of the servlet.
     * @param res a {@link HttpServletResponse} object that contains the response the servlet sends to the client.
     * @throws ServletException if the request for the POST could not be handled.
     * @throws IOException if an input or output error is detected when the servlet handles the POST request.
     */
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        LogContext.setIPAddress(req.getRemoteAddr());
        LogContext.setAction("EDIT EVENT");

        HttpSession session = req.getSession();

        Integer eventID = (Integer)session.getAttribute("sessionEventId");
        Event oldEvent = null;
        try {
            oldEvent = new EventInfoDAO(getConnection(), eventID).access().getOutputParam();
        }catch (SQLException sqle){
            Message m = new Message("Cannot edit the event: unexpected error while accessing the database.");

            LOGGER.error("Cannot edit the event: unexpected error while accessing the database.");

        }

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

        try {
            // retrieves the request parameters
            name = req.getParameter("name");
            description = req.getParameter("description");
            price = Float.parseFloat(req.getParameter("price"));
            if(req.getParameter("visibility") == null){
                visibility = oldEvent.getVisibility();
            }else{
                visibility = Integer.parseInt(req.getParameter("visibility"));
            }
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
            attributes = req.getParameter("attributes").trim().replace(", ",",").replace(" ,",",").split(",");

            // set the name of the event as the resource in the log context
            LogContext.setResource(req.getParameter("name"));

            location = new JSONObject();
            location.put("city", city);
            location.put("street", street);
            location.put("number", number);
            location.put("country", country);

            LOGGER.info("The location is:  \""+location.toString()+"\"");

            String contextFolder = req.getContextPath().replace("/","").replace("\\","");


            //create path

            String contextPath = req.getServletContext().getRealPath("/");

            final String path = contextPath+"ESSENTLS_Cloud";
            final String relative_path = "ESSENTLS_Cloud";

            //create folder if doesn't exists
            new File(path).mkdirs();

            // get file
            boolean isThereAPoster = false;
            boolean isThereAThumbnail = false;
            Part posterPart = null;
            Part thumbnailPart = null;

            if(req.getPart("poster").getSize()>0){
                isThereAPoster = true;
                posterPart = req.getPart("poster");
            }
            if(req.getPart("poster").getSize()<=0){
                isThereAPoster = false;
            }
            if(req.getPart("thumbnail").getSize()>0){
                isThereAThumbnail = true;
                thumbnailPart = req.getPart("thumbnail");
            }
            if(req.getPart("thumbnail").getSize()<=0){
                isThereAThumbnail = false;
            }

            if(isThereAPoster){
                String posterOrigName = getFileName(posterPart);
                String posterExt = posterOrigName.split("\\.")[1];
                String posterName = "poster_"+eventID+"."+posterExt;
                OutputStream out = null;
                InputStream filecontent = null;

                //save poster

                out = new FileOutputStream(new File(path + File.separator + posterName));
                filecontent = posterPart.getInputStream();

                int read = 0;
                final byte[] posterBytes = new byte[1024];

                while ((read = filecontent.read(posterBytes)) != -1) {
                    out.write(posterBytes, 0, read);
                }
                LOGGER.info("New file " + posterName + " created at " + path);

                poster = relative_path + File.separator + posterName;
            }else{
                poster = oldEvent.getPoster();
            }

            if(isThereAThumbnail){
                String thumbnailOrigName = getFileName(thumbnailPart);
                String thumbnailExt = thumbnailOrigName.split("\\.")[1];
                String thumbnailName = "thumbnail_"+eventID+"."+thumbnailExt;

                OutputStream out = null;
                InputStream filecontent = null;

                //save poster

                out = new FileOutputStream(new File(path + File.separator + thumbnailName));
                filecontent = thumbnailPart.getInputStream();

                int read = 0;
                final byte[] thumbnailBytes = new byte[1024];

                while ((read = filecontent.read(thumbnailBytes)) != -1) {
                    out.write(thumbnailBytes, 0, read);
                }
                LOGGER.info("New file " + thumbnailName + " created at " + path);

                thumbnail = relative_path + File.separator + thumbnailName;
            }else{
                thumbnail = oldEvent.getThumbnail();
            }

            // creates a new event from the request parameters

            e = new Event(oldEvent.getId(), name, description, price, visibility, location, maxParticipantsInternational,
                    maxParticipantVolunteer, Timestamp.valueOf(eventStart), Timestamp.valueOf(eventEnd),
                    Timestamp.valueOf(subscriptionStart), Timestamp.valueOf(subscriptionEnd), Timestamp.valueOf(withdrawalEnd),
                    maxWaitingList, attributes, thumbnail, poster, new ArrayList<String>(), new ArrayList<String>());

            // creates a new object for accessing the database and stores the event
            new AdminEditEventDAO(getConnection(), e).access();

            List<Cause> causes = new ArrayList<>();
            try {
                causes = new CausesListDAO(getConnection(), -1, "").access().getOutputParam();
            }catch (SQLException sqle){
                LOGGER.info("Unexpected Database error: "+sqle.getMessage());
            }

            new EventCausesDeleteDAO(getConnection(), eventID).access();
            for (Cause cause:causes) {
                int causeId= cause.getId();
                EventCause ec= new EventCause(eventID, causeId);
                if (cause.getName().equals(req.getParameter("cs_"+causeId))){
                    new EventCausesCreationDAO(getConnection(), ec).access();
                }
            }
            new EventTagsDeleteDAO(getConnection(), eventID).access();

            String tags_input = req.getParameter("tags");
            String[] tags = new String[0];
            if(tags_input != null && tags_input.trim().length() > 0){
                tags = tags_input.trim().split(",");
            }
            for (String tag:tags) {
                EventTag et= new EventTag(eventID, tag);
                new EventTagsCreationDAO(getConnection(), et).access();
            }

            m = new Message(String.format("Event \""+e.getName()+"\" successfully edited."));

            LOGGER.info("Event \""+e.getName()+"\" successfully edited in the database.");
            req.setAttribute("message", m);
            res.sendRedirect(req.getContextPath()+"/home");

        } catch (NumberFormatException ex) {
            m = new Message(
                    "Cannot edit the event. Invalid input parameters.",
                    "E100", ex.getMessage());

            LOGGER.error(
                    "Cannot edit the event. Invalid input parameters.",
                    ex);
        } catch (SQLException ex) {

            m = new Message("Cannot edit the event: unexpected error while accessing the database.", "E200",
                    ex.getMessage());

            LOGGER.error("Cannot edit the event: unexpected error while accessing the database.", ex);

        } catch (FileNotFoundException fne) {
            LOGGER.info("You either did not specify a file to upload or are "
                    + "trying to upload a file to a protected or nonexistent "
                    + "location.");
            LOGGER.info("<br/> ERROR: " + fne.getMessage());

            LOGGER.info("Problems during file upload. Error: {0}", new Object[]{fne.getMessage()});
        }
    }

}
