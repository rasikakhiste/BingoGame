package com.rasika.interview.driver;

import com.rasika.interview.game.BingoGame;
import com.rasika.interview.game.Game;
import com.rasika.interview.game.GameOptions;
import com.rasika.interview.game.TicketGenerator;
import com.rasika.interview.input.GameInput;
import com.rasika.interview.input.GameInputReader;
import com.rasika.interview.strategy.DefaultStrategyEvaluator;
import com.rasika.interview.strategy.StrategyEvaluator;
import com.rasika.interview.util.IOProcessor;
import com.rasika.interview.util.Logger;
import com.rasika.interview.util.SystemIOProcessor;
import com.rasika.interview.validator.GameInputValidator;
import com.rasika.interview.validator.InputValidator;

import java.util.Scanner;

import static com.rasika.interview.util.LogLevel.ERROR;
import static com.rasika.interview.util.LogLevel.INFO;

/**
 * @author Rasika Khiste
 */
public class Driver {

    public static void main(String[] args) {
        IOProcessor processor = new SystemIOProcessor(new Scanner(System.in));
        try {
            // Define the logger
            Logger.setProcessor(processor);

            Logger.log("**** Lets Play Housie *****\n", INFO);
            Logger.log("Note: - Press 'Q' to quit any time.\n", INFO);

            GameInput gameInput = new GameInputReader(processor).readGameInput();

            // Validate all inputs provided, otherwise quit game
            InputValidator validator = new GameInputValidator(gameInput);
            validator.validateGameInput();

            TicketGenerator generator = new TicketGenerator();
            StrategyEvaluator strategyEvaluator = new DefaultStrategyEvaluator();

            GameOptions gameOptions = new GameOptions(processor, generator, strategyEvaluator);
            Game game = new BingoGame(gameInput, gameOptions);

            game.playGame();

            Logger.log("***** Game Over *****\n", INFO);
            game.printSummary();
        } catch (Exception e) {
            Logger.log("Exception occurred. Quiting game", ERROR);
        }
    }
}
