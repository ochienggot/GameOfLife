package game;

import gameoflife.Simulator;

public class Main {

    public static void main(String[] args) {
        // test your program here
	PersonalBoard board = new PersonalBoard(7, 5);
	board.initiateRandomCells(0.7);

	Simulator simulator = new Simulator(board);
	simulator.simulate();
    }
} 