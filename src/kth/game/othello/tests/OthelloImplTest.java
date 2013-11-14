package kth.game.othello.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import java.util.List;

import kth.game.othello.Othello;
import kth.game.othello.OthelloFactoryImpl;
import kth.game.othello.OthelloImpl;
import kth.game.othello.board.Node;

import org.junit.Test;

public class OthelloImplTest {

	@Test
	public void getNodesToSwapTest() {
		Othello game = new OthelloFactoryImpl().createHumanGameOnOriginalBoard();
		String startingPlayerId = game.getPlayers().get(0).getId();
		game.start(startingPlayerId);
	
		assertEquals(0, game.getNodesToSwap(startingPlayerId, "500").size());
		assertEquals(0, game.getNodesToSwap(startingPlayerId, "10").size());
		assertEquals(0, game.getNodesToSwap(startingPlayerId, "-1").size());
		assertEquals(0, game.getNodesToSwap(startingPlayerId, "26").size());
		assertEquals(0, game.getNodesToSwap(startingPlayerId, "21").size());
		assertEquals(1, game.getNodesToSwap(startingPlayerId, "20").size());

		List<Node> nodesToSwap =  game.getNodesToSwap(startingPlayerId, "29");
		assertEquals(1, nodesToSwap.size());
		assertEquals("28", nodesToSwap.get(0).getId());
		assertNull(game.getBoard().getNodes().get(29).getOccupantPlayerId());
	}
	
	@Test
	public void isActiveTest() {
		Othello game = mock(OthelloImpl.class);
		when(game.hasValidMove(anyString())).thenReturn(true);
		game.start();
		System.out.println(game.hasValidMove("derp"));
		assertTrue(game.isActive());
	}

}
