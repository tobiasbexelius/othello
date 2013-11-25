package kth.game.othello.player.movestrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import kth.game.othello.Rules;
import kth.game.othello.board.Board;
import kth.game.othello.board.Node;

public class RandomMoveStrategy implements MoveStrategy {

	private static final String STRATEGY_NAME = "Random movestrategy";

	@Override
	public String getName() {
		return STRATEGY_NAME;
	}

	@Override
	public Node move(String playerId, Rules rules, Board board) {
		List<Node> possibleMoves = findPossibleMoves(playerId, rules, board);
		int index = new Random().nextInt(possibleMoves.size());
		return possibleMoves.get(index);
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
