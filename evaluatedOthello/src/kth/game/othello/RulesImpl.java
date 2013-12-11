package kth.game.othello;

import java.util.ArrayList;
import java.util.List;

import kth.game.othello.board.BoardImpl;
import kth.game.othello.board.Direction;
import kth.game.othello.board.Node;

/**
 * Author: Lina och Oskar Date: 2013-11-28 Time: 19:45
 */
public class RulesImpl implements Rules {
	private final BoardImpl boardImpl;

	public RulesImpl(BoardImpl theBoard) {
		boardImpl = theBoard;
	}

	@Override
	public List<Node> getNodesToSwap(String playerId, String nodeId) {
		List<Node> noNodesToSwap = new ArrayList<>();
		if (!boardImpl.hasNode(nodeId) || playerId == null) {
			return noNodesToSwap;
		}

		Node nodeImpl = boardImpl.getNodeById(nodeId);
		if (nodeImpl.isMarked()) {
			return noNodesToSwap;
		}

		List<Node> nodesToSwap = new ArrayList<>();
		for (Direction dir : Direction.values()) {
			nodesToSwap.addAll(getNodesToSwapInOneDirection(playerId, nodeImpl, dir));
		}
		return nodesToSwap;
	}

	private List<Node> getNodesToSwapInOneDirection(String playerId, Node node, Direction dir) {
		Node nextNode;
		int nodeX = node.getXCoordinate();
		int nodeY = node.getYCoordinate();
		List<Node> nodesToSwapOneDir = new ArrayList<>();

		for (int i = 1; i < boardImpl.getNodes().size(); i++) {
			int nextNodeX = nodeX + (i * dir.getXCoord());
			int nextNodeY = nodeY + (i * dir.getYCoord());
			if (!boardImpl.hasNode(nextNodeX, nextNodeY)) {
				nodesToSwapOneDir = new ArrayList<>();
				break;
			}
			nextNode = boardImpl.getNode(nextNodeX, nextNodeY);
			if (!nextNode.isMarked()) {
				nodesToSwapOneDir = new ArrayList<>();
				break;
			}
			String nextNodeOccupant = nextNode.getOccupantPlayerId();
			if (nextNodeOccupant.equals(playerId)) {
				break;
			} else {
				nodesToSwapOneDir.add(nextNode);
			}
		}
		return nodesToSwapOneDir;
	}

	@Override
	public boolean hasValidMove(String playerId) {
		for (Node node : boardImpl.getNodes()) {
			if (isMoveValid(playerId, node.getId())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isMoveValid(String playerId, String nodeId) {
		List<Node> tmpList = getNodesToSwap(playerId, nodeId);
		return !tmpList.isEmpty();
	}
}
