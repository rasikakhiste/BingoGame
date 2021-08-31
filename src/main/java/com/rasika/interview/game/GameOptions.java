package com.rasika.interview.game;

import com.rasika.interview.strategy.StrategyEvaluator;
import com.rasika.interview.util.IOProcessor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Rasika Khiste
 */
@Getter
@Setter
public class GameOptions {

    private IOProcessor processor;
    private TicketGenerator ticketGenerator;
    private StrategyEvaluator strategyEvaluator;

    public GameOptions(IOProcessor processor, TicketGenerator ticketGenerator, StrategyEvaluator strategyEvaluator) {
        this.processor = processor;
        this.ticketGenerator = ticketGenerator;
        this.strategyEvaluator = strategyEvaluator;
    }
}
