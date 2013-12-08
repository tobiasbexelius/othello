package kth.game.othello.tournament;

import java.util.Observable;
import java.util.Observer;
import java.util.Queue;

import kth.game.othello.Othello;
import kth.game.othello.view.swing.OthelloView;
import kth.game.othello.view.swing.OthelloViewFactory;

/**
 * The responsibility of this class is to take a schedule and make sure all
 * matches from the schedule are played. It must notify it's observers every
 * time a match has finished.
 * 
 */
class Round extends Observable implements Observer {
	private boolean graphicalView;
	private Schedule schedule;

	/**
	 * Creates a new tournament round.
	 * 
	 * @param players
	 *            The players who will play in the round
	 * @param graphicalView
	 *            If true, each match will be shown graphically. Otherwise, the
	 *            matches will not be shown.
	 */
	public Round(boolean graphicalView, Schedule schedule) {
		this.graphicalView = graphicalView;
		this.schedule = schedule;
	}

	/**
	 * Plays one round of the tournament. One round consists of playing all the
	 * matches in the tournament schedule.
	 * 
	 * @return the total number of matches played in the round.
	 */
	public int playRound() {
		int matchesPlayed = 0;
		schedule.createSchedule();
		Queue<Match> matchQueue = schedule.getSchedule();
		while (!matchQueue.isEmpty()) {
			Match currentMatch = matchQueue.poll();
			playMatch(currentMatch);
			matchesPlayed++;
		}
		return matchesPlayed;
	}

	/**
	 * Plays a tournament match.
	 * 
	 * @param match
	 *            the match to be played
	 */
	private void playMatch(Match match) {
		Othello game = match.getOthelloGame();
		game.addGameFinishedObserver(this);
		if (graphicalView) {
			OthelloView othelloView = OthelloViewFactory.create(game, 5, 5);
			othelloView.start(match.getStartingPlayerId());
		} else {
			game.start(match.getStartingPlayerId());
			while (game.isActive()) {
				game.move();
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
