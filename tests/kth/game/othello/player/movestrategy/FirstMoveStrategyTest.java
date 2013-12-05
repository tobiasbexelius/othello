package kth.game.othello.player.movestrategy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import kth.game.othello.Rules;
import kth.game.othello.board.Board;
import kth.game.othello.board.Node;

import org.junit.Test;

public class FirstMoveStrategyTest {

	@Test
	public void testWithValidMoves() {
		Rules ruleHandler = mock(Rules.class);
		Board board = mock(Board.class);
		ArrayList<Node> mockedNodes = new ArrayList<Node>();

		Node node1 = newMockedNode(3, 5, null);
		mockedNodes.add(node1);
		Node node2 = newMockedNode(4, 5, null);
		mockedNodes.add(node2);

		when(board.getNodes()).thenReturn(mockedNodes);
		when(ruleHandler.isMoveValid(anyString(), anyString())).thenReturn(true);
		MoveStrategy moveStrategy = new FirstMoveStrategy();
		Node firstMove = moveStrategy.move("player1", ruleHandler, board);
		Node secondMove = moveStrategy.move("player1", ruleHandler, board);

		assertEquals(node1, firstMove);
		assertEquals(node1, secondMove);
	}

	@Test
	public void testWithNoValidMove() {
		Rules ruleHandler = mock(Rules.class);
		Board board = mock(Board.class);
		ArrayList<Node> mockedNodes = new ArrayList<Node>();

		Node node1 = newMockedNode(3, 5, "player1");
		mockedNodes.add(node1);
		Node node2 = newMockedNode(4, 5, "player2");
		mockedNodes.add(node2);

		when(board.getNodes()).thenReturn(mockedNodes);
		when(ruleHandler.isMoveValid(anyString(), anyString())).thenReturn(true);
		MoveStrategy moveStrategy = new FirstMoveStrategy();
		Node firstMove = moveStrategy.move("player1", ruleHandler, board);
		Node secondMove = moveStrategy.move("player1", ruleHandler, board);

		assertNull(firstMove);
		assertNull(secondMove);

	}

	private Node newMockedNode(int x, int y, String occupantPLayer) {
		Node node = mock(Node.class);
		when(node.getXCoordinate()).thenReturn(x);
		when(node.getYCoordinate()).thenReturn(y);
		when(node.getOccupantPlayerId()).thenReturn(occupantPLayer);
		if (occupantPLayer == null) {
			when(node.isMarked()).thenReturn(false);
		} else {
			when(node.isMarked()).thenReturn(true);
		}
		return node;
	}
}
