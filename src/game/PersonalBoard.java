/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import gameoflife.GameOfLifeBoard;

/**
 *
 * @author ngot
 */
public class PersonalBoard extends GameOfLifeBoard {

    public PersonalBoard(int width, int height) {
	super(width, height);
    }

    @Override
    public void turnToLiving(int x, int y) {
	if (inBoard(x,y)) {
	    super.getBoard()[x][y] = true;
	}
    }

    @Override
    public void turnToDead(int x, int y) {
	if (inBoard(x,y)) {
	    super.getBoard()[x][y] = false;
	}
    }

    @Override
    public boolean isAlive(int x, int y) {
	if (inBoard(x,y)) {
	    return super.getBoard()[x][y];
	}
	return false;
    }

    @Override
    public void initiateRandomCells(double probabilityForEachCell) {
	boolean[][] values = super.getBoard();

	for (int i = 0; i < getWidth(); i++) {
	    for (int j = 0; j < getHeight(); j++) {
		double p = Math.random();
		if (p < probabilityForEachCell) {
		    values[i][j] = true;
		}
	    }
	}
	
    }

    /*
      Determines whether the cell(x,y) is within the board.
     @param x - integer, position along the width of the board
     @param y - integer, position along the height of the board
     @return true if the cell is in the board and false otherwise
    */
    private boolean inBoard(int x, int y) {
	return (x >= 0 && x < getWidth()) && (y >= 0 && y < getHeight());
    }

    @Override
    public int getNumberOfLivingNeighbours(int x, int y) {
	boolean[][] values = super.getBoard();
	int count = 0;
	// all possible neighbors
	if (inBoard(x,y)) {
	    if (inBoard(x, y - 1)) {
		try {
		    boolean val = values[x][y-1];
		    count += val ? 1 : 0;
		} catch (ArrayIndexOutOfBoundsException e) {
		    // do nothing
		}
	    }
	    if (inBoard(x + 1, y - 1)) {
		
		try {
		    boolean val = values[x+1][y-1];
		    count += val ? 1 : 0;
		} catch (ArrayIndexOutOfBoundsException e) {
		    // do nothing
		}
	    }
	    if (inBoard(x + 1, y)) {
		
		try {
		    boolean val = values[x+1][y];
		    count += val ? 1 : 0;
		} catch (ArrayIndexOutOfBoundsException e) {
		    // do nothing
		}
	    }
	    if (inBoard(x + 1, y + 1)) {
		
		try {
		    boolean val = values[x+1][y+1];
		    count += val ? 1 : 0;
		} catch (ArrayIndexOutOfBoundsException e) {
		    // do nothing
		}
	    }
	    if (inBoard(x, y + 1)) {
		
		try {
		    boolean val = values[x][y+1];
		    count += val ? 1 : 0;
		} catch (ArrayIndexOutOfBoundsException e) {
		    // do nothing
		}
	    }
	    if (inBoard(x - 1, y + 1)) {
		
		try {
		    boolean val = values[x-1][y+1];
		    count += val ? 1 : 0;
		} catch (ArrayIndexOutOfBoundsException e) {
		    // do nothing
		}
	    }
	    if (inBoard(x - 1, y)) {
		
		try {
		    boolean val = values[x-1][y];
		    count += val ? 1 : 0;
		} catch (ArrayIndexOutOfBoundsException e) {
		    // do nothing
		}
	    }
	    if (inBoard(x - 1, y - 1)) {
		
		try {
		    boolean val = values[x-1][y-1];
		    count += val ? 1 : 0;
		} catch (ArrayIndexOutOfBoundsException e) {
		    // do nothing
		}
	    }
	}
	return count;
    }

    @Override
    public void manageCell(int x, int y, int livingNeighbours) {
	// Rule 1: Each living cell dies if they have less than two living neighbours
	if (getBoard()[x][y]) {
	    // it's alive!
	    if (livingNeighbours < 2) {
		turnToDead(x,y);
	    } 
	    // Rule 2: Each living cell continues living during the following iteration
	    // if they have two or three living neighbors
	    if (livingNeighbours == 2 || livingNeighbours == 3) {
		// Do nothing - happy life!
	    } else if (livingNeighbours > 3) {
		turnToDead(x,y);
	    }
	} else {
	    if (livingNeighbours == 3) {
		turnToLiving(x,y);
	    }
	}
    }
}
