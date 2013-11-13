package kth.game.othello.board;

import java.util.ArrayList;
import java.util.List;

public class OthelloBoard implements Board{
	
	private List<Node> nodes;
	private static final int ROWS = 8;
	private static final int COLUMNS = 8;

	
	public OthelloBoard() {
		nodes = new ArrayList<Node>();
		createNodes();
	}
	
	/**
	 * Fill the board with nodes.
	 */
	private void createNodes() {
		for(int row = 0; row < ROWS; row++)  {
			for(int column = 0; column < COLUMNS; column++) {
				String id = Integer.toString((COLUMNS*row+column));
				nodes.add(new OthelloNode(column, row, false, id, null));
			}
		}
	}
	
	@Override
	public List<Node> getNodes() {
		return nodes;
	}

}
