package kth.game.othello.score;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import kth.game.othello.board.Node;
import kth.game.othello.board.NodeImpl;
import kth.game.othello.score.calculatorstrategy.CalculatorStrategy;
import kth.game.othello.score.calculatorstrategy.ClassicCalculatorStrategy;

import org.junit.Test;

public class ClassicCalculatorStrategyTest {

	@Test
	public void testPlayerPoint4() {
		CalculatorStrategy scoreCalculator = new ClassicCalculatorStrategy();

		// Mocked Nodes
		List<Node> mockedNodes = new ArrayList<>(64);
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				Node tempMockNode = mock(NodeImpl.class);
				when(tempMockNode.getXCoordinate()).thenReturn(x);
				when(tempMockNode.getYCoordinate()).thenReturn(y);
				when(tempMockNode.getId()).thenReturn(String.valueOf(x) + String.valueOf(y));
				when(tempMockNode.getOccupantPlayerId()).thenReturn(null);
				mockedNodes.add(tempMockNode);
			}
		}

		Node mockedNode1 = mock(NodeImpl.class);
		when(mockedNode1.getOccupantPlayerId()).thenReturn("Oskar");
		Node mockedNode2 = mock(NodeImpl.class);
		when(mockedNode2.getOccupantPlayerId()).thenReturn("Oskar");
		Node mockedNode3 = mock(NodeImpl.class);
		when(mockedNode3.getOccupantPlayerId()).thenReturn("Oskar");
		Node mockedNode4 = mock(NodeImpl.class);
		when(mockedNode4.getOccupantPlayerId()).thenReturn("Oskar");

		mockedNodes.set(0, mockedNode1);
		mockedNodes.set(1, mockedNode2);
		mockedNodes.set(2, mockedNode3);
		mockedNodes.set(3, mockedNode4);

		assertEquals(scoreCalculator.getPlayerPoints(mockedNodes, "Oskar"), 4);
	}

	@Test
	public void testPlayerPoint0() {
		CalculatorStrategy scoreCalculator = new ClassicCalculatorStrategy();

		// Mocked Nodes
		List<Node> mockedNodes = new ArrayList<>(64);
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				Node tempMockNode = mock(NodeImpl.class);
				when(tempMockNode.getXCoordinate()).thenReturn(x);
				when(tempMockNode.getYCoordinate()).thenReturn(y);
				when(tempMockNode.getId()).thenReturn(String.valueOf(x) + String.valueOf(y));
				when(tempMockNode.getOccupantPlayerId()).thenReturn(null);
				mockedNodes.add(tempMockNode);
			}
		}

		assertEquals(scoreCalculator.getPlayerPoints(mockedNodes, "RandomPlayerThatDoesntExist"), 0);
	}
}
