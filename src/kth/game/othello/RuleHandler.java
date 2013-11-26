package kth.game.othello;

import java.util.ArrayList;
import java.util.List;

import kth.game.othello.board.Node;
import kth.game.othello.player.Player;

/**
 * TODO
 * 
 */
public class RuleHandler implements Rules {

	private int[] dX = { 0, 0, 1, -1, 1, -1, -1, 1 };
	private int[] dY = { 1, -1, 0, 0, 1, -1, 1, -1 };

	private BoardHandler boardHandler;
	private PlayerHandler playerHandler;

	public RuleHandler(BoardHandler boardHandler, PlayerHandler playerHandler) {
		this.boardHandler = boardHandler;
		this.playerHandler = playerHandler;
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
	public boolean hasValidMove(String playerId) {
		for (Node node : boardHandler.getNodes()) {
			if (!node.isMarked()) {
				if (isMoveValid(playerId, node.getId())) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean isActive() {
		List<Player> players = playerHandler.getPlayers();
		for (Player p : players) {
			if (hasValidMove(p.getId()))
				return true;
		}
		return false;
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

	/**
	 * Checks if a player can swap nodes in a certain direction. For example, to check left xDir and yDir should be (-1,
	 * 0).
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
	 * Retrieves all the nodes that will be swapped in a certain direction when making a move. For example, to retrieve
	 * nodes to the left, xDir and yDir should be (-1, 0).
	 * 
	 * @param playerId
	 *            the player who is swapping nodes
	 * @param move
	 *            the move the player makes to swap nodes
	 * @param xDir
	 *            the x-axis direction to check in
	 * @param yDir
	 *            the y axis direction to check in
	 * @return a list of all the nodes that were swapped. Returns null if no node could be swapped
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
	 * Swap the players in turn. Turns rotate Round Robin-style.
	 */
	public void swapPlayerInTurn() {
		Player currentPlayer = playerHandler.getPlayerInTurn();
		List<Player> players = playerHandler.getPlayers();
		for (int i = 0; i < players.size(); i++) {
			if (currentPlayer.equals(players.get(i))) {
				if (i == players.size() - 1)
					playerHandler.setPlayerInTurn(players.get(0));
				else
					playerHandler.setPlayerInTurn(players.get(i + 1));
				break;
			}
		}
	}

	public Player getPlayerInTurn() {
		return playerHandler.getPlayerInTurn();
	}

}
