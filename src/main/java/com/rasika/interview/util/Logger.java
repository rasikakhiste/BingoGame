package com.rasika.interview.util;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

import static com.rasika.interview.util.LogLevel.ERROR;
import static com.rasika.interview.util.LogLevel.INFO;

/**
 * @author Rasika Khiste
 */

public class Logger {

    private static Logger logger = null;

    @Getter
    @Setter
    private static Set<LogLevel> logLevels = initializeSet();

    @Getter
    @Setter
    private static IOProcessor processor;

    private Logger () { }

    /**
     * Static method to return instance of Logger class
     * @return Logger logger
     */
    public static Logger getInstance() {
        // TODO: Synchronized and double-checked locking for multiple threads
        if (logger == null)
            logger = new Logger();
        return logger;
    }

    /**
     * Logs given line with provided log level
     * @param line
     * @param level
     */
    public static void log(String line, LogLevel level) {
        if (logLevels.contains(level))
            processor.writeLine(line);
    }

    private static Set<LogLevel> initializeSet() {
        Set<LogLevel> set = new HashSet<>();
        set.add(ERROR);
        set.add(INFO);

        return set;
    }
}
