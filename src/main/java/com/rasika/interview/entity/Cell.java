package com.rasika.interview.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Rasika Khiste
 */

@Getter
@Setter
@ToString
public class Cell {

    private int row;
    private int column;
    private int value;
    private boolean isMarked;

    public Cell(int row, int column, int value) {
        this.row = row;
        this.column = column;
        this.value = value;
    }
}
