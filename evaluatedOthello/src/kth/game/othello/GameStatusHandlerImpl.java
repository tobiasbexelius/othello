package kth.game.othello;

import java.util.*;

import kth.game.othello.player.Player;
import kth.game.othello.player.PlayerCreatorImpl;
import kth.game.othello.score.Score;

/**
 * Author: Lina och Oskar Date: 2013-11-19 Time: 18:37
 */
public class GameStatusHandlerImpl extends Observable implements GameStatusHandler {

	private final Score score;
	private final Rules rules;
	private final List<Player> players;
	private final PlayerCreatorImpl playerCreatorImpl;
	private final List<Observer> finishedGameObservers;
	private boolean gameActive;
	private Player playerInTurn;

	public GameStatusHandlerImpl(Score score, List<Player> players, Rules rules, PlayerCreatorImpl playerCreatorImpl) {
		this.finishedGameObservers = new ArrayList<>();
		this.score = score;
		this.rules = rules;
		this.players = players;
		this.gameActive = false;
		this.playerInTurn = null;
		this.playerCreatorImpl = playerCreatorImpl;
	}

	@Override
	public List<Player> getPlayers() {
		return players;
	}

	@Override
	public void start() {
		if (players.size() > 0) {
			Random ran = new Random();
			int starting = ran.nextInt(players.size());
			playerInTurn = players.get(starting);
			gameActive = true;
		}
	}

	@Override
	public void start(String playerId) {
		for (Player p : players) {
			if (p.getId().equals(playerId)) {
				playerInTurn = p;
				break;
			}
		}
		gameActive = true;
	}

	@Override
	public boolean isActive() {
		return gameActive;
	}

	private void endGame() {
		playerInTurn = playerCreatorImpl.createNullPlayer(playerInTurn);
		gameActive = false;
		notifyObservers();
	}

	@Override
	public Player getPlayerInTurn() {
		return playerInTurn;
	}

	/**
	 * Updates the player in turn to the next player in the list.
	 */
	@Override
	public void setNextPlayerInTurn() {
		Player lastPlayer = playerInTurn;

		do {
			playerInTurn = getNextPlayer(playerInTurn);
			if (rules.hasValidMove(playerInTurn.getId())) {
				break;
			}
		} while (playerInTurn != lastPlayer);
		if (playerInTurn == lastPlayer) {
			endGame();
		}
	}

	private Player getNextPlayer(Player playerInTurn) {
		int nextPlayer = players.indexOf(playerInTurn) + 1;
		if (nextPlayer < players.size()) {
			return players.get(nextPlayer);
		} else {
			return players.get(0);
		}
	}

	@Override
	public void notifyObservers() {
		if (!gameActive && (finishedGameObservers.size() > 0)) {
			for (Observer obs : finishedGameObservers) {
				obs.update(this, null);
			}
		}
	}

	@Override
	public void addGameFinishedObserver(Observer obs) {
		finishedGameObservers.add(obs);
	}

	@Override
	public Score getScore() {
		return score;
	}
}
