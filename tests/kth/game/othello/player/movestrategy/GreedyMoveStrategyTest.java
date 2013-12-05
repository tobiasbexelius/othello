package kth.game.othello.player.movestrategy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import kth.game.othello.Rules;
import kth.game.othello.board.Board;
import kth.game.othello.board.Node;

import org.junit.Test;

public class GreedyMoveStrategyTest {

	@SuppressWarnings("unchecked")
	@Test
	public void makeValidMove() {
		Rules ruleHandler = mock(Rules.class);
		Board board = mock(Board.class);

		Node node1 = newMockedNode(5, 2, null);
		when(node1.getId()).thenReturn("1");
		Node node2 = newMockedNode(4, 2, null);
		when(node2.getId()).thenReturn("2");
		Node node3 = newMockedNode(4, 3, null);
		when(node3.getId()).thenReturn("3");
		Node node4 = newMockedNode(4, 4, null);
		when(node4.getId()).thenReturn("4");

		List<Node> nodes = new ArrayList<Node>();

		nodes.add(node1);
		nodes.add(node2);
		nodes.add(node3);
		nodes.add(node4);

		ArrayList<Node> someNodes1 = mock(ArrayList.class);
		when(someNodes1.size()).thenReturn(5);
		ArrayList<Node> someNodes2 = mock(ArrayList.class);
		when(someNodes2.size()).thenReturn(7);
		ArrayList<Node> someNodes3 = mock(ArrayList.class);
		when(someNodes3.size()).thenReturn(2);
		ArrayList<Node> someNodes4 = mock(ArrayList.class);
		when(someNodes4.size()).thenReturn(3);

		when(someNodes1.size()).thenReturn(5);
		when(board.getNodes()).thenReturn(nodes);
		when(ruleHandler.isMoveValid(anyString(), anyString())).thenReturn(true);
		when(ruleHandler.getNodesToSwap("player1", "1")).thenReturn(someNodes1);
		when(ruleHandler.getNodesToSwap("player1", "2")).thenReturn(someNodes2);
		when(ruleHandler.getNodesToSwap("player1", "3")).thenReturn(someNodes3);
		when(ruleHandler.getNodesToSwap("player1", "4")).thenReturn(someNodes4);

		MoveStrategy moveStrategy = new GreedyMoveStrategy();
		assertEquals(node2, moveStrategy.move("player1", ruleHandler, board));
	}

	@Test
	public void makeNonValidMove() {
		Rules ruleHandler = mock(Rules.class);
		Board board = mock(Board.class);

		Node node1 = newMockedNode(5, 2, null);
		when(node1.getId()).thenReturn("1");
		Node node2 = newMockedNode(4, 2, null);
		when(node2.getId()).thenReturn("2");
		Node node3 = newMockedNode(4, 3, null);
		when(node3.getId()).thenReturn("3");
		Node node4 = newMockedNode(4, 4, null);
		when(node4.getId()).thenReturn("4");

		List<Node> nodes = new ArrayList<Node>();

		nodes.add(node1);
		nodes.add(node2);
		nodes.add(node3);
		nodes.add(node4);

		when(board.getNodes()).thenReturn(nodes);
		when(ruleHandler.isMoveValid(anyString(), anyString())).thenReturn(false);

		MoveStrategy moveStrategy = new GreedyMoveStrategy();
		assertNull(moveStrategy.move("player1", ruleHandler, board));
	}

	private Node newMockedNode(int x, int y, String occupantPlayer) {
		Node node = mock(Node.class);
		when(node.getXCoordinate()).thenReturn(x);
		when(node.getYCoordinate()).thenReturn(y);
		when(node.getOccupantPlayerId()).thenReturn(occupantPlayer);
		if (occupantPlayer == null) {
			when(node.isMarked()).thenReturn(false);
		} else {
			when(node.isMarked()).thenReturn(true);
		}
		return node;
	}
}
