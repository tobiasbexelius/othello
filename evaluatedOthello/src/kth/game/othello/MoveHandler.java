package kth.game.othello;

import java.util.List;

import kth.game.othello.board.Board;
import kth.game.othello.board.Node;

/**
 * Responsible for movement on the board.
 * 
 * Author: Lina och Oskar Date: 2013-11-19 Time: 17:53
 */
public interface MoveHandler {

	/**
	 * Places a specified marker (node) for the given player and updates markers
	 * according to the rules.
	 * 
	 * @param playerId
	 *            player who makes the move
	 * @param nodeId
	 *            the target node for the move
	 * @return the full list of nodes that were updated
	 */
	public List<Node> move(String playerId, String nodeId);

	/**
	 * @return the active game board
	 */
	public Board getBoard();

}
