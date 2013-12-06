package kth.game.othello.tournament;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import kth.game.othello.Othello;
import kth.game.othello.player.Player;
import kth.game.othello.score.ScoreItem;

/**
 * The responsibility of this class is to keep tabs on the results of the
 * matches in an observed tournament.
 * 
 */
class TournamentHighScore implements Observer {
	private Map<String, Integer> highScore;

	public TournamentHighScore(Tournament tournament, List<Player> players) {
		highScore = new HashMap<String, Integer>();
		for (Player player : players) {
			highScore.put(player.getId(), 0);
		}
		tournament.addObserver(this);
	}

	/**
	 * Finds the winners (players with the highest score) of the game. If two or
	 * more players had the highest score, they are all returned.
	 * 
	 * @param othello
	 *            the game for which to find winners
	 * @return the players who had the (same) highest score
	 */
	private List<String> findWinners(Othello othello) {
		List<ScoreItem> scores = othello.getScore().getPlayersScore();
		List<String> winners = new ArrayList<String>();
		int highestScore = getHighestScore(scores);
		for (ScoreItem score : scores)
			if (score.getScore() == highestScore)
				winners.add(score.getPlayerId());
		return winners;
	}

	/**
	 * Searches through a list of ScoreItems to find the highest score in the
	 * list.
	 * 
	 * @param scores
	 *            the list to search through
	 * @return the highest score in the list
	 */
	private int getHighestScore(List<ScoreItem> scores) {
		int highestScore = -1;
		for (ScoreItem score : scores)
			if (score.getScore() > highestScore)
				highestScore = score.getScore();
		return highestScore;

	}

	/**
	 * Returns the highest achieved score in the observed tournament.
	 * 
	 * @return the highest score in the tournament.
	 */
	public int getHighestTournamentScore() {
		int highestScore = -1;
		for (String player : highScore.keySet()) {
			if (highestScore < highScore.get(player)) {
				highestScore = highScore.get(player);
			}
		}
		return highestScore;
	}

	/**
	 * Get the id of the player who has the highest score in the observed
	 * tournament.
	 * 
	 * @return the id of the highest scoring player
	 */
	public String getHighestScoringPlayer() {
		String highest = null;
		int highestScore = -1;
		for (String player : highScore.keySet()) {
			if (highestScore < highScore.get(player)) {
				highest = player;
				highestScore = highScore.get(player);
			} else if (highestScore == highScore.get(player)) {
				highest = null;
			}
		}
		return highest;
	}

	/**
	 * Updates the winner of the Othello game's tournament points by 2. If there
	 * were more than one winner (draw), each player with the highest score gets
	 * 1 point.
	 * 
	 * @param othello
	 *            The finished game
	 */
	private void updateScore(Othello othello) {
		List<String> winners = findWinners(othello);
		if (winners.size() == 1) {
			highScore.put(winners.get(0), 2 + highScore.get(winners.get(0)));
		} else {
			for (String drawer : winners)
				highScore.put(drawer, 1 + highScore.get(drawer));
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		if (!(o instanceof Tournament)) {
			throw new RuntimeException("The observed object was not a tournament!");
		} else if (!(arg instanceof Othello)) {
			throw new RuntimeException("The supplied argument was not an Othello game!");
		}
		Othello othello = (Othello) arg;
		updateScore(othello);
	}
}
