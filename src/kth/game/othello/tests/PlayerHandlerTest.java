package kth.game.othello.tests;

import kth.game.othello.PlayerHandler;
import kth.game.othello.player.Player;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class PlayerHandlerTest {

	@Test
	public void getPlayerTest() {
		Player player1 = Mockito.mock(Player.class);
		Mockito.when(player1.getId()).thenReturn("player1");
		Player player2 = Mockito.mock(Player.class);
		Mockito.when(player2.getId()).thenReturn("player2");
		PlayerHandler playerHandler = new PlayerHandler(player1, player2);
		Assert.assertNotNull(playerHandler.getPlayer("player1"));
		Assert.assertNotNull(playerHandler.getPlayer("player2"));
		Assert.assertNull(playerHandler.getPlayer("player3"));
	}
	
	@Test
	public void swapPlayerInTurnTest() {
		Player player1 = Mockito.mock(Player.class);
		Mockito.when(player1.getId()).thenReturn("player1");
		Player player2 = Mockito.mock(Player.class);
		Mockito.when(player2.getId()).thenReturn("player2");
		
		PlayerHandler playerHandler = new PlayerHandler(player1, player2);
		
		playerHandler.setPlayerInTurn(player1);
		Assert.assertTrue(playerHandler.playerIsInTurn(player1.getId()));
		Assert.assertFalse(playerHandler.playerIsInTurn(player2.getId()));
		
		playerHandler.swapPlayerInTurn();
		Assert.assertTrue(playerHandler.playerIsInTurn(player2.getId()));
		playerHandler.swapPlayerInTurn();
		Assert.assertTrue(playerHandler.playerIsInTurn(player1.getId()));
	}
	
	@Test
	public void playerIsInTurnTest() {
		Player player1 = Mockito.mock(Player.class);
		Mockito.when(player1.getId()).thenReturn("player1");
		Player player2 = Mockito.mock(Player.class);
		Mockito.when(player2.getId()).thenReturn("player2");
		
		PlayerHandler playerHandler = new PlayerHandler(player1, player2);
		
		playerHandler.setPlayerInTurn(player1);
		Assert.assertTrue(playerHandler.playerIsInTurn(player1.getId()));
		playerHandler.setPlayerInTurn(player2);
		Assert.assertTrue(playerHandler.playerIsInTurn(player2.getId()));
		Assert.assertFalse(playerHandler.playerIsInTurn(player1.getId()));
	}

}
