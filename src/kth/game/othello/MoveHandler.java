package kth.game.othello;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import kth.game.othello.board.Node;
import kth.game.othello.player.Player.Type;

public class MoveHandler {
	
	private BoardHandler boardHandler;
	private PlayerHandler playerHandler;
	private RuleHandler ruleHandler;
	private Random random;
	
	public MoveHandler(BoardHandler boardHandler, PlayerHandler playerHandler, RuleHandler ruleHandler) {
		this.boardHandler = boardHandler;
		this.playerHandler = playerHandler;
		this.ruleHandler = ruleHandler;
		random = new Random();
	}
	
	public List<Node> move() {
		if (playerHandler.getPlayerInTurn().getType() != Type.COMPUTER)
			throw new IllegalArgumentException();

		String playerId = playerHandler.getPlayerInTurn().getId();
		if (!ruleHandler.hasValidMove(playerId)) {
			playerHandler.swapPlayerInTurn();
			return new ArrayList<Node>();
		}
		List<Node> possibleMoves = ruleHandler.findPossibleMoves(playerId);
		int moveIndex = random.nextInt(possibleMoves.size());
		Node move = possibleMoves.get(moveIndex);

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
