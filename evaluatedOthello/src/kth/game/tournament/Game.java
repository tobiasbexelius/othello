package kth.game.tournament;

import java.util.List;

import kth.game.othello.player.Player;
import kth.game.othello.player.movestrategy.MoveStrategy;

/**
 * This class is responsible for determining the winner between two
 * MoveStrategies in a given GameType.
 * 
 * @author Oskar & Lina
 */
class Game {
	private final GameTypeWrapper gameType;

	/**
	 * 
	 * @param gameType
	 *            a game between two Computer Players
	 * @param ms1
	 *            MoveStrategy 1
	 * @param ms2
	 *            MoveStrategy 2
	 * @throws IllegalArgumentException
	 *             is thrown if the Players are not Computers
	 */
	public Game(GameTypeWrapper gameType, MoveStrategy ms1, MoveStrategy ms2) throws IllegalArgumentException {
		this.gameType = gameType;
		ensureExactlyTwoPlayers(gameType.getPlayers());
		gameType.getPlayers().get(0).setMoveStrategy(ms1);
		gameType.getPlayers().get(1).setMoveStrategy(ms2);
	}

	private void ensureExactlyTwoPlayers(List<Player> players) throws IllegalArgumentException {
		if (players.size() != 2) {
			throw new IllegalArgumentException();
		}
	}

	private MoveStrategy playGameAndGetWinner() throws NullPointerException {
		GameTypeWrapper playedGame = gameType.playGame();

		Player winner = playedGame.getWinner();
		return winner.getMoveStrategy();
	}

	public MoveStrategy getWinningStrategy() throws NullPointerException {
		return playGameAndGetWinner();
	}

}
