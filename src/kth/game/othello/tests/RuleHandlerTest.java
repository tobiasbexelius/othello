package kth.game.othello.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import kth.game.othello.BoardHandler;
import kth.game.othello.PlayerHandler;
import kth.game.othello.RuleHandler;
import kth.game.othello.board.Node;

import org.junit.Test;

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
}
