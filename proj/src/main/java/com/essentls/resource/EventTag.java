package com.essentls.resource;

public class EventTag {
    private int eventId;
    private String tagName;
    
    public EventTag(int eventId, String tagName) {
        this.eventId = eventId;
        this.tagName = tagName;
    }

    public int getEventId() {
        return eventId;
    }

    public String getTagName() {
        return tagName;
    }

    @Override
    public String toString() {
        return "EventTag [eventId=" + eventId + ", tagName=" + tagName + "]";
    }
}
