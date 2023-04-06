package com.essentls.resource;

import java.util.Date;

import org.json.JSONObject;

public class Payment {
    private long id;
    private long userId;
    private long eventId;
    private String method;
    private float amount;
    private Date date;
    private String notes;
    
    public Payment(long id, long userId, long eventId, String method, float amount, Date date, String notes) {
        this.id = id;
        this.userId = userId;
        this.eventId = eventId;
        this.method = method;
        this.amount = amount;
        this.date = date;
        this.notes = notes;
    }

    public long getId() {
        return id;
    }

    public long getUserId() {
        return userId;
    }

    public long getEventId() {
        return eventId;
    }

    public String getMethod() {
        return method;
    }

    public float getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }

    public String getNotes() {
        return notes;
    }

    public JSONObject toJSON() {
        JSONObject uJson = new JSONObject();
        uJson.put("id", id);
        uJson.put("userId", userId);
        uJson.put("eventId", eventId);
        uJson.put("method", method);
        uJson.put("amount", amount);
        uJson.put("date", date);
        uJson.put("notes", notes);
        return uJson;
    }
}
