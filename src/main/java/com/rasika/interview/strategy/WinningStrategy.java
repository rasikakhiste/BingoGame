package com.rasika.interview.strategy;

import com.rasika.interview.entity.Ticket;
import com.rasika.interview.entity.WinningCombination;

/**
 * @author Rasika Khiste
 */
public interface WinningStrategy {
    /**
     * Returns the description for winning combination
     *
     * @return WinningCombination
     */
    WinningCombination getDescription();

    /**
     * Checks for particular winning combination on give ticket, and returns true if valid
     *
     * @param ticket
     * @return boolean
     */
    boolean isWinningCombination(Ticket ticket);
}
