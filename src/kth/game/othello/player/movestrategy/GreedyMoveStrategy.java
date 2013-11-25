package kth.game.othello.player.movestrategy;

import kth.game.othello.Rules;
import kth.game.othello.board.Board;
import kth.game.othello.board.Node;

public class GreedyMoveStrategy implements MoveStrategy {

	private static final String STRATEGY_NAME = "Greedy movestrategy";

	@Override
	public String getName() {
		return STRATEGY_NAME;
	}

	@Override
	public Node move(String playerId, Rules rules, Board board) {
		// TODO Auto-generated method stub
		return null;
	}

}
