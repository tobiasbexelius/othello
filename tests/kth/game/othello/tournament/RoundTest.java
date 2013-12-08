package kth.game.othello.tournament;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.Observer;
import java.util.Queue;

import kth.game.othello.OthelloImpl;

import org.junit.Test;

public class RoundTest {

	@Test
	public void notifyObserverWhenGameHasFinished() {
		OthelloImpl othello = mock(OthelloImpl.class);
		when(othello.isActive()).thenReturn(false);

		Match match1 = mock(Match.class);
		when(match1.getOthelloGame()).thenReturn(othello);
		when(match1.getStartingPlayerId()).thenReturn("player1");
		Match match2 = mock(Match.class);
		when(match2.getOthelloGame()).thenReturn(othello);
		when(match2.getStartingPlayerId()).thenReturn("player2");

		Schedule schedule = mock(Schedule.class);
		Queue<Match> queue = new LinkedList<Match>();
		queue.add(match1);
		queue.add(match2);
		when(schedule.getSchedule()).thenReturn(queue);

		Round round = new Round(false, schedule);
		Observer observer = mock(Observer.class);
		round.addObserver(observer);
		round.playRound();
		round.update(othello, null);
		round.update(othello, null);
		verify(observer, times(2)).update(round, othello);
	}

	@Test
	public void playRoundWithTwoMatches() {
		OthelloImpl othello = mock(OthelloImpl.class);
		when(othello.isActive()).thenReturn(false);

		Match match1 = mock(Match.class);
		when(match1.getOthelloGame()).thenReturn(othello);
		when(match1.getStartingPlayerId()).thenReturn("player1");
		Match match2 = mock(Match.class);
		when(match2.getOthelloGame()).thenReturn(othello);
		when(match2.getStartingPlayerId()).thenReturn("player2");

		Schedule schedule = mock(Schedule.class);
		Queue<Match> queue = new LinkedList<Match>();
		queue.add(match1);
		queue.add(match2);
		when(schedule.getSchedule()).thenReturn(queue);

		Round round = new Round(false, schedule);
		Observer observer = mock(Observer.class);
		round.addObserver(observer);
		int numberOfMatches = round.playRound();
		assertEquals(2, numberOfMatches);

	}

	@Test
	public void playRoundWithTenMatches() {
		OthelloImpl othello = mock(OthelloImpl.class);
		when(othello.isActive()).thenReturn(false);

		Schedule schedule = mock(Schedule.class);
		Queue<Match> queue = new LinkedList<Match>();
		for (int i = 0; i < 10; i++) {
			Match match = mock(Match.class);
			when(match.getOthelloGame()).thenReturn(othello);
			when(match.getStartingPlayerId()).thenReturn("player1");
			queue.add(match);
		}
		when(schedule.getSchedule()).thenReturn(queue);

		Round round = new Round(false, schedule);
		Observer observer = mock(Observer.class);
		round.addObserver(observer);
		int numberOfMatches = round.playRound();
		assertEquals(10, numberOfMatches);
	}

}
