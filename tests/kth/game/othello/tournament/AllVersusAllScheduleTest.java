package kth.game.othello.tournament;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import kth.game.othello.player.Player;
import kth.game.othello.tests.MockCreator;

import org.junit.Test;

public class AllVersusAllScheduleTest {

	@Test
	public void scheduleWithThreePlayers() {
		List<Player> players = MockCreator.createMockedComputerPlayers(3);
		MatchFactory matchFactory = mock(MatchFactory.class);
		Match match = mock(Match.class);
		when(matchFactory.createMatchWithPlayers((Player) anyObject(), (Player) anyObject(), (Player) anyObject()))
				.thenReturn(match);
		AllVersusAllSchedule schedule = new AllVersusAllSchedule(players, matchFactory);
		schedule.createSchedule();
		assertEquals(6, schedule.getSchedule().size());
	}

	@Test
	public void scheduleWithFourPlayers() {
		List<Player> players = MockCreator.createMockedComputerPlayers(4);
		MatchFactory matchFactory = mock(MatchFactory.class);
		Match match = mock(Match.class);
		when(matchFactory.createMatchWithPlayers((Player) anyObject(), (Player) anyObject(), (Player) anyObject()))
				.thenReturn(match);
		AllVersusAllSchedule schedule = new AllVersusAllSchedule(players, matchFactory);
		schedule.createSchedule();
		assertEquals(12, schedule.getSchedule().size());
	}

	@Test
	public void scheduleWithTenPlayers() {
		List<Player> players = MockCreator.createMockedComputerPlayers(10);
		MatchFactory matchFactory = mock(MatchFactory.class);
		Match match = mock(Match.class);
		when(matchFactory.createMatchWithPlayers((Player) anyObject(), (Player) anyObject(), (Player) anyObject()))
				.thenReturn(match);
		AllVersusAllSchedule schedule = new AllVersusAllSchedule(players, matchFactory);
		schedule.createSchedule();
		assertEquals(90, schedule.getSchedule().size());
	}

	@Test
	public void playersStartOnceEachMatchUp() {
		Player player1 = MockCreator.createMockedComputerPlayer("player1", "player1", null);
		Player player2 = MockCreator.createMockedComputerPlayer("player2", "player2", null);
		List<Player> players = new ArrayList<Player>();
		players.add(player1);
		players.add(player2);
		MatchFactory matchFactory = mock(MatchFactory.class);
		Match match1 = mockedMatch("player1");
		Match match2 = mockedMatch("player2");
		when(matchFactory.createMatchWithPlayers(player1, player2, player1)).thenReturn(match1);
		when(matchFactory.createMatchWithPlayers(player1, player2, player2)).thenReturn(match2);
		AllVersusAllSchedule schedule = new AllVersusAllSchedule(players, matchFactory);
		schedule.createSchedule();
		Queue<Match> queue = schedule.getSchedule();
		assertEquals("player1", queue.poll().getStartingPlayerId());
		assertEquals("player2", queue.poll().getStartingPlayerId());
	}

	private Match mockedMatch(String startingPlayerId) {
		Match match = mock(Match.class);
		when(match.getStartingPlayerId()).thenReturn(startingPlayerId);
		return match;
	}

}
