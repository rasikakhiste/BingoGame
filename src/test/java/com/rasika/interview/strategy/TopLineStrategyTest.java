package com.rasika.interview.strategy;

import com.rasika.interview.entity.Ticket;
import com.rasika.interview.game.TicketGenerator;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static com.rasika.interview.helper.TestHelper.*;
import static com.rasika.interview.util.GameHelper.generateListWithinRange;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TopLineStrategyTest {

    private List<Integer> numbers;
    private TicketGenerator ticketGenerator;
    private WinningStrategy strategy;

    @Before
    public void initialize() {
        strategy = new TopLineStrategy();
        numbers = generateListWithinRange(1, RANGE);
        ticketGenerator = new TicketGenerator();
        ticketGenerator.setNumbers(numbers);
    }

    @Test
    public void testTopLineWinningCombination() {
        Ticket ticket = ticketGenerator.generate(ROWS, COLUMNS, NUMBERS_PER_ROW);
        markTicket(ROWS, COLUMNS, ROWS * NUMBERS_PER_ROW, ticket);

        assertTrue(strategy.isWinningCombination(ticket));
    }

    @Test
    public void testTopLineWithNoWinningCombination() {
        Ticket ticket = ticketGenerator.generate(ROWS, COLUMNS, NUMBERS_PER_ROW);
        markTicket(ROWS, COLUMNS, ROWS, ticket);

        assertFalse(strategy.isWinningCombination(ticket));
    }
}
