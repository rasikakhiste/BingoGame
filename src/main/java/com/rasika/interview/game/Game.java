package com.rasika.interview.game;

/**
 * @author Rasika Khiste
 */
public interface Game {
    /**
     * Initialize all the data needed for game
     */
    void initialize();

    /**
     * Implements actual logic of playing the game
     */
    void playGame();

    /**
     * Quits the game if stopped
     */
    void quitGame();

    /**
     * Print game winning summary
     */
    void printSummary();
}
