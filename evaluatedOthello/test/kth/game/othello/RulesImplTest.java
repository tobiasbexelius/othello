package kth.game.othello;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import kth.game.othello.board.BoardImpl;
import kth.game.othello.board.Node;
import kth.game.othello.board.NodeImpl;

import org.junit.Test;

/**
 * author Lina & Oskar
 */
public class RulesImplTest {
	@Test
	public void testGetNodesToSwap_ValidMove() throws Exception {
		String testPlayer = "test";
		String testNodeId = "3,3";
		List<Node> nodes = new ArrayList<>();

		Node mockNode0 = mock(NodeImpl.class);
		when(mockNode0.getId()).thenReturn("0,0");
		when(mockNode0.getXCoordinate()).thenReturn(0);
		when(mockNode0.getYCoordinate()).thenReturn(0);
		when(mockNode0.isMarked()).thenReturn(true);
		when(mockNode0.getOccupantPlayerId()).thenReturn(testPlayer);
		nodes.add(mockNode0);

		Node mockNode1 = mock(NodeImpl.class);
		when(mockNode1.getId()).thenReturn("1,1");
		when(mockNode1.getXCoordinate()).thenReturn(1);
		when(mockNode1.getYCoordinate()).thenReturn(1);
		when(mockNode1.isMarked()).thenReturn(true);
		when(mockNode1.getOccupantPlayerId()).thenReturn("other");
		nodes.add(mockNode1);

		Node mockNode2 = mock(NodeImpl.class);
		when(mockNode2.getId()).thenReturn("2,2");
		when(mockNode2.getXCoordinate()).thenReturn(2);
		when(mockNode2.getYCoordinate()).thenReturn(2);
		when(mockNode2.isMarked()).thenReturn(true);
		when(mockNode2.getOccupantPlayerId()).thenReturn("other");
		nodes.add(mockNode2);

		Node mockNode3 = mock(NodeImpl.class);
		when(mockNode3.getId()).thenReturn("3,3");
		when(mockNode3.getXCoordinate()).thenReturn(3);
		when(mockNode3.getYCoordinate()).thenReturn(3);
		when(mockNode3.isMarked()).thenReturn(false);
		nodes.add(mockNode3);

		BoardImpl boardImpl = mock(BoardImpl.class);
		when(boardImpl.getNodes()).thenReturn(nodes);

		when(boardImpl.hasNode("0,0")).thenReturn(true);
		when(boardImpl.hasNode("1,1")).thenReturn(true);
		when(boardImpl.hasNode("2,2")).thenReturn(true);
		when(boardImpl.hasNode("3,3")).thenReturn(true);
		when(boardImpl.hasNode("4,4")).thenReturn(false);

		when(boardImpl.hasNode(0, 0)).thenReturn(true);
		when(boardImpl.hasNode(1, 1)).thenReturn(true);
		when(boardImpl.hasNode(2, 2)).thenReturn(true);
		when(boardImpl.hasNode(3, 3)).thenReturn(true);
		when(boardImpl.hasNode(4, 4)).thenReturn(false);

		when(boardImpl.getNodeById("0,0")).thenReturn(mockNode0);
		when(boardImpl.getNodeById("1,1")).thenReturn(mockNode1);
		when(boardImpl.getNodeById("2,2")).thenReturn(mockNode2);
		when(boardImpl.getNodeById("3,3")).thenReturn(mockNode3);
		when(boardImpl.getNodeById(null)).thenReturn(null);

		when(boardImpl.getNode(0, 0)).thenReturn(mockNode0);
		when(boardImpl.getNode(1, 1)).thenReturn(mockNode1);
		when(boardImpl.getNode(2, 2)).thenReturn(mockNode2);
		when(boardImpl.getNode(3, 3)).thenReturn(mockNode3);
		when(boardImpl.getNode(4, 4)).thenReturn(null);

		RulesImpl rules = new RulesImpl(boardImpl);

		List<Node> result = rules.getNodesToSwap(testPlayer, testNodeId);
		List<Node> expected = new ArrayList<>();
		expected.add(mockNode2);
		expected.add(mockNode1);

		assertEquals(expected, result);
	}

