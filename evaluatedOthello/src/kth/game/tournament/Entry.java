package kth.game.tournament;

import kth.game.othello.player.movestrategy.MoveStrategy;

/**
 * A MoveStrategy entry 'playing' in the Tournament.
 * 
 * @author Oskar & Lina
 */
public class Entry implements Comparable<Entry> {

	private final MoveStrategy moveStrat;
	private int wonGames = 0;

	public Entry(MoveStrategy moveStrat) {
		this.moveStrat = moveStrat;
	}

	public void addWonGame() {
		wonGames++;
	}

	int getWonGames() {
		return wonGames;
	}

	public MoveStrategy getMoveStrategy() {
		return moveStrat;
	}

	@Override
	public int compareTo(Entry e) {
		if (e.getWonGames() > wonGames)
			return 1;
		else if (e.getWonGames() < wonGames)
			return -1;
		else
			return 0;
	}

}
