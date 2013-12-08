package kth.game.othello.tournament;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import kth.game.othello.player.Player;

/**
 * The responsibility of this class is to take a list of players and create
 * matches where every player play against every other player two times.
 * 
 */
public class AllVersusAllSchedule implements Schedule {

	private List<Player> players;
	private MatchFactory matchFactory;

	/**
	 * Create a new schedule.
	 * 
	 * @param players
	 *            The players that are to be matched against each other
	 * @param gameCreator
	 *            The game creator with which the othello games will be created
	 */
	public AllVersusAllSchedule(List<Player> players, MatchFactory gameCreator) {
		this.players = players;
		this.matchFactory = gameCreator;
	}

	@Override
	public Queue<Match> getSchedule() {
		Queue<Match> schedule = new LinkedList<Match>();
		int j = 0;
		for (Player player : players) {
			for (int i = j + 1; i < players.size(); i++) {
				Match match1 = matchFactory.createMatchWithPlayers(player, players.get(i), player);
				Match match2 = matchFactory.createMatchWithPlayers(player, players.get(i), players.get(i));
				schedule.add(match1);
				schedule.add(match2);
			}
			j++;
		}
		return schedule;
	}
}
