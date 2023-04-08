package com.essentls.resource;

public class EventTag {
    private long eventId;
    private String tagName;
    
    public EventTag(long eventId, String tagName) {
        this.eventId = eventId;
        this.tagName = tagName;
    }

    public long getEventId() {
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
