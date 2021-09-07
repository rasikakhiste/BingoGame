package com.rasika.interview.strategy;

import com.rasika.interview.entity.Player;
import com.rasika.interview.entity.Ticket;

/**
 * @author Rasika Khiste
 */
public interface StrategyEvaluator {
    /**
     * Evaluates each winning combination against given ticket
     *
     * @param player
     * @param ticket
     * @return boolean
     */
    boolean evaluate(Player player, Ticket ticket);
}
