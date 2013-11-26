package kth.game.othello.tests;

import static org.junit.Assert.assertTrue;

import java.util.List;

import kth.game.othello.BoardHandler;
import kth.game.othello.MoveHandler;
import kth.game.othello.RuleHandler;
import kth.game.othello.board.Node;
import kth.game.othello.player.Player;
import kth.game.othello.player.Player.Type;
import kth.game.othello.player.movestrategy.RandomMoveStrategy;

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
		BoardHandler boardHandler = Mockito.mock(BoardHandler.class);
		RuleHandler ruleHandler = Mockito.mock(RuleHandler.class);
		Player player = Mockito.mock(Player.class);

		Mockito.when(ruleHandler.getPlayerInTurn()).thenReturn(player);
		Mockito.when(player.getType()).thenReturn(Type.COMPUTER);
		Mockito.when(ruleHandler.hasValidMove(Mockito.anyString())).thenReturn(false);

		MoveHandler moveHandler = new MoveHandler(boardHandler, ruleHandler);
		List<Node> nodes = moveHandler.move();

		assertTrue(nodes.isEmpty());

	}

	@Test
	public void testComputerMoveWithValidMove() {
		BoardHandler boardHandler = Mockito.mock(BoardHandler.class);
		RuleHandler ruleHandler = Mockito.mock(RuleHandler.class);
		Player player = Mockito.mock(Player.class);
		Node node = Mockito.mock(Node.class);
		RandomMoveStrategy randomMoveStrategy = Mockito.mock(RandomMoveStrategy.class);

		MoveHandler moveHandler = new MoveHandler(boardHandler, ruleHandler);
		// Mockito.when(methodCall)

		// Mockito.when(randomMoveStrategy.move(player.getId(), ruleHandler, boardHandler)).thenReturn();
		Mockito.when(player.getType()).thenReturn(Type.COMPUTER);
		Mockito.when(player.getMoveStrategy()).thenReturn(randomMoveStrategy);

		// Mockito.when(ruleHandler.hasValidMove(Mockito.anyString())).thenReturn(true);
		// List<Node> mockedNodes = Mockito.mock(ArrayList.class);
		// mockedNodes.add(node);
		//
		// Mockito.when(mockedNodes.size()).thenReturn(1);
		// Mockito.when(mockedNodes.get(Mockito.anyInt())).thenReturn(node);
		// Mockito.when(ruleHandler.getNodesToSwap(Mockito.anyString(), Mockito.anyString())).thenReturn(mockedNodes);
		// Mockito.when(boardHandler.swapNodes(Mockito.anyList(), Mockito.anyString())).thenReturn(mockedNodes);
		// List<Node> nodes = moveHandler.move();
		//
		// Assert.assertEquals(1, nodes.size());
		// Assert.assertEquals(mockedNodes, nodes);
	}
}
