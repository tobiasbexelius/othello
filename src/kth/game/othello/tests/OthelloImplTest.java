package kth.game.othello.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

import java.util.List;

import kth.game.othello.Othello;
import kth.game.othello.OthelloFactoryImpl;
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
	public void testIsMoveValid() {
		Othello game = new OthelloFactoryImpl().createHumanGameOnOriginalBoard();
		String startingPlayerId = game.getPlayers().get(0).getId();
		game.start(startingPlayerId);

		assertFalse(game.isMoveValid(startingPlayerId, "500"));
		assertFalse(game.isMoveValid(startingPlayerId, "10"));
		assertFalse(game.isMoveValid(startingPlayerId, "-1"));
		assertFalse(game.isMoveValid(startingPlayerId, "26"));
		assertFalse(game.isMoveValid(startingPlayerId, "21"));
		assertFalse(game.isMoveValid(startingPlayerId, "27"));
		assertFalse(game.isMoveValid(startingPlayerId, "28"));
		assertTrue(game.isMoveValid(startingPlayerId, "20"));
		assertTrue(game.isMoveValid(startingPlayerId, "29"));

		game.move(startingPlayerId, "20");
		assertFalse(game.isMoveValid(startingPlayerId, "20"));
	}

	
	/*
	 * 
	 * MOOOOOOOOOOCKKKKKKKKKAAAAAAAA*/
	@Test
	public void testMoveComputer() {
		Othello game = new OthelloFactoryImpl().createComputerGameOnClassicalBoard();
		String startingPlayerId = game.getPlayers().get(0).getId();
		game.start(startingPlayerId);

		assertEquals(2, game.move().size());
		assertTrue(!startingPlayerId.equals(game.getPlayerInTurn().getId()));
		assertEquals(2, game.move().size());
		assertTrue(startingPlayerId.equals(game.getPlayerInTurn().getId()));
		int moves = 2;
		while(game.isActive()) {
			List<Node> captured = game.move();
			if(!game.hasValidMove(game.getPlayerInTurn().getId()))
				assertTrue(game.move().size() == 0);
			moves++;
			System.out.println("Moves made: " + moves);
		}
	}

	/*
	 * 
	 * MOOOOOOOOOOCKKKKKKKKKAAAAAAAA*/
	@Test
	public void testMoveHuman() {
		Othello game = new OthelloFactoryImpl().createHumanGameOnOriginalBoard();
		String startingPlayerId = game.getPlayers().get(0).getId();
		String opponentPlayer = game.getPlayers().get(1).getId();
		game.start(startingPlayerId);
		assertEquals(2, game.move(startingPlayerId, "29").size());
		assertEquals(2, game.move(opponentPlayer, "21").size());
		assertEquals(2, game.move(startingPlayerId, "13").size());
		try {
			game.move(startingPlayerId, "13").size();
		} catch(IllegalArgumentException e) {
		}

	}

	@Test 
	public void testIsActive() {
		Othello spyOnOthello = spy(new OthelloFactoryImpl().createComputerGameOnClassicalBoard());
		spyOnOthello.start();
		
		when(spyOnOthello.hasValidMove(anyString())).thenReturn(false).thenReturn(true).thenReturn(true).thenReturn(false);
		assertTrue(spyOnOthello.isActive());
		assertTrue(spyOnOthello.isActive());
		assertFalse(spyOnOthello.isActive());
	}
	


	public static void printBoard(Othello o) {
		List<Node> board = o.getBoard().getNodes();
		System.out.println("##########");
		System.out.print("#");
		for(Node node: board) {
			char c;
			if(node.isMarked()) {
				c = node.getOccupantPlayerId().equals(o.getPlayers().get(0).getId()) ? 'B' : 'W';
			} else {
				c = ' ';
			}
			System.out.print(c);
			if((Double.parseDouble(node.getId())+1) % 8 == 0) {
				System.out.print("#\n#");
			}
		}
		System.out.println("#########");
	}

}
