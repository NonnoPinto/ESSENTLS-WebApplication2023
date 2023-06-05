package com.essentls.resource;

/**
 * @author Alessandro Borsato (alessandro.borsato.1@studenti.unipd.it)
 * @version 1.0
 * @since 1.0
 * EventTag class
 */
public class EventTag {
    private int eventId;
    private String tagName;

    /**
     * Default constructor
     */
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

    /**
     * Writes the eventCause object to JSON
     */
    @Override
    public String toString() {
        return "EventTag [eventId=" + eventId + ", tagName=" + tagName + "]";
    }
}
