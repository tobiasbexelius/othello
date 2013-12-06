package kth.game.othello.tournament;

import java.util.List;

import kth.game.othello.player.Player;

public class Tournament {
	private List<Player> players;
	private boolean graphicalView;
	private int tournamentRounds;
	private TournamentHighScore highScore;

	/**
	 * Creates a new tournament.
	 * 
	 * @param players
	 *            The players who will play in the tournament
	 * @param graphicalView
	 *            If true, each match will be shown graphically. Otherwise, the
	 *            matches will not be shown.
	 * @param tournamentRounds
	 *            The amount of times each player will play against one another
	 */
	public Tournament(List<Player> players, boolean graphicalView, int tournamentRounds) {
		this.graphicalView = graphicalView;
		this.players = players;
		this.tournamentRounds = tournamentRounds;
		highScore = new TournamentHighScore(players);
	}

	/**
	 * Returns the highest scoring player of the tournament (winner).
	 * 
	 * @return The winning player, or null if the tournament was a draw.
	 */
	public String getWinnerOfTournament() {
		return highScore.getHighestScoringPlayer();
	}

	public int highestScore() {
		return highScore.getHighestTournamentScore();
	}

	/**
	 * Plays all rounds of the tournament.The result of the tournament is
	 * printed afterwards.
	 * 
	 * @return the total number of matches played
	 */
	public int playTournament() {
		int matchesPlayed = 0;
		for (int i = 0; i < tournamentRounds; i++) {
			TournamentRound round = new TournamentRound(players, graphicalView, new TournamentGameCreator());
			round.addObserver(highScore);
			matchesPlayed += round.playRound();
		}
		return matchesPlayed;
	}
}
