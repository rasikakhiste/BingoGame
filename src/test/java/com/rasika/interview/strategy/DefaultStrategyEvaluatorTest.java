package com.rasika.interview.strategy;

import com.rasika.interview.entity.Player;
import com.rasika.interview.entity.Ticket;
import com.rasika.interview.entity.WinningCombination;
import com.rasika.interview.game.TicketGenerator;
import com.rasika.interview.util.IOProcessor;
import com.rasika.interview.util.LogLevel;
import com.rasika.interview.util.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.rasika.interview.helper.TestHelper.*;
import static com.rasika.interview.strategy.DefaultStrategyEvaluator.getStrategyMap;
import static com.rasika.interview.util.GameHelper.generateListWithinRange;
import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;

@RunWith(MockitoJUnitRunner.class)
public class DefaultStrategyEvaluatorTest {

    private List<Integer> numbers;
    private StrategyEvaluator evaluator;
    private TicketGenerator ticketGenerator;

    @Mock
    private IOProcessor mockIOProcessor;

    @Before
    public void initialize() {
        evaluator = new DefaultStrategyEvaluator();
        numbers = generateListWithinRange(1, RANGE);
        ticketGenerator = new TicketGenerator();
        ticketGenerator.setNumbers(numbers);
        Logger.setProcessor(mockIOProcessor);
    }

    @After
    public void clean() {
        for (Map.Entry<WinningStrategy, Boolean> entry : getStrategyMap().entrySet()) {
            entry.setValue(false);
        }
    }

    @Test
    public void testEvaluateSingleWinningCombination() {
        Ticket ticket = ticketGenerator.generate(ROWS, COLUMNS, NUMBERS_PER_ROW);
        List<Ticket> tickets = new ArrayList<>();
        tickets.add(ticket);
        Player player = new Player(1, tickets);

        doNothing().when(mockIOProcessor).writeLine(anyString());
        markTicket(ROWS, COLUMNS, EARLY_FIVE_COUNT, ticket);

        assertFalse(evaluator.evaluate(player, ticket));
        assertEquals(1, player.getWinningCombinations().size());
        assertEquals(player.getWinningCombinations().get(0), WinningCombination.EARLY_FIVE);
    }

    @Test
    public void testEvaluateNoWinningCombination() {
        Ticket ticket = ticketGenerator.generate(ROWS, COLUMNS, NUMBERS_PER_ROW);
        List<Ticket> tickets = new ArrayList<>();
        tickets.add(ticket);
        Player player = new Player(1, tickets);

        doNothing().when(mockIOProcessor).writeLine(anyString());
        markTicket(ROWS, COLUMNS, EARLY_FIVE_COUNT - 1, ticket);

        assertFalse(evaluator.evaluate(player, ticket));
        assertEquals(0, player.getWinningCombinations().size());
    }

    @Test
    public void testEvaluateMultipleWinningCombinations() {
        Ticket ticket = ticketGenerator.generate(ROWS, COLUMNS, NUMBERS_PER_ROW);
        List<Ticket> tickets = new ArrayList<>();
        tickets.add(ticket);
        Player player = new Player(1, tickets);

        doNothing().when(mockIOProcessor).writeLine(anyString());
        markTicket(ROWS, COLUMNS, (ROWS * NUMBERS_PER_ROW) - 1, ticket);

        assertFalse(evaluator.evaluate(player, ticket));
        assertEquals(2, player.getWinningCombinations().size());
        assertTrue(player.getWinningCombinations().contains(WinningCombination.EARLY_FIVE));
        assertTrue(player.getWinningCombinations().contains(WinningCombination.TOP_LINE));
        assertFalse(player.getWinningCombinations().contains(WinningCombination.FULL_HOUSE));
    }

    @Test
    public void testEvaluateAllWinningCombinations() {
        Ticket ticket = ticketGenerator.generate(ROWS, COLUMNS, NUMBERS_PER_ROW);
        List<Ticket> tickets = new ArrayList<>();
        tickets.add(ticket);
        Player player = new Player(1, tickets);

        doNothing().when(mockIOProcessor).writeLine(anyString());
        markTicket(ROWS, COLUMNS, ROWS * NUMBERS_PER_ROW, ticket);

        assertTrue(evaluator.evaluate(player, ticket));
        assertEquals(3, player.getWinningCombinations().size());
        assertTrue(player.getWinningCombinations().contains(WinningCombination.EARLY_FIVE));
        assertTrue(player.getWinningCombinations().contains(WinningCombination.TOP_LINE));
        assertTrue(player.getWinningCombinations().contains(WinningCombination.FULL_HOUSE));
    }

}
