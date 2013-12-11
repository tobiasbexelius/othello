package kth.game.othello.score;

import java.util.Comparator;

/**
 * The responsibility of this class is to compare ScoreItems by decreasing
 * order.
 * 
 * @author Oskar & Lina
 * 
 */
class ScoreItemComparatorDecreasing implements Comparator<ScoreItem> {

	@Override
	public int compare(ScoreItem sc1, ScoreItem sc2) {
		if (sc1.getScore() > sc2.getScore())
			return -1;
		else if (sc1.getScore() < sc2.getScore())
			return 1;
		else
			return 0;
	}

}
