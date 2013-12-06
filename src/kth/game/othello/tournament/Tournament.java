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
	private TournamentHighScore highScore;

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
		highScore = new TournamentHighScore(players);
	}

	/**
	 * Returns the highest scoring player of the tournament (winner).
	 * 
	 * @return The winning player, or null if the tournament was a draw.
	 */
	public String getWinner() {
		return highScore.getHighestScoringPlayer();
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
		TournamentRound round = new TournamentRound(players, graphicalView, new TournamentGameCreator());
		round.addObserver(highScore);
		int matchesPlayed = round.playRound();
		return matchesPlayed;
	}
}
