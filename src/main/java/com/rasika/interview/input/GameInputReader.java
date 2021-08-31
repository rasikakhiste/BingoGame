package com.rasika.interview.input;

import com.rasika.interview.util.IOProcessor;
import com.rasika.interview.util.Logger;

import java.io.IOException;

import static com.rasika.interview.util.LogLevel.ERROR;
import static com.rasika.interview.util.LogLevel.INFO;

/**
 * @author Rasika Khiste
 */
public class GameInputReader implements InputReader {

    private static final int RANGE = 99;
    private static final int PLAYERS = 2;
    private static final int ROWS = 3;
    private static final int COLUMNS = 10;
    private static final int NUMBERS_PER_ROW = 5;

    private IOProcessor processor;

    public GameInputReader(IOProcessor processor) {
        this.processor = processor;
    }

    @Override
    public GameInput readGameInput() {
        while (true) {
            Logger.log("Enter the number range(1-n): ", INFO);

            int range = readNextInt(RANGE);

            Logger.log("Enter number of players playing the game: ", INFO);
            int numPlayers = readNextInt(PLAYERS);

            Logger.log("Enter number of rows in Ticket: ", INFO);
            int rows = readNextInt(ROWS);

            Logger.log("Enter number of columns in Ticket: ", INFO);
            int columns = readNextInt(COLUMNS);

            Logger.log("Enter numbers per row: ", INFO);
            int numbersPerRow = readNextInt(NUMBERS_PER_ROW);

            return new GameInput(range, numPlayers, rows, columns, numbersPerRow);
        }
    }

    private int readNextInt(int defaultValue) {
        try {
            String line = processor.readNextLine();
            return (line == null || line.isEmpty()) ? defaultValue : Integer.parseInt(line);
        } catch (IOException e) {
            Logger.log("Exception occurred while reading input. Let's start again.", ERROR);
        }
        return -1;
    }
}