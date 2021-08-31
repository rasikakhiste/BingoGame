package com.rasika.interview.input;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Rasika Khiste
 */
@Getter
@Setter
@NoArgsConstructor
public class GameInput {

    private int range;
    private int numPlayers;
    private int rows;
    private int columns;
    private int numbersPerRow;

    public GameInput(int range, int numPlayers, int rows, int columns, int numbersPerRow) {
        this.range = range;
        this.numPlayers = numPlayers;
        this.rows = rows;
        this.columns = columns;
        this.numbersPerRow = numbersPerRow;
    }
}
