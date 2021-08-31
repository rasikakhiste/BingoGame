package com.rasika.interview.game;

import com.rasika.interview.entity.Ticket;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static com.rasika.interview.helper.TestHelper.*;
import static com.rasika.interview.util.GameHelper.generateListWithinRange;
import static org.junit.Assert.assertEquals;

public class TicketGeneratorTest {

    private List<Integer> numbers;
    private TicketGenerator ticketGenerator;

    @Before
    public void initialize() {
        numbers = generateListWithinRange(1, RANGE);
        ticketGenerator = new TicketGenerator();
        ticketGenerator.setNumbers(numbers);
    }

    @Test
    public void testGenerateTicket() {
        int count = 0;
        Ticket ticket = ticketGenerator.generate(ROWS, COLUMNS, NUMBERS_PER_ROW);

        assertEquals(ROWS, ticket.getRows());
        assertEquals(COLUMNS, ticket.getColumns());
        for (int i = 0; i  < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if (ticket.isValuePresent(i, j))
                    count++;
            }
            assertEquals(NUMBERS_PER_ROW, count);
            count = 0;
        }
    }
}
