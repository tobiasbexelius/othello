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
	private int[] dX = { 0, 0, 1, -1, 1, -1, -1, 1 };
	private int[] dY = { 1, -1, 0, 0, 1, -1, 1, -1 };

	public OthelloImpl(Player player1, Player player2, Board board) {
		boardHandler = new BoardHandler(board);
		playerHandler = new PlayerHandler(player1, player2); 		
		random = new Random();
	}

	@Override
	public Board getBoard() {
		return boardHandler.getBoard();
	}

	@Override
	public List<Node> getNodesToSwap(String playerId, String nodeId) {
		List<Node> swappedNodes = new ArrayList<Node>();
		Node move = boardHandler.getNode(nodeId);
		if (move == null)
			return swappedNodes;

		for (int i = 0; i < dY.length; i++) {
			List<Node> nodesInDirection = getNodesToSwapInDirection(playerId, move, dX[i], dY[i]);
			if (nodesInDirection != null)
				swappedNodes.addAll(nodesInDirection);
		}
		return swappedNodes;
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
		List<Player> players = playerHandler.getPlayers();
		for(Player p: players) {
			if(hasValidMove(p.getId()))
				return true;
		}
		return false;
	}

	@Override
	public boolean isMoveValid(String playerId, String nodeId) {
		Node move = boardHandler.getNode(nodeId);
		if (move == null)
			return false;
		if (move.isMarked())
			return false;
		for (int i = 0; i < dY.length; i++) {
			if (canSwapInDirection(playerId, move, dX[i], dY[i]))
				return true;
		}
		return false;
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
	 * Checks if a player can swap nodes in a certain direction. For example, to
	 * check left xDir and yDir should be (-1, 0).
	 * 
	 * @param playerId
	 *            the player who is trying to capture nodes
	 * @param move
	 *            the move the player makes to capture nodes
	 * @param xDir
	 *            the x-axis direction to check in
	 * @param yDir
	 *            the y axis direction to check in
	 * @return true if a capture could be made in the direction, false otherwise
	 */
	private boolean canSwapInDirection(String playerId, Node move, int xDir, int yDir) {
		Node next = boardHandler.getNode(move.getXCoordinate() + xDir, move.getYCoordinate() + yDir);

		if (next == null || !next.isMarked() || next.getOccupantPlayerId().equals(playerId))
			return false;

		while (true) {
			next = boardHandler.getNode(next.getXCoordinate() + xDir, next.getYCoordinate() + yDir);
			if (next == null || !next.isMarked())
				return false;
			if (next.getOccupantPlayerId().equals(playerId))
				return true;
		}
	}

	/**
	 * Retrieves all the nodes that will be swapped in a certain direction when
	 * making a move. For example, to retrieve nodes to the left, xDir and yDir
	 * should be (-1, 0).
	 * 
	 * @param playerId
	 *            the player who is swapping nodes
	 * @param move
	 *            the move the player makes to swap nodes
	 * @param xDir
	 *            the x-axis direction to check in
	 * @param yDir
	 *            the y axis direction to check in
	 * @return a list of all the nodes that were swapped. Returns null if no
	 *         node could be swapped
	 */
	private List<Node> getNodesToSwapInDirection(String playerId, Node move, int xDir, int yDir) {
		Node next = boardHandler.getNode(move.getXCoordinate() + xDir, move.getYCoordinate() + yDir);
		if (next == null || !next.isMarked() || next.getOccupantPlayerId().equals(playerId))
			return null;
		List<Node> swappedNodes = new ArrayList<Node>();
		while (!next.getOccupantPlayerId().equals(playerId)) {
			swappedNodes.add(next);
			next = boardHandler.getNode(next.getXCoordinate() + xDir, next.getYCoordinate() + yDir);
			if (next == null || !next.isMarked())
				return null;
		}
		return swappedNodes;
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
