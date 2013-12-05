package kth.game.othello;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyList;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import kth.game.othello.BoardHandler;
import kth.game.othello.MoveHandler;
import kth.game.othello.RuleHandler;
import kth.game.othello.board.Board;
import kth.game.othello.board.Node;
import kth.game.othello.player.Player;
import kth.game.othello.player.Player.Type;
import kth.game.othello.player.movestrategy.RandomMoveStrategy;
import kth.game.othello.tests.MockCreator;

import org.junit.Test;
import org.mockito.Mockito;

public class MoveHandlerTest {

	@Test
	public void testComputerMoveWithHumanType() {
		BoardHandler boardHandler = Mockito.mock(BoardHandler.class);
		RuleHandler ruleHandler = Mockito.mock(RuleHandler.class);
		Player player = Mockito.mock(Player.class);

		Mockito.when(ruleHandler.getPlayerInTurn()).thenReturn(player);
		Mockito.when(player.getType()).thenReturn(Type.HUMAN);

		MoveHandler moveHandler = new MoveHandler(boardHandler, ruleHandler);

		boolean illegalArgumentHappend = false;
		try {
			moveHandler.move();

		} catch (IllegalStateException e) {
			illegalArgumentHappend = true;
		}
		assertTrue(illegalArgumentHappend);
	}

	@Test
	public void testComputerMoveWithoutValidMove() {
		BoardHandler boardHandler = mock(BoardHandler.class);
		RuleHandler ruleHandler = mock(RuleHandler.class);
		Player player = mock(Player.class);

		when(ruleHandler.getPlayerInTurn()).thenReturn(player);
		when(player.getType()).thenReturn(Type.COMPUTER);
		when(ruleHandler.hasValidMove(anyString())).thenReturn(false);

		MoveHandler moveHandler = new MoveHandler(boardHandler, ruleHandler);
		List<Node> nodes = moveHandler.move();

		assertTrue(nodes.isEmpty());

	}

	@SuppressWarnings("unchecked")
	@Test
	public void testComputerMoveWithValidMove() {
		BoardHandler boardHandler = mock(BoardHandler.class);
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
		Board board = mock(Board.class);
		when(boardHandler.getBoard()).thenReturn(board);

		RuleHandler ruleHandler = mock(RuleHandler.class);
		when(ruleHandler.hasValidMove(anyString())).thenReturn(true);
		List<Node> nodesToSwap = new ArrayList<Node>();
		nodesToSwap.add(node2);
		when(ruleHandler.getNodesToSwap(anyString(), anyString())).thenReturn(nodesToSwap);
		when(boardHandler.swapNodes(anyList(), anyString())).thenReturn(nodesToSwap);
		MoveHandler moveHandler = new MoveHandler(boardHandler, ruleHandler);
		RandomMoveStrategy randomMoveStrategy = mock(RandomMoveStrategy.class);
		Player player = Mockito.mock(Player.class);
		when(player.getId()).thenReturn(("player1"));
		when(player.getType()).thenReturn(Type.COMPUTER);
		when(player.getMoveStrategy()).thenReturn(randomMoveStrategy);
		when(ruleHandler.getPlayerInTurn()).thenReturn(player);
		when(randomMoveStrategy.move("player1", ruleHandler, board)).thenReturn(toBeOccupied);
		List<Node> swappedNodes = moveHandler.move();
		assertEquals(2, swappedNodes.size());
	}
}