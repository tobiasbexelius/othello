package kth.game.othello.tournament;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import kth.game.othello.player.Player;
import kth.game.othello.player.PlayerCreator;
import kth.game.othello.player.PlayerCreatorImpl;
import kth.game.othello.player.movestrategy.FirstMoveStrategy;
import kth.game.othello.player.movestrategy.GreedyMoveStrategy;
import kth.game.othello.player.movestrategy.RandomMoveStrategy;

import org.junit.Test;

/**
 * System tests for the tournament module
 * 
 * 
 */
public class TournamentTest {

	@Test
	public void playTournamentWithThreePlayers() {
		PlayerCreator playerCreator = new PlayerCreatorImpl();
		Player greedy = playerCreator.createComputerPlayer("Mr. Greedy", new GreedyMoveStrategy());
		Player random = playerCreator.createComputerPlayer("Sir Random", new RandomMoveStrategy());
		Player first = playerCreator.createComputerPlayer("Duke First", new FirstMoveStrategy());
		List<Player> players = new ArrayList<Player>();
		players.add(greedy);
		players.add(first);
		players.add(random);

		Tournament tournament = new Tournament(players, false, 2);
		assertEquals(6, tournament.playTournament());
	}

	@Test
	public void playTournamentWithFivePlayers() {
		PlayerCreator playerCreator = new PlayerCreatorImpl();
		Player greedy = playerCreator.createComputerPlayer("Mr. Greedy", new GreedyMoveStrategy());
		Player random = playerCreator.createComputerPlayer("Sir Random", new RandomMoveStrategy());
		Player first = playerCreator.createComputerPlayer("Duke First", new FirstMoveStrategy());
		Player first2 = playerCreator.createComputerPlayer("Duke First Jr.", new FirstMoveStrategy());
		Player first3 = playerCreator.createComputerPlayer("Duchess First", new FirstMoveStrategy());

		List<Player> players = new ArrayList<Player>();
		players.add(greedy);
		players.add(first);
		players.add(random);
		players.add(first2);
		players.add(first3);

		Tournament tournament = new Tournament(players, false, 2);
		assertEquals(20, tournament.playTournament());
	}

	@Test
	public void highestScoreBounds() {
		PlayerCreator playerCreator = new PlayerCreatorImpl();
		Player greedy = playerCreator.createComputerPlayer("Mr. Greedy", new GreedyMoveStrategy());
		Player random = playerCreator.createComputerPlayer("Sir Random", new RandomMoveStrategy());
		Player first = playerCreator.createComputerPlayer("Duke First", new FirstMoveStrategy());
		Player first2 = playerCreator.createComputerPlayer("Duke First Jr.", new FirstMoveStrategy());
		Player first3 = playerCreator.createComputerPlayer("Duchess First", new FirstMoveStrategy());

		List<Player> players = new ArrayList<Player>();
		players.add(greedy);
		players.add(first);
		players.add(random);
		players.add(first2);
		players.add(first3);

		Tournament tournament = new Tournament(players, false, 2);
		tournament.playTournament();
		assertTrue(tournament.highestScore() < 16);
		assertTrue(tournament.highestScore() > 8);
	}

}
