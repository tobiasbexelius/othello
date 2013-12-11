package kth.game.othello.player.movestrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import kth.game.othello.Rules;
import kth.game.othello.board.Board;
import kth.game.othello.board.Node;

/**
 * Picks a random node in the board.
 * 
 * @author Oskar & Lina
 * 
 */
public class RandomMoveStrategy implements MoveStrategy {
	@Override
	public String getName() {
		return "RandomMoveStrategy";
	}

	@Override
	public Node move(String playerId, Rules rules, Board board) {
		List<Node> validNodes = new ArrayList<>();
		for (Node n : board.getNodes()) {
			if (rules.isMoveValid(playerId, n.getId()))
				validNodes.add(n);
		}
		if (validNodes.size() > 0) {
			Random ran = new Random();
			int picked = ran.nextInt(validNodes.size());
			return validNodes.get(picked);
		}
		return null;
	}
}
