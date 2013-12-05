package kth.game.othello.board;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import kth.game.othello.BoardHandler;
import kth.game.othello.board.Board;
import kth.game.othello.board.BoardCreatorImpl;
import kth.game.othello.board.Node;
import kth.game.othello.board.NodeCreator;
import kth.game.othello.board.factory.Castle;
import kth.game.othello.tests.MockCreator;

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

	@Test
	public void testBoardContainsNoDuplicates() {
		NodeCreator nodeCreator = mock(NodeCreator.class);
		int boardSize = 7;
		for (int i = 0; i <= boardSize; i++) {
			for (int j = 0; j <= boardSize; j++) {
				Node mockedNode = MockCreator.createMockedNode(i, j);
				when(nodeCreator.createNodeWithCoordinate(i, j)).thenReturn(mockedNode);
				mockedNode = MockCreator.createMockedNode(i, j, "playerId");
				when(nodeCreator.createNodeWithCoordinateAndOccupant(Mockito.eq(i), Mockito.eq(j), Mockito.anyString()))
						.thenReturn(mockedNode);
			}
		}

		Castle castle = new Castle(nodeCreator, new BoardCreatorImpl());
		Board board = castle.getBoard(MockCreator.createMockedPlayers(2));

		for (int i = 0; i < board.getNodes().size(); i++) {
			for (int j = 0; j < board.getNodes().size(); j++) {
				if (i == j)
					continue;

				Node node = board.getNodes().get(i);
				Node compareNode = board.getNodes().get(j);
				Assert.assertFalse((node.getXCoordinate() == compareNode.getXCoordinate() && node.getYCoordinate() == compareNode
						.getYCoordinate()));
			}
		}
	}
}
