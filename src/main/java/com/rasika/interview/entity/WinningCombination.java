package com.rasika.interview.entity;

/**
 * @author Rasika Khiste
 */
public enum WinningCombination {

    EARLY_FIVE("Early Five"),
    TOP_LINE("Top Line"),
    FULL_HOUSE("Full House");

    private String combination;

    WinningCombination(String combination) {
        this.combination = combination;
    }

    public String getCombination() {
        return combination;
    }
}
