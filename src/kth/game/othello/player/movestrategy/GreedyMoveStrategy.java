package kth.game.othello.player.movestrategy;

import java.util.ArrayList;
import java.util.List;

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
		int bestScore = -1;
		Node bestMove = null;
		List<Node> possibleMoves = findPossibleMoves(playerId, rules, board);
		for (Node move : possibleMoves) {
			int currentScore = rules.getNodesToSwap(playerId, move.getId()).size();
			if (currentScore > bestScore) {
				bestScore = currentScore;
				bestMove = move;
			}
		}
		return bestMove;
	}

	/**
	 * Finds all possible moves a certain player can make.
	 * 
	 * @param playerId
	 *            the player whom moves will be found for
	 * @return a list of all the moves for the player
	 */
	private List<Node> findPossibleMoves(String playerId, Rules rules, Board board) {
		List<Node> moves = new ArrayList<Node>();
		for (Node node : board.getNodes()) {
			if (!node.isMarked()) {
				if (rules.isMoveValid(playerId, node.getId())) {
					moves.add(node);
				}
			}
		}
		return moves;
	}
}
