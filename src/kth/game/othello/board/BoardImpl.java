package kth.game.othello.board;

import java.util.Collections;
import java.util.List;

public class BoardImpl implements Board {

	private List<Node> nodes;
	private int maxX;
	private int maxY;

	public BoardImpl(List<Node> nodes) {
		Collections.sort(nodes);
		this.nodes = nodes;
		maxX = -1;
		maxY = -1;
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
		if (maxX == -1) {
			int max = Integer.MIN_VALUE;
			for (Node node : getNodes()) {
				if (node.getXCoordinate() > max) {
					max = node.getXCoordinate();
				}
			}
			maxX = max;
		}
		return maxX;
	}

	@Override
	public int getMaxY() {
		if (maxY == -1) {
			int max = Integer.MIN_VALUE;
			for (Node node : getNodes()) {
				if (node.getYCoordinate() > max) {
					max = node.getYCoordinate();
				}
			}
			maxY = max;
		}
		return maxY;
	}

	@Override
	public boolean hasNode(int x, int y) {
		for (Node node : getNodes()) {
			if (node.getXCoordinate() == x && node.getYCoordinate() == y) {
				return true;
			}
		}
		return false;
	}

}
