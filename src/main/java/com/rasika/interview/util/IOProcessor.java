package com.rasika.interview.util;

import java.io.IOException;

/**
 * @author Rasika Khiste
 */
public interface IOProcessor {
    /**
     * Reads a token from IO
     * @return String token
     * @throws IOException
     */
    String readNext() throws IOException;
    /**
     * Reads a line from IO
     * @return String line
     * @throws IOException
     */
    String readNextLine() throws IOException;

    /**
     * Writes a line to IO
     * @param line
     */
    void writeLine(String line);
}
