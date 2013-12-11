package kth.game.othello.board;

/**
 * This is a helper class that keeps directions to traverse the board. It's
 * condidered a 'friendly'.
 * 
 * author Lina och Oskar
 * 
 */
public enum Direction {
	NORTH(0, 1), NORTHEAST(1, 1), EAST(1, 0), SOUTHEAST(1, -1), SOUTH(0, -1), SOUTHWEST(-1, -1), WEST(-1, 0), NORTHWEST(
			-1, 1);
	private final int x_coord;
	private final int y_coord;

	private Direction(int x, int y) {
		x_coord = x;
		y_coord = y;
	}

	public int getXCoord() {
		return x_coord;
	}

	public int getYCoord() {
		return y_coord;
	}
}
