package com.essentls.resource;

import java.io.*;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import org.json.JSONObject;

import java.io.OutputStream;

public class Cause extends AbstractResource{

    private long id;
    private String name;

    public Cause(long id, String name) {
        this.name = name;
        this.id=id;
    }

    public String getName() {
        return name;
    }
    public Long getId() {
        return id;
    }
    @Override
    public String toString() {
        return "Cause [id=" +id + "] [name=" + name + "]";
    }

    @Override
    protected void writeJSON(final OutputStream out) throws IOException {
        final JsonGenerator jg = JSON_FACTORY.createGenerator(out);

        jg.writeStartObject();
        jg.writeStringField("id", String.valueOf(id));
        jg.writeStringField("name", name);
        jg.writeEndObject();

        jg.flush();
    }


    public static Cause fromJSON(final InputStream in) throws IOException  {

        // the fields read from JSON
        long id = -1;
        String name = null;


        try {
            final JsonParser jp = JSON_FACTORY.createParser(in);

            // while we are not on the start of an element or the element is not
            // a token element, advance to the next element (if any)
            while (jp.getCurrentToken() != JsonToken.FIELD_NAME || !"cause".equals(jp.getCurrentName())) {

                // there are no more events
                if (jp.nextToken() == null) {
                    LOGGER.error("No cause object found in the stream.");
                    throw new EOFException("Unable to parse JSON: no cause object found.");
                }
            }

            while (jp.nextToken() != JsonToken.END_OBJECT) {

                if (jp.getCurrentToken() == JsonToken.FIELD_NAME) {

                    switch (jp.getCurrentName()) {
                        case "id":
                            jp.nextToken();
                            id=jp.getLongValue();
                        case "name":
                            jp.nextToken();
                            name = jp.getText().replaceAll("[^a-zA-Z0-9]", "");
                            break;


                    }
                }
            }
        } catch(IOException e) {
            LOGGER.error("Unable to parse a cause object from JSON.", e);
            throw e;
        }

        return new Cause(id,name);
    }

}
