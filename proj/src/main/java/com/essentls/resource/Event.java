package com.essentls.resource;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONObject;

import static com.essentls.resource.AbstractResource.LOGGER;

/**
 * Event class
 * @author Vittorio Cardillo
 * @version 1.0
 * @since 1.0
 */
public class Event {
    private int id;
    private String name;
    private String description;
    private float price;
    private int visibility;
    private JSONObject location;
    private int maxParticipantsInternational;
    private int maxParticipantsVolunteer;
    private Timestamp eventStart;
    private Timestamp eventEnd;
    private Timestamp subscriptionStart;
    private Timestamp subscriptionEnd;
    private Timestamp withdrawalEnd;
    private int maxWaitingList;
    private String[] attributes;
    private String thumbnail;
    private String poster;

    /**
     * Constructor of event
     */
    public Event(int id, String name, String description, float price, int visibility, JSONObject location,
            int maxParticipantsInternational, int maxParticipantsVolunteer, Timestamp eventStart, Timestamp eventEnd,
                 Timestamp subscriptionStart, Timestamp subscriptionEnd, Timestamp withdrawalEnd, int maxWaitingList, String[] attributes, String thumbnail, String poster) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.visibility = visibility;
        this.location = location;
        this.maxParticipantsInternational = maxParticipantsInternational;
        this.maxParticipantsVolunteer = maxParticipantsVolunteer;
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
        this.subscriptionStart = subscriptionStart;
        this.subscriptionEnd = subscriptionEnd;
        this.withdrawalEnd = withdrawalEnd;
        this.maxWaitingList = maxWaitingList;
        this.attributes = attributes;
        this.thumbnail = thumbnail;
        this.poster = poster;
    }

    /**
     * Constructor of event, Used in the home to get the list of events
     */
    public Event(int id, String name, String description, float price, JSONObject location, Timestamp eventStart, Timestamp eventEnd, Timestamp subscriptionEnd, String thumbnail) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.location = location;
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
        this.subscriptionEnd = subscriptionEnd;
        this.thumbnail = thumbnail;
    }

    /**
     * Constructor of event, Used in the profile to get a list of the joined events
     */
    public Event(int id, String name, Timestamp startDate) {
        this.id = id;
        this.name = name;
        this.eventStart = startDate;
    }

    /**
     *   Constructor of event, without id
     */
    public Event(String name, String description, float price, int visibility, JSONObject location, int maxParticipantsInternational, int maxParticipantsVolunteer, Timestamp eventStart, Timestamp eventEnd, Timestamp subscriptionStart, Timestamp subscriptionEnd, Timestamp withdrawalEnd, int maxWaitingList, String[] attributes, String thumbnail, String poster) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.visibility = visibility;
        this.location = location;
        this.maxParticipantsInternational = maxParticipantsInternational;
        this.maxParticipantsVolunteer = maxParticipantsVolunteer;
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
        this.subscriptionStart = subscriptionStart;
        this.subscriptionEnd = subscriptionEnd;
        this.withdrawalEnd = withdrawalEnd;
        this.maxWaitingList = maxWaitingList;
        this.attributes = attributes;
        this.thumbnail = thumbnail;
        this.poster = poster;

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public float getPrice() {
        return price;
    }

    public int getVisibility() {
        return visibility;
    }

    public JSONObject getLocation() {
        return location;
    }

    public int getMaxParticipantsInternational() {
        return maxParticipantsInternational;
    }

    public int getMaxParticipantsVolunteer() {
        return maxParticipantsVolunteer;
    }

    public Timestamp getEventStart() {
        return eventStart;
    }

    public Timestamp getEventEnd() {
        return eventEnd;
    }

    public Timestamp getSubscriptionStart() {
        return subscriptionStart;
    }

    public Timestamp getSubscriptionEnd() {
        return subscriptionEnd;
    }

    public Timestamp getWithdrawalEnd() {
        return withdrawalEnd;
    }

    public int getMaxWaitingList() {
        return maxWaitingList;
    }

    public String[] getAttributes() {
        return attributes;
    }

    public String getThumbnail() {
        return thumbnail.replace('\\', '/');
    }

    public String getPoster() {
        return poster.replace('\\', '/');
    }

    public String getAttributes_asString(){
        String s = "";
        if (this.attributes.length == 0){ return "";}
        if (this.attributes.length ==1){ return this.attributes[0];}
        s += this.attributes[0];
        for (int i=1; i<this.attributes.length; i++) {
            s += ", ";
            s += this.attributes[i];
        }
        return s;
    }
    

    public void setName(String _name){
        name = _name;
    }
    public void setDescription(String _description){
        name = _description;
    }
    public void setPrice(float _price){
        price = _price;
    }
    public void setVisibility(int _visibility){
        visibility = _visibility;
    }
    public void setLocation(JSONObject _location){
        location = _location;
    }
    public void setMaxParticipantsInternational(int _maxParticipantsInternational){
        maxParticipantsInternational = _maxParticipantsInternational;
    }
    public void setMaxParticipantsVolunteer(int _maxParticipantsVolunteer){
        maxParticipantsVolunteer = _maxParticipantsVolunteer;
    }
    public void setEventStart(Timestamp _eventStart){
        eventStart = _eventStart;
    }
    public void setEventEnd(Timestamp _eventEnd){
        eventEnd = _eventEnd;
    }
    public void setSubscriptionStart(Timestamp _subscriptionStart){
        subscriptionStart = _subscriptionStart;
    }
    public void setSubscriptionEnd(Timestamp _subscriptionEnd){
        subscriptionEnd = _subscriptionEnd;
    }
    public void setWithdrawalEnd(Timestamp _withdrawalEnd){
        withdrawalEnd = _withdrawalEnd;
    }
    public void setMaxWaitingList(int _maxWaitingList){
        maxWaitingList = _maxWaitingList;
    }
    public void setAttributes(String[] _attributes){
        attributes = _attributes;
    }
    public void setThumbnail(String _thumbnail){
        thumbnail = _thumbnail;
    }
    public void setPoster(String _poster){
        poster = _poster;
    }

    public Map<String, String> getLocationMap() {
        Map<String, String> locationMap = new HashMap<>();
        try {
            Iterator<String> locationKeys = this.location.keys();
            while (locationKeys.hasNext()) {
                String key = locationKeys.next();
                String value = this.location.getString(key);
                locationMap.put(key, value);
            }
        }catch(Exception e){
            LOGGER.error("Invalid attributes on database");
        }
        return locationMap;
    }

    public JSONObject toJSON() {
        JSONObject uJson = new JSONObject();
        uJson.put("id", id);
        uJson.put("name", name);
        uJson.put("description", description);
        uJson.put("price", price);
        uJson.put("visibility", visibility);
        uJson.put("location", location);
        uJson.put("maxParticipantsInternational", maxParticipantsInternational);
        uJson.put("maxParticipantsVolunteer", maxParticipantsVolunteer);
        uJson.put("eventStart", eventStart);
        uJson.put("eventEnd", eventEnd);
        uJson.put("subscriptionStart", subscriptionStart);
        uJson.put("subscriptionEnd", subscriptionEnd);
        uJson.put("withdrawalEnd", withdrawalEnd);
        uJson.put("maxWaitingList", maxWaitingList);
        uJson.put("attributes", attributes);
        uJson.put("thumbnail", thumbnail);
        uJson.put("poster", poster);
        return uJson;
    }
}
