package com.rasika.interview.validator;

import com.rasika.interview.input.GameInput;
import com.rasika.interview.util.Logger;

import static com.rasika.interview.util.LogLevel.ERROR;
import static com.rasika.interview.util.LogLevel.INFO;

/**
 * @author Rasika Khiste
 */
public class GameInputValidator implements InputValidator {
    private static final int EARLY_FIVE_COUNT = 5;

    private GameInput input;

    public GameInputValidator(GameInput input) {
        this.input = input;
    }

    @Override
    public void validateGameInput() {
        validateRange(input.getRange());
        validatePlayers(input.getNumPlayers());
        validateTicket(input.getRows(), input.getColumns(), input.getNumbersPerRow());
        Logger.log("Game input validated successfully. Let's play!", INFO);
    }

    private void validateTicket(int rows, int columns, int numbersPerRow) {
        if (numbersPerRow > columns) {
            Logger.log(String.format("Numbers per row: %d cannot be greater than columns: %d. Cannot play further. Quitting game", numbersPerRow, columns), ERROR);
            System.exit(1);
        }
        if (numbersPerRow * rows < EARLY_FIVE_COUNT) {
            Logger.log(String.format("Total numbers in the ticket: %d not sufficient to play game " +
                    "because Early Five combination cannot be claimed. " +
                    "Cannot play further. Quitting game", numbersPerRow, columns), ERROR);
            System.exit(1);
        }
    }

    private void validatePlayers(int numPlayers) {
        if (numPlayers < 2) {
            Logger.log(String.format("Need at least 2 players to play. Cannot play further. Quitting game"), ERROR);
            System.exit(1);
        }
    }

    private void validateRange(int range) {
        if (range <= 0) {
            Logger.log(String.format("Invalid range: %d. Cannot play further. Quitting game", range),ERROR);
            System.exit(1);
        }
    }
}
