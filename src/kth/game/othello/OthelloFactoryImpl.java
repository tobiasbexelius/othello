package kth.game.othello;

import java.util.List;

import kth.game.othello.board.Board;
import kth.game.othello.board.Node;
import kth.game.othello.board.BoardImpl;
import kth.game.othello.board.NodeImpl;
import kth.game.othello.player.PlayerImpl;
import kth.game.othello.player.Player;
import kth.game.othello.player.Player.Type;

public class OthelloFactoryImpl implements OthelloFactory{
	
	private static final int ROWS = 8;
	private static final int COLUMNS = 8;
	
	@Override
	public Othello createComputerGameOnClassicalBoard() {
		Player player1 = new PlayerImpl("1", "Computer 1", Type.COMPUTER);
		Player player2 = new PlayerImpl("2", "Computer 2", Type.COMPUTER);
		Board board = createBoard(player1,player2);
		occupyInitialNodes(board, player1, player2);
		Othello game = new OthelloImpl(player1, player2, board);
		return game;
	}
	
	@Override
	public Othello createHumanGameOnOriginalBoard() {
		Player player1 = new PlayerImpl("1", "Human 1", Type.HUMAN);
		Player player2 = new PlayerImpl("2", "Human 2", Type.HUMAN);
		Board board = createBoard(player1,player2);
		occupyInitialNodes(board, player1, player2);
		Othello game = new OthelloImpl(player1, player2, board);
		return game;
	}

	@Override
	public Othello createHumanVersusComputerGameOnOriginalBoard() {
		Player player1 = new PlayerImpl("1", "Human 1", Type.HUMAN);
		Player player2 = new PlayerImpl("2", "Computer 1", Type.COMPUTER);
		Board board = createBoard(player1,player2);
		occupyInitialNodes(board, player1, player2);
		Othello game = new OthelloImpl(player1, player2, board);
		return game;
	}
	
	/**
	 * Create a new board and fill it with nodes. 
	 * 
	 * @return a new othello board filled with nodes.
	 */
	private Board createBoard(Player player1, Player player2) {
		Board board = new BoardImpl();
		List<Node> nodes = board.getNodes();
		fillBoard(nodes);
		return board;
	}

	/**
	 * Fill the board with nodes. All nodes except for the four middle ones (27,28,35,36) will 
	 * be unoccupied. Nodes 27 and 35 will be occupied by player 1, and 28 and 36 by player 2.
	 */
	private void fillBoard(List<Node> nodes) {
		for(int row = 0; row < ROWS; row++)  {
			for(int column = 0; column < COLUMNS; column++) {
				String id = Integer.toString((COLUMNS*row+column));
				nodes.add(new NodeImpl(column, row, false, id, null));
			}
		}
	}
	
	/**
	 * Occupies the four middle nodes of the board. 
	 * 
	 * @param board the board which will have it's nodes occupied
	 * @param player1 the player who will occupy node 27 and 36
	 * @param player2 the player who will occupy node 28 and 35
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
	 * @param node the node to be occupied
	 * @param a list of nodes which contains the node to be occupied
	 * @param occupantPlayerId the player who will occupy the node
	 * @return true if the node was on the board, false otherwise
	 */
	private void occupyNode(Node node, List<Node> nodes, String occupantPlayerId) {
		int nodeIndex = nodes.indexOf(node);
		nodes.remove(node);
		nodes.add(nodeIndex, new NodeImpl(node.getXCoordinate(), node.getYCoordinate(), true, node.getId(), occupantPlayerId));
	}
	
}
