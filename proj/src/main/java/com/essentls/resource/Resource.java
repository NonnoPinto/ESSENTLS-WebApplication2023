package com.essentls.resource;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Interface for resources.
 * @author Laura Pallante
 * @since 1.0
 * @version 1.0
 */

public interface Resource {
    void toJSON(OutputStream out) throws IOException;

}