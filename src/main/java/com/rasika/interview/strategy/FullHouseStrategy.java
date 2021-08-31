package com.rasika.interview.strategy;

import com.rasika.interview.entity.Ticket;
import com.rasika.interview.entity.WinningCombination;

/**
 * @author Rasika Khiste
 */
public class FullHouseStrategy implements WinningStrategy {

    @Override
    public WinningCombination getDescription() {
        return WinningCombination.FULL_HOUSE;
    }

    @Override
    public boolean isWinningCombination(Ticket ticket) {
        int rows = ticket.getRows(), columns = ticket.getColumns();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (ticket.isValuePresent(i, j) && !ticket.isValueMarked(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }
}
