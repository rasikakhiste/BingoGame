package com.rasika.interview.input;

import com.rasika.interview.util.IOProcessor;
import com.rasika.interview.util.Logger;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.IOException;

import static com.rasika.interview.util.LogLevel.ERROR;
import static com.rasika.interview.util.LogLevel.INFO;

/**
 * @author Rasika Khiste
 */
@NoArgsConstructor
@AllArgsConstructor
public class GameInputReader implements InputReader {

    private static final int RANGE = 99;
    private static final int PLAYERS = 2;
    private static final int ROWS = 3;
    private static final int COLUMNS = 10;
    private static final int NUMBERS_PER_ROW = 5;
    private static final String QUIT = "Q";

    private IOProcessor processor;

    @Override
    public GameInput readGameInput() {

        while (true) {
            Logger.log("Enter the number range(1-n): ", INFO);
            int range = readNextInt(RANGE);
            if (range == -1)
                continue;

            Logger.log("Enter number of players playing the game: ", INFO);
            int numPlayers = readNextInt(PLAYERS);
            if (numPlayers == -1)
                continue;

            Logger.log("Enter number of rows in Ticket: ", INFO);
            int rows = readNextInt(ROWS);
            if (rows == -1)
                continue;

            Logger.log("Enter number of columns in Ticket: ", INFO);
            int columns = readNextInt(COLUMNS);
            if (columns == -1)
                continue;

            Logger.log("Enter numbers per row: ", INFO);
            int numbersPerRow = readNextInt(NUMBERS_PER_ROW);
            if (numbersPerRow == -1)
                continue;

            return new GameInput(range, numPlayers, rows, columns, numbersPerRow);
        }
    }

    /**
     * Reads next line of input and converts to int. If null or empty, assign default value
     *
     * @param defaultValue
     * @return
     */
    private int readNextInt(int defaultValue) {
        try {
            String line = processor.readNextLine();
            if (QUIT.equalsIgnoreCase(line)) {
                Logger.log("***** Game Quit *****", INFO);
                System.exit(1);
            }
            return (line == null || line.isEmpty()) ? defaultValue : Integer.parseInt(line);
        } catch (NumberFormatException | IOException e) {
            Logger.log("Exception occurred while reading invalid input. Let's start again.", ERROR);
        }
        return -1;
    }
}