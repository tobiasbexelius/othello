package kth.game.othello;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyList;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import kth.game.othello.board.Board;
import kth.game.othello.board.Node;
import kth.game.othello.player.Player;
import kth.game.othello.player.movestrategy.RandomMoveStrategy;
import kth.game.othello.tests.MockCreator;

import org.junit.Test;

public class MoveHandlerTest {

	@Test
	public void computerMoveWithHumanPlayer() {
		BoardHandler boardHandler = mock(BoardHandler.class);
		RuleHandler ruleHandler = mock(RuleHandler.class);
		Player player = MockCreator.createMockedHumanPlayer("player1", "player1");
		when(ruleHandler.getPlayerInTurn()).thenReturn(player);
		MoveHandler moveHandler = new MoveHandler(boardHandler, ruleHandler);

		boolean illegalStateHappend = false;
		try {
			moveHandler.move();

		} catch (IllegalStateException e) {
			illegalStateHappend = true;
		}
		assertTrue(illegalStateHappend);
	}

	@Test
	public void computerMoveWithoutValidMove() {
		BoardHandler boardHandler = mock(BoardHandler.class);
		RuleHandler ruleHandler = mock(RuleHandler.class);
		Player player = MockCreator.createMockedComputerPlayer("player1", "player1", null);

		when(ruleHandler.getPlayerInTurn()).thenReturn(player);
		when(ruleHandler.hasValidMove(anyString())).thenReturn(false);

		MoveHandler moveHandler = new MoveHandler(boardHandler, ruleHandler);
		List<Node> nodes = moveHandler.move();

		assertTrue(nodes.isEmpty());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void computerMoveWithValidMove() {
		BoardHandler boardHandler = mock(BoardHandler.class);
		RuleHandler ruleHandler = mock(RuleHandler.class);
		MoveHandler moveHandler = new MoveHandler(boardHandler, ruleHandler);
		RandomMoveStrategy randomMoveStrategy = mock(RandomMoveStrategy.class);
		Player player = MockCreator.createMockedComputerPlayer("player1", "player1", randomMoveStrategy);
		Board board = mock(Board.class);
		Node node1 = MockCreator.createMockedNode(3, 3, "player1");
		Node node2 = MockCreator.createMockedNode(3, 4, "player2");
		Node node3 = MockCreator.createMockedNode(4, 3, "player1");
		Node node4 = MockCreator.createMockedNode(4, 4, "player2");
		Node toBeOccupied = MockCreator.createMockedNode(3, 5, null);
		when(boardHandler.getNode(3, 3)).thenReturn(node1);
		when(boardHandler.getNode(3, 4)).thenReturn(node2);
		when(boardHandler.getNode(4, 3)).thenReturn(node3);
		when(boardHandler.getNode(4, 4)).thenReturn(node4);
		when(boardHandler.getNode(3, 5)).thenReturn(toBeOccupied);

		List<Node> nodesToSwap = new ArrayList<Node>();
		nodesToSwap.add(node2);
		when(ruleHandler.hasValidMove(anyString())).thenReturn(true);
		when(ruleHandler.getNodesToSwap(anyString(), anyString())).thenReturn(nodesToSwap);
		when(ruleHandler.getPlayerInTurn()).thenReturn(player);

		when(boardHandler.getBoard()).thenReturn(board);
		when(boardHandler.swapNodes(anyList(), anyString())).thenReturn(nodesToSwap);

		when(randomMoveStrategy.move("player1", ruleHandler, board)).thenReturn(toBeOccupied);
		List<Node> swappedNodes = moveHandler.move();
		assertEquals(2, swappedNodes.size());
	}

	@Test
	public void humanMoveWithInvalidPlayer() {
		Player playerInTurn = MockCreator.createMockedHumanPlayer("playerInTurn", "playerInTurn");
		RuleHandler ruleHandler = mock(RuleHandler.class);
		BoardHandler boardHandler = mock(BoardHandler.class);
		when(ruleHandler.getPlayerInTurn()).thenReturn(playerInTurn);
		when(ruleHandler.isMoveValid(anyString(), anyString())).thenReturn(true);

		MoveHandler moveHandler = new MoveHandler(boardHandler, ruleHandler);

		boolean illegalArgumentHappend = false;
		try {
			moveHandler.move("otherPlayer", "nullNode");
		} catch (IllegalArgumentException e) {
			illegalArgumentHappend = true;
		}
		assertTrue(illegalArgumentHappend);
	}

	@Test
	public void humanMoveWithInvalidMove() {
		Player playerInTurn = MockCreator.createMockedHumanPlayer("playerInTurn", "playerInTurn");
		RuleHandler ruleHandler = mock(RuleHandler.class);
		when(ruleHandler.getPlayerInTurn()).thenReturn(playerInTurn);
		BoardHandler boardHandler = mock(BoardHandler.class);
		when(ruleHandler.isMoveValid(anyString(), anyString())).thenReturn(false);
		MoveHandler moveHandler = new MoveHandler(boardHandler, ruleHandler);

		boolean illegalArgumentHappend = false;
		try {
			moveHandler.move("playerInTurn", "nullNode");
		} catch (IllegalArgumentException e) {
			illegalArgumentHappend = true;
		}
		assertTrue(illegalArgumentHappend);
	}

	@Test
	public void humanMoveWithValidMove() {
		BoardHandler boardHandler = mock(BoardHandler.class);
		RuleHandler ruleHandler = mock(RuleHandler.class);
		MoveHandler moveHandler = new MoveHandler(boardHandler, ruleHandler);
		Player player = MockCreator.createMockedHumanPlayer("player1", "player1");
		Board board = mock(Board.class);
		Node node1 = MockCreator.createMockedNode(3, 3, "player1");
		Node node2 = MockCreator.createMockedNode(3, 4, "player2");
		Node node3 = MockCreator.createMockedNode(4, 3, "player1");
		Node node4 = MockCreator.createMockedNode(4, 4, "player2");
		Node toBeOccupied = MockCreator.createMockedNode(3, 5, null);
		when(boardHandler.getNode(node1.getId())).thenReturn(node1);
		when(boardHandler.getNode(node2.getId())).thenReturn(node2);
		when(boardHandler.getNode(node3.getId())).thenReturn(node3);
		when(boardHandler.getNode(node4.getId())).thenReturn(node4);
		when(boardHandler.getNode(toBeOccupied.getId())).thenReturn(toBeOccupied);

		List<Node> nodesToSwap = new ArrayList<Node>();
		nodesToSwap.add(node2);
		when(ruleHandler.isMoveValid(player.getId(), toBeOccupied.getId())).thenReturn(true);
		when(ruleHandler.getNodesToSwap(anyString(), anyString())).thenReturn(nodesToSwap);
		when(ruleHandler.getPlayerInTurn()).thenReturn(player);
		when(boardHandler.getBoard()).thenReturn(board);
		when(boardHandler.swapNodes(anyList(), anyString())).thenReturn(nodesToSwap);

		List<Node> swappedNodes = moveHandler.move(player.getId(), toBeOccupied.getId());
		assertEquals(2, swappedNodes.size());
	}
}
