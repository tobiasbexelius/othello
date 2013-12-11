package kth.game.othello.player.movestrategy;

import kth.game.othello.Rules;
import kth.game.othello.board.Board;
import kth.game.othello.board.Node;

/**
 * Picks a node starting from the last node in the list.
 * 
 * @author Oskar & Lina
 * 
 */
public class SimpleFromLastNodeMoveStrategy implements MoveStrategy {
	@Override
	public String getName() {
		return "SimpleFromLastNodeMoveStrategy";
	}

	@Override
	public Node move(String playerId, Rules rules, Board board) {
		for (int i = board.getNodes().size() - 1; i > 0; i--) {
			if (rules.isMoveValid(playerId, board.getNodes().get(i).getId())) {
				return board.getNodes().get(i);
			}
		}
		return null;
	}
}
