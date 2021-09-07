package com.rasika.interview.game;

import com.rasika.interview.strategy.StrategyEvaluator;
import com.rasika.interview.util.IOProcessor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Rasika Khiste
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GameOptions {

    private IOProcessor processor;
    private TicketGenerator ticketGenerator;
    private StrategyEvaluator strategyEvaluator;
}
