package kth.game.othello.score;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;

public class ScoreItemComparatorDecreasingTest {

	@Test
	public void testFivePointsHighest() {
		ScoreItem threePointsScoreItem, twoPointsScoreItem, fivePointsScoreItem;
		List<ScoreItem> playerScoreItems;

		playerScoreItems = new ArrayList<>();
		threePointsScoreItem = Mockito.mock(ScoreItem.class);
		Mockito.when(threePointsScoreItem.getScore()).thenReturn(3);
		twoPointsScoreItem = Mockito.mock(ScoreItem.class);
		Mockito.when(twoPointsScoreItem.getScore()).thenReturn(2);
		fivePointsScoreItem = Mockito.mock(ScoreItem.class);
		Mockito.when(fivePointsScoreItem.getScore()).thenReturn(5);
		playerScoreItems.clear();
		playerScoreItems.add(threePointsScoreItem);
		playerScoreItems.add(fivePointsScoreItem);

		Collections.sort(playerScoreItems, new ScoreItemComparatorDecreasing());

		assertEquals(playerScoreItems.get(0), fivePointsScoreItem);
	}

	@Test
	public void testTwoPointsLowest() {
		ScoreItem threePointsScoreItem, twoPointsScoreItem, fivePointsScoreItem;
		List<ScoreItem> playerScoreItems;

		playerScoreItems = new ArrayList<>();
		threePointsScoreItem = Mockito.mock(ScoreItem.class);
		Mockito.when(threePointsScoreItem.getScore()).thenReturn(3);
		twoPointsScoreItem = Mockito.mock(ScoreItem.class);
		Mockito.when(twoPointsScoreItem.getScore()).thenReturn(2);
		fivePointsScoreItem = Mockito.mock(ScoreItem.class);
		Mockito.when(fivePointsScoreItem.getScore()).thenReturn(5);
		playerScoreItems.clear();
		playerScoreItems.add(threePointsScoreItem);
		playerScoreItems.add(fivePointsScoreItem);
		playerScoreItems.add(twoPointsScoreItem);

		Collections.sort(playerScoreItems, new ScoreItemComparatorDecreasing());

		assertEquals(playerScoreItems.get(2), twoPointsScoreItem);
	}
}
