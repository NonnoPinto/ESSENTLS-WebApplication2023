package com.essentls.resource;

import java.time.LocalDate;

import org.json.JSONObject;

public class Event {
    private long id;
    private String name;
    private String description;
    private float price;
    private boolean visibility;
    private String location;
    private int maxParticipantsInternational;
    private int maxParticipantsVolunteer;
    private LocalDate eventStart;
    private LocalDate eventEnd;
    private LocalDate subscriptionStart;
    private LocalDate subscriptionEnd;
    private LocalDate withdrawalEnd;
    private int maxWaitingList;
    private String attributes;
    private String thumbnail;
    private String poster;

    public Event(long id, String name, String description, float price, boolean visibility, String location,
            int maxParticipantsInternational, int maxParticipantsVolunteer, LocalDate eventStart, LocalDate eventEnd,
            LocalDate subscriptionStart, LocalDate subscriptionEnd, LocalDate withdrawalEnd, int maxWaitingList, String attributes, String thumbnail, String poster) {
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
    public Event(long id, String name, LocalDate startDate) {
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

    public boolean isVisibility() {
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

    public LocalDate getEventStart() {
        return eventStart;
    }

    public LocalDate getEventEnd() {
        return eventEnd;
    }

    public LocalDate getSubscriptionStart() {
        return subscriptionStart;
    }

    public LocalDate getSubscriptionEnd() {
        return subscriptionEnd;
    }

    public LocalDate getWithdrawalEnd() {
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
        return uJson;
    }
}
