package kth.game.othello.board;

import java.util.ArrayList;
import java.util.List;

import kth.game.othello.tests.MockCreator;

import org.junit.Assert;
import org.junit.Test;

public class BoardImplTest {

	@Test
	public void getExistingNodeWithCoordinates() {
		Node node1 = MockCreator.createMockedNode(1, 2);
		Node node2 = MockCreator.createMockedNode(2, 2);

		List<Node> nodes = new ArrayList<Node>();
		nodes.add(node1);
		nodes.add(node2);

		Board board = new BoardImpl(nodes);

		Assert.assertNotNull(board.getNode(1, 2));
		Assert.assertNotNull(board.getNode(2, 2));
	}

	@Test
	public void getNonExistingNodeWithCoordinates() {
		Node node1 = MockCreator.createMockedNode(1, 2);
		Node node2 = MockCreator.createMockedNode(2, 2);

		List<Node> nodes = new ArrayList<Node>();
		nodes.add(node1);
		nodes.add(node2);

		Board board = new BoardImpl(nodes);

		Assert.assertNull(board.getNode(3, 2));
	}

	@Test
	public void hasNodeWithExistingNode() {
		Node node1 = MockCreator.createMockedNode(1, 2);
		Node node2 = MockCreator.createMockedNode(2, 2);

		List<Node> nodes = new ArrayList<Node>();
		nodes.add(node1);
		nodes.add(node2);

		Board board = new BoardImpl(nodes);
		Assert.assertTrue(board.hasNode(1, 2));
	}

	@Test
	public void hasNodeWithNonExistingNode() {
		Node node1 = MockCreator.createMockedNode(1, 2);
		Node node2 = MockCreator.createMockedNode(2, 2);

		List<Node> nodes = new ArrayList<Node>();
		nodes.add(node1);
		nodes.add(node2);

		Board board = new BoardImpl(nodes);
		Assert.assertFalse(board.hasNode(3, 2));
	}

	@Test
	public void getMaxX() {
		Node node1 = MockCreator.createMockedNode(4, 2);
		Node node2 = MockCreator.createMockedNode(3, 2);
		Node node3 = MockCreator.createMockedNode(1, 2);

		List<Node> nodes = new ArrayList<Node>();
		nodes.add(node1);
		nodes.add(node2);
		nodes.add(node3);
		Board board = new BoardImpl(nodes);

		Assert.assertEquals(4, board.getMaxX());
	}

	@Test
	public void getMaxY() {
		Node node1 = MockCreator.createMockedNode(4, 3);
		Node node2 = MockCreator.createMockedNode(1, 4);
		Node node3 = MockCreator.createMockedNode(3, 1);

		List<Node> nodes = new ArrayList<Node>();
		nodes.add(node1);
		nodes.add(node2);
		nodes.add(node3);
		Board board = new BoardImpl(nodes);

		Assert.assertEquals(4, board.getMaxY());
	}

}
