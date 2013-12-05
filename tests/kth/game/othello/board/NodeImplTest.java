package kth.game.othello.board;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
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
	public void notifyObservers() {
		NodeImpl node = new NodeImpl(1, 1, null);
		ScoreImpl score = Mockito.mock(ScoreImpl.class);
		node.addObserver(score);
		node.updateOccupantPlayer("player1");
		Mockito.verify(score).update(node, null);
	}

	@Test
	public void isMarkedWhenMarked() {
		NodeImpl node = new NodeImpl(1, 1, "player2");
		assertTrue(node.isMarked());
	}

	@Test
	public void isMarkedWhenNotMarked() {
		NodeImpl node = new NodeImpl(1, 1, "player2");
		node.updateOccupantPlayer(null);
		assertFalse(node.isMarked());
	}

	@Test
	public void equalsWithEqualNodes() {
		NodeImpl node = new NodeImpl(1, 1, null);
		NodeImpl node2 = new NodeImpl(1, 1, null);
		NodeImpl node3 = new NodeImpl(1, 1, "player 1");

		assertEquals(node, node2);
		node = new NodeImpl(1, 1, "player 1");
		assertEquals(node, node3);
	}

	@Test
	public void equalsWithUnEqualNodes() {
		NodeImpl node = new NodeImpl(1, 1, null);
		NodeImpl node2 = new NodeImpl(1, 1, "player 1");
		NodeImpl node3 = new NodeImpl(1, 2, null);

		assertFalse(node.equals(node2));
		assertFalse(node.equals(node3));
	}

	@Test
	public void idDoesNotChangeWithOccupyingPlayer() {
		NodeImpl node = new NodeImpl(1, 1, null);
		String oldId = node.getId();
		node.updateOccupantPlayer("player2");
		assertEquals(oldId, node.getId());
	}
}
