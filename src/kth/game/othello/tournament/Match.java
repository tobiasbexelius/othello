package kth.game.othello.tournament;

import kth.game.othello.Othello;
import kth.game.othello.player.Player;

public class Match {

	private Othello matchUp;
	private Player startingPlayer;

	public Match(Othello matchUp, Player startingPlayer) {
		this.matchUp = matchUp;
		this.startingPlayer = startingPlayer;
	}

	public Othello getOthelloGame() {
		return matchUp;
	}

	public String getStartingPlayerId() {
		return startingPlayer.getId();
	}
}
