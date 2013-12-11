package kth.game.othello.score;

import static org.junit.Assert.assertEquals;

import java.util.List;

import kth.game.othello.board.Node;
import kth.game.othello.score.calculatorstrategy.CalculatorStrategy;

import org.junit.Test;

public class ScoreImplTest {

	@Test
	public void test() {
		// TODO Implement this test
		List<Node> mockedNodes = null;
		List<String> playerIds;
		CalculatorStrategy mockedCalculator = null;

		Score gameScore;

		gameScore = new ScoreImpl(mockedNodes, null, mockedCalculator);

		// TODO Really? ;-)
		assertEquals(true, true);
	}
}
