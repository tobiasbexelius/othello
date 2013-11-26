package kth.game.othello.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import kth.game.othello.BoardHandler;
import kth.game.othello.PlayerHandler;
import kth.game.othello.RuleHandler;
import kth.game.othello.board.Node;
import kth.game.othello.player.Player;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class RuleHandlerTest {

	@Test
	public void testIsMoveValidFails() {
		BoardHandler bh = mock(BoardHandler.class);
		PlayerHandler ph = mock(PlayerHandler.class);
		RuleHandler rh = new RuleHandler(bh, ph);

		when(bh.getNode(anyString())).thenReturn(null);
		assertFalse(rh.isMoveValid("player1", "node1"));

		Node node = mock(Node.class);
		when(node.isMarked()).thenReturn(true);
		when(bh.getNode(anyString())).thenReturn(node);
		assertFalse(rh.isMoveValid("player1", "node1"));
	}

	@Test
	public void testIsMoveValid() {
		BoardHandler bh = mock(BoardHandler.class);
		PlayerHandler ph = mock(PlayerHandler.class);
		RuleHandler rh = new RuleHandler(bh, ph);

		Node moveTo = mock(Node.class);
		when(moveTo.getXCoordinate()).thenReturn(1);
		when(moveTo.getYCoordinate()).thenReturn(1);
		when(moveTo.isMarked()).thenReturn(false);
		when(moveTo.getOccupantPlayerId()).thenReturn(null);

		Node node2 = mock(Node.class);
		when(node2.getXCoordinate()).thenReturn(1);
		when(node2.getYCoordinate()).thenReturn(2);
		when(node2.isMarked()).thenReturn(true);
		when(node2.getOccupantPlayerId()).thenReturn("player2");

		Node node3 = mock(Node.class);
		when(node3.getXCoordinate()).thenReturn(1);
		when(node3.getYCoordinate()).thenReturn(3);
		when(node3.isMarked()).thenReturn(true);
		when(node3.getOccupantPlayerId()).thenReturn("player1");

		when(bh.getNode(anyString())).thenReturn(moveTo);
		when(bh.getNode(1, 2)).thenReturn(node2);
		when(bh.getNode(1, 3)).thenReturn(node3);

		assertTrue(rh.isMoveValid("player1", "node1"));
	}

	@Test
	public void testHasValidMove() {
		BoardHandler bh = mock(BoardHandler.class);
		PlayerHandler ph = mock(PlayerHandler.class);
		RuleHandler rh = new RuleHandler(bh, ph);

		Node moveTo = mock(Node.class);
		when(moveTo.getXCoordinate()).thenReturn(1);
		when(moveTo.getYCoordinate()).thenReturn(1);
		when(moveTo.isMarked()).thenReturn(false);
		when(moveTo.getOccupantPlayerId()).thenReturn(null);

		Node node2 = mock(Node.class);
		when(node2.getXCoordinate()).thenReturn(1);
		when(node2.getYCoordinate()).thenReturn(2);
		when(node2.isMarked()).thenReturn(true);
		when(node2.getOccupantPlayerId()).thenReturn("player2");

		Node node3 = mock(Node.class);
		when(node3.getXCoordinate()).thenReturn(1);
		when(node3.getYCoordinate()).thenReturn(3);
		when(node3.isMarked()).thenReturn(true);
		when(node3.getOccupantPlayerId()).thenReturn("player1");

		when(bh.getNode(anyString())).thenReturn(moveTo);
		when(bh.getNode(1, 2)).thenReturn(node2);
		when(bh.getNode(1, 3)).thenReturn(node3);

		List<Node> nodeList = new ArrayList<Node>();
		nodeList.add(moveTo);
		nodeList.add(node2);
		nodeList.add(node3);

		when(bh.getNodes()).thenReturn(nodeList);
		assertTrue(rh.hasValidMove("player1"));
		assertFalse(rh.hasValidMove("player2"));
	}

	@Test
	public void testIsActive() {
		BoardHandler bh = mock(BoardHandler.class);
		PlayerHandler ph = mock(PlayerHandler.class);
		RuleHandler rh = new RuleHandler(bh, ph);

		Node moveTo = mock(Node.class);
		when(moveTo.getXCoordinate()).thenReturn(1);
		when(moveTo.getYCoordinate()).thenReturn(1);
		when(moveTo.isMarked()).thenReturn(false);
		when(moveTo.getOccupantPlayerId()).thenReturn(null);

		Node node2 = mock(Node.class);
		when(node2.getXCoordinate()).thenReturn(1);
		when(node2.getYCoordinate()).thenReturn(2);
		when(node2.isMarked()).thenReturn(true);
		when(node2.getOccupantPlayerId()).thenReturn("player2");

		Node node3 = mock(Node.class);
		when(node3.getXCoordinate()).thenReturn(1);
		when(node3.getYCoordinate()).thenReturn(3);
		when(node3.isMarked()).thenReturn(true);
		when(node3.getOccupantPlayerId()).thenReturn("player1");

		when(bh.getNode(anyString())).thenReturn(moveTo);
		when(bh.getNode(1, 2)).thenReturn(node2);
		when(bh.getNode(1, 3)).thenReturn(node3);

		List<Node> nodeList = new ArrayList<Node>();
		nodeList.add(moveTo);
		nodeList.add(node2);
		nodeList.add(node3);

		when(bh.getNodes()).thenReturn(nodeList);
		List<Player> players = new ArrayList<Player>();
		Player p1 = mock(Player.class);
		when(p1.getId()).thenReturn("player1");
		Player p2 = mock(Player.class);
		when(p2.getId()).thenReturn("player2");
		players.add(p1);
		players.add(p2);
		when(ph.getPlayers()).thenReturn(players);

		assertTrue(rh.isActive());
	}

	@Test
	public void testGetNodesToSwap() {
		BoardHandler bh = mock(BoardHandler.class);
		PlayerHandler ph = mock(PlayerHandler.class);
		RuleHandler rh = new RuleHandler(bh, ph);

		when(bh.getNode(anyString())).thenReturn(null);
		assertEquals(0, rh.getNodesToSwap("player1", "node1").size());

		Node moveTo = mock(Node.class);
		when(moveTo.getXCoordinate()).thenReturn(1);
		when(moveTo.getYCoordinate()).thenReturn(1);
		when(moveTo.isMarked()).thenReturn(false);
		when(moveTo.getOccupantPlayerId()).thenReturn(null);

		Node node2 = mock(Node.class);
		when(node2.getXCoordinate()).thenReturn(1);
		when(node2.getYCoordinate()).thenReturn(2);
		when(node2.isMarked()).thenReturn(true);
		when(node2.getOccupantPlayerId()).thenReturn("player2");

		Node node3 = mock(Node.class);
		when(node3.getXCoordinate()).thenReturn(1);
		when(node3.getYCoordinate()).thenReturn(3);
		when(node3.isMarked()).thenReturn(true);
		when(node3.getOccupantPlayerId()).thenReturn("player1");

		when(bh.getNode(anyString())).thenReturn(moveTo);
		when(bh.getNode(1, 2)).thenReturn(node2);
		when(bh.getNode(1, 3)).thenReturn(node3);

		assertEquals(1, rh.getNodesToSwap("player1", moveTo.getId()).size());
	}

	@Test
	public void swapPlayerInTurnTest() {
		Player player1 = Mockito.mock(Player.class);
		Mockito.when(player1.getId()).thenReturn("player1");
		Player player2 = Mockito.mock(Player.class);
		Mockito.when(player2.getId()).thenReturn("player2");
		Player player3 = Mockito.mock(Player.class);
		Mockito.when(player3.getId()).thenReturn("player3");
		Player player4 = Mockito.mock(Player.class);
		Mockito.when(player4.getId()).thenReturn("player4");

		List<Player> players = new ArrayList<Player>();
		players.add(player1);
		players.add(player2);
		players.add(player3);
		players.add(player4);

		PlayerHandler playerHandler = new PlayerHandler(players);
		RuleHandler ruleHandler = new RuleHandler(mock(BoardHandler.class), playerHandler);

		playerHandler.setPlayerInTurn(player1);
		Assert.assertTrue(ruleHandler.getPlayerInTurn().getId().equals(player1.getId()));
		Assert.assertFalse(ruleHandler.getPlayerInTurn().getId().equals(player2.getId()));
		ruleHandler.swapPlayerInTurn();
		Assert.assertTrue(ruleHandler.getPlayerInTurn().getId().equals(player2.getId()));
		ruleHandler.swapPlayerInTurn();
		Assert.assertTrue(ruleHandler.getPlayerInTurn().getId().equals(player3.getId()));
		ruleHandler.swapPlayerInTurn();
		Assert.assertTrue(ruleHandler.getPlayerInTurn().getId().equals(player4.getId()));
		ruleHandler.swapPlayerInTurn();
		Assert.assertTrue(ruleHandler.getPlayerInTurn().getId().equals(player1.getId()));
	}
}
