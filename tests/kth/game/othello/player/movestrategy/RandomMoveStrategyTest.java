package kth.game.othello.player.movestrategy;

import static org.junit.Assert.assertNotNull;
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

public class RandomMoveStrategyTest {

	@Test
	public void makeValidMove() {
		Rules ruleHandler = mock(Rules.class);
		Board board = mock(Board.class);

		Node node1 = newMockedNode(5, 2, null);
		Node node2 = newMockedNode(4, 2, null);

		List<Node> nodes = new ArrayList<Node>();

		nodes.add(node1);
		nodes.add(node2);

		when(board.getNodes()).thenReturn(nodes);
		when(ruleHandler.isMoveValid(anyString(), anyString())).thenReturn(true);

		MoveStrategy moveStrategy = new RandomMoveStrategy();
		assertNotNull(moveStrategy.move("player2", ruleHandler, board));
		assertNotNull(moveStrategy.move("player2", ruleHandler, board));
	}

	@Test
	public void tryToMoveWhenNoValidMoveExists() {
		Rules ruleHandler = mock(Rules.class);
		Board board = mock(Board.class);

		Node node1 = newMockedNode(5, 2, null);
		Node node2 = newMockedNode(4, 2, null);

		List<Node> nodes = new ArrayList<Node>();

		nodes.add(node1);
		nodes.add(node2);

		when(board.getNodes()).thenReturn(nodes);
		when(ruleHandler.isMoveValid(anyString(), anyString())).thenReturn(false);

		MoveStrategy moveStrategy = new RandomMoveStrategy();
		assertNull(moveStrategy.move("player1", ruleHandler, board));
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
