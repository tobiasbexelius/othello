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

		List<Node> nodes = new ArrayList<Node>();
		nodes.add(node1);
		nodes.add(node2);
		nodes.add(node3);
		
		List<Node> nodesToSwap = new ArrayList<Node>();
		nodesToSwap.add(node1);
		nodesToSwap.add(node2);
		Mockito.when(board.getNodes()).thenReturn(nodes);
		
		BoardHandler boardHandler = new BoardHandler(board);
		
		boardHandler.swapNodes(nodesToSwap, "player");
		Assert.assertTrue(boardHandler.getNode(1,1).isMarked());
		Assert.assertEquals("player", boardHandler.getNode(1,1).getOccupantPlayerId());
		Assert.assertTrue(boardHandler.getNode(1,2).isMarked());
		Assert.assertEquals("player", boardHandler.getNode(1,2).getOccupantPlayerId());
		Assert.assertFalse(boardHandler.getNode(1,3).isMarked());
		Assert.assertNull(boardHandler.getNode(1,3).getOccupantPlayerId());		
	}

	@Test
	public void getNodeWithCoordinatesTest() {
		Board board = Mockito.mock(Board.class);
		
		Node node1 = Mockito.mock(Node.class);
		Mockito.when(node1.getXCoordinate()).thenReturn(1);
		Mockito.when(node1.getYCoordinate()).thenReturn(2);

		Node node2 = Mockito.mock(Node.class);
		Mockito.when(node2.getXCoordinate()).thenReturn(2);
		Mockito.when(node2.getYCoordinate()).thenReturn(2);

		List<Node> nodes = new ArrayList<Node>();
		nodes.add(node1);
		nodes.add(node2);
		Mockito.when(board.getNodes()).thenReturn(nodes);
		
		BoardHandler boardHandler = new BoardHandler(board);
		
		Assert.assertNotNull(boardHandler.getNode(1, 2));
		Assert.assertNotNull(boardHandler.getNode(2, 2));
		Assert.assertNull(boardHandler.getNode(3, 2));
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
