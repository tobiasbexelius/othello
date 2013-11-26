package kth.game.othello;

import java.util.ArrayList;
import java.util.List;

import kth.game.othello.board.Node;
import kth.game.othello.player.Player.Type;
import kth.game.othello.player.movestrategy.MoveStrategy;

public class MoveHandler {

	private BoardHandler boardHandler;
	private PlayerHandler playerHandler;
	private RuleHandler ruleHandler;

	public MoveHandler(BoardHandler boardHandler, PlayerHandler playerHandler, RuleHandler ruleHandler) {
		this.boardHandler = boardHandler;
		this.playerHandler = playerHandler;
		this.ruleHandler = ruleHandler;
	}

	public List<Node> move() {
		if (playerHandler.getPlayerInTurn().getType() != Type.COMPUTER)
			throw new IllegalArgumentException();

		String playerId = playerHandler.getPlayerInTurn().getId();
		if (!ruleHandler.hasValidMove(playerId)) {
			playerHandler.swapPlayerInTurn();
			return new ArrayList<Node>();
		}
		MoveStrategy moveStrategy = playerHandler.getPlayer(playerId).getMoveStrategy();
		Node move = moveStrategy.move(playerId, ruleHandler, boardHandler.getBoard());

		List<Node> nodesToSwap = ruleHandler.getNodesToSwap(playerId, move.getId());
		nodesToSwap.add(move);
		List<Node> swappedNodes = boardHandler.swapNodes(nodesToSwap, playerId);

		playerHandler.swapPlayerInTurn();

		return swappedNodes;
	}

	public List<Node> move(String playerId, String nodeId) throws IllegalArgumentException {
		if (!playerHandler.playerIsInTurn(playerId) || !ruleHandler.isMoveValid(playerId, nodeId))
			throw new IllegalArgumentException();

		Node move = boardHandler.getNode(nodeId);
		List<Node> nodesToSwap = ruleHandler.getNodesToSwap(playerId, move.getId());
		nodesToSwap.add(move);
		List<Node> swappedNodes = boardHandler.swapNodes(nodesToSwap, playerId);
		playerHandler.swapPlayerInTurn();
		return swappedNodes;
	}
}
