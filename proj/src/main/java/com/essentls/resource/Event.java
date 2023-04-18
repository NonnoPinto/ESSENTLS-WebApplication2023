package com.essentls.resource;

import java.awt.image.BufferedImage;
import java.sql.Date;

import org.json.JSONObject;

public class Event {
    private long id;
    private String name;
    private String description;
    private float price;
    private int visibility;
    private String location;
    private int maxParticipantsInternational;
    private int maxParticipantsVolunteer;
    private Date eventStart;
    private Date eventEnd;
    private Date subscriptionStart;
    private Date subscriptionEnd;
    private Date withdrawalEnd;
    private int maxWaitingList;
    private String attributes;
    private String thumbnail;
    private String poster;


    //TODO: in JoinedEventsList, Event has less argouments: maybe a default constructor?
    public Event(long id, String name, String description, float price, int visibility, String location,
            int maxParticipantsInternational, int maxParticipantsVolunteer, Date eventStart, Date eventEnd,
            Date subscriptionStart, Date subscriptionEnd, Date withdrawalEnd, int maxWaitingList, String attributes, String thumbnail, String poster) {
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

    // Used in the profile to get a list of the joined events 
    public Event(long id, String name, Date startDate) {
        this.id = id;
        this.name = name;
        this.eventStart = startDate;
    }

    public long getId() {
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

    public String getLocation() {
        return location;
    }

    public int getMaxParticipantsInternational() {
        return maxParticipantsInternational;
    }

    public int getMaxParticipantsVolunteer() {
        return maxParticipantsVolunteer;
    }

    public Date getEventStart() {
        return eventStart;
    }

    public Date getEventEnd() {
        return eventEnd;
    }

    public Date getSubscriptionStart() {
        return subscriptionStart;
    }

    public Date getSubscriptionEnd() {
        return subscriptionEnd;
    }

    public Date getWithdrawalEnd() {
        return withdrawalEnd;
    }

    public int getMaxWaitingList() {
        return maxWaitingList;
    }

    public String getAttributes() {
        return attributes;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getPoster() {
        return poster;
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
    public void setLocation(String _location){
        location = _location;
    }
    public void setMaxParticipantsInternational(int _maxParticipantsInternational){
        maxParticipantsInternational = _maxParticipantsInternational;
    }
    public void setMaxParticipantsVolunteer(int _maxParticipantsVolunteer){
        maxParticipantsVolunteer = _maxParticipantsVolunteer;
    }
    public void setEventStart(Date _eventStart){
        eventStart = _eventStart;
    }
    public void setEventEnd(Date _eventEnd){
        eventEnd = _eventEnd;
    }
    public void setSubscriptionStart(Date _subscriptionStart){
        subscriptionStart = _subscriptionStart;
    }
    public void setSubscriptionEnd(Date _subscriptionEnd){
        subscriptionEnd = _subscriptionEnd;
    }
    public void setWithdrawalEnd(Date _withdrawalEnd){
        withdrawalEnd = _withdrawalEnd;
    }
    public void setMaxWaitingList(int _maxWaitingList){
        maxWaitingList = _maxWaitingList;
    }
    public void setAttributes(String _attributes){
        attributes = _attributes;
    }
    public void setThumbnail(String _thumbnail){
        thumbnail = _thumbnail;
    }
    public void setPoster(String _poster){
        poster = _poster;
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
