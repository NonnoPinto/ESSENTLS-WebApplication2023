package com.essentls.resource;

import java.sql.Date;
import org.json.JSONObject;
/**
 * @author Vittorio Cardillo
 * @version 1.0
 * @since 1.0
 */


/**
 * Represents a payment.
 */
public class Payment {
    private int id;
    private int userId;
    private int eventId;
    private String method;
    private float amount;
    private Date date;
    private String notes;

    /**
     * Creates a payment.
     *
     * @param id the id of the payment.
     *
     * @param userId the id of the user.
     *
     * @param eventId the id of the event.
     *
     * @param method the method of the payment.
     *
     * @param amount the amount of the payment.
     *
     * @param date the date of the payment.
     *
     * @param notes the notes of the payment.
     *
     */
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

    /**
     * Returns a JSON representation of the payment.
     *
     * @return a JSON representation of the payment.
     */

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