	@Test
	public void testGetNodesToSwap_InvalidMove() throws Exception {
		BoardImpl board = mock(BoardImpl.class);
		String mockPlayerId = null;
		String mockNodeId = "1, 1";

		Rules rules = new RulesImpl(board);
		List<Node> result = rules.getNodesToSwap(mockPlayerId, mockNodeId);
		ArrayList<Node> expected = new ArrayList<Node>();
		assertEquals(result, expected);
	}

	@Test
	public void testHasValidMove_True1() throws Exception {

		String testPlayer = "test";
		List<Node> nodes = new ArrayList<>();

		Node mockNode0 = mock(NodeImpl.class);
		when(mockNode0.getId()).thenReturn("0,0");
		when(mockNode0.getXCoordinate()).thenReturn(0);
		when(mockNode0.getYCoordinate()).thenReturn(0);
		when(mockNode0.isMarked()).thenReturn(true);
		when(mockNode0.getOccupantPlayerId()).thenReturn(testPlayer);
		nodes.add(mockNode0);

		Node mockNode1 = mock(NodeImpl.class);
		when(mockNode1.getId()).thenReturn("1,1");
		when(mockNode1.getXCoordinate()).thenReturn(1);
		when(mockNode1.getYCoordinate()).thenReturn(1);
		when(mockNode1.isMarked()).thenReturn(true);
		when(mockNode1.getOccupantPlayerId()).thenReturn("other");
		nodes.add(mockNode1);

		Node mockNode2 = mock(NodeImpl.class);
		when(mockNode2.getId()).thenReturn("2,2");
		when(mockNode2.getXCoordinate()).thenReturn(2);
		when(mockNode2.getYCoordinate()).thenReturn(2);
		when(mockNode2.isMarked()).thenReturn(false);
		when(mockNode2.getOccupantPlayerId()).thenReturn(null);
		nodes.add(mockNode2);

		Node mockNode3 = mock(NodeImpl.class);
		when(mockNode3.getId()).thenReturn("3,3");
		when(mockNode3.getXCoordinate()).thenReturn(3);
		when(mockNode3.getYCoordinate()).thenReturn(3);
		when(mockNode3.isMarked()).thenReturn(false);
		when(mockNode3.getOccupantPlayerId()).thenReturn(null);
		nodes.add(mockNode3);

		BoardImpl board = mock(BoardImpl.class);
		when(board.getNodes()).thenReturn(nodes);

		when(board.hasNode("0,0")).thenReturn(true);
		when(board.hasNode("1,1")).thenReturn(true);
		when(board.hasNode("2,2")).thenReturn(true);
		when(board.hasNode("3,3")).thenReturn(true);

		when(board.hasNode(0, 0)).thenReturn(true);
		when(board.hasNode(1, 1)).thenReturn(true);
		when(board.hasNode(2, 2)).thenReturn(true);
		when(board.hasNode(3, 3)).thenReturn(true);

		when(board.getNodeById("0,0")).thenReturn(mockNode0);
		when(board.getNodeById("1,1")).thenReturn(mockNode1);
		when(board.getNodeById("2,2")).thenReturn(mockNode2);
		when(board.getNodeById("3,3")).thenReturn(mockNode3);

		when(board.getNode(0, 0)).thenReturn(mockNode0);
		when(board.getNode(1, 1)).thenReturn(mockNode1);
		when(board.getNode(2, 2)).thenReturn(mockNode2);
		when(board.getNode(3, 3)).thenReturn(mockNode3);
		RulesImpl rules = new RulesImpl(board);

		assertTrue(rules.hasValidMove(testPlayer));
	}

