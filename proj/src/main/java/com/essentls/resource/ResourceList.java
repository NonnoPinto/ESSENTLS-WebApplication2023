package com.essentls.resource;

import com.fasterxml.jackson.core.JsonGenerator;
import java.io.*;

/**
 * Represents a list of {@link AbstractResource} objects.
 *
 * @author Laura Pallante
 * @version 1.00
 * @since 1.00
 *
 * @param <T> the type of the actual class extending {@link AbstractResource}.
 *
 */
public final class ResourceList<T extends Resource> extends AbstractResource {

    /**
     * The list of {@link AbstractResource}s.
     */
    private final Iterable<T> list;

    /**
     * Creates a list of {@link AbstractResource}s.
     *
     * @param list the list of {@link AbstractResource}s.
     */
    public ResourceList(final Iterable<T> list) {

        if (list == null) {
            LOGGER.error("Resource list cannot be null.");
            throw new NullPointerException("Resource list cannot be null.");
        }

        this.list = list;
    }

    @Override
    protected void writeJSON(final OutputStream out) throws IOException {

        final JsonGenerator jg = JSON_FACTORY.createGenerator(out);

        jg.writeStartObject();

        jg.writeFieldName("resource-list");

        jg.writeStartArray();

        jg.flush();

        boolean firstElement = true;

        for (final Resource r : list) {

            if (firstElement) {
                r.toJSON(out);
                jg.flush();

                firstElement = false;
            } else {
                jg.writeRaw(',');
                jg.flush();

                r.toJSON(out);
                jg.flush();
            }
        }

        jg.writeEndArray();

        jg.writeEndObject();

        jg.flush();
    }
}