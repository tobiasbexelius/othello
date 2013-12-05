package kth.game.othello;

import java.util.ArrayList;
import java.util.List;

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
		List<Player> players = new ArrayList<Player>();
		players.add(player1);
		players.add(player2);
		PlayerHandler playerHandler = new PlayerHandler(players);
		Assert.assertNotNull(playerHandler.getPlayer("player1"));
		Assert.assertNotNull(playerHandler.getPlayer("player2"));
		Assert.assertNull(playerHandler.getPlayer("player3"));
	}
}
