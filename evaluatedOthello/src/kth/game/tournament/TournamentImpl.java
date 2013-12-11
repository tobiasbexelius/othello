package kth.game.tournament;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import kth.game.othello.player.movestrategy.MoveStrategy;

public class TournamentImpl implements Tournament {

	private final GameTypeWrapper gameType;
	private final List<Entry> startingEntries;
	private List<Entry> resultEntries = null;

	public TournamentImpl(GameTypeWrapper gameType, List<Entry> entries) {
		this.gameType = gameType;
		this.startingEntries = entries;
	}

	@Override
	public Entry findEntryByMoveStrategy(MoveStrategy moveStrategy) throws NullPointerException {
		for (Entry entry : startingEntries) {
			if (entry.getMoveStrategy().equals(moveStrategy)) {
				return entry;
			}
		}
		throw new NullPointerException();
	}

	@Override
	public void presentResult() {
		if (resultEntries == null) {
			resultEntries = playAllGames();
		}
		System.out.println("Presenting strategies from best to worst");
		for (int i = 0; i < resultEntries.size(); i++) {
			System.out.println(String.valueOf(i) + ": " + resultEntries.get(i).getMoveStrategy().getName() + " ("
					+ resultEntries.get(i).getWonGames() + " won games)");
		}
	}

	public List<Entry> playAllGames() {
		resultEntries = new ArrayList<>(startingEntries);
		for (Entry challenger : resultEntries) {
			for (Entry opponent : resultEntries) {
				Game game = new Game(gameType, challenger.getMoveStrategy(), opponent.getMoveStrategy());
				MoveStrategy winningStrat = game.getWinningStrategy();
				findEntryByMoveStrategy(winningStrat).addWonGame();
			}
		}
		Collections.sort(resultEntries);
		return resultEntries;
	}

}
