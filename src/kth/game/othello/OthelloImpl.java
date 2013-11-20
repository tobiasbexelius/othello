package kth.game.othello;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import kth.game.othello.board.Board;
import kth.game.othello.board.Node;
import kth.game.othello.player.Player;
import kth.game.othello.player.Player.Type;

public class OthelloImpl implements Othello {

	private BoardHandler boardHandler;
	private Random random;
	private PlayerHandler playerHandler;
	private RuleHandler ruleHandler;

	public OthelloImpl(Player player1, Player player2, Board board) {
		boardHandler = new BoardHandler(board);
		playerHandler = new PlayerHandler(player1, player2); 
		ruleHandler = new RuleHandler(boardHandler, playerHandler);
		random = new Random();
	}

	@Override
	public Board getBoard() {
		return boardHandler.getBoard();
	}

	@Override
	public List<Node> getNodesToSwap(String playerId, String nodeId) {
		return ruleHandler.getNodesToSwap(playerId, nodeId);
	}

	@Override
	public Player getPlayerInTurn() {
		return playerHandler.getPlayerInTurn();
	}

	@Override
	public List<Player> getPlayers() {
		return playerHandler.getPlayers();
	}

	@Override
	public boolean hasValidMove(String playerId) {
		if (findPossibleMoves(playerId).size() == 0)
			return false;
		return true;
	}

	@Override
	public boolean isActive() {
		return ruleHandler.isActive();
	}

	@Override
	public boolean isMoveValid(String playerId, String nodeId) {
		return ruleHandler.isMoveValid(playerId, nodeId);
	}

	@Override
	public List<Node> move() throws IllegalArgumentException {
		if (getPlayerInTurn().getType() != Type.COMPUTER)
			throw new IllegalArgumentException();

		String playerId = getPlayerInTurn().getId();
		if (!hasValidMove(playerId)) {
			playerHandler.swapPlayerInTurn();
			return new ArrayList<Node>();
		}
		List<Node> possibleMoves = findPossibleMoves(playerId);
		int moveIndex = random.nextInt(possibleMoves.size());
		Node move = possibleMoves.get(moveIndex);

		List<Node> nodesToSwap = getNodesToSwap(playerId, move.getId());
		nodesToSwap.add(move);
		List<Node> swappedNodes = boardHandler.swapNodes(nodesToSwap, playerId);

		playerHandler.swapPlayerInTurn();

		return swappedNodes;
	}

	@Override
	public List<Node> move(String playerId, String nodeId) throws IllegalArgumentException {
		if (!playerHandler.playerIsInTurn(playerId) || !isMoveValid(playerId, nodeId))
			throw new IllegalArgumentException();

		Node move = boardHandler.getNode(nodeId);
		List<Node> nodesToSwap = getNodesToSwap(playerId, move.getId());
		nodesToSwap.add(move);
		List<Node> swappedNodes = boardHandler.swapNodes(nodesToSwap, playerId);
		playerHandler.swapPlayerInTurn();
		return swappedNodes;
	}

	@Override
	public void start() {
		int index = random.nextInt(getPlayers().size());
		playerHandler.setPlayerInTurn(getPlayers().get(index));
	}

	@Override
	public void start(String playerId) {
		playerHandler.setPlayerInTurn(playerHandler.getPlayer(playerId));
	}
	
	/**
	 * Finds all possible moves a certain player can make.
	 * 
	 * @param playerId the player whom moves will be found for
	 * @return a list of all the moves for the player
	 */
	private List<Node> findPossibleMoves(String playerId) {
		List<Node> moves = new ArrayList<Node>();
		for (Node node : boardHandler.getNodes()) {
			if (!node.isMarked()) {
				if (isMoveValid(playerId, node.getId())) {
					moves.add(node);
				}
			}
		}
		return moves;
	}
}
