package kth.game.tournament;

import java.util.List;

import kth.game.othello.player.Player;
import kth.game.othello.score.Score;

/**
 * This class is responsible for wrapping different game types to work with
 * Game.
 * 
 * @author Oskar & Lina
 */
public interface GameTypeWrapper {

	/**
	 * This is expected to play an entire game.
	 * 
	 * @return the played game
	 */
	public GameTypeWrapper playGame();

	public List<Player> getPlayers();

	public Score getScore();

	public Player getWinner();

}
