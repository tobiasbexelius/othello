package kth.game.othello;

import java.util.ArrayList;
import java.util.List;

import kth.game.othello.board.Board;
import kth.game.othello.board.BoardImpl;
import kth.game.othello.board.Node;
import kth.game.othello.board.NodeImpl;
import kth.game.othello.player.Player;
import kth.game.othello.player.Player.Type;
import kth.game.othello.player.PlayerImpl;

public class OthelloFactoryImpl  {

	public Othello createComputerGameOnClassicalBoard() {
		List<Player> players = new ArrayList<Player>();
		players.add(new PlayerImpl("1", "Computer 1", Type.COMPUTER));
		players.add(new PlayerImpl("2", "Computer 2", Type.COMPUTER));
		return createGame(players, 8, 8);
	}

	public Othello createHumanGameOnOriginalBoard() {
		List<Player> players = new ArrayList<Player>();
		players.add(new PlayerImpl("1", "Human 1", Type.HUMAN));
		players.add(new PlayerImpl("2", "Human 2", Type.HUMAN));
		return createGame(players, 8, 8);
	}

	public Othello createHumanVersusComputerGameOnOriginalBoard() {
		List<Player> players = new ArrayList<Player>();
		players.add(new PlayerImpl("1", "Computer 1", Type.COMPUTER));
		players.add(new PlayerImpl("2", "Human 1", Type.HUMAN));
		return createGame(players, 8, 8);
	}

	/**
	 * Create a new othello with two players and a set board width and height.
	 * 
	 * @param player1
	 *            the first player
	 * @param player2
	 *            the second player
	 * @param boardWidth
	 *            the width of the othello board (in squares)
	 * @param boardHeight
	 *            the height of the othello board (in squares)
	 * @return a new othello
	 */
	private Othello createGame(List<Player> players, int boardWidth, int boardHeight) {
		Board board = createBoard();
		occupyInitialNodes(board, players.get(0), players.get(1));
		Othello game = new OthelloImpl(players, board);
		return game;
	}

	/**
	 * Create a new board with the given players.
	 * 
	 * @return a new othello board filled with nodes.
	 */
	private Board createBoard() {
		List<Node> nodes = new ArrayList<Node>();
		for (int row = 0; row < 8; row++) {
			for (int column = 0; column < 8; column++) {
				String id = Integer.toString((8 * row + column));
				nodes.add(new NodeImpl(column, row, id, null));
			}
		}
		return new BoardImpl(nodes);
	}

	/**
	 * Occupies the four middle nodes of the board.
	 * 
	 * @param board
	 *            the board which will have it's nodes occupied
	 * @param player1
	 *            the player who will occupy node 27 and 36
	 * @param player2
	 *            the player who will occupy node 28 and 35
	 */
	private void occupyInitialNodes(Board board, Player player1, Player player2) {
		List<Node> nodes = board.getNodes();

		occupyNode(nodes.get(27), nodes, player1.getId());
		occupyNode(nodes.get(28), nodes, player2.getId());
		occupyNode(nodes.get(35), nodes, player2.getId());
		occupyNode(nodes.get(36), nodes, player1.getId());
	}

	/**
	 * Occupies a certain node on the board.
	 * 
	 * @param node
	 *            the node to be occupied
	 * @param a
	 *            list of nodes which contains the node to be occupied
	 * @param occupantPlayerId
	 *            the player who will occupy the node
	 * @return true if the node was on the board, false otherwise
	 */
	private void occupyNode(Node node, List<Node> nodes, String occupantPlayerId) {
		int nodeIndex = nodes.indexOf(node);
		nodes.remove(node);
		nodes.add(nodeIndex, new NodeImpl(node.getXCoordinate(), node.getYCoordinate(), node.getId(),
				occupantPlayerId));
	}

}
