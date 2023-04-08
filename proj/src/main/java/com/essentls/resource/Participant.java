package com.essentls.resource;

import java.time.LocalDate;

import org.json.JSONObject;

public class Participant {
    private long userId;
    private long eventId;
    private String role;
    private LocalDate date;
    private String attributeValues;
    
    public Participant(long userId, long eventId, String role, LocalDate date, String attributeValues) {
        this.userId = userId;
        this.eventId = eventId;
        this.role = role;
        this.date = date;
        this.attributeValues = attributeValues;
    }

    public long getUserId() {
        return userId;
    }

    public long getEventId() {
        return eventId;
    }

    public String getRole() {
        return role;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getAttributeValues() {
        return attributeValues;
    }

    public JSONObject toJSON() {
        JSONObject uJson = new JSONObject();
        uJson.put("userId", userId);
        uJson.put("eventId", eventId);
        uJson.put("role", role);
        uJson.put("date", date);
        uJson.put("attributeValues", attributeValues);
        return uJson;
    }
}
