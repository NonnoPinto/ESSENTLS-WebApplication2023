package com.essentls.resource;

import java.sql.Date;
import java.sql.Timestamp;

import org.json.JSONObject;

public class Payment {
    private int id;
    private int userId;
    private int eventId;
    private String method;
    private float amount;
    private Date date;
    private String notes;
    
    public Payment(int id, int userId, int eventId, String method, float amount, Date date, String notes) {
        this.id = id;
        this.userId = userId;
        this.eventId = eventId;
        this.method = method;
        this.amount = amount;
        this.date = date;
        this.notes = notes;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public int getEventId() {
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
