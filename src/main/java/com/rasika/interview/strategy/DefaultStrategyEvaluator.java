package com.rasika.interview.strategy;

import com.rasika.interview.entity.Player;
import com.rasika.interview.entity.Ticket;
import com.rasika.interview.util.Logger;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

import static com.rasika.interview.util.LogLevel.INFO;

/**
 * @author Rasika Khiste
 */
public class DefaultStrategyEvaluator implements StrategyEvaluator {

    @Getter
    private static Map<WinningStrategy, Boolean> strategyMap = initializeStrategyMap();
    private static int winningCombinationsSoFar = 0;

    @Override
    public boolean evaluate(Player player, Ticket ticket) {

        // Check for a winni ng combination for each unclaimed winning strategy
        for (Map.Entry<WinningStrategy, Boolean> entry : strategyMap.entrySet()) {
            WinningStrategy strategy = entry.getKey();
            Boolean isClaimed = entry.getValue();

            // If winning combination is found:
            // 1. Print the winner
            // 2. Add the winning combination to the player's list of winning combinations
            // 3. Mark the winning strategy as claimed, so it cannot be claimed again
            if (Boolean.FALSE.equals(isClaimed) && strategy.isWinningCombination(ticket)) {
                Logger.log(String.format("We have a winner: " +
                                "Player#%d has won '%s' winning combination.",
                        player.getId(), strategy.getDescription().getCombination()), INFO);
                player.getWinningCombinations().add(strategy.getDescription());
                entry.setValue(true);

                if (++winningCombinationsSoFar == strategyMap.size()) {
                    Logger.log("All strategies have been claimed", INFO);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Initialize the strategy map with all strategies when the class loads
     *
     * @return
     */
    private static Map<WinningStrategy, Boolean> initializeStrategyMap() {
        Map<WinningStrategy, Boolean> map = new HashMap<>();
        map.put(new EarlyFiveStrategy(), false);
        map.put(new TopLineStrategy(), false);
        map.put(new FullHouseStrategy(), false);

        return map;
    }
}
