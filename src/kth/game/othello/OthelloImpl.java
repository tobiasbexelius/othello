package kth.game.othello;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import kth.game.othello.board.Board;
import kth.game.othello.board.Node;
import kth.game.othello.board.NodeImpl;
import kth.game.othello.player.Player;
import kth.game.othello.player.Player.Type;

public class OthelloImpl implements Othello {

	private Board board;
	private Random random;
	private PlayerHandler ph;
	private int[] dX = { 0, 0, 1, -1, 1, -1, -1, 1 };
	private int[] dY = { 1, -1, 0, 0, 1, -1, 1, -1 };

	public OthelloImpl(Player player1, Player player2, Board board) {
		this.board = board;
		ph = new PlayerHandler(player1, player2); 		
		random = new Random();
	}

	@Override
	public Board getBoard() {
		return board;
	}

	@Override
	public List<Node> getNodesToSwap(String playerId, String nodeId) {
		List<Node> swappedNodes = new ArrayList<Node>();
		Node move = getNode(nodeId);
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
		return ph.getPlayerInTurn();
	}

	@Override
	public List<Player> getPlayers() {
		return ph.getPlayers();
	}

	@Override
	public boolean hasValidMove(String playerId) {
		if (findPossibleMoves(playerId).size() == 0)
			return false;
		return true;
	}

	@Override
	public boolean isActive() {
		List<Player> players = ph.getPlayers();
		for(Player p: players) {
			if(hasValidMove(p.getId()))
				return true;
		}
		return false;
	}

	@Override
	public boolean isMoveValid(String playerId, String nodeId) {
		Node move = getNode(nodeId);
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
			ph.swapPlayerInTurn();
			return new ArrayList<Node>();
		}
		List<Node> possibleMoves = findPossibleMoves(playerId);
		int moveIndex = random.nextInt(possibleMoves.size());
		Node move = possibleMoves.get(moveIndex);

		List<Node> nodesToSwap = getNodesToSwap(playerId, move.getId());
		nodesToSwap.add(move);
		List<Node> swappedNodes = swapNodes(nodesToSwap, playerId);

		ph.swapPlayerInTurn();

		return swappedNodes;
	}

	@Override
	public List<Node> move(String playerId, String nodeId) throws IllegalArgumentException {
		if (!ph.playerIsInTurn(playerId) || !isMoveValid(playerId, nodeId))
			throw new IllegalArgumentException();

		Node move = getNode(nodeId);
		List<Node> nodesToSwap = getNodesToSwap(playerId, move.getId());
		nodesToSwap.add(move);
		List<Node> swappedNodes = swapNodes(nodesToSwap, playerId);
		ph.swapPlayerInTurn();
		return swappedNodes;
	}

	@Override
	public void start() {
		int index = random.nextInt(getPlayers().size());
		ph.setPlayerInTurn(getPlayers().get(index));
	}

	@Override
	public void start(String playerId) {
		ph.setPlayerInTurn(ph.getPlayer(playerId));
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
		Node next = getNode(move.getXCoordinate() + xDir, move.getYCoordinate() + yDir);

		if (next == null || !next.isMarked() || next.getOccupantPlayerId().equals(playerId))
			return false;

		while (true) {
			next = getNode(next.getXCoordinate() + xDir, next.getYCoordinate() + yDir);
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
		Node next = getNode(move.getXCoordinate() + xDir, move.getYCoordinate() + yDir);
		if (next == null || !next.isMarked() || next.getOccupantPlayerId().equals(playerId))
			return null;
		List<Node> swappedNodes = new ArrayList<Node>();
		while (!next.getOccupantPlayerId().equals(playerId)) {
			swappedNodes.add(next);
			next = getNode(next.getXCoordinate() + xDir, next.getYCoordinate() + yDir);
			if (next == null || !next.isMarked())
				return null;
		}
		return swappedNodes;
	}

	/**
	 * Retrieves a node from the board.
	 * 
	 * @param nodeId
	 *            the id of the node to be retrieved
	 * @return a node with the specified id. Null if no node with the id exists
	 */
	private Node getNode(String nodeId) {
		for (Node node : board.getNodes()) {
			if (node.getId().equals(nodeId)) {
				return node;
			}
		}
		return null;
	}

	/**
	 * Retrieves a node from the board.
	 * 
	 * @param x
	 *            the x-coordinate of the node
	 * @param y
	 *            the y-coordinate of the node
	 * @return a node with the specified coordinates. Null if the coordinates
	 *         are outside of the board
	 */
	private Node getNode(int x, int y) {
		int index = 8 * y + x;
		if (index >= board.getNodes().size() || index < 0)
			return null;
		return board.getNodes().get(index);
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
	private List<Node> swapNodes(List<Node> nodesToSwap, String playerId) {
		for (Node node : nodesToSwap) {
			occupyNode(node, playerId);
		}
		return nodesToSwap;
	}

	/**
	 * Occupies a certain node on the board.
	 * 
	 * @param node the node to be occupied
	 * @param occupantPlayerId the player who will occupy the node
	 * @return true if the node was on the board, false otherwise
	 */
	private boolean occupyNode(Node node, String occupantPlayerId) {
		int nodeIndex = board.getNodes().indexOf(node);
		if (nodeIndex == -1) {
			return false;
		}
		board.getNodes().remove(node);
		board.getNodes().add(nodeIndex, new NodeImpl(node.getXCoordinate(), node.getYCoordinate(), true, node.getId(),
				occupantPlayerId));
		return true;
	}
	
	/**
	 * Finds all possible moves a certain player can make.
	 * 
	 * @param playerId the player whom moves will be found for
	 * @return a list of all the moves for the player
	 */
	private List<Node> findPossibleMoves(String playerId) {
		List<Node> moves = new ArrayList<Node>();
		for (Node node : board.getNodes()) {
			if (!node.isMarked()) {
				if (isMoveValid(playerId, node.getId())) {
					moves.add(node);
				}
			}
		}
		return moves;
	}

}
