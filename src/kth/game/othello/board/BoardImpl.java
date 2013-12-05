package kth.game.othello.board;

import java.util.Collections;
import java.util.List;

public class BoardImpl implements Board {

	private List<Node> nodes;

	public BoardImpl(List<Node> nodes) {
		Collections.sort(nodes);
		this.nodes = nodes;
	}

	@Override
	public List<Node> getNodes() {
		return nodes;
	}

	/**
	 * Retrieves a node from the board.
	 * 
	 * @param x
	 *            the x-coordinate of the node
	 * @param y
	 *            the y-coordinate of the node
	 * @return a node with the specified coordinates. Null if the coordinates
	 *         are outside of the board
	 */
	@Override
	public Node getNode(int x, int y) {
		for (Node node : getNodes()) {
			if (node.getXCoordinate() == x && node.getYCoordinate() == y) {
				return node;
			}
		}
		return null;
	}

	@Override
	public int getMaxX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMaxY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean hasNode(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}

}
