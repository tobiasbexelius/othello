package kth.game.othello.tournament;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import kth.game.othello.Othello;
import kth.game.othello.player.Player;
import kth.game.othello.score.Score;
import kth.game.othello.score.ScoreItem;
import kth.game.othello.tests.MockCreator;

import org.junit.Test;

public class HighScoreTest {

	@Test
	public void getScoreAfterOneWonMatch() {
		Round tournament = mock(Round.class);
		List<Player> players = new ArrayList<Player>();
		players.add(MockCreator.createMockedComputerPlayer("player1", "player1", null));
		players.add(MockCreator.createMockedComputerPlayer("player2", "player2", null));

		Othello othello = createMockedOthelloMatch("player1", "player2");

		HighScore highScore = new HighScore(players);
		highScore.update(tournament, othello);
		assertEquals(2, highScore.getScoreForPlayer("player1"));
		assertEquals(0, highScore.getScoreForPlayer("player2"));
	}

	@Test
	public void getScoreAfterOneDrawMatch() {
		Round tournament = mock(Round.class);
		List<Player> players = new ArrayList<Player>();
		players.add(MockCreator.createMockedComputerPlayer("player1", "player1", null));
		players.add(MockCreator.createMockedComputerPlayer("player2", "player2", null));

		Othello othello = mock(Othello.class);
		List<ScoreItem> scores = new ArrayList<ScoreItem>();
		scores.add(MockCreator.createMockedScoreItem("player1", 32));
		scores.add(MockCreator.createMockedScoreItem("player2", 32));
		Score score = mock(Score.class);
		when(score.getPlayersScore()).thenReturn(scores);
		when(othello.getScore()).thenReturn(score);

		HighScore highScore = new HighScore(players);
		highScore.update(tournament, othello);
		assertEquals(1, highScore.getScoreForPlayer("player1"));
		assertEquals(1, highScore.getScoreForPlayer("player2"));
	}

	@Test
	public void getScoreAfterFourMatches() {
		Round tournament = mock(Round.class);
		List<Player> players = new ArrayList<Player>();
		players.add(MockCreator.createMockedComputerPlayer("player1", "player1", null));
		players.add(MockCreator.createMockedComputerPlayer("player2", "player2", null));

		Othello othelloMatch1 = createMockedOthelloMatch("player1", "player2");
		Othello othelloMatch2 = createMockedOthelloMatch("player2", "player1");
		Othello othelloMatch3 = createMockedOthelloMatch("player1", "player2");
		Othello othelloMatch4 = createMockedOthelloMatch("player1", "player2");

		HighScore highScore = new HighScore(players);
		highScore.update(tournament, othelloMatch1);
		highScore.update(tournament, othelloMatch2);
		highScore.update(tournament, othelloMatch3);
		highScore.update(tournament, othelloMatch4);

		assertEquals(6, highScore.getScoreForPlayer("player1"));
		assertEquals(2, highScore.getScoreForPlayer("player2"));
	}

	@Test
	public void getHighestTournamentScoreAfterFourWins() {
		Round tournament = mock(Round.class);
		List<Player> players = new ArrayList<Player>();
		players.add(MockCreator.createMockedComputerPlayer("player1", "player1", null));
		players.add(MockCreator.createMockedComputerPlayer("player2", "player2", null));

		Othello othelloMatch1 = createMockedOthelloMatch("player1", "player2");
		Othello othelloMatch2 = createMockedOthelloMatch("player1", "player2");
		Othello othelloMatch3 = createMockedOthelloMatch("player1", "player2");
		Othello othelloMatch4 = createMockedOthelloMatch("player1", "player2");

		HighScore highScore = new HighScore(players);
		highScore.update(tournament, othelloMatch1);
		highScore.update(tournament, othelloMatch2);
		highScore.update(tournament, othelloMatch3);
		highScore.update(tournament, othelloMatch4);

		assertEquals(8, highScore.getHighestTournamentScore());
	}

