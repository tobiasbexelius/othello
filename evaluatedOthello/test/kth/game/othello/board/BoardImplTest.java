package kth.game.othello.board;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class BoardImplTest {
	@Test
	public void testGetNode_ExistingNode() throws Exception {
		List<NodeImpl> nodeImpls = new ArrayList<>();
		for (Integer i = 0; i < 3; i++) {
			NodeImpl mockNodeImpl = mock(NodeImpl.class);
			when(mockNodeImpl.getXCoordinate()).thenReturn(i);
			when(mockNodeImpl.getYCoordinate()).thenReturn(i);
			when(mockNodeImpl.getId()).thenReturn(i.toString() + ", " + i.toString());

			nodeImpls.add(mockNodeImpl);
		}

		BoardImpl board = new BoardImpl(nodeImpls);
		Node result = board.getNode(2, 2);
		Node expected = nodeImpls.get(2);

		assertEquals(expected, result);
	}

	@Test
	public void testGetNode_NonExistingNode() throws Exception {

		List<NodeImpl> nodeImpls = new ArrayList<>();

		for (Integer i = 0; i < 3; i++) {
			NodeImpl mockNodeImpl = mock(NodeImpl.class);
			when(mockNodeImpl.getXCoordinate()).thenReturn(i);
			when(mockNodeImpl.getYCoordinate()).thenReturn(i);
			nodeImpls.add(mockNodeImpl);
		}

		BoardImpl board = new BoardImpl(nodeImpls);
		Node result = board.getNode(3, 3);

		assertNull(result);
	}

	@Test
	public void testGetNodes() throws Exception {

		List<NodeImpl> nodeImpls = new ArrayList<>();
		List<Node> nodes = new ArrayList<>();

		for (Integer i = 0; i < 3; i++) {
			NodeImpl mockNodeImpl = mock(NodeImpl.class);
			when(mockNodeImpl.getId()).thenReturn(i.toString() + ", " + i.toString());
			nodeImpls.add(mockNodeImpl);
			nodes.add(mockNodeImpl);
		}

		BoardImpl board = new BoardImpl(nodeImpls);
		List<Node> result = board.getNodes();

		assertEquals(nodes, result);
	}

	@Test
	public void testGetNodeById_ExistingNode() throws Exception {

		List<NodeImpl> nodeImpls = new ArrayList<>();

		for (Integer i = 0; i < 3; i++) {
			NodeImpl mockNodeImpl = mock(NodeImpl.class);
			when(mockNodeImpl.getXCoordinate()).thenReturn(i);
			when(mockNodeImpl.getYCoordinate()).thenReturn(i);
			when(mockNodeImpl.getId()).thenReturn(i.toString() + ", " + i.toString());
			nodeImpls.add(mockNodeImpl);
		}

		BoardImpl board = new BoardImpl(nodeImpls);
		Node actualNode = board.getNodeById("1, 1");

		assertEquals(actualNode, nodeImpls.get(1));
	}

	@Test
	public void testGetNodeById_NonExistingNode() throws Exception {

		String nonExistingId = "test";
		List<NodeImpl> nodeImpls = new ArrayList<>();

		for (Integer i = 0; i < 3; i++) {
			NodeImpl mockNodeImpl = mock(NodeImpl.class);
			when(mockNodeImpl.getXCoordinate()).thenReturn(i);
			when(mockNodeImpl.getYCoordinate()).thenReturn(i);
			when(mockNodeImpl.getId()).thenReturn(i.toString() + ", " + i.toString());
			nodeImpls.add(mockNodeImpl);
		}

		BoardImpl board = new BoardImpl(nodeImpls);
		Node actualNode = board.getNodeById(nonExistingId);

		assertNull(actualNode);
	}

	@Test
	public void testHasNode_Existing_Node() {

		List<NodeImpl> nodeImpls = new ArrayList<>();

		for (Integer i = 0; i < 3; i++) {
			NodeImpl mockNodeImpl = mock(NodeImpl.class);
			when(mockNodeImpl.getId()).thenReturn(i.toString() + ", " + i.toString());
			when(mockNodeImpl.getXCoordinate()).thenReturn(i);
			when(mockNodeImpl.getYCoordinate()).thenReturn(i);

			nodeImpls.add(mockNodeImpl);
		}

		BoardImpl board = new BoardImpl(nodeImpls);
		boolean actual = board.hasNode(2, 2);
		assertTrue(actual);
	}

	@Test
	public void testHasNode_NonExistingNode() {

		List<NodeImpl> nodeImpls = new ArrayList<>();

		for (Integer i = 0; i < 3; i++) {
			NodeImpl mockNodeImpl = mock(NodeImpl.class);
			when(mockNodeImpl.getId()).thenReturn(i.toString() + ", " + i.toString());
			when(mockNodeImpl.getXCoordinate()).thenReturn(i);
			when(mockNodeImpl.getYCoordinate()).thenReturn(i);

			nodeImpls.add(mockNodeImpl);
		}

		BoardImpl board = new BoardImpl(nodeImpls);
		boolean actual = board.hasNode(2, 3);

		assertFalse(actual);
	}

	@Test
	public void testGetMaxXandY() {

		List<NodeImpl> nodeImpls = new ArrayList<>();

		for (Integer i = 0; i < 3; i++) {
			NodeImpl mockNodeImpl = mock(NodeImpl.class);
			when(mockNodeImpl.getId()).thenReturn(i.toString() + ", " + i.toString());
			when(mockNodeImpl.getXCoordinate()).thenReturn(i);
			when(mockNodeImpl.getYCoordinate()).thenReturn(i);
			nodeImpls.add(mockNodeImpl);
		}

		BoardImpl board = new BoardImpl(nodeImpls);

		assertEquals(board.getMaxX(), 2);
		assertEquals(board.getMaxY(), 2);
	}
}
