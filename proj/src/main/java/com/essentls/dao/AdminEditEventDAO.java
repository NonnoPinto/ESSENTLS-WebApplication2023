package com.essentls.dao;

import com.essentls.resource.Event;

import java.util.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Admin Edit Event DAO, to modify details of an event
 *
 * @author Matteo VIllani (matteo.villani@studenti.unipd.it)
 * @version 1.00
 * @since 1.00
 */

public class AdminEditEventDAO extends AbstractDAO<Event>{

    /**
     * new attributes
     */
    private final String new_name;
    private final String new_description;
    private final float new_price;
    private final boolean new_visibility;
    private final String new_location;
    private final int new_maxParticipantsInternational;
    private final int new_maxParticipantsVolunteer;
    private final Date new_eventStart;
    private final Date new_eventEnd;
    private final Date new_subscriptionStart;
    private final Date new_subscriptionEnd;
    private final Date new_withdrawalEnd;
    private final int new_maxWaitingList;
    private final String new_attributes;
    private final String new_thumbnail;
    private final String new_poster;


    /**
     * Creates a new object for gather info about the event.
     *
     * @param con    the connection to the database.
     * @param event   the event selected.
     */
    public AdminEditEventDAO(final Connection con, final Event event, final String _newname,
                                                                      final String _newdescription,
                                                                      final float _newprice,
                                                                      final boolean _newvisibility,
                                                                      final String _newlocation,
                                                                      final int _newmaxParticipantsInternational,
                                                                      final int _newmaxParticipantsVolunteer,
                                                                      final Date _neweventStart,
                                                                      final Date _neweventEnd,
                                                                      final Date _newsubscriptionStart,
                                                                      final Date _newsubscriptionEnd,
                                                                      final Date _newwithdrawalEnd,
                                                                      final int _newmaxWaitingList,
                                                                      final String _newattributes,
                                                                      final String _newthumbnail,
                                                                      final String _newposter){

        super(con);
        this.event = event;

        new_name = _newname;
        new_description =_newdescription;
        new_price =_newprice;
        new_visibility = _newvisibility;
        new_location = _newlocation;
        new_maxParticipantsInternational = _newmaxParticipantsInternational;
        new_maxParticipantsVolunteer = _newmaxParticipantsVolunteer;
        new_eventStart = _neweventStart;    
        new_eventEnd = _neweventEnd;
        new_subscriptionStart = _newsubscriptionStart;
        new_subscriptionEnd = _newsubscriptionEnd;
        new_withdrawalEnd = _newwithdrawalEnd;
        new_maxWaitingList = _newmaxWaitingList;
        new_attributes = _newattributes;
        new_thumbnail = _newthumbnail;
        new_poster = _newposter;
    }


    /**
     * The SQL statement to be executed
     */
    private static final String STATEMENT = "SELECT name, description, price, visibility, location,maxParticipantsInternational, maxParticipantsVolunteer, eventStart, eventEnd, subscriptionStart, subscriptionEnd, withdrawalEnd, maxWaitingList, attributes, thumbnail, poster FROM Events WHERE id = ?";
  


    /**
     * The event selected to be edited by the Admin
     */
    private final Event event;

    @Override
    public final void doAccess() throws SQLException{

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try{

            stmt = con.prepareStatement(STATEMENT);
            stmt.setLong(1, this.event.getId());


            this.event.setName(new_name);
            this.event.setDescription(new_description);
            this.event.setPrice(new_price);
            this.event.setVisivility(new_visibility);
            this.event.setLocation(new_location);
            this.event.setMaxParticipantsInternational(new_maxParticipantsInternational);
            this.event.setMaxParticipantsVolunteer(new_maxParticipantsVolunteer);
            this.event.setEventStart(new_eventStart);
            this.event.setEventEnd(new_eventEnd);
            this.event.setSubscriptionStart(new_subscriptionStart);
            this.event.setSubscriptionEnd(new_subscriptionEnd);
            this.event.setWithdrawalEnd(new_withdrawalEnd);
            this.event.setMaxWaitingList(new_maxWaitingList);
            this.event.setAttributes(new_attributes);
            this.event.setThumbnail(new_thumbnail);
            this.event.setPoster(new_poster);

            LOGGER.info("Details of event {} successifully changed.", this.event.getId());
        }finally{
            if(rs != null){
                rs.close();
            }
            if(stmt != null){
                stmt.close();
            }
        }
        con.close();
    }
}