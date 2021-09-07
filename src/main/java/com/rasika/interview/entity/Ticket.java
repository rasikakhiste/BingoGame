package com.rasika.interview.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Rasika Khiste
 */
@Getter
@Setter
@NoArgsConstructor
public class Ticket {

    private int rows;
    private int columns;
    // TODO: Use HashMap to create ticket
    private Cell[][] cells;

    public Ticket(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.cells = new Cell[rows][columns];
    }

    /**
     * Adds a value in ticket at given position (row, column)
     *
     * @param row
     * @param column
     * @param value
     */
    public void addValue(int row, int column, int value) {
        Cell cell = new Cell(row, column, value);
        this.cells[row][column] = cell;
    }

    /**
     * Gets a value from ticket for given position (row, column)
     *
     * @param row
     * @param column
     * @return
     */
    public int getValue(int row, int column) {
        Cell cell = this.cells[row][column];
        return cell.getValue();
    }

    /**
     * Marks a value to true if present in ticket
     *
     * @param value
     * @return
     */
    public boolean markValue(int value) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                Cell cell = cells[i][j];
                if (cell != null && cell.getValue() == value) {
                    cell.setMarked(true);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Checks if given value is present in ticket
     * @param row
     * @param column
     * @return
     */
    public boolean isValuePresent(int row, int column) {
        return cells[row][column] != null;
    }

    /**
     * Returns true if value in ticket is marked
     * @param row
     * @param column
     * @return boolean
     */
    public boolean isValueMarked(int row, int column) {
        Cell cell = cells[row][column];
        return cell != null && cell.isMarked();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                sb.append(cells[i][j] != null ? cells[i][j].getValue() : "-1").append("\t");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
