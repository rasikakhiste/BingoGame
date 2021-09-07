package com.rasika.interview.input;

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
public class GameInput {

    private int range;
    private int numPlayers;
    private int rows;
    private int columns;
    private int numbersPerRow;
}
