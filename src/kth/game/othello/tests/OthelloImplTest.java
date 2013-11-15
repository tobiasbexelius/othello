package kth.game.othello.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.util.List;

import kth.game.othello.Othello;
import kth.game.othello.OthelloFactoryImpl;
import kth.game.othello.board.Board;
import kth.game.othello.board.Node;
import kth.game.othello.board.NodeImpl;

import org.junit.Test;

public class OthelloImplTest {

	@Test
	public void testGetNodesToSwap() {
		Othello game = new OthelloFactoryImpl().createHumanGameOnOriginalBoard();
		String startingPlayerId = game.getPlayers().get(0).getId();
		game.start(startingPlayerId);

		assertEquals(0, game.getNodesToSwap(startingPlayerId, "500").size());
		assertEquals(0, game.getNodesToSwap(startingPlayerId, "10").size());
		assertEquals(0, game.getNodesToSwap(startingPlayerId, "-1").size());
		assertEquals(0, game.getNodesToSwap(startingPlayerId, "26").size());
		assertEquals(0, game.getNodesToSwap(startingPlayerId, "21").size());
		assertEquals(1, game.getNodesToSwap(startingPlayerId, "20").size());

		List<Node> nodesToSwap = game.getNodesToSwap(startingPlayerId, "29");
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
	 * Mock: getPlayerInTurn, hasValidMove, getNodesToSwap
	 * */
	@Test
	public void testMoveComputer() {
		Othello spyOnOthelloGame = spy(new OthelloFactoryImpl().createComputerGameOnClassicalBoard());
		spyOnOthelloGame.start();
		assertEquals(2, spyOnOthelloGame.move().size());
		String startingPlayerId = spyOnOthelloGame.getPlayers().get(0).getId();
		when(spyOnOthelloGame.getPlayerInTurn().getId()).thenReturn("lol");
		try{
			spyOnOthelloGame.move();
		}catch(IllegalArgumentException e){
			assertEquals(5,getNumberOfOccupiedNodes(spyOnOthelloGame));
		}
		
		assertEquals(2, spyOnOthelloGame.move().size());	
		for(int i = 0; i < 10; i++){
			spyOnOthelloGame.move();
		}
		assertEquals(16, getNumberOfOccupiedNodes(spyOnOthelloGame));
		
		when(spyOnOthelloGame.isActive()).thenReturn(true).thenReturn(false);
		when(spyOnOthelloGame.hasValidMove(anyString())).thenReturn(true).thenReturn(false);
		while (spyOnOthelloGame.isActive()) {
			spyOnOthelloGame.move();
			if (!spyOnOthelloGame.hasValidMove(spyOnOthelloGame.getPlayerInTurn().getId()))
				assertTrue(spyOnOthelloGame.move().size() == 0);
		}
	}

	/*
	 * Mock: getplayerinturn, ismovevalid, getNodestoswap
	 */
	@Test
	public void testMoveHuman() {
//		Othello spyOnOthello = spy(new OthelloFactoryImpl().createHumanGameOnOriginalBoard());
//		spyOnOthello.start();
//		Player startingPlayerId = spyOnOthello.getPlayers().get(0);
//		Player opponentPlayer = spyOnOthello.getPlayers().get(1);
//		when(spyOnOthello.getPlayerInTurn());
//		
//		
		Othello game = new OthelloFactoryImpl().createHumanGameOnOriginalBoard();
		String startingPlayerId = game.getPlayers().get(0).getId();
		String opponentPlayer = game.getPlayers().get(1).getId();
		game.start(startingPlayerId);
		assertEquals(2, game.move(startingPlayerId, "29").size());
		assertEquals(2, game.move(opponentPlayer, "21").size());
		assertEquals(2, game.move(startingPlayerId, "13").size());
		boolean threwException = false;
		try {
			game.move(opponentPlayer, "43").size();
		} catch (IllegalArgumentException e) {
			threwException = true;
		}
		assertTrue(threwException);
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

	@Test
	public void testStart() {
		Othello othello = new OthelloFactoryImpl().createComputerGameOnClassicalBoard();
		assertNull(othello.getPlayerInTurn());
		othello.start();
		assertEquals(2, othello.getPlayers().size());
		System.out.println(othello.getPlayers().get(0).getId());
		System.out.println(othello.getPlayers().get(1).getId());
		assertTrue(othello.getPlayerInTurn() != null);
		assertTrue(othello.getPlayers().contains(othello.getPlayerInTurn()));
		assertFalse(othello.getPlayers().contains(null));

		othello = new OthelloFactoryImpl().createComputerGameOnClassicalBoard();
		assertNull(othello.getPlayerInTurn());
		othello.start(othello.getPlayers().get(0).getId());
		assertEquals(2, othello.getPlayers().size());
		assertTrue(othello.getPlayerInTurn() != null);
		assertTrue(othello.getPlayers().contains(othello.getPlayerInTurn()));
		assertFalse(othello.getPlayers().contains(null));
	}
	
	public void testHasValidMove() {
		Othello othello = new OthelloFactoryImpl().createHumanGameOnOriginalBoard();
		String startingPlayer = othello.getPlayers().get(0).getId();
		String opponentPlayer = othello.getPlayers().get(1).getId();
		
		othello.start(startingPlayer);
		assertTrue(othello.hasValidMove(startingPlayer));
		int[] markedNodes = {2,2,2,2,2,2,2,2,
							 0,1,1,1,1,1,1,1,
							 2,1,1,1,2,2,1,1,
							 2,1,2,2,2,2,1,1,
							 2,1,2,2,2,2,1,1,
							 2,2,2,1,2,2,1,1,
							 2,1,1,1,1,1,0,0,
							 2,1,2,2,2,2,2,2};
		updateBoard(markedNodes, othello, startingPlayer, opponentPlayer);
		assertFalse(othello.hasValidMove(startingPlayer));
		assertTrue(othello.hasValidMove(opponentPlayer));
	}
	
	private void updateBoard(int[] state, Othello othello, String player1Id, String player2Id) {
		for(int i = 0; i < 64; i++) {
			switch(state[i]) {
			case 1:
				occupyNode(othello.getBoard(), othello.getBoard().getNodes().get(i), player1Id);
				break;
			case 2:
				occupyNode(othello.getBoard(), othello.getBoard().getNodes().get(i), player2Id);
				break;
			}
		}
	}
	
	private boolean occupyNode(Board board, Node node, String occupantPlayerId) {
		int nodeIndex = board.getNodes().indexOf(node);
		if (nodeIndex == -1) {
			return false;
		}
		board.getNodes().remove(node);
		board.getNodes().add(nodeIndex, new NodeImpl(node.getXCoordinate(), node.getYCoordinate(), true, node.getId(),
				occupantPlayerId));
		return true;
	}

	public static void printBoard(Othello o) {
		List<Node> board = o.getBoard().getNodes();
		System.out.println("##########");
		System.out.print("#");
		for (Node node : board) {
			char c;
			if (node.isMarked()) {
				c = node.getOccupantPlayerId().equals(o.getPlayers().get(0).getId()) ? 'B' : 'W';
			} else {
				c = ' ';
			}
			System.out.print(c);
			if ((Double.parseDouble(node.getId()) + 1) % 8 == 0) {
				System.out.print("#\n#");
			}
		}
		System.out.println("#########");
	}
	private Object getNumberOfOccupiedNodes(Othello othello) {
		int occupiedNodesCounter = 0;
		for (Node node : othello.getBoard().getNodes()) {
			if (node.isMarked()) {
				occupiedNodesCounter++;
			}
		}
		return occupiedNodesCounter;
	}

}
