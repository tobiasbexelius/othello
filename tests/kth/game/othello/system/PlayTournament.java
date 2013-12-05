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

public class PlayTournament {

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
		tournament.playOneRound();
		tournament.playOneRound();
		String winner = tournament.getHighestScoringPlayer();
		if (winner == null) {
			System.out.println("The tournament ended in a draw!");
		}
		System.out.println("The winner of the tournament is: " + winner + "!");
	}
}
