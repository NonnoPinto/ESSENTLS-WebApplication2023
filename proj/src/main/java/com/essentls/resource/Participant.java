package com.essentls.resource;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

public class Participant extends User{
    private long userId;
    private long eventId;
    private String role;
    private Date date;
    private String attributeValues;

    public Participant(long userId, long eventId, String role, Date date, String attributeValues) {
        super();
        this.userId = userId;
        this.eventId = eventId;
        this.role = role;
        this.date = date;
        this.attributeValues = attributeValues;
    }

    public Participant(long userId, long eventId, String role, Date date, String attributeValues, User user) {
        super(user);
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

    public Date getDate() {
        return date;
    }

    public String getAttributeValues() {
        return attributeValues;
    }

    public List<EventAttribute> getAttributeList() {
        List<EventAttribute> attributeList = new ArrayList<>();
        JSONObject attributes = new JSONObject(this.attributeValues);
        Iterator<String> attributesKeys = attributes.keys();
        while(attributesKeys.hasNext()){
            String key = attributesKeys.next();
            String value = attributes.getString(key);
            attributeList.add(new EventAttribute(key, value));
        }
        return attributeList;
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
