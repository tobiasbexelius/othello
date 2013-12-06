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
 * they all play two games of Othello against each other. It must notify it's
 * observers each time a match in the round has finished.
 * 
 */
public class TournamentRound extends Observable implements Observer {
	private List<Player> players;
	private boolean graphicalView;
	private TournamentGameCreator gameCreator;

	/**
	 * Creates a new tournament round.
	 * 
	 * @param players
	 *            The players who will play in the round
	 * @param graphicalView
	 *            If true, each match will be shown graphically. Otherwise, the
	 *            matches will not be shown.
	 */
	public TournamentRound(List<Player> players, boolean graphicalView, TournamentGameCreator gameCreator) {
		this.graphicalView = graphicalView;
		this.players = players;
		this.gameCreator = gameCreator;
	}

	public List<Player> getPlayers() {
		return players;
	}

	/**
	 * Plays one round of the tournament. One round consists of all players
	 * playing two times against each other. Each player starts the match once.
	 * 
	 * @return the total number of matches played in the round.
	 */
	public int playRound() {
		int j = 0;
		int matchesPlayed = 0;
		for (Player player : players) {
			for (int i = j + 1; i < players.size(); i++) {
				playOneGame(player, players.get(i), player);
				playOneGame(players.get(i), player, players.get(i));
				matchesPlayed += 2;
			}
			j++;
		}
		return matchesPlayed;
	}

	/**
	 * Plays a game of Othello between two players. The tournament listens on
	 * the match and notifies the highScore when the match is finished.
	 * 
	 * @param player1
	 *            The first of the two players
	 * @param player2
	 *            The second of the two players
	 * @param startingPlayer
	 *            the player who will make the first move
	 */
	private void playOneGame(Player player1, Player player2, Player startingPlayer) {
		Othello othello = gameCreator.createGameWithPlayers(player1, player2);
		othello.addGameFinishedObserver(this);
		if (graphicalView) {
			OthelloView othelloView = OthelloViewFactory.create(othello, 5, 5);
			othelloView.start(startingPlayer.getId());
		} else {
			othello.start(startingPlayer.getId());
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
