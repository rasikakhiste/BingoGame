package com.rasika.interview.strategy;

import com.rasika.interview.entity.Ticket;
import com.rasika.interview.entity.WinningCombination;

/**
 * @author Rasika Khiste
 */
public class EarlyFiveStrategy implements WinningStrategy {

    private static final int EARLY_FIVE_COUNT = 5;

    @Override
    public WinningCombination getDescription() {
        return WinningCombination.EARLY_FIVE;
    }

    @Override
    public boolean isWinningCombination(Ticket ticket) {

        int count = 0;

        for(int i = 0; i < ticket.getRows(); i++) {
            for(int j = 0; j < ticket.getColumns(); j++) {
               if (ticket.isValuePresent(i, j) && ticket.isValueMarked(i, j)) {
                    count++;
                    if(count == EARLY_FIVE_COUNT)
                        return true;
                }
            }
        }
        return false;
    }
}
