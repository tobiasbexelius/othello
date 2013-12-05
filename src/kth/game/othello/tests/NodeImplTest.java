package kth.game.othello.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import kth.game.othello.board.NodeImpl;
import kth.game.othello.score.ScoreImpl;

import org.junit.Test;
import org.mockito.Mockito;

public class NodeImplTest {

	@Test
	public void updateOccupantPlayerTest() {
		NodeImpl node = new NodeImpl(1, 1, null);
		assertFalse(node.isMarked());
		assertNull(node.getOccupantPlayerId());
		node.updateOccupantPlayer("player1");
		assertTrue(node.isMarked());
		assertNotNull(node.getOccupantPlayerId());
		assertEquals("player1", node.getOccupantPlayerId());
	}

	@Test
	public void notifyObserversTest() {
		NodeImpl node = new NodeImpl(1, 1, null);
		ScoreImpl score = Mockito.mock(ScoreImpl.class);
		node.addObserver(score);
		node.updateOccupantPlayer("player1");
		Mockito.verify(score).update(node, null);
	}

	@Test
	public void isMarkedTest() {
		NodeImpl node = new NodeImpl(1, 1, null);
		assertFalse(node.isMarked());
		node = new NodeImpl(1, 1, "player2");
		assertTrue(node.isMarked());
		node.updateOccupantPlayer(null);
		assertFalse(node.isMarked());
		node.updateOccupantPlayer("player 1");
		assertTrue(node.isMarked());
	}

	@Test
	public void testEquals() {
		NodeImpl node = new NodeImpl(1, 1, null);
		NodeImpl node2 = new NodeImpl(1, 1, null);
		NodeImpl node3 = new NodeImpl(1, 1, "player 1");
		NodeImpl node4 = new NodeImpl(1, 2, null);

		assertEquals(node, node2);
		assertFalse(node.equals(node3));
		assertFalse(node.equals(node4));
		assertFalse(node3.equals(node4));

		node = new NodeImpl(1, 1, "player 2");

		assertFalse(node.equals(node3));
		node = new NodeImpl(1, 1, "player 1");
		assertEquals(node, node3);
	}
}
