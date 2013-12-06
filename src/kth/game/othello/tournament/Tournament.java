package kth.game.othello.tournament;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import kth.game.othello.Othello;
import kth.game.othello.player.Player;
import kth.game.othello.view.swing.OthelloView;
import kth.game.othello.view.swing.OthelloViewFactory;

/**
 * The responsibility of this class is to take a list of players and make sure
 * they all play at least one game of Othello against each other. It must notify
 * it's observers each time a game has finished.The number of times they will
 * play against each other can be specified in the constructor. The result of
 * the matches will be printed in the console.
 * 
 */
public class Tournament extends Observable implements Observer {
	private List<Player> players;
	private boolean graphicalView;
	private TournamentGameCreator gameCreator;
	private int tournamentRounds;
	private TournamentHighScore highScore;

	/**
	 * Creates a new tournament. Each player will play against one another two
	 * times.
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
		tournamentRounds = 2;
		gameCreator = new TournamentGameCreator();
		highScore = new TournamentHighScore(this);
	}

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
		gameCreator = new TournamentGameCreator();
		highScore = new TournamentHighScore(this);
	}

	/**
	 * Returns the highest scoring player of the tournament (winner).
	 * 
	 * @return The winning player, or null if the tournament was a draw.
	 */
	public String getWinnerOfTournament() {
		return highScore.getHighestScoringPlayer();
	}

	public List<Player> getPlayers() {
		return players;
	}

	public int highestScore() {
		return highScore.getHighestTournamentScore();
	}

	/**
	 * Plays all rounds of the tournament.The result of the tournament is
	 * printed afterwards.
	 */
	public void playTournament() {
		for (int i = 0; i < tournamentRounds; i++) {
			playOneRound();
		}
	}

	/**
	 * Plays one round of the tournament. One round consists of all players
	 * playing once against each other.
	 */
	private void playOneRound() {
		int j = 0;
		for (Player player : players) {
			for (int i = j + 1; i < players.size(); i++) {
				playOneGame(player, players.get(i));
			}
			j++;
		}
	}

	/**
	 * Plays a game of Othello between two players. The tournament listens on
	 * the match and notifies the highScore when the match is finished.
	 * 
	 * @param player1
	 *            The first of the two players
	 * @param player2
	 *            The second of the two playes
	 */
	private void playOneGame(Player player1, Player player2) {
		Othello othello = gameCreator.createGameWithPlayers(player1, player2);
		othello.addGameFinishedObserver(this);
		if (graphicalView) {
			OthelloView othelloView = OthelloViewFactory.create(othello, 5, 5);
			othelloView.start();
		} else {
			othello.start();
			while (othello.isActive()) {
				othello.move();
			}
		}
	}

	@Override
	public void update(Observable o, Object arg) throws RuntimeException {
		if (!(o instanceof Othello))
			throw new RuntimeException("The observed object is not an Othello game");
		Othello othello = (Othello) o;
		setChanged();
		notifyObservers(othello);
	}

}