	@Test
	public void getWinnerOfTournamentAfterFourMatches() {
		Round tournament = mock(Round.class);
		List<Player> players = new ArrayList<Player>();
		players.add(MockCreator.createMockedComputerPlayer("player1", "player1", null));
		players.add(MockCreator.createMockedComputerPlayer("player2", "player2", null));

		Othello othelloMatch1 = createMockedOthelloMatch("player1", "player2");
		Othello othelloMatch2 = createMockedOthelloMatch("player1", "player2");
		Othello othelloMatch3 = createMockedOthelloMatch("player1", "player2");
		Othello othelloMatch4 = createMockedOthelloMatch("player1", "player2");

		HighScore highScore = new HighScore(players);
		highScore.update(tournament, othelloMatch1);
		highScore.update(tournament, othelloMatch2);
		highScore.update(tournament, othelloMatch3);
		highScore.update(tournament, othelloMatch4);

		assertEquals("player1", highScore.getHighestScoringPlayer());
	}

	@Test
	public void getWinnerOfTournamentWhenItWasADraw() {
		Round tournament = mock(Round.class);
		List<Player> players = new ArrayList<Player>();
		players.add(MockCreator.createMockedComputerPlayer("player1", "player1", null));
		players.add(MockCreator.createMockedComputerPlayer("player2", "player2", null));

		Othello othelloMatch1 = createMockedOthelloMatch("player1", "player2");
		Othello othelloMatch2 = createMockedOthelloMatch("player1", "player2");
		Othello othelloMatch3 = createMockedOthelloMatch("player2", "player1");
		Othello othelloMatch4 = createMockedOthelloMatch("player2", "player1");

		HighScore highScore = new HighScore(players);
		highScore.update(tournament, othelloMatch1);
		highScore.update(tournament, othelloMatch2);
		highScore.update(tournament, othelloMatch3);
		highScore.update(tournament, othelloMatch4);

		assertNull(highScore.getHighestScoringPlayer());
	}

	@Test
	public void getScoreAfterOneWinAndThreeDraws() {
		Round tournament = mock(Round.class);
		List<Player> players = new ArrayList<Player>();
		players.add(MockCreator.createMockedComputerPlayer("player1", "player1", null));
		players.add(MockCreator.createMockedComputerPlayer("player2", "player2", null));

		Othello othelloMatch1 = createMockedOthelloMatch("player1", "player2");
		Othello othelloMatch2 = createMockedOthelloDrawMatch("player2", "player1");
		Othello othelloMatch3 = createMockedOthelloDrawMatch("player1", "player2");
		Othello othelloMatch4 = createMockedOthelloDrawMatch("player1", "player2");

		HighScore highScore = new HighScore(players);
		highScore.update(tournament, othelloMatch1);
		highScore.update(tournament, othelloMatch2);
		highScore.update(tournament, othelloMatch3);
		highScore.update(tournament, othelloMatch4);

		assertEquals(5, highScore.getScoreForPlayer("player1"));
		assertEquals(3, highScore.getScoreForPlayer("player2"));
	}

	@Test
	public void updateScoreForActiveOthello() {
		Round tournament = mock(Round.class);
		List<Player> players = new ArrayList<Player>();
		HighScore highScore = new HighScore(players);
		Othello othello = mock(Othello.class);
		when(othello.isActive()).thenReturn(true);
		boolean threwException = false;
		try {
			highScore.update(tournament, othello);
		} catch (RuntimeException e) {
			threwException = true;
		}

		assertTrue(threwException);

	}

	private Othello createMockedOthelloMatch(String winningPlayer, String loosingPlayer) {
		Othello othello = mock(Othello.class);
		List<ScoreItem> scores = new ArrayList<ScoreItem>();
		scores.add(MockCreator.createMockedScoreItem(winningPlayer, 48));
		scores.add(MockCreator.createMockedScoreItem(loosingPlayer, 16));
		Score score = mock(Score.class);
		when(score.getPlayersScore()).thenReturn(scores);
		when(othello.getScore()).thenReturn(score);
		when(othello.isActive()).thenReturn(false);
		return othello;
	}

	private Othello createMockedOthelloDrawMatch(String player1, String player2) {
		Othello othello = mock(Othello.class);
		List<ScoreItem> scores = new ArrayList<ScoreItem>();
		scores.add(MockCreator.createMockedScoreItem(player1, 32));
		scores.add(MockCreator.createMockedScoreItem(player2, 32));
		Score score = mock(Score.class);
		when(score.getPlayersScore()).thenReturn(scores);
		when(othello.getScore()).thenReturn(score);
		when(othello.isActive()).thenReturn(false);
		return othello;
	}
}
