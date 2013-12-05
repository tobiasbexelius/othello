package kth.game.othello;

import java.util.ArrayList;
import java.util.List;

import kth.game.othello.board.Node;
import kth.game.othello.player.Player.Type;
import kth.game.othello.player.movestrategy.MoveStrategy;

/**
 * 
 * The responsibility of this class is to handle logic for moving a player.
 * 
 */
class MoveHandler {

	private BoardHandler boardHandler;
	private RuleHandler ruleHandler;

	public MoveHandler(BoardHandler boardHandler, RuleHandler ruleHandler) {
		this.boardHandler = boardHandler;
		this.ruleHandler = ruleHandler;
	}

	public List<Node> move() {
		if (ruleHandler.getPlayerInTurn().getType() != Type.COMPUTER)
			throw new IllegalStateException();

		String playerId = ruleHandler.getPlayerInTurn().getId();
		if (!ruleHandler.hasValidMove(playerId)) {
			return new ArrayList<Node>();
		}
		MoveStrategy moveStrategy = ruleHandler.getPlayerInTurn().getMoveStrategy();
		Node move = moveStrategy.move(playerId, ruleHandler, boardHandler.getBoard());

		List<Node> nodesToSwap = ruleHandler.getNodesToSwap(playerId, move.getId());
		nodesToSwap.add(move);
		List<Node> swappedNodes = boardHandler.swapNodes(nodesToSwap, playerId);

		return swappedNodes;
	}

	public List<Node> move(String playerId, String nodeId) throws IllegalArgumentException {
		if (!ruleHandler.getPlayerInTurn().getId().equals(playerId) || !ruleHandler.isMoveValid(playerId, nodeId))
			throw new IllegalArgumentException();

		Node move = boardHandler.getNode(nodeId);
		List<Node> nodesToSwap = ruleHandler.getNodesToSwap(playerId, move.getId());
		nodesToSwap.add(move);
		List<Node> swappedNodes = boardHandler.swapNodes(nodesToSwap, playerId);
		return swappedNodes;
	}
}
