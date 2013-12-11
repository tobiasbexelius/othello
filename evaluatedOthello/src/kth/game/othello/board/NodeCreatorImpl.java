package kth.game.othello.board;

/**
 * Author: Lina och Oskar Date: 2013-11-21 Time: 22:54
 */
public class NodeCreatorImpl implements NodeCreator {
	@Override
	public Node createNodeWithCoordinate(int x, int y) {
		return new NodeImpl(x, y);
	}

	@Override
	public Node createNodeWithCoordinateAndOccupant(int x, int y, String occupantPlayerId) {
		return new NodeImpl(x, y, occupantPlayerId);
	}
}
