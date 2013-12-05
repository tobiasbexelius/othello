package kth.game.othello.board;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class BoardImplTest {

	@Test
	public void getExistingNodeWithCoordinates() {
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

		Assert.assertNotNull(board.getNode(1, 2));
		Assert.assertNotNull(board.getNode(2, 2));
	}

	@Test
	public void getNonExistingNodeWithCoordinates() {
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

		Assert.assertNull(board.getNode(3, 2));
	}

	@Test
	public void hasNodeWithExistingNode() {
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
		Assert.assertTrue(board.hasNode(1, 2));
	}

	@Test
	public void hasNodeWithNonExistingNode() {
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
		Assert.assertFalse(board.hasNode(3, 2));
	}

	@Test
	public void getMaxX() {
		Node node1 = Mockito.mock(Node.class);
		Mockito.when(node1.getXCoordinate()).thenReturn(4);
		Mockito.when(node1.getYCoordinate()).thenReturn(2);

		Node node2 = Mockito.mock(Node.class);
		Mockito.when(node2.getXCoordinate()).thenReturn(3);
		Mockito.when(node2.getYCoordinate()).thenReturn(2);

		Node node3 = Mockito.mock(Node.class);
		Mockito.when(node3.getXCoordinate()).thenReturn(1);
		Mockito.when(node3.getYCoordinate()).thenReturn(2);

		List<Node> nodes = new ArrayList<Node>();
		nodes.add(node1);
		nodes.add(node2);
		nodes.add(node3);
		Board board = new BoardImpl(nodes);

		Assert.assertEquals(4, board.getMaxX());
	}

	@Test
	public void getMaxY() {
		Node node1 = Mockito.mock(Node.class);
		Mockito.when(node1.getXCoordinate()).thenReturn(4);
		Mockito.when(node1.getYCoordinate()).thenReturn(1);

		Node node2 = Mockito.mock(Node.class);
		Mockito.when(node2.getXCoordinate()).thenReturn(3);
		Mockito.when(node2.getYCoordinate()).thenReturn(4);

		Node node3 = Mockito.mock(Node.class);
		Mockito.when(node3.getXCoordinate()).thenReturn(1);
		Mockito.when(node3.getYCoordinate()).thenReturn(3);

		List<Node> nodes = new ArrayList<Node>();
		nodes.add(node1);
		nodes.add(node2);
		nodes.add(node3);
		Board board = new BoardImpl(nodes);

		Assert.assertEquals(4, board.getMaxY());
	}

}