	@Test
	public void testHasValidMove_True2() throws Exception {
		String testPlayer = "test";
		List<Node> nodes = new ArrayList<>();

		Node mockNode0 = mock(NodeImpl.class);
		when(mockNode0.getId()).thenReturn("0,0");
		when(mockNode0.getXCoordinate()).thenReturn(0);
		when(mockNode0.getYCoordinate()).thenReturn(0);
		when(mockNode0.isMarked()).thenReturn(true);
		when(mockNode0.getOccupantPlayerId()).thenReturn(testPlayer);
		nodes.add(mockNode0);

		Node mockNode1 = mock(NodeImpl.class);
		when(mockNode1.getId()).thenReturn("1,1");
		when(mockNode1.getXCoordinate()).thenReturn(1);
		when(mockNode1.getYCoordinate()).thenReturn(1);
		when(mockNode1.isMarked()).thenReturn(true);
		when(mockNode1.getOccupantPlayerId()).thenReturn("other");
		nodes.add(mockNode1);

		Node mockNode2 = mock(NodeImpl.class);
		when(mockNode2.getId()).thenReturn("2,2");
		when(mockNode2.getXCoordinate()).thenReturn(2);
		when(mockNode2.getYCoordinate()).thenReturn(2);
		when(mockNode2.isMarked()).thenReturn(true);
		when(mockNode2.getOccupantPlayerId()).thenReturn("other");
		nodes.add(mockNode2);

		Node mockNode3 = mock(NodeImpl.class);
		when(mockNode3.getId()).thenReturn("3,3");
		when(mockNode3.getXCoordinate()).thenReturn(3);
		when(mockNode3.getYCoordinate()).thenReturn(3);
		when(mockNode3.isMarked()).thenReturn(false);
		when(mockNode3.getOccupantPlayerId()).thenReturn(null);
		nodes.add(mockNode3);

		BoardImpl board = mock(BoardImpl.class);
		when(board.getNodes()).thenReturn(nodes);

		when(board.hasNode("0,0")).thenReturn(true);
		when(board.hasNode("1,1")).thenReturn(true);
		when(board.hasNode("2,2")).thenReturn(true);
		when(board.hasNode("3,3")).thenReturn(true);

		when(board.hasNode(0, 0)).thenReturn(true);
		when(board.hasNode(1, 1)).thenReturn(true);
		when(board.hasNode(2, 2)).thenReturn(true);
		when(board.hasNode(3, 3)).thenReturn(true);

		when(board.getNodeById("0,0")).thenReturn(mockNode0);
		when(board.getNodeById("1,1")).thenReturn(mockNode1);
		when(board.getNodeById("2,2")).thenReturn(mockNode2);
		when(board.getNodeById("3,3")).thenReturn(mockNode3);

		when(board.getNode(0, 0)).thenReturn(mockNode0);
		when(board.getNode(1, 1)).thenReturn(mockNode1);
		when(board.getNode(2, 2)).thenReturn(mockNode2);
		when(board.getNode(3, 3)).thenReturn(mockNode3);
		RulesImpl rules = new RulesImpl(board);

		assertTrue(rules.hasValidMove(testPlayer));
	}

	@Test
	public void testHasValidMove_False() throws Exception {

		String testPlayer = "test";
		List<Node> nodes = new ArrayList<>();

		Node mockNode0 = mock(NodeImpl.class);
		when(mockNode0.getId()).thenReturn("0,0");
		when(mockNode0.getXCoordinate()).thenReturn(0);
		when(mockNode0.getYCoordinate()).thenReturn(0);
		when(mockNode0.isMarked()).thenReturn(true);
		when(mockNode0.getOccupantPlayerId()).thenReturn(testPlayer);
		nodes.add(mockNode0);

		Node mockNode1 = mock(NodeImpl.class);
		when(mockNode1.getId()).thenReturn("1,1");
		when(mockNode1.getXCoordinate()).thenReturn(1);
		when(mockNode1.getYCoordinate()).thenReturn(1);
		when(mockNode1.isMarked()).thenReturn(true);
		when(mockNode1.getOccupantPlayerId()).thenReturn("other");
		nodes.add(mockNode1);

		Node mockNode2 = mock(NodeImpl.class);
		when(mockNode2.getId()).thenReturn("2,2");
		when(mockNode2.getXCoordinate()).thenReturn(2);
		when(mockNode2.getYCoordinate()).thenReturn(2);
		when(mockNode2.isMarked()).thenReturn(true);
		when(mockNode2.getOccupantPlayerId()).thenReturn("other");
		nodes.add(mockNode2);

		Node mockNode3 = mock(NodeImpl.class);
		when(mockNode3.getId()).thenReturn("3,3");
		when(mockNode3.getXCoordinate()).thenReturn(3);
		when(mockNode3.getYCoordinate()).thenReturn(3);
		when(mockNode3.isMarked()).thenReturn(true);
		when(mockNode3.getOccupantPlayerId()).thenReturn(testPlayer);
		nodes.add(mockNode3);

		BoardImpl board = mock(BoardImpl.class);
		when(board.getNodes()).thenReturn(nodes);

		when(board.hasNode("0,0")).thenReturn(true);
		when(board.hasNode("1,1")).thenReturn(true);
		when(board.hasNode("2,2")).thenReturn(true);
		when(board.hasNode("3,3")).thenReturn(true);
		when(board.hasNode("4,4")).thenReturn(false);

		when(board.hasNode(0, 0)).thenReturn(true);
		when(board.hasNode(1, 1)).thenReturn(true);
		when(board.hasNode(2, 2)).thenReturn(true);
		when(board.hasNode(3, 3)).thenReturn(true);
		when(board.hasNode(4, 4)).thenReturn(false);

		when(board.getNodeById("0,0")).thenReturn(mockNode0);
		when(board.getNodeById("1,1")).thenReturn(mockNode1);
		when(board.getNodeById("2,2")).thenReturn(mockNode2);
		when(board.getNodeById("3,3")).thenReturn(mockNode3);
		when(board.getNodeById(null)).thenReturn(null);

		when(board.getNode(0, 0)).thenReturn(mockNode0);
		when(board.getNode(1, 1)).thenReturn(mockNode1);
		when(board.getNode(2, 2)).thenReturn(mockNode2);
		when(board.getNode(3, 3)).thenReturn(mockNode3);
		when(board.getNode(4, 4)).thenReturn(null);

		RulesImpl rules = new RulesImpl(board);

		assertFalse(rules.hasValidMove(testPlayer));
	}

