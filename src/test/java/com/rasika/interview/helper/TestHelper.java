package com.rasika.interview.helper;

import com.rasika.interview.entity.Ticket;

public class TestHelper {

    public static final int RANGE = 20;
    public static final int NUM_PLAYERS = 5;
    public static final int ROWS = 3;
    public static final int COLUMNS = 10;
    public static final int NUMBERS_PER_ROW = 5;
    public static final int EARLY_FIVE_COUNT = 5;

    public static void markTicket(int rows, int columns, int n, Ticket ticket) {
        int count = 0;
        for (int j = 0; j < columns; j++) {
            for (int i = 0; i < rows; i++) {
                if (ticket.isValuePresent(i, j)) {
                    ticket.markValue(ticket.getValue(i, j));
                    count++;
                }
                if (count == n)
                    return;
            }
        }
    }
}
