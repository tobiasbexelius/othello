package kth.game.othello.tournament;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

import kth.game.othello.OthelloImpl;
import kth.game.othello.player.Player;
import kth.game.othello.tests.MockCreator;

import org.junit.Test;

public class TournamentRoundTest {

	@Test
	public void notifyObserverWhenGameHasFinished() {
		List<Player> players = new ArrayList<Player>();
		Player player1 = MockCreator.createMockedComputerPlayer("player1", "player1", null);
		Player player2 = MockCreator.createMockedComputerPlayer("player1", "player1", null);

		players.add(player1);
		players.add(player2);
		OthelloImpl othello = mock(OthelloImpl.class);
		when(othello.isActive()).thenReturn(false);
		TournamentGameCreator gameCreator = mock(TournamentGameCreator.class);
		when(gameCreator.createGameWithPlayers(player1, player2)).thenReturn(othello);
		TournamentRound round = new TournamentRound(players, false, gameCreator);
		Observer observer = mock(Observer.class);
		round.addObserver(observer);
		round.playRound();
		round.update(othello, null);
		verify(observer).update(round, othello);
	}

	@Test
	public void playRoundWithFourPlayers() {
		List<Player> players = MockCreator.createMockedComputerPlayers(4);

		OthelloImpl othello = mock(OthelloImpl.class);
		when(othello.isActive()).thenReturn(false);
		TournamentGameCreator gameCreator = mock(TournamentGameCreator.class);
		when(gameCreator.createGameWithPlayers((Player) anyObject(), (Player) anyObject())).thenReturn(othello);

		TournamentRound round = new TournamentRound(players, false, gameCreator);

		int totalNumberOfMatches = round.playRound();
		assertEquals(6, totalNumberOfMatches);
	}

	@Test
	public void playRoundWithTenPlayers() {
		List<Player> players = MockCreator.createMockedComputerPlayers(10);

		OthelloImpl othello = mock(OthelloImpl.class);
		when(othello.isActive()).thenReturn(false);
		TournamentGameCreator gameCreator = mock(TournamentGameCreator.class);
		when(gameCreator.createGameWithPlayers((Player) anyObject(), (Player) anyObject())).thenReturn(othello);

		TournamentRound round = new TournamentRound(players, false, gameCreator);

		int totalNumberOfMatches = round.playRound();
		assertEquals(45, totalNumberOfMatches);
	}

}
