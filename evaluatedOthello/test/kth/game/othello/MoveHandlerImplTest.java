package kth.game.othello;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import kth.game.othello.board.BoardImpl;
import kth.game.othello.board.Node;
import kth.game.othello.board.NodeImpl;

import org.junit.Test;
import org.mockito.Mockito;

public class MoveHandlerImplTest {

	@Test
	public void testMove11() {
		Rules mockedRules;
		BoardImpl mockedBoard;
		MoveHandler mover;
		NodeImpl n1, n2, n3;
		List<Node> mockedNodesToSwap;

		mockedRules = Mockito.mock(Rules.class);
		mockedBoard = Mockito.mock(BoardImpl.class);

		mockedNodesToSwap = new ArrayList<>();

		n1 = Mockito.mock(NodeImpl.class);
		n2 = Mockito.mock(NodeImpl.class);
		n3 = Mockito.mock(NodeImpl.class);
		Mockito.when(n1.getId()).thenReturn("11");
		Mockito.when(n2.getId()).thenReturn("12");
		Mockito.when(n3.getId()).thenReturn("13");
		Mockito.when(mockedBoard.getNodeById("11")).thenReturn(n1);
		Mockito.when(mockedBoard.getNodeById("12")).thenReturn(n2);
		Mockito.when(mockedBoard.getNodeById("13")).thenReturn(n3);

		mover = new MoveHandlerImpl(mockedRules, mockedBoard);
		mockedNodesToSwap.add(n2);
		mockedNodesToSwap.add(n3);

		Mockito.when(mockedRules.getNodesToSwap("osk", "11")).thenReturn(mockedNodesToSwap);

		List<Node> returnedNodes = mover.move("osk", "11");

		assertEquals(3, returnedNodes.size());
	}
}
