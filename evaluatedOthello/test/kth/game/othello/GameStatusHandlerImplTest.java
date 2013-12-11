package kth.game.othello;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;

import kth.game.othello.player.Player;
import kth.game.othello.score.ScoreImpl;

import org.junit.Test;
import org.mockito.Mockito;

public class GameStatusHandlerImplTest {

	private ScoreImpl mockedScore;
	private Rules mockedRules;
	private ArrayList<Player> mockedPlayers;
	private Player mockedP1;
	private Player mockedP2;
	private Player mockedP3;
	private GameStatusHandlerImpl game;

	@Test
	public void testStartNoPlayersActive() {
		mockedScore = Mockito.mock(ScoreImpl.class);
		mockedRules = Mockito.mock(Rules.class);
		mockedPlayers = new ArrayList<>();
		mockedP1 = Mockito.mock(Player.class);
		mockedP2 = Mockito.mock(Player.class);
		mockedP3 = Mockito.mock(Player.class);
		Mockito.when(mockedP1.getId()).thenReturn("osk");
		Mockito.when(mockedP2.getId()).thenReturn("comp");
		Mockito.when(mockedP3.getId()).thenReturn("lin");
		game = new GameStatusHandlerImpl(mockedScore, mockedPlayers, mockedRules,
				new kth.game.othello.player.PlayerCreatorImpl());

		GameStatusHandlerImpl invalidGame = new GameStatusHandlerImpl(mockedScore, new ArrayList<Player>(),
				mockedRules, new kth.game.othello.player.PlayerCreatorImpl());
		invalidGame.start();
		assertFalse(invalidGame.isActive());
	}

	@Test
	public void testStartActive() {
		mockedScore = Mockito.mock(ScoreImpl.class);
		mockedRules = Mockito.mock(Rules.class);
		mockedPlayers = new ArrayList<>();
		mockedP1 = Mockito.mock(Player.class);
		mockedP2 = Mockito.mock(Player.class);
		mockedP3 = Mockito.mock(Player.class);
		Mockito.when(mockedP1.getId()).thenReturn("osk");
		Mockito.when(mockedP2.getId()).thenReturn("comp");
		Mockito.when(mockedP3.getId()).thenReturn("lin");
		game = new GameStatusHandlerImpl(mockedScore, mockedPlayers, mockedRules,
				new kth.game.othello.player.PlayerCreatorImpl());

		mockedPlayers.add(mockedP1);
		mockedPlayers.add(mockedP2);
		mockedPlayers.add(mockedP3);
		game = new GameStatusHandlerImpl(mockedScore, mockedPlayers, mockedRules,
				new kth.game.othello.player.PlayerCreatorImpl());
		game.start();
		assertEquals(true, game.isActive());
	}

	@Test
	public void testNextPlayerInTurn() {
		mockedScore = Mockito.mock(ScoreImpl.class);
		mockedRules = Mockito.mock(Rules.class);
		mockedPlayers = new ArrayList<>();
		mockedP1 = Mockito.mock(Player.class);
		mockedP2 = Mockito.mock(Player.class);
		mockedP3 = Mockito.mock(Player.class);
		Mockito.when(mockedP1.getId()).thenReturn("osk");
		Mockito.when(mockedP2.getId()).thenReturn("comp");
		Mockito.when(mockedP3.getId()).thenReturn("lin");
		game = new GameStatusHandlerImpl(mockedScore, mockedPlayers, mockedRules,
				new kth.game.othello.player.PlayerCreatorImpl());

		Mockito.when(mockedRules.hasValidMove(mockedP1.getId())).thenReturn(true);
		Mockito.when(mockedRules.hasValidMove(mockedP2.getId())).thenReturn(true);
		Mockito.when(mockedRules.hasValidMove(mockedP3.getId())).thenReturn(true);

		mockedPlayers.add(mockedP1);
		mockedPlayers.add(mockedP2);
		mockedPlayers.add(mockedP3);
		game = new GameStatusHandlerImpl(mockedScore, mockedPlayers, mockedRules,
				new kth.game.othello.player.PlayerCreatorImpl());
		game.start(mockedP1.getId());

		game.setNextPlayerInTurn();

		assertEquals(mockedP2.getId(), game.getPlayerInTurn().getId());
	}

	@Test
	public void testEndGame() {
		mockedScore = Mockito.mock(ScoreImpl.class);
		mockedRules = Mockito.mock(Rules.class);
		mockedPlayers = new ArrayList<>();
		mockedP1 = Mockito.mock(Player.class);
		mockedP2 = Mockito.mock(Player.class);
		mockedP3 = Mockito.mock(Player.class);
		Mockito.when(mockedP1.getId()).thenReturn("osk");
		Mockito.when(mockedP2.getId()).thenReturn("comp");
		Mockito.when(mockedP3.getId()).thenReturn("lin");
		game = new GameStatusHandlerImpl(mockedScore, mockedPlayers, mockedRules,
				new kth.game.othello.player.PlayerCreatorImpl());

		Mockito.when(mockedRules.hasValidMove(mockedP1.getId())).thenReturn(true);
		Mockito.when(mockedRules.hasValidMove(mockedP2.getId())).thenReturn(false);

		mockedPlayers.add(mockedP1);
		mockedPlayers.add(mockedP2);
		mockedPlayers.add(mockedP3);
		game = new GameStatusHandlerImpl(mockedScore, mockedPlayers, mockedRules,
				new kth.game.othello.player.PlayerCreatorImpl());
		game.start(mockedP1.getId());

		game.setNextPlayerInTurn();

		assertEquals(false, game.isActive());
	}
}
