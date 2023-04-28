package com.essentls.resource;

import java.io.*;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.io.OutputStream;

/**
 * Represents a tag.
 * @author Laura Pallante
 * @version 1.00
 * @since 1.00
 */
public class Tag extends AbstractResource{
    private String name;

    public Tag(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Tag [name=" + name + "]";
    }

    @Override
    protected void writeJSON(final OutputStream out) throws IOException {
        final JsonGenerator jg = JSON_FACTORY.createGenerator(out);

        jg.writeStartObject();

        jg.writeFieldName("esntag");

        jg.writeStartObject();

        jg.writeStringField("name", name);

        jg.writeEndObject();

        jg.writeEndObject();

        jg.flush();
    }


    /**
     * Reads a tag object from JSON
     * @param in the input stream to read from
     * @return the tag object
     * @throws IOException if an error occurs while reading from the stream
     */
    public static Tag fromJSON(final InputStream in) throws IOException  {

        // the fields read from JSON

        String name = null;


        try {
            final JsonParser jp = JSON_FACTORY.createParser(in);

            // while we are not on the start of an element or the element is not
            // a token element, advance to the next element (if any)
            while (jp.getCurrentToken() != JsonToken.FIELD_NAME || !"esntag".equals(jp.getCurrentName())) {

                // there are no more events
                if (jp.nextToken() == null) {
                    LOGGER.error("No tag object found in the stream.");
                    throw new EOFException("Unable to parse JSON: no tag object found.");
                }
            }

            while (jp.nextToken() != JsonToken.END_OBJECT) {

                if (jp.getCurrentToken() == JsonToken.FIELD_NAME) {

                    if (jp.getCurrentName().equals("name")) {
                        jp.nextToken();
                        name = jp.getText().replaceAll("[^a-zA-Z0-9]", "");
                    }
                }
            }
        } catch(IOException e) {
            LOGGER.error("Unable to parse a tag object from JSON.", e);
            throw e;
        }

        return new Tag(name);
    }

}
