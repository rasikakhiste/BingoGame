package com.rasika.interview.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Rasika Khiste
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Player {

    private int id;
    private List<Ticket> tickets;
    private List<WinningCombination> winningCombinations;

    public Player(int id, List<Ticket> tickets) {
        this.id = id;
        this.tickets = tickets;
        this.winningCombinations = new ArrayList<>();
    }
}
