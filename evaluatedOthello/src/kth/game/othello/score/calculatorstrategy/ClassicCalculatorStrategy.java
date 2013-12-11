package kth.game.othello.score.calculatorstrategy;

import java.util.List;

import kth.game.othello.board.Node;

/**
 * Calculates score the given player by 1 point per node owned.
 * 
 * @author Oskar & Lina
 * 
 */
public class ClassicCalculatorStrategy implements CalculatorStrategy {

	@Override
	public int getPlayerPoints(List<Node> nodes, String playerId) {
		int points = 0;
		for (Node n : nodes) {
			points = points + givePointsIfPlayerOwnsNode(n, playerId);
		}
		return points;
	}

	private int givePointsIfPlayerOwnsNode(Node node, String playerId) {
		if (node.getOccupantPlayerId() != null)
			if (node.getOccupantPlayerId().equals(playerId))
				return 1;
		return 0;
	}
}
