package com.rasika.interview.game;

import com.rasika.interview.entity.Ticket;
import com.rasika.interview.input.GameInput;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

import static com.rasika.interview.util.GameHelper.generateListWithinRange;

/**
 * @author Rasika Khiste
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TicketGenerator {

    private List<Integer> numbers;
    private GameInput gameInput;

    /**
     * Helper method which calls generate(rows, cols, numsPerRow)
     *
     * @return
     */
    public Ticket generateTicket() {
        return generate(gameInput.getRows(), gameInput.getColumns(),
                gameInput.getNumbersPerRow());
    }

    /**
     * Generates the ticket with random values
     * and with given constraints
     *
     * @param rows
     * @param columns
     * @param numbersPerRow
     * @return Ticket
     */
    public Ticket generate(int rows, int columns, int numbersPerRow) {
        Ticket ticket = new Ticket(rows, columns);
        List<Integer> columnIndexes = generateListWithinRange(0, columns - 1);
        int count = 0;

        Collections.shuffle(numbers);

        // For each row, shuffle the column indexes
        // Keep adding numbers till numbersPerRow
        for (int i = 0; i < ticket.getRows(); i++) {
            Collections.shuffle(columnIndexes);
            for (int j = 0; j < numbersPerRow; j++) {
                ticket.addValue(i, columnIndexes.get(j), numbers.get(count));
                count++;
            }
        }
        return ticket;
    }
}
