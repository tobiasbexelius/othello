package kth.game.othello.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import kth.game.othello.Rules;
import kth.game.othello.board.Board;
import kth.game.othello.board.Node;
import kth.game.othello.player.movestrategy.GreedyMoveStrategy;
import kth.game.othello.player.movestrategy.MoveStrategy;

import org.junit.Test;
import org.mockito.Mockito;

public class GreedyMoveStrategyTest {
	
	@SuppressWarnings("unchecked")
	@Test
	public void makeValidMove() {
		Rules ruleHandler = Mockito.mock(Rules.class);
		Board board = Mockito.mock(Board.class);
	
		Node node1 = newMockedNode(5,2,null);
		Mockito.when(node1.getId()).thenReturn("1");
		Node node2 = newMockedNode(4,2,null);
		Mockito.when(node2.getId()).thenReturn("2");
		Node node3 = newMockedNode(4,3,null);
		Mockito.when(node3.getId()).thenReturn("3");
		Node node4 = newMockedNode(4,4,null);
		Mockito.when(node4.getId()).thenReturn("4");
		
		List<Node> nodes = new ArrayList<Node>();
		
		nodes.add(node1);
		nodes.add(node2);
		nodes.add(node3);
		nodes.add(node4);
		
		ArrayList<Node> someNodes1 = Mockito.mock(ArrayList.class);
		Mockito.when(someNodes1.size()).thenReturn(5);
		ArrayList<Node> someNodes2 = Mockito.mock(ArrayList.class);
		Mockito.when(someNodes2.size()).thenReturn(7);
		ArrayList<Node> someNodes3 = Mockito.mock(ArrayList.class);
		Mockito.when(someNodes3.size()).thenReturn(2);
		ArrayList<Node> someNodes4 = Mockito.mock(ArrayList.class);
		Mockito.when(someNodes4.size()).thenReturn(3);
		
		Mockito.when(someNodes1.size()).thenReturn(5);
		Mockito.when(board.getNodes()).thenReturn(nodes);
		Mockito.when(ruleHandler.isMoveValid(Mockito.anyString(), Mockito.anyString())).thenReturn(true);
		Mockito.when(ruleHandler.getNodesToSwap("player1", "1")).thenReturn(someNodes1);
		Mockito.when(ruleHandler.getNodesToSwap("player1", "2")).thenReturn(someNodes2);
		Mockito.when(ruleHandler.getNodesToSwap("player1", "3")).thenReturn(someNodes3);
		Mockito.when(ruleHandler.getNodesToSwap("player1", "4")).thenReturn(someNodes4);
		
		MoveStrategy moveStrategy = new GreedyMoveStrategy();
		assertEquals(node2, moveStrategy.move("player1", ruleHandler, board));
	}
	
	@Test
	public void makeNonValidMove() {
		Rules ruleHandler = Mockito.mock(Rules.class);
		Board board = Mockito.mock(Board.class);
	
		Node node1 = newMockedNode(5,2,null);
		Mockito.when(node1.getId()).thenReturn("1");
		Node node2 = newMockedNode(4,2,null);
		Mockito.when(node2.getId()).thenReturn("2");
		Node node3 = newMockedNode(4,3,null);
		Mockito.when(node3.getId()).thenReturn("3");
		Node node4 = newMockedNode(4,4,null);
		Mockito.when(node4.getId()).thenReturn("4");
		
		List<Node> nodes = new ArrayList<Node>();
		
		nodes.add(node1);
		nodes.add(node2);
		nodes.add(node3);
		nodes.add(node4);
	
		Mockito.when(board.getNodes()).thenReturn(nodes);
		Mockito.when(ruleHandler.isMoveValid(Mockito.anyString(), Mockito.anyString())).thenReturn(false);
		
		MoveStrategy moveStrategy = new GreedyMoveStrategy();
		assertNull(moveStrategy.move("player1", ruleHandler, board));
	}
	
	private Node newMockedNode(int x, int y, String occupantPlayer) {
		Node node = Mockito.mock(Node.class);
		Mockito.when(node.getXCoordinate()).thenReturn(x);
		Mockito.when(node.getYCoordinate()).thenReturn(y);
		Mockito.when(node.getOccupantPlayerId()).thenReturn(occupantPlayer);
		if(occupantPlayer == null) {
			Mockito.when(node.isMarked()).thenReturn(false);
		} else {
			Mockito.when(node.isMarked()).thenReturn(true);
		}
		return node;
	}
}
