package com.essentls.resource;


import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.StringFormatterMessageFactory;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Represents a generic resource.
 * @author Laura Pallante
 * @version 1.00
 * @since 1.00
 */
public abstract class AbstractResource implements Resource {
    protected static final Logger LOGGER = LogManager.getLogger(AbstractResource.class, StringFormatterMessageFactory.INSTANCE);

    /**
     * The JSON factory to be used for creating JSON parsers and generators.
     */
    protected static final JsonFactory JSON_FACTORY;

    /**
     * Initializes the JSON factory.
     */
    static {
        JSON_FACTORY = new JsonFactory();
        JSON_FACTORY.disable(JsonGenerator.Feature.AUTO_CLOSE_TARGET);
        JSON_FACTORY.disable(JsonParser.Feature.AUTO_CLOSE_SOURCE);

        LOGGER.debug("JSON factory successfully setup.");
    }

    /**
     * Serializes the {@link Resource} to JSON and writes it to the specified output stream.
     * @param out
     * @throws IOException
     */
    @Override
    public void toJSON(final OutputStream out) throws IOException {

        if(out == null) {
            LOGGER.error("The output stream cannot be null.");
            throw new IOException("The output stream cannot be null.");
        }

        try {
            writeJSON(out);
        } catch(Exception e) {
            LOGGER.error("Unable to serialize the resource to JSON.", e);
            throw new IOException("Unable to serialize the resource to JSON.", e);
        }

    }

    /**
     * Performs the actual writing of JSON.
     *
     * Subclasses have to implement this method to provide the actual logic needed for representing the {@link
     * Resource} to JSON.
     *
     * @param out the stream to which the JSON representation of the {@link Resource} has to be written.
     *
     * @throws Exception if something goes wrong during writing.
     */
    protected abstract void writeJSON(OutputStream out) throws Exception;
}