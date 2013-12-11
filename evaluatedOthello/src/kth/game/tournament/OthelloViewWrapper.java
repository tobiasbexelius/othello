package kth.game.tournament;

import java.util.List;

import kth.game.othello.Othello;
import kth.game.othello.player.Player;
import kth.game.othello.score.Score;
import kth.game.othello.score.ScoreItem;
import kth.game.othello.view.swing.OthelloView;

public class OthelloViewWrapper implements GameTypeWrapper {

	private final Othello oth;
	private final OthelloView othView;

	public OthelloViewWrapper(Othello oth, OthelloView othView) {
		this.oth = oth;
		this.othView = othView;
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
		othView.start();
		return this;
	}

}
