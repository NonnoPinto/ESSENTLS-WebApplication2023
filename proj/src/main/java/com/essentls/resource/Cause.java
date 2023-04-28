package com.essentls.resource;

import java.io.*;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.io.OutputStream;

/**
 * Cause class
 * @author Laura Pallante
 * @version 1.0
 * @since 1.0
 */
public class Cause extends AbstractResource{

    private int id;
    private String name;

    /**
     * Default constructor
     */
    public Cause(int id, String name) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return this.name;
    }
    public int getId() {
        return this.id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Cause [id=" +this.id + "] [name=" + this.name + "]";
    }

    /**
     * Writes the cause object to JSON
     * @param out the output stream to write to
     * @throws IOException if an error occurs while writing to the stream
     */
    @Override
    protected void writeJSON(final OutputStream out) throws IOException {
        final JsonGenerator jg = JSON_FACTORY.createGenerator(out);

        jg.writeStartObject();

        jg.writeFieldName("esncause");

        jg.writeStartObject();

        jg.writeNumberField("id", this.id);
        jg.writeStringField("name", this.name);

        jg.writeEndObject();

        jg.writeEndObject();

        jg.flush();
    }

    /**
     * Reads a cause object from JSON
     * @param in the input stream to read from
     * @return the cause object
     * @throws IOException if an error occurs while reading from the stream
     */

    public static Cause fromJSON(final InputStream in) throws IOException  {

        // the fields read from JSON
        int id = -1;
        String name = null;


        try {
            final JsonParser jp = JSON_FACTORY.createParser(in);

            // while we are not on the start of an element or the element is not
            // a token element, advance to the next element (if any)
            while (jp.getCurrentToken() != JsonToken.FIELD_NAME || !"esncause".equals(jp.getCurrentName())) {

                // there are no more events
                if (jp.nextToken() == null) {
                    LOGGER.error("No cause object found in the stream.");
                    throw new EOFException("Unable to parse JSON: no cause object found.");
                }
            }

            while (jp.nextToken() != JsonToken.END_OBJECT) {

                if (jp.getCurrentToken() == JsonToken.FIELD_NAME) {

                    switch (jp.getCurrentName()) {
                        case "id" -> {
                            jp.nextToken();
                            id = jp.getIntValue();
                        }
                        case "name" -> {
                            jp.nextToken();
                            name = jp.getText();
                        }
                    }
                    LOGGER.info("id: " + id + " name: " + name);
                }
            }
        } catch(IOException e) {
            LOGGER.error("Unable to parse a cause object from JSON.", e);
            throw e;
        }

        return new Cause(id,name);
    }

}
