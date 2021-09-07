package com.rasika.interview.validator;

import com.rasika.interview.input.GameInput;
import com.rasika.interview.util.Logger;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static com.rasika.interview.util.LogLevel.ERROR;
import static com.rasika.interview.util.LogLevel.INFO;

/**
 * @author Rasika Khiste
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GameInputValidator implements InputValidator {

    private static final int EARLY_FIVE_COUNT = 5;

    private GameInput input;

    @Override
    public void validateGameInput() {
        validateRange();
        validatePlayers();
        validateTicket();
        Logger.log("Game input validated successfully. Let's play!", INFO);
    }

    private void validateRange() {
        if (input.getRange() <= 0) {
            Logger.log(String.format("Invalid range: %d. Cannot play further. Quitting game", input.getRange()),ERROR);
            System.exit(1);
        }
    }

    private void validatePlayers() {
        if (input.getNumPlayers() < 2) {
            Logger.log("Need at least 2 players to play. Cannot play further. Quitting game", ERROR);
            System.exit(1);
        }
    }

    private void validateTicket() {
        int rows = input.getRows();
        int columns = input.getColumns();
        int numbersPerRow = input.getNumbersPerRow();
        int range = input.getRange();

        if (numbersPerRow > columns) {
            Logger.log(String.format("Numbers per row: %d cannot be greater than columns: %d. Cannot play further. Quitting game", numbersPerRow, columns), ERROR);
            System.exit(1);
        }
        if ((numbersPerRow * rows) < EARLY_FIVE_COUNT) {
            Logger.log(String.format("Total numbers in the ticket: %d not sufficient to play game " +
                    "because Early Five combination cannot be claimed. " +
                    "Cannot play further. Quitting game", numbersPerRow, columns), ERROR);
            System.exit(1);
        }
        if (range < (rows * numbersPerRow)) {
            Logger.log(String.format("Numbers in given range are less than total numbers to be filled on ticket" +
                    "Cannot play further. Quitting game", numbersPerRow, columns), ERROR);
            System.exit(1);
        }
    }
}
