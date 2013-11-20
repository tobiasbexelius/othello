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
import kth.game.othello.player.Player;

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
	public void testMoveComputer1() {
		Othello spyOnOthelloGame = spy(new OthelloFactoryImpl().createComputerGameOnClassicalBoard());
		spyOnOthelloGame.start();
		assertEquals(2, spyOnOthelloGame.move().size());
		//String startingPlayerId = spyOnOthelloGame.getPlayers().get(0).getId();
		Player humanPlayer = new Player() {
			
			@Override
			public Type getType() {
				return Type.HUMAN;
			}
			
			@Override
			public String getName() {
				return "something";
			}
			
			@Override
			public String getId() {
				return "something else";
			}
		};
		when(spyOnOthelloGame.getPlayerInTurn()).thenReturn(humanPlayer);
		try{
			spyOnOthelloGame.move();
		}catch(IllegalArgumentException e){
			assertEquals(5,getNumberOfOccupiedNodes(spyOnOthelloGame));
		}	
	}
	@Test
	public void testMoveComputer2(){
		Othello spyOnOthelloGame = spy(new OthelloFactoryImpl().createComputerGameOnClassicalBoard());
		spyOnOthelloGame.start();
		assertEquals(2, spyOnOthelloGame.move().size());
		assertEquals(2, spyOnOthelloGame.move().size());	
		for(int i = 0; i < 10; i++){
			spyOnOthelloGame.move();
		}
		assertEquals(16, getNumberOfOccupiedNodes(spyOnOthelloGame));
		
		when(spyOnOthelloGame.hasValidMove(anyString())).thenReturn(false).thenReturn(true);
		
		Player firstPlayer = spyOnOthelloGame.getPlayerInTurn();
		List<Node> firstMove = spyOnOthelloGame.move();
		
		assertTrue(firstPlayer.getId()!=null);
		
		Player secondPlayer = spyOnOthelloGame.getPlayerInTurn();
		List<Node> secondMove = spyOnOthelloGame.move();
		assertTrue(firstPlayer.getId()!=null);
		
		assertTrue(firstMove.size() == 0);
		assertTrue(secondMove.size() > 0);
		assertFalse(firstPlayer.equals(secondPlayer));
		
		assertTrue(firstPlayer.getId().equals(spyOnOthelloGame.getPlayerInTurn().getId()));
	}

	@Test
	public void testMoveHuman1() {
		Othello othelloSpy = spy(new OthelloFactoryImpl().createHumanGameOnOriginalBoard());
		String startingPlayerId = othelloSpy.getPlayers().get(0).getId();
		othelloSpy.start(startingPlayerId);
		
		boolean threwException = false;
		try {
			othelloSpy.move(startingPlayerId, "500");
		} catch (IllegalArgumentException e) {
			threwException = true;
		}
		assertTrue(threwException);
		assertTrue(startingPlayerId.equals(othelloSpy.getPlayerInTurn().getId()));
		
		threwException = false;
		when(othelloSpy.isMoveValid(anyString(), anyString())).thenReturn(false);
		try {
			othelloSpy.move(startingPlayerId, "29");
		} catch (IllegalArgumentException e) {
			threwException = true;
		}
		assertTrue(threwException);
		assertTrue(startingPlayerId.equals(othelloSpy.getPlayerInTurn().getId()));
		
		when(othelloSpy.isMoveValid(anyString(), anyString())).thenReturn(true);
		threwException = false;
		try {
			othelloSpy.move(startingPlayerId, "29");
		} catch (IllegalArgumentException e) {
			threwException = true;
		}
		assertFalse(threwException);
		assertFalse(startingPlayerId.equals(othelloSpy.getPlayerInTurn().getId()));
	}
	
	@Test
	public void testMoveHuman2() {
		Othello othello = new OthelloFactoryImpl().createHumanGameOnOriginalBoard();
		String startingPlayerId = othello.getPlayers().get(0).getId();
		String opponentPlayerId = othello.getPlayers().get(1).getId();
		othello.start(startingPlayerId);
		
		assertEquals(2, othello.move(startingPlayerId, "29").size());
		assertTrue(opponentPlayerId.equals(othello.getPlayerInTurn().getId()));
		assertEquals(2, othello.move(opponentPlayerId, "21").size());
		assertTrue(startingPlayerId.equals(othello.getPlayerInTurn().getId()));
	}
	
	@Test
	public void testMoveHuman3() {
		Othello othello = new OthelloFactoryImpl().createHumanGameOnOriginalBoard();
		String startingPlayerId = othello.getPlayers().get(0).getId();
		String opponentPlayerId = othello.getPlayers().get(1).getId();
		othello.start(opponentPlayerId);
		int[] markedNodes = 
				{2,1,1,1,1,1,1,2,
				 1,1,1,1,1,1,1,1,
				 1,1,1,1,1,1,1,1,
				 1,1,1,1,1,1,1,1,
				 1,1,1,1,1,1,1,1,
				 1,1,1,1,1,1,1,1,
				 1,1,1,1,1,1,1,1,
				 2,1,1,1,1,1,1,0};
		updateBoard(markedNodes, othello, startingPlayerId, opponentPlayerId);
		assertEquals(19, othello.move(opponentPlayerId, "63").size());
	
		boolean threwException = false;
		assertTrue(startingPlayerId.equals(othello.getPlayerInTurn().getId()));
		try {
			othello.move(startingPlayerId, "63");
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
		
		int numberOfPlayers = othello.getPlayers().size();
		assertEquals(2, numberOfPlayers);
		assertTrue(othello.getPlayerInTurn() != null);
		assertTrue(othello.getPlayers().contains(othello.getPlayerInTurn()));
		assertFalse(othello.getPlayers().contains(null));
	}
	
	@Test
	public void testStartWithParameters() {
		Othello othello = new OthelloFactoryImpl().createComputerGameOnClassicalBoard();
		assertNull(othello.getPlayerInTurn());
		othello.start(othello.getPlayers().get(0).getId());
		
		int numberOfPlayers = othello.getPlayers().size();
		assertEquals(2, numberOfPlayers);
		assertTrue(othello.getPlayerInTurn() != null);
		assertTrue(othello.getPlayers().contains(othello.getPlayerInTurn()));
		assertFalse(othello.getPlayers().contains(null));
	}

	public void testHasValidMove() {
		Othello game = new OthelloFactoryImpl().createHumanGameOnOriginalBoard();
		String startingPlayer = game.getPlayers().get(0).getId();
		String opponentPlayer = game.getPlayers().get(1).getId();
		
		game.start(startingPlayer);
		assertTrue(game.hasValidMove(startingPlayer));
		int[] boardState = 	{2,2,2,2,2,2,2,2,
							 0,1,1,1,1,1,1,1,
							 2,1,1,1,2,2,1,1,
							 2,1,2,2,2,2,1,1,
							 2,1,2,2,2,2,1,1,
							 2,2,2,1,2,2,1,1,
							 2,1,1,1,1,1,0,0,
							 2,1,2,2,2,2,2,2};
		
		updateBoard(boardState, game, startingPlayer, opponentPlayer);
		assertFalse(game.hasValidMove(startingPlayer));
		assertTrue(game.hasValidMove(opponentPlayer));
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