	@Test
	public void testIsMoveValid_True() throws Exception {
		String testPlayer = "test";
		String testNodeId = "3,3";
		List<Node> nodes = new ArrayList<>();

		Node mockNode0 = mock(NodeImpl.class);
		when(mockNode0.getId()).thenReturn("0,0");
		when(mockNode0.getXCoordinate()).thenReturn(0);
		when(mockNode0.getYCoordinate()).thenReturn(0);
		when(mockNode0.isMarked()).thenReturn(true);
		when(mockNode0.getOccupantPlayerId()).thenReturn(testPlayer);
		nodes.add(mockNode0);

		Node mockNode1 = mock(NodeImpl.class);
		when(mockNode1.getId()).thenReturn("1,1");
		when(mockNode1.getXCoordinate()).thenReturn(1);
		when(mockNode1.getYCoordinate()).thenReturn(1);
		when(mockNode1.isMarked()).thenReturn(true);
		when(mockNode1.getOccupantPlayerId()).thenReturn("other");
		nodes.add(mockNode1);

		Node mockNode2 = mock(NodeImpl.class);
		when(mockNode2.getId()).thenReturn("2,2");
		when(mockNode2.getXCoordinate()).thenReturn(2);
		when(mockNode2.getYCoordinate()).thenReturn(2);
		when(mockNode2.isMarked()).thenReturn(true);
		when(mockNode2.getOccupantPlayerId()).thenReturn("other");
		nodes.add(mockNode2);

		Node mockNode3 = mock(NodeImpl.class);
		when(mockNode3.getId()).thenReturn("3,3");
		when(mockNode3.getXCoordinate()).thenReturn(3);
		when(mockNode3.getYCoordinate()).thenReturn(3);
		when(mockNode3.isMarked()).thenReturn(false);
		// when(mockNode3.getOccupantPlayerId()).thenReturn(null);
		nodes.add(mockNode3);

		BoardImpl boardImpl = mock(BoardImpl.class);
		when(boardImpl.getNodes()).thenReturn(nodes);

		when(boardImpl.hasNode("0,0")).thenReturn(true);
		when(boardImpl.hasNode("1,1")).thenReturn(true);
		when(boardImpl.hasNode("2,2")).thenReturn(true);
		when(boardImpl.hasNode("3,3")).thenReturn(true);
		when(boardImpl.hasNode("4,4")).thenReturn(false);

		when(boardImpl.hasNode(0, 0)).thenReturn(true);
		when(boardImpl.hasNode(1, 1)).thenReturn(true);
		when(boardImpl.hasNode(2, 2)).thenReturn(true);
		when(boardImpl.hasNode(3, 3)).thenReturn(true);
		when(boardImpl.hasNode(4, 4)).thenReturn(false);

		when(boardImpl.getNodeById("0,0")).thenReturn(mockNode0);
		when(boardImpl.getNodeById("1,1")).thenReturn(mockNode1);
		when(boardImpl.getNodeById("2,2")).thenReturn(mockNode2);
		when(boardImpl.getNodeById("3,3")).thenReturn(mockNode3);
		when(boardImpl.getNodeById(null)).thenReturn(null);

		when(boardImpl.getNode(0, 0)).thenReturn(mockNode0);
		when(boardImpl.getNode(1, 1)).thenReturn(mockNode1);
		when(boardImpl.getNode(2, 2)).thenReturn(mockNode2);
		when(boardImpl.getNode(3, 3)).thenReturn(mockNode3);
		when(boardImpl.getNode(4, 4)).thenReturn(null);

		RulesImpl rules = new RulesImpl(boardImpl);

		assertTrue(rules.isMoveValid(testPlayer, testNodeId));
	}

