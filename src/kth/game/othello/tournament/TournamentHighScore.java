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
 * The responsibility of this class is to keep track of the total points for
 * each player in a tournament. The score should be updated each time a match in
 * the tournament has finished.
 * 
 */
class TournamentHighScore implements Observer {
	private Map<String, Integer> highScore;

	public TournamentHighScore(List<Player> players) {
		highScore = new HashMap<String, Integer>();
		for (Player player : players) {
			highScore.put(player.getId(), 0);
		}
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
		int highestScore = getHighestScoreInList(scores);
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
	private int getHighestScoreInList(List<ScoreItem> scores) {
		int highestScore = -1;
		for (ScoreItem score : scores)
			if (score.getScore() > highestScore)
				highestScore = score.getScore();
		return highestScore;

	}

	/**
	 * Retrieve the tournament score for a certain player
	 * 
	 * @param playerId
	 *            The id of the player
	 * @return the player's score
	 */
	public int getScoreForPlayer(String playerId) {
		return highScore.get(playerId);
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
	 * @return the id of the highest scoring player. Null if the tournament was
	 *         a draw.
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
	 * 1 point. The provided Othello game most be finished in order to determine
	 * a winner.
	 * 
	 * @param othello
	 *            The finished game
	 * @throws a
	 *             runtime exception if the Othello game is active
	 */
	private void updateScore(Othello othello) {
		if (othello.isActive())
			throw new RuntimeException(
					"Cannot determine the winner of an unfinished othello game! The game is still active.");
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
		if (!(o instanceof TournamentRound)) {
			throw new RuntimeException("The observed object was not a tournament!");
		} else if (!(arg instanceof Othello)) {
			throw new RuntimeException("The supplied argument was not an Othello game!");
		}
		Othello othello = (Othello) arg;
		updateScore(othello);
	}
}
