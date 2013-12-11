package kth.game.othello;

import java.util.List;

import kth.game.othello.board.Board;
import kth.game.othello.board.BoardImpl;
import kth.game.othello.board.Node;
import kth.game.othello.board.NodeImpl;

public class MoveHandlerImpl implements MoveHandler {

	private final Rules rules;
	private final Board board;

	public MoveHandlerImpl(Rules rules, Board board) {
		this.rules = rules;
		this.board = board;
	}

	@Override
	public List<Node> move(String playerId, String nodeId) {
		List<Node> nodesToUpdate = rules.getNodesToSwap(playerId, nodeId);
		if (nodesToUpdate.size() > 0) {
			nodesToUpdate.add(((BoardImpl) board).getNodeById(nodeId));
			updateNodes(nodesToUpdate, playerId);
		}
		return nodesToUpdate;
	}

	private void updateNodes(List<Node> nodesToUpdate, String playerId) {
		for (Node n : nodesToUpdate) {
			((NodeImpl) n).setOccupantPlayer(playerId);
		}
	}

	@Override
	public Board getBoard() {
		return board;
	}

}
