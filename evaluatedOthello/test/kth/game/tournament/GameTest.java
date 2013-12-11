package kth.game.tournament;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import kth.game.othello.player.Player;
import kth.game.othello.player.Player.Type;
import kth.game.othello.player.movestrategy.MoveStrategy;
import kth.game.othello.score.ScoreImpl;
import kth.game.othello.score.ScoreItem;

import org.junit.Test;

public class GameTest {

	List<Player> getTwoMockedComputers() {
		Player p1 = mock(Player.class);
		Player p2 = mock(Player.class);
		when(p1.getId()).thenReturn("p1");
		when(p2.getId()).thenReturn("p2");
		when(p1.getType()).thenReturn(Type.COMPUTER);
		when(p2.getType()).thenReturn(Type.COMPUTER);
		List<Player> players = new ArrayList<>();
		players.add(p1);
		players.add(p2);
		return players;
	}

	@Test(expected = IllegalArgumentException.class)
	public void testWithWrongPlayers() {
		GameTypeWrapper mockedOthello = mock(OthelloWrapper.class);
		MoveStrategy mockedMS1 = mock(MoveStrategy.class);
		MoveStrategy mockedMS2 = mock(MoveStrategy.class);
		Game game = new Game(mockedOthello, mockedMS1, mockedMS2);
	}

	@Test
	public void testWinningStrategyIsCorrectClass() {
		// Mocks
		GameTypeWrapper mockedOthello = mock(OthelloWrapper.class);
		MoveStrategy mockedMS1 = mock(MoveStrategy.class);
		MoveStrategy mockedMS2 = mock(MoveStrategy.class);
		List<Player> mockedPlayers = getTwoMockedComputers();
		ScoreImpl mockedScore = mock(ScoreImpl.class);
		ScoreItem mockedScoreItem = mock(ScoreItem.class);
		List<ScoreItem> mockedScoreItems = new ArrayList<>();
		mockedScoreItems.add(mockedScoreItem);

		// Mocked return values
		when(mockedPlayers.get(0).getMoveStrategy()).thenReturn(mockedMS1);
		when(mockedPlayers.get(1).getMoveStrategy()).thenReturn(mockedMS2);
		String winnerId = mockedPlayers.get(0).getId();
		when(mockedScoreItem.getPlayerId()).thenReturn(winnerId);
		when(mockedScore.getPlayersScore()).thenReturn(mockedScoreItems);
		when(mockedOthello.getScore()).thenReturn(mockedScore);
		when(mockedOthello.getPlayers()).thenReturn(mockedPlayers);
		when(mockedOthello.playGame()).thenReturn(mockedOthello);
		when(mockedOthello.getWinner()).thenReturn(mockedPlayers.get(0));

		// Create real stuff
		Game game = new Game(mockedOthello, mockedMS1, mockedMS2);
		MoveStrategy winner = game.getWinningStrategy();

		// Assert
		assertEquals(winner.getClass(), mock(MoveStrategy.class).getClass());
	}

	@Test
	public void testWinningStrategyIsCorrect() {
		// Mocks
		GameTypeWrapper mockedOthello = mock(OthelloWrapper.class);
		MoveStrategy mockedMS1 = mock(MoveStrategy.class);
		MoveStrategy mockedMS2 = mock(MoveStrategy.class);
		List<Player> mockedPlayers = getTwoMockedComputers();
		ScoreImpl mockedScore = mock(ScoreImpl.class);
		ScoreItem mockedScoreItem = mock(ScoreItem.class);
		List<ScoreItem> mockedScoreItems = new ArrayList<>();
		mockedScoreItems.add(mockedScoreItem);

		// Mocked return values
		when(mockedPlayers.get(0).getMoveStrategy()).thenReturn(mockedMS1);
		when(mockedPlayers.get(1).getMoveStrategy()).thenReturn(mockedMS2);
		String winnerId = mockedPlayers.get(1).getId(); // Winning Player &
		// Strategy
		when(mockedScoreItem.getPlayerId()).thenReturn(winnerId);
		when(mockedScore.getPlayersScore()).thenReturn(mockedScoreItems);
		when(mockedOthello.getScore()).thenReturn(mockedScore);
		when(mockedOthello.getPlayers()).thenReturn(mockedPlayers);
		when(mockedOthello.playGame()).thenReturn(mockedOthello);
		when(mockedOthello.getWinner()).thenReturn(mockedPlayers.get(1));

		// Create real stuff
		Game game = new Game(mockedOthello, mockedMS1, mockedMS2);
		MoveStrategy winner = game.getWinningStrategy();

		// Assert
		assertEquals(winner, mockedMS2);
	}
}
