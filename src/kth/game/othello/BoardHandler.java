package kth.game.othello;

import java.util.List;

import kth.game.othello.board.Board;
import kth.game.othello.board.Node;
import kth.game.othello.board.NodeImpl;

/**
 * 
 * This class handles operations on a board made up of nodes.
 * 
 */
class BoardHandler {

	private Board board;

	public BoardHandler(Board board) {
		this.board = board;
	}

	/**
	 * Swaps all nodes in a list so that they belong to a certain player.
	 * 
	 * @param nodesToSwap
	 *            the nodes to be swapped
	 * @param playerId
	 *            the id of the player who will own the swapped nodes
	 * @return a list of all nodes that were swapped
	 */
	public List<Node> swapNodes(List<Node> nodesToSwap, String playerId) {
		for (Node node : nodesToSwap) {
			occupyNode(node, playerId);
		}
		return nodesToSwap;
	}

	/**
	 * Occupies a certain node on the board.
	 * 
	 * @param node
	 *            the node to be occupied
	 * @param occupantPlayerId
	 *            the player who will occupy the node
	 */
	private void occupyNode(Node node, String occupantPlayerId) {
		NodeImpl nodeImpl = (NodeImpl) node;
		nodeImpl.updateOccupantPlayer(occupantPlayerId);
	}

	public Node getNode(int x, int y) {
		return board.getNode(x, y);
	}

	/**
	 * Retrieves a node from the board.
	 * 
	 * @param nodeId
	 *            the id of the node to be retrieved
	 * @return a node with the specified id. Null if no node with the id exists
	 */
	public Node getNode(String nodeId) {
		for (Node node : board.getNodes()) {
			if (node.getId().equals(nodeId)) {
				return node;
			}
		}
		return null;
	}

	public Board getBoard() {
		return board;
	}

	public List<Node> getNodes() {
		return board.getNodes();
	}
}
