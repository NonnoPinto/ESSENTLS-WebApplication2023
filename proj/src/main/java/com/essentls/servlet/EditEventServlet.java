package com.essentls.servlet;

import com.essentls.dao.*;
import com.essentls.resource.Event;
import com.essentls.resource.Message;
import com.essentls.resource.Participant;
import com.essentls.resource.User;
import jakarta.servlet.ServletException;
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
import java.util.List;

@WebServlet(name = "EditEventServlet", urlPatterns = {"", "/editEvent"})
public final class EditEventServlet extends AbstractDatabaseServlet {

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

    private String getAbsoluteCloudPath(){
        URL url = EditEventServlet.class.getProtectionDomain().getCodeSource().getLocation();
        File file = new java.io.File(url.getFile());
        File parent = file.getParentFile();
        while (!"proj-1.0".equals(parent.getName()))
        {
            parent = parent.getParentFile();
        }

        return (parent.getPath() + File.separator + "ESSENTLS_Cloud").replaceAll("%20", " ");
    }

    private String getProjectPath(){
        URL url = EditEventServlet.class.getProtectionDomain().getCodeSource().getLocation();
        File file = new java.io.File(url.getFile());
        File parent = file.getParentFile();
        while (!"proj-1.0".equals(parent.getName()))
        {
            parent = parent.getParentFile();
        }

        return (parent.getPath()).replaceAll("%20", " ");
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        LogContext.setIPAddress(req.getRemoteAddr());
        LogContext.setResource(req.getRequestURI());
        LogContext.setResource(req.getRequestURI());
        LogContext.setAction("EDIT EVENT");

        Integer eventId = Integer.parseInt(req.getParameter("id").trim());
        try {
            Event e = new EventInfoDAO(getConnection(),eventId).access().getOutputParam();
            HttpSession session = req.getSession();
            long userId = -1;
            if(session.getAttribute("sessionUserId") != null)
                userId = (long)session.getAttribute("sessionUserId");
            User user = new UserProfileInfoDAO(getConnection(), userId).access().getOutputParam();
            if(user == null || user.getTier() < 3){ //Auth check TODO make three dynamic
                req.getRequestDispatcher("/jsp/unauthorized.jsp").forward(req, res);
            }else {
                req.setAttribute("event", e);
                req.setAttribute("city", e.getLocation().getString("city"));
                req.setAttribute("street", e.getLocation().getString("street"));
                req.setAttribute("number", e.getLocation().getString("number"));
                req.setAttribute("thumbnail", ""+getProjectPath()+e.getThumbnail());
                req.setAttribute("poster", ""+getProjectPath()+e.getPoster());
                req.getRequestDispatcher("/jsp/editevent.jsp").forward(req, res);
            }
        } catch (Exception e) {
            LOGGER.error("stacktrace:", e);
            throw new ServletException(e);
        }


    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        LogContext.setIPAddress(req.getRemoteAddr());
        LogContext.setAction("EDIT EVENT");

        HttpSession session = req.getSession();
        Integer eventID = (Integer)session.getAttribute("sessionEventId");

        // request parameters
        String name = null;
        String description = null;
        float price = -1;
        int visibility = 0;
        JSONObject location = null;
        String city;
        String street;
        String number;
        int maxPartecipantsInternational = -1;
        int maxPartecipantVolunteer = -1;
        LocalDateTime eventStart = null;
        LocalDateTime eventEnd = null;
        LocalDateTime subscriptionStart = null;
        LocalDateTime subscriptionEnd = null;
        LocalDateTime withdrawalEnd = null;
        int maxWaitingList = -1;
        String[] attributes = null;
        String thumbnail = null;
        String poster = null;

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
            visibility = Integer.parseInt(req.getParameter("visibility"));
            city= req.getParameter("city");
            street= req.getParameter("street");
            number= req.getParameter("number");
            maxPartecipantsInternational = Integer.parseInt(req.getParameter("maxParticipantsInternational"));
            maxPartecipantVolunteer = Integer.parseInt(req.getParameter("maxParticipantsVolunteer"));
            eventStart = LocalDateTime.parse(req.getParameter("eventStart"), formatter);
            eventEnd = LocalDateTime.parse(req.getParameter("eventEnd"), formatter);
            subscriptionStart = LocalDateTime.parse(req.getParameter("subscriptionStart"), formatter);
            subscriptionEnd = LocalDateTime.parse(req.getParameter("subscriptionEnd"), formatter);
            withdrawalEnd = LocalDateTime.parse(req.getParameter("withdrawalEnd"), formatter);
            maxWaitingList = Integer.parseInt(req.getParameter("maxWaitingList"));
            attributes = req.getParameter("attributes").replace(", ",",").split(",");

            // set the name of the event as the resource in the log context
            LogContext.setResource(req.getParameter("name"));

            location = new JSONObject();
            location = location.put("city", city);
            location = location.put("street", street);
            location = location.put("number", number);

            LOGGER.info("The location is:  \""+location.toString()+"\"");

            //create path
            final String path = getAbsoluteCloudPath();
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
                String posterName = getFileName(posterPart);
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
                e = new EventInfoDAO(getConnection(),eventID).access().getOutputParam();
                poster = e.getPoster();
            }

            if(isThereAThumbnail){
                String thumbnailName = getFileName(thumbnailPart);
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
                e = new EventInfoDAO(getConnection(),eventID).access().getOutputParam();
                thumbnail = e.getThumbnail();
            }

            // creates a new event from the request parameters
            e = new Event(name, description, price, visibility, location, maxPartecipantsInternational,
                    maxPartecipantVolunteer, Timestamp.valueOf(eventStart), Timestamp.valueOf(eventEnd),
                    Timestamp.valueOf(subscriptionStart), Timestamp.valueOf(subscriptionEnd), Timestamp.valueOf(withdrawalEnd),
                    maxWaitingList, attributes, thumbnail, poster);

            // creates a new object for accessing the database and stores the event
            new AdminEditEventDAO(getConnection(), e).access();

            m = new Message(String.format("Event \""+e.getName()+"\" successfully created."));

            LOGGER.info("Event \""+e.getName()+"\" successfully created in the database.");
            req.setAttribute("message", m);
            req.getRequestDispatcher("/jsp/home.jsp").forward(req, res);

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
