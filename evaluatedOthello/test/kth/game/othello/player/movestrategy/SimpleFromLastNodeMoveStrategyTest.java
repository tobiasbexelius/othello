package kth.game.othello.player.movestrategy;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import kth.game.othello.Rules;
import kth.game.othello.board.Board;
import kth.game.othello.board.BoardImpl;
import kth.game.othello.board.Node;

import org.junit.Test;

public class SimpleFromLastNodeMoveStrategyTest {

	@Test
	public void testMoveReturnsCorrectNode() {
		MoveStrategy strat;
		Board mockedBoard;
		Rules mockedRules;

		List<Node> mockedNodes = new ArrayList<>();

		for (int x = 0; x < 2; x++) {
			for (int y = 0; y < 2; y++) {
				Node mockNode = mock(Node.class);
				when(mockNode.getId()).thenReturn("" + String.valueOf(x) + "" + String.valueOf(y));
				mockedNodes.add(mockNode);
			}
		}
		mockedRules = mock(Rules.class);
		mockedBoard = mock(BoardImpl.class);
		when(mockedBoard.getNodes()).thenReturn(mockedNodes);
		strat = new SimpleFromLastNodeMoveStrategy();

		when(mockedRules.isMoveValid("oskar", "00")).thenReturn(true);
		when(mockedRules.isMoveValid("oskar", "01")).thenReturn(true);
		when(mockedRules.isMoveValid("oskar", "10")).thenReturn(true);
		when(mockedRules.isMoveValid("oskar", "11")).thenReturn(true);
		Node resultNode = strat.move("oskar", mockedRules, mockedBoard);
		assertEquals("11", resultNode.getId());
	}
}
