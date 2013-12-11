package kth.game.othello.score.calculatorstrategy;

import java.util.List;

import kth.game.othello.board.Node;

/**
 * Strategy for calculating the points for a player given a list of Nodes.
 * 
 * @author Oskar & Lina
 */
public interface CalculatorStrategy {

	/**
	 * Calculates a given player's points.
	 * 
	 * @param nodes
	 *            the game board's Nodes
	 * @param playerId
	 *            the current player
	 * @return the points
	 */
	public int getPlayerPoints(List<Node> nodes, String playerId);
}
