package com.essentls.resource;

public class EventCause {
    private int eventId;
    private int causeId;

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

    @Override
    public String toString() {
        return "EventTag [eventId=" + eventId + ", causeId=" + causeId + "]";
    }
}
