package kth.game.othello.player.movestrategy;

import kth.game.othello.Rules;
import kth.game.othello.board.Board;
import kth.game.othello.board.Node;

/**
 * Picks a node starting from the first in the node list.
 * 
 * @author Oskar & Lina
 * 
 */
public class SimpleFromFirstNodeMoveStrategy implements MoveStrategy {
	@Override
	public String getName() {
		return "SimpleFromFirstNodeMoveStrategy";
	}

	@Override
	public Node move(String playerId, Rules rules, Board board) {
		for (Node n : board.getNodes()) {
			if (rules.isMoveValid(playerId, n.getId()))
				return n;
		}
		return null;
	}
}
