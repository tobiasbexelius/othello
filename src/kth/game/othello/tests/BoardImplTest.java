package kth.game.othello.tests;

import java.util.ArrayList;
import java.util.List;

import kth.game.othello.BoardHandler;
import kth.game.othello.board.Board;
import kth.game.othello.board.BoardImpl;
import kth.game.othello.board.Node;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class BoardImplTest {

	@Test
	public void getNodeWithCoordinatesTest() {
		Node node1 = Mockito.mock(Node.class);
		Mockito.when(node1.getXCoordinate()).thenReturn(1);
		Mockito.when(node1.getYCoordinate()).thenReturn(2);

		Node node2 = Mockito.mock(Node.class);
		Mockito.when(node2.getXCoordinate()).thenReturn(2);
		Mockito.when(node2.getYCoordinate()).thenReturn(2);
		
		List<Node> nodes = new ArrayList<Node>();
		nodes.add(node1);
		nodes.add(node2);
		
		Board board = new BoardImpl(nodes);
		
		BoardHandler boardHandler = new BoardHandler(board);
		
		Assert.assertNotNull(boardHandler.getNode(1, 2));
		Assert.assertNotNull(boardHandler.getNode(2, 2));
		Assert.assertNull(boardHandler.getNode(3, 2));
	}

}
