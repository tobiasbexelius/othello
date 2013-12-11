package kth.game.othello;

import java.util.List;
import java.util.Observer;

import kth.game.othello.player.Player;
import kth.game.othello.score.Score;

/**
 * Author: Lina och Oskar Date: 2013-11-21 Time: 22:36 The responsibility of
 * this class is the state of the game
 */
public interface GameStatusHandler {
	/**
	 * Starts a new game with a random order of players
	 */
	public void start();

	/**
	 * Starts a new game
	 * 
	 * @param playerId
	 *            sets the first player to move
	 */
	public void start(String playerId);

	public boolean isActive();

	public Player getPlayerInTurn();

	public Score getScore();

	public List<Player> getPlayers();

	public void setNextPlayerInTurn();

	public void addGameFinishedObserver(Observer observer);
}
