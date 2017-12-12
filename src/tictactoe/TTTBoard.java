package tictactoe;

//package dm550.tictactoe;

/** represents a tic tac toe board of a given size */
public class TTTBoard {

	/**
	 * 2-dimensional array representing the board coordinates are counted from
	 * top-left (0,0) to bottom-right (size-1, size-1) board[x][y] == 0 signifies
	 * free at position (x,y) board[x][y] == i for i > 0 signifies that Player i
	 * made a move on (x,y)
	 */
	private int[][] board;

	/** size of the (quadratic) board */
	private int size;

	/**
	 * constructor for creating a copy of the board not needed in Part 1 - can be
	 * viewed as an example
	 */
	public TTTBoard(TTTBoard original) {
		this.size = original.size;
		for (int y = 0; y < this.size; y++) {
			for (int x = 0; x < this.size; x++) {
				this.board[y][x] = original.board[y][x];
			}
		}
	}

	/** constructor for creating an empty board for a given number of players */
	public TTTBoard(int numPlayers) {
		this.size = numPlayers + 1;
		this.board = new int[this.getSize()][this.getSize()];
	}

	/** checks whether the board is free at the given position */
	public boolean isFree(Coordinate c) {
		if (board[c.getX()][c.getY()] == 0) {
			return true;
		}
		return false;
	}

	/** returns the players that made a move on (x,y) or 0 if the positon is free */
	public int getPlayer(Coordinate c) {
		if (isFree(c)) {
			return 0;
		}
		return (board[c.getX()][c.getY()]);
	}

	/**
	 * record that a given player made a move at the given position checks that the
	 * given positions is on the board checks that the player number is valid
	 */
	public void addMove(Coordinate c, int player) {
		if (isFree(c) && player > 0 && player <= TTTGame.numPlayers && c.checkBoundaries(size, size) == true) {
			board[c.getX()][c.getY()] = player;
		} else {
			throw new IllegalArgumentException("I cant let you do that David");
		}
	}

	/**
	 * returns true if, and only if, there are no more free positions on the board
	 */
	public boolean checkFull() {

		if (turnCounter() > Math.pow((TTTGame.numPlayers + 1), 2))
			return true;
		else
			;
		return false;

	}

	public static int count = 0;

	public static int turnCounter() {
		count++;
		return count;
	}

	/**
	 * returns 0 if no player has won (yet) otherwise returns the number of the
	 * player that has three in a row
	 */

	public int checkWinning() {
		
		for (int i = 0; i < getSize(); i++) {// row check first the coloum.
			for (int j = 2; j < getSize(); j++) {
				double row = Math.pow((board[i][j - 2] * board[i][j - 1] * board[i][j]), 1.0 / 3);
				if (row == Math.floor(row) && row!=0) {
					return (int) row;
				}
				double col = Math.pow((board[j - 2][i] * board[j - 1][i] * board[j][i]), 1.0 / 3);
				if (col == Math.floor(col) && col!=0) {
					return (int) col;
				}

			}
		}
		for (int i = 0; i < getSize()-2; i++) {// diaDown
			for (int j = 0; j < getSize()-2; j++) {
				double diaDown = Math.pow((board[i][j] * board[i+1][j+1] * board[i+2][j+2]), 1.0 / 3);
				if (diaDown == Math.floor(diaDown) && diaDown!=0) {
					return (int) diaDown;
				}
			}
		}
		for (int i = getSize()-1; i >=2; i--) {// diaUp
			for (int j = 0; j < getSize()-2; j++) {
				double diaUp = Math.pow((board[i][j] * board[i-1][j+1] * board[i-2][j+2]), 1.0 / 3);
				if (diaUp == Math.floor(diaUp) && diaUp!=0) {
					return (int) diaUp;
				}

			}
		}		
		return 0;
	}

	/** getter for size of the board */
	public int getSize() {
		return this.size;
	}

	/**
	 * pretty printing of the board usefule for debugging purposes
	 */
	public String toString() {
		String result = "";
		for (int y = 0; y < this.size; y++) {
			for (int x = 0; x < this.size; x++) {
				result += this.board[y][x] + " ";
			}
			result += "\n";
		}
		return result;
	}

}