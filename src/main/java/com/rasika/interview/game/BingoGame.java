package com.rasika.interview.game;

import com.rasika.interview.entity.Player;
import com.rasika.interview.entity.Ticket;
import com.rasika.interview.entity.WinningCombination;
import com.rasika.interview.input.GameInput;
import com.rasika.interview.strategy.StrategyEvaluator;
import com.rasika.interview.util.IOProcessor;
import com.rasika.interview.util.Logger;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.rasika.interview.util.GameHelper.generateListWithinRange;
import static com.rasika.interview.util.LogLevel.*;

/**
 * @author Rasika Khiste
 */
@Getter
@Setter
@NoArgsConstructor
public class BingoGame implements Game {

    private static final String GENERATE_NEXT_NUMBER = "N";
    private static final String QUIT = "Q";

    private static List<Integer> numbers = new ArrayList<>();

    private List<Player> players;
    private IOProcessor processor;
    private GameInput gameInput;
    private TicketGenerator ticketGenerator;
    private StrategyEvaluator strategyEvaluator;

    public BingoGame(GameInput gameInput, GameOptions gameOptions) {
        this.gameInput = gameInput;
        this.processor = gameOptions.getProcessor();
        this.ticketGenerator = gameOptions.getTicketGenerator();
        this.strategyEvaluator = gameOptions.getStrategyEvaluator();
        this.players = new ArrayList<>();
    }

    @Override
    public void initialize() {

        // Initialize a list of numbers within given range
        numbers = generateListWithinRange(1, gameInput.getRange());
        ticketGenerator.setNumbers(numbers);
        ticketGenerator.setGameInput(gameInput);

        // Create players and generate ticket(s) for each player
        for (int i = 1; i <= gameInput.getNumPlayers(); i++) {

            Ticket ticket = ticketGenerator.generateTicket();

            // Since Player has List<Ticket>, game can be configured to multiple tickets per player.
            // Default to 1 ticket per player
            List<Ticket> tickets = new ArrayList<>();
            tickets.add(ticket);

            Player player = new Player(i, tickets);
            players.add(player);

            Logger.log(String.format("Player#%d added successfully", player.getId()), DEBUG);
            Logger.log(String.format("*** Ticket created successfully for Player#%d ****", player.getId()), INFO);
            Logger.log(ticket.toString(), DEBUG);
        }
    }

    @Override
    public void playGame() {

        int index = 0;
        int nextNumber = 0;

        // Shuffle the list of numbers once before playing the game
        Collections.shuffle(numbers);

        while (true) {
            try {
                Logger.log("\nEnter 'N' to generate next number.", INFO);
                String nextLine = processor.readNext();

                // Quit game if user pressed "Q"
                if (QUIT.equalsIgnoreCase(nextLine))
                    quitGame();

                // Generate next number and check for winning combinations
                if (GENERATE_NEXT_NUMBER.equalsIgnoreCase(nextLine)) {

                    // Get next number from shuffled list of numbers
                    nextNumber = numbers.get(index++);
                    Logger.log(String.format("Next number is: %d", nextNumber), INFO);

                    /*
                     * Shuffle the list of players to ensure equal winning chances
                     * TODO: Spawn multiple threads, so that all players can play simultaneously
                     */
                    Collections.shuffle(players);

                    /* For each player, check if number exists on each ticket
                     * If number exists, mark the number and proceed to next ticket
                     */
                    for (Player player : players) {
                        for (Ticket ticket : player.getTickets()) {
                            if (ticket.markValue(nextNumber))
                                Logger.log(String.format("Number %d marked on ticket for player#%d", nextNumber, player.getId()), DEBUG);

                            // Break if all winning combinations are claimed
                            if (strategyEvaluator.evaluate(player, ticket))
                                return;
                        }
                    }
                } else {
                    Logger.log(String.format("Invalid input: %s. " +
                            "Please enter valid input (N to generate next number or Q to quit the game)", nextLine), ERROR);
                }
            } catch (IOException e) {
                Logger.log("Exception occurred while reading input. Let's try again.", ERROR);
            }
        }
    }

    @Override
    public void quitGame() {
        printSummary();
        Logger.log("***** Game Quit *****", INFO);
        System.exit(1);
    }

    @Override
    public void printSummary() {
        StringBuilder sb = new StringBuilder();
        Logger.log("======================", INFO);
        Logger.log("\tSummary:", INFO);
        for (Player player : players) {
            sb.setLength(0);
            sb.append("Player#").append(player.getId()).append(" : ");

            if (player.getWinningCombinations() == null || player.getWinningCombinations().isEmpty())
                sb.append("Nothing").append(" ");

            for (WinningCombination winningCombination : player.getWinningCombinations()) {
                sb.append(winningCombination.getCombination()).append(" ");
            }
            Logger.log(sb.toString(), INFO);
        }
        Logger.log("======================", INFO);
    }
}
