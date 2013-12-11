package kth.game.tournament;

import java.util.List;

import kth.game.othello.Othello;
import kth.game.othello.player.Player;
import kth.game.othello.score.Score;
import kth.game.othello.score.ScoreItem;

public class OthelloWrapper implements GameTypeWrapper {

	private final Othello oth;

	public OthelloWrapper(Othello oth) {
		this.oth = oth;
	}

	@Override
	public List<Player> getPlayers() {
		return oth.getPlayers();
	}

	@Override
	public Score getScore() {
		return oth.getScore();
	}

	@Override
	public Player getWinner() {
		String playerId = getWinnerId();

		for (Player p : getPlayers()) {
			if (playerId.equals(p.getId())) {
				return p;
			}
		}
		throw new NullPointerException();
	}

	private String getWinnerId() {
		Score score = getScore();
		List<ScoreItem> scoreItems = score.getPlayersScore();
		ScoreItem leaderScoreItem = scoreItems.get(0);
		return leaderScoreItem.getPlayerId();
	}

	@Override
	public GameTypeWrapper playGame() {
		oth.start();
		while (oth.isActive()) {
			oth.move();
		}
		return this;
	}
}
