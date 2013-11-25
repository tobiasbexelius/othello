package kth.game.othello.tests;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import kth.game.othello.BoardHandler;
import kth.game.othello.board.Board;
import kth.game.othello.board.Node;

import org.junit.Test;
import org.mockito.Mockito;

public class BoardHandlerTest {
	
	@Test
	public void swapNodesTest() {
		Board board = Mockito.mock(Board.class);
		Node node1 = Mockito.mock(Node.class);
		Mockito.when(node1.getXCoordinate()).thenReturn(1);
		Mockito.when(node1.getYCoordinate()).thenReturn(1);

		Node node2 = Mockito.mock(Node.class);
		Mockito.when(node2.getXCoordinate()).thenReturn(1);
		Mockito.when(node2.getYCoordinate()).thenReturn(2);

		Node node3 = Mockito.mock(Node.class);
		Mockito.when(node3.getXCoordinate()).thenReturn(1);
		Mockito.when(node3.getYCoordinate()).thenReturn(3);
		
		List<Node> nodesToSwap = new ArrayList<Node>();
		nodesToSwap.add(node1);
		nodesToSwap.add(node2);
		
		List<Node> nodes = new ArrayList<Node>();
		nodes.add(node1);
		nodes.add(node2);
		nodes.add(node3);
		
		Mockito.when(board.getNodes()).thenReturn(nodes);
		
		BoardHandler boardHandler = new BoardHandler(board);
		
		boardHandler.swapNodes(nodesToSwap, "player");
		Assert.assertTrue(nodes.get(0).isMarked());
		Assert.assertEquals("player", nodes.get(0).getOccupantPlayerId());
		Assert.assertTrue(nodes.get(1).isMarked());
		Assert.assertEquals("player", nodes.get(1).getOccupantPlayerId());
		Assert.assertFalse(nodes.get(2).isMarked());
		Assert.assertNull(nodes.get(2).getOccupantPlayerId());		
	}
	
	@Test
	public void getNodeWithIdTest() {
		Board board = Mockito.mock(Board.class);
		
		Node node1 = Mockito.mock(Node.class);
		Mockito.when(node1.getId()).thenReturn("1");

		Node node2 = Mockito.mock(Node.class);
		Mockito.when(node2.getId()).thenReturn("2");

		List<Node> nodes = new ArrayList<Node>();
		nodes.add(node1);
		nodes.add(node2);
		Mockito.when(board.getNodes()).thenReturn(nodes);
		
		BoardHandler boardHandler = new BoardHandler(board);
		
		Assert.assertNotNull(boardHandler.getNode("2"));
		Assert.assertNotNull(boardHandler.getNode("1"));
		Assert.assertNull(boardHandler.getNode("3"));
	}
}
