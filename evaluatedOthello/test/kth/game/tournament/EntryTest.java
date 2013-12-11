package kth.game.tournament;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import kth.game.othello.player.movestrategy.MoveStrategy;

import org.junit.Test;
import org.mockito.Mockito;

public class EntryTest {

	@Test
	public void testSort() {
		List<Entry> entries = new ArrayList<>();
		MoveStrategy mockedStrat = Mockito.mock(MoveStrategy.class);

		Entry e1 = new Entry(mockedStrat);
		e1.addWonGame();
		e1.addWonGame();
		Entry e2 = new Entry(mockedStrat);
		Entry e3 = new Entry(mockedStrat);
		e3.addWonGame();
		Entry e4 = new Entry(mockedStrat);
		entries.add(e1);
		entries.add(e2);
		entries.add(e3);
		entries.add(e4);

		Collections.sort(entries);

		List<Entry> manuallySortedEntries = new ArrayList<>();
		manuallySortedEntries.add(e1);
		manuallySortedEntries.add(e3);
		manuallySortedEntries.add(e2);
		manuallySortedEntries.add(e4);

		assertEquals(entries, manuallySortedEntries);
	}

	@Test
	public void testFailSort() {
		List<Entry> entries = new ArrayList<>();
		MoveStrategy mockedStrat = Mockito.mock(MoveStrategy.class);

		Entry e1 = new Entry(mockedStrat);
		e1.addWonGame();
		e1.addWonGame();
		Entry e2 = new Entry(mockedStrat);
		e2.addWonGame();
		e2.addWonGame();
		e2.addWonGame();
		e2.addWonGame();
		Entry e3 = new Entry(mockedStrat);
		e3.addWonGame();
		Entry e4 = new Entry(mockedStrat);
		entries.add(e1);
		entries.add(e2);
		entries.add(e3);
		entries.add(e4);

		Collections.sort(entries);

		List<Entry> manuallySortedEntries = new ArrayList<>();
		manuallySortedEntries.add(e1);
		manuallySortedEntries.add(e3);
		manuallySortedEntries.add(e2);
		manuallySortedEntries.add(e4);

		assertFalse(entries.equals(manuallySortedEntries));
	}
}
