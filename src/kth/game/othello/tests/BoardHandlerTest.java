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
		
		//BoardHandler boardHandler = Mockito.spy(new BoardHandler(board));
		
	}
	
	@Test
	public void occupyNodeTest() {
		Board board = Mockito.mock(Board.class);
		Node node1 = Mockito.mock(Node.class);
		Node node2 = Mockito.mock(Node.class);
		Node node3 = Mockito.mock(Node.class);

		List<Node> nodes = new ArrayList<Node>();
		nodes.add(node1);
		nodes.add(node2);
		Mockito.when(board.getNodes()).thenReturn(nodes);
		
		BoardHandler boardHandler = new BoardHandler(board);
		
		Assert.assertFalse(boardHandler.occupyNode(node3, "player"));
		Assert.assertFalse(board.getNodes().contains(node3));
		
		Assert.assertTrue(board.getNodes().contains(node1));
		Assert.assertTrue(boardHandler.occupyNode(node1, "player"));
		Assert.assertFalse(board.getNodes().contains(node1));
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
}
