package kth.game.othello;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import kth.game.othello.player.Player;
import kth.game.othello.tests.MockCreator;

import org.junit.Test;

public class PlayerHandlerTest {

	@Test
	public void getPlayer() {
		Player player1 = MockCreator.createMockedHumanPlayer("player1", "player1");
		Player player2 = MockCreator.createMockedHumanPlayer("player2", "player2");
		List<Player> players = new ArrayList<Player>();
		players.add(player1);
		players.add(player2);
		PlayerHandler playerHandler = new PlayerHandler(players);
		assertNotNull(playerHandler.getPlayer("player1"));
		assertNotNull(playerHandler.getPlayer("player2"));
	}

	public void getNullPlayer() {
		List<Player> players = new ArrayList<Player>();
		PlayerHandler playerHandler = new PlayerHandler(players);
		assertNull(playerHandler.getPlayer("player"));
	}
}
