package kth.game.tournament;

import java.util.List;

import kth.game.othello.player.movestrategy.MoveStrategy;

/**
 * This class is responsible for ranking given MoveStrategies in a specific
 * GameType.
 * 
 * @author Oskar & Lina
 */
public interface Tournament {

	/**
	 * Plays all tournament games.
	 * 
	 * @return an ordered list of MoveStrategy entries with the winner first.
	 */
	public List<Entry> playAllGames();

	/**
	 * Plays the games if they haven't been played already and presents the
	 * ranked strategies in the console.
	 */
	public void presentResult();

	/**
	 * Finds an entry based on its MoveStrategy.
	 * 
	 * @param moveStrat
	 * @return Entry with matching moveStrat
	 * @throws NullPointerException
	 *             if no Entry was found
	 */
	public Entry findEntryByMoveStrategy(MoveStrategy moveStrat);
}
