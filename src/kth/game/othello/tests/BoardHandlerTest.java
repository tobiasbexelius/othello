package kth.game.othello.tests;

import java.util.ArrayList;
import java.util.List;

import kth.game.othello.BoardHandler;
import kth.game.othello.board.Board;
import kth.game.othello.board.Node;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class BoardHandlerTest {
	
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
