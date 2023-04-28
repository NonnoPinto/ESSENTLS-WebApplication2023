package com.essentls.resource;

/**
 * @author Alessandro Borsato (alessandro.borsato.1@studenti.unipd.it)
 * @version 1.0
 * @since 1.0
 * EventCause class
 */
public class EventCause {
    private int eventId;
    private int causeId;

    /**
     * Default constructor
     */
    public EventCause(int eventId, int causeId) {
        this.eventId = eventId;
        this.causeId = causeId;
    }

    public int getEventId() {
        return eventId;
    }

    public int getCauseId() {
        return causeId;
    }

    /**
     * Writes the eventCause object to JSON
     */
    @Override
    public String toString() {
        return "EventTag [eventId=" + eventId + ", causeId=" + causeId + "]";
    }
}
