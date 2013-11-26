package kth.game.othello.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import kth.game.othello.board.NodeImpl;
import kth.game.othello.player.Player;
import kth.game.othello.player.PlayerImpl;
import kth.game.othello.score.ScoreImpl;

import org.junit.Test;

public class NodeImplTest {

	@Test
	public void updateOccupantPlayer() {
		NodeImpl node = new NodeImpl(1,1,null);
		assertFalse(node.isMarked());
		assertNull(node.getOccupantPlayerId());
		node.updateOccupantPlayer("player1");
		assertTrue(node.isMarked());
		assertNotNull(node.getOccupantPlayerId());
		assertEquals("player1", node.getOccupantPlayerId());
	}
	
	@Test
	public void notifyObserversTest() {
		NodeImpl node = new NodeImpl(1,1,null);
		List<Player> players = new ArrayList<Player>();
		players.add(new PlayerImpl("player1", "player1", Player.Type.HUMAN));
		ScoreImpl score = new ScoreImpl(players);
		node.addObserver(score);
		assertEquals(0, score.getPoints("player1"));
		node.updateOccupantPlayer("player1");
		assertEquals(1, score.getPoints("player1"));
	}
}
