package kth.game.othello.system;

import java.util.ArrayList;
import java.util.List;

import kth.game.othello.player.Player;
import kth.game.othello.player.PlayerCreator;
import kth.game.othello.player.PlayerCreatorImpl;
import kth.game.othello.player.movestrategy.FirstMoveStrategy;
import kth.game.othello.player.movestrategy.GreedyMoveStrategy;
import kth.game.othello.player.movestrategy.RandomMoveStrategy;
import kth.game.othello.tournament.Tournament;

import org.junit.Test;

public class PlayTournamentGraphically {

	@Test
	public void playATournament() {
		PlayerCreator playerCreator = new PlayerCreatorImpl();
		Player greedy = playerCreator.createComputerPlayer("Mr. Greedy", new GreedyMoveStrategy());
		Player random = playerCreator.createComputerPlayer("Sir Random", new RandomMoveStrategy());
		Player first = playerCreator.createComputerPlayer("Duke First", new FirstMoveStrategy());
		List<Player> players = new ArrayList<Player>();
		players.add(greedy);
		players.add(first);
		players.add(random);

		Tournament tournament = new Tournament(players, true);
		tournament.playTournament();
		System.out.println("The winner is: " + tournament.getWinnerOfTournament() + " with "
				+ tournament.highestScore() + " points!");
	}
}
