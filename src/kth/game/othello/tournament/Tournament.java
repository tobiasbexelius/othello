package kth.game.othello.tournament;

import java.util.List;

import kth.game.othello.player.Player;

/**
 * The responsibility of this class is to hold a tournament among a group of
 * computer players. The tournament works like this: every computer plays
 * against one another two times, each starting the game one time each. The
 * player who performs the best is the winner.
 * 
 */
public class Tournament {
	private List<Player> players;
	private boolean graphicalView;
	private HighScore highScore;

	/**
	 * Creates a new tournament.
	 * 
	 * @param players
	 *            The players who will play in the tournament
	 * @param graphicalView
	 *            If true, each match will be shown graphically. Otherwise, the
	 *            matches will not be shown.
	 */
	public Tournament(List<Player> players, boolean graphicalView) {
		this.graphicalView = graphicalView;
		this.players = players;
		highScore = new HighScore(players);
	}

	/**
	 * Returns the id of the highest scoring player of the tournament (winner).
	 * 
	 * @return The winning player, or null if the tournament was a draw.
	 */
	public String getWinnerId() {
		return highScore.getHighestScoringPlayer();
	}

	/**
	 * Returns the id of the highest scoring player of the tournament (winner).
	 * 
	 * @return The winning player, or null if the tournament was a draw.
	 */
	public String getWinnerName() {
		return getNameFromId(highScore.getHighestScoringPlayer());
	}

	/**
	 * Returns the name of a player with a certain id.
	 * 
	 * @param playerId
	 *            The id of the player
	 * @return the name of the player with the id. Null if no player with the id
	 *         exists
	 */
	private String getNameFromId(String playerId) {
		for (Player player : players) {
			if (player.getId().equals(playerId)) {
				return player.getName();
			}
		}
		return null;
	}

	/**
	 * Returns the highest score achieved in the tournament
	 * 
	 * @return the highest score in the tournament
	 */
	public int highestScore() {
		return highScore.getHighestTournamentScore();
	}

	/**
	 * Plays all rounds of the tournament.
	 * 
	 * @return the total number of matches played int the tournament
	 */
	public int playTournament() {
		Schedule schedule = new AllVersusAllSchedule(players, new MatchFactory());
		Round round = new Round(graphicalView, schedule);
		round.addObserver(highScore);
		int matchesPlayed = round.playRound();
		return matchesPlayed;
	}
}
