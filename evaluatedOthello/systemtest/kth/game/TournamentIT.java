package kth.game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import kth.game.othello.Othello;
import kth.game.othello.OthelloCreator;
import kth.game.othello.OthelloCreatorImpl;
import kth.game.othello.OthelloFactory;
import kth.game.othello.board.BoardCreator;
import kth.game.othello.board.BoardCreatorImpl;
import kth.game.othello.board.NodeCreator;
import kth.game.othello.board.NodeCreatorImpl;
import kth.game.othello.board.factory.BoardFactory;
import kth.game.othello.player.PlayerCreator;
import kth.game.othello.player.PlayerCreatorImpl;
import kth.game.othello.player.movestrategy.RandomMoveStrategy;
import kth.game.othello.player.movestrategy.SimpleFromFirstNodeMoveStrategy;
import kth.game.othello.player.movestrategy.SimpleFromLastNodeMoveStrategy;
import kth.game.othello.view.swing.OthelloView;
import kth.game.othello.view.swing.OthelloViewFactory;
import kth.game.tournament.*;

import org.junit.Test;

public class TournamentIT {

	@Test
	public void basicOthelloTest() {
		OthelloCreator oc = new OthelloCreatorImpl();
		NodeCreator nc = new NodeCreatorImpl();
		BoardCreator bc = new BoardCreatorImpl();
		BoardFactory bf = new BoardFactory(nc, bc);
		PlayerCreator pc = new PlayerCreatorImpl();
		OthelloFactory of = new OthelloFactory(oc, bf, pc);

		Othello othello = of.createComputerGameOnClassicalBoard();

		GameTypeWrapper gameType = new OthelloWrapper(othello);
		Entry e1 = new Entry(new RandomMoveStrategy());
		Entry e2 = new Entry(new SimpleFromFirstNodeMoveStrategy());
		Entry e3 = new Entry(new SimpleFromLastNodeMoveStrategy());
		List<Entry> entries = new ArrayList<>();
		entries.add(e1);
		entries.add(e2);
		entries.add(e3);

		Tournament tournament = new TournamentImpl(gameType, entries);
		assertTrue(tournament.playAllGames().size() == 3);
		tournament.presentResult();
	}

	@Test
	public void testFindEntryByMoveStrat() {
		OthelloCreator oc = new OthelloCreatorImpl();
		NodeCreator nc = new NodeCreatorImpl();
		BoardCreator bc = new BoardCreatorImpl();
		BoardFactory bf = new BoardFactory(nc, bc);
		PlayerCreator pc = new PlayerCreatorImpl();
		OthelloFactory of = new OthelloFactory(oc, bf, pc);

		Othello othello = of.createComputerGameOnClassicalBoard();

		GameTypeWrapper gameType = new OthelloWrapper(othello);
		Entry e1 = new Entry(new RandomMoveStrategy());
		Entry e2 = new Entry(new SimpleFromFirstNodeMoveStrategy());
		Entry e3 = new Entry(new SimpleFromLastNodeMoveStrategy());
		List<Entry> entries = new ArrayList<>();
		entries.add(e1);
		entries.add(e2);
		entries.add(e3);

		Tournament tournament = new TournamentImpl(gameType, entries);
		assertEquals(tournament.findEntryByMoveStrategy(e2.getMoveStrategy()), e2);
	}

	@Test
	public void baiscOthelloViewTest() {
		OthelloCreator oc = new OthelloCreatorImpl();
		NodeCreator nc = new NodeCreatorImpl();
		BoardCreator bc = new BoardCreatorImpl();
		BoardFactory bf = new BoardFactory(nc, bc);
		PlayerCreator pc = new PlayerCreatorImpl();
		OthelloFactory of = new OthelloFactory(oc, bf, pc);

		Othello othello = of.createComputerGameOnClassicalBoard();

		OthelloView othView = OthelloViewFactory.create(othello, 10, 30);
		GameTypeWrapper gameType = new OthelloViewWrapper(othello, othView);
		Entry e1 = new Entry(new RandomMoveStrategy());
		Entry e2 = new Entry(new SimpleFromFirstNodeMoveStrategy());
		Entry e3 = new Entry(new SimpleFromLastNodeMoveStrategy());
		List<Entry> entries = new ArrayList<>();
		entries.add(e1);
		entries.add(e2);
		entries.add(e3);

		Tournament tournament = new TournamentImpl(gameType, entries);
		assertTrue(tournament.playAllGames().size() == 3);
		tournament.presentResult();
	}
}
