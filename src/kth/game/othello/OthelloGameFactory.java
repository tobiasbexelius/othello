package kth.game.othello;

import java.util.List;

import kth.game.othello.board.Board;
import kth.game.othello.board.Node;
import kth.game.othello.board.OthelloBoard;
import kth.game.othello.board.OthelloNode;
import kth.game.othello.player.OthelloPlayer;
import kth.game.othello.player.Player;
import kth.game.othello.player.Player.Type;

public class OthelloGameFactory implements OthelloFactory{
	
	private static final int ROWS = 8;
	private static final int COLUMNS = 8;
	
	@Override
	public Othello createComputerGameOnClassicalBoard() {
		Player player1 = new OthelloPlayer("1", "Computer 1", Type.COMPUTER);
		Player player2 = new OthelloPlayer("2", "Computer 2", Type.COMPUTER);
		Board board = createBoard(player1,player2);
		Othello game = new OthelloGame(player1, player2, board);
		return game;
	}
	
	@Override
	public Othello createHumanGameOnOriginalBoard() {
		Player player1 = new OthelloPlayer("1", "Computer 1", Type.HUMAN);
		Player player2 = new OthelloPlayer("2", "Computer 2", Type.HUMAN);
		Board board = createBoard(player1,player2);
		Othello game = new OthelloGame(player1, player2, board);
		return game;
	}

	@Override
	public Othello createHumanVersusComputerGameOnOriginalBoard() {
		Player player1 = new OthelloPlayer("1", "Computer 1", Type.HUMAN);
		Player player2 = new OthelloPlayer("2", "Computer 2", Type.COMPUTER);
		Board board = createBoard(player1,player2);
		Othello game = new OthelloGame(player1, player2, board);
		return game;
	}
	
	private Board createBoard(Player player1, Player player2) {
		Board board = new OthelloBoard();
		List<Node> nodes = board.getNodes();
		fillBoard(nodes, player1, player2);
		return board;
	}

	/**
	 * Fill the board with nodes.
	 * 27,28
	 * 35,36
	 */
	private void fillBoard(List<Node> nodes, Player player1, Player player2) {
		for(int row = 0; row < ROWS; row++)  {
			for(int column = 0; column < COLUMNS; column++) {
				String id = Integer.toString((COLUMNS*row+column));
				nodes.add(new OthelloNode(column, row, false, id, null));
			}
		}
		occupyNode(nodes.get(27), nodes, player1.getId());
		occupyNode(nodes.get(28), nodes, player2.getId());
		occupyNode(nodes.get(35), nodes, player2.getId());
		occupyNode(nodes.get(36), nodes, player1.getId());
	}

	private void occupyNode(Node node, List<Node> nodes, String occupantPlayerId) {
		int nodeIndex = nodes.indexOf(node);
		nodes.remove(node);
		nodes.add(nodeIndex, new OthelloNode(node.getXCoordinate(), node.getYCoordinate(), true, node.getId(), occupantPlayerId));
	}
	
}
