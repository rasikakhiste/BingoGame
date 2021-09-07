package com.rasika.interview.strategy;

import com.rasika.interview.entity.Ticket;
import com.rasika.interview.entity.WinningCombination;

/**
 * @author Rasika Khiste
 */
public class TopLineStrategy implements WinningStrategy {

    @Override
    public WinningCombination getDescription() {
        return WinningCombination.TOP_LINE;
    }

    @Override
    public boolean isWinningCombination(Ticket ticket) {

        for (int j = 0; j < ticket.getColumns(); j++) {
            if (ticket.isValuePresent(0, j) && !ticket.isValueMarked(0, j))
                return false;
        }
        return true;
    }
}