	@Test
	public void testIsMoveValid_FalseWhenOccupied() throws Exception {
		String testPlayer = "test";
		String testNodeId = "3,3";
		List<Node> nodes = new ArrayList<>();

		Node mockNode0 = mock(NodeImpl.class);
		when(mockNode0.getId()).thenReturn("0,0");
		when(mockNode0.getXCoordinate()).thenReturn(0);
		when(mockNode0.getYCoordinate()).thenReturn(0);
		when(mockNode0.isMarked()).thenReturn(true);
		when(mockNode0.getOccupantPlayerId()).thenReturn(testPlayer);
		nodes.add(mockNode0);

		Node mockNode1 = mock(NodeImpl.class);
		when(mockNode1.getId()).thenReturn("1,1");
		when(mockNode1.getXCoordinate()).thenReturn(1);
		when(mockNode1.getYCoordinate()).thenReturn(1);
		when(mockNode1.isMarked()).thenReturn(true);
		when(mockNode1.getOccupantPlayerId()).thenReturn("other");
		nodes.add(mockNode1);

		Node mockNode2 = mock(NodeImpl.class);
		when(mockNode2.getId()).thenReturn("2,2");
		when(mockNode2.getXCoordinate()).thenReturn(2);
		when(mockNode2.getYCoordinate()).thenReturn(2);
		when(mockNode2.isMarked()).thenReturn(true);
		when(mockNode2.getOccupantPlayerId()).thenReturn("other");
		nodes.add(mockNode2);

		Node mockNode3 = mock(NodeImpl.class);
		when(mockNode3.getId()).thenReturn("3,3");
		when(mockNode3.getXCoordinate()).thenReturn(3);
		when(mockNode3.getYCoordinate()).thenReturn(3);
		when(mockNode3.isMarked()).thenReturn(true);
		when(mockNode3.getOccupantPlayerId()).thenReturn("other");
		nodes.add(mockNode3);

		BoardImpl board = mock(BoardImpl.class);
		when(board.getNodes()).thenReturn(nodes);

		when(board.hasNode("0,0")).thenReturn(true);
		when(board.hasNode("1,1")).thenReturn(true);
		when(board.hasNode("2,2")).thenReturn(true);
		when(board.hasNode("3,3")).thenReturn(true);
		when(board.hasNode("4,4")).thenReturn(false);

		when(board.hasNode(0, 0)).thenReturn(true);
		when(board.hasNode(1, 1)).thenReturn(true);
		when(board.hasNode(2, 2)).thenReturn(true);
		when(board.hasNode(3, 3)).thenReturn(true);
		when(board.hasNode(4, 4)).thenReturn(false);

		when(board.getNodeById("0,0")).thenReturn(mockNode0);
		when(board.getNodeById("1,1")).thenReturn(mockNode1);
		when(board.getNodeById("2,2")).thenReturn(mockNode2);
		when(board.getNodeById("3,3")).thenReturn(mockNode3);
		when(board.getNodeById(null)).thenReturn(null);

		when(board.getNode(0, 0)).thenReturn(mockNode0);
		when(board.getNode(1, 1)).thenReturn(mockNode1);
		when(board.getNode(2, 2)).thenReturn(mockNode2);
		when(board.getNode(3, 3)).thenReturn(mockNode3);
		when(board.getNode(4, 4)).thenReturn(null);

		RulesImpl rules = new RulesImpl(board);

		assertFalse(rules.isMoveValid(testPlayer, testNodeId));
	}
}
