package kth.game.othello;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

import kth.game.othello.board.Board;
import kth.game.othello.board.BoardImpl;
import kth.game.othello.board.Node;
import kth.game.othello.player.Player;
import kth.game.othello.player.PlayerCreatorImpl;
import kth.game.othello.score.Score;
import kth.game.othello.score.ScoreImpl;
import kth.game.othello.score.calculatorstrategy.ClassicCalculatorStrategy;

/**
 * author Lina and Oskar
 * 
 * The responsibility of this class is to create a game of Othello with the
 * specified board dimensions and players.
 */
public class OthelloCreatorImpl implements OthelloCreator {
	@Override
	public Othello createOthello(Board board, List<Player> players) {

		Rules rules = new RulesImpl((BoardImpl) board);

		ClassicCalculatorStrategy calc = new ClassicCalculatorStrategy();
		Score score = new ScoreImpl(board.getNodes(), getPlayerIdList(players), calc);

		addObservers(board, score);

		PlayerCreatorImpl playerCreatorImpl = new PlayerCreatorImpl();
		GameStatusHandler game = new GameStatusHandlerImpl(score, players, rules, playerCreatorImpl);
		MoveHandler mover = new MoveHandlerImpl(rules, board);

		return new OthelloImpl(mover, game, rules);
	}

	private List<String> getPlayerIdList(List<Player> players) {
		List<String> playerIds = new ArrayList<>();
		for (Player p : players) {
			playerIds.add(p.getId());
		}
		return playerIds;
	}

	private void addObservers(Board board, Score score) {
		for (Node n : board.getNodes()) {
			n.addObserver(((Observer) score));
		}
	}
}
