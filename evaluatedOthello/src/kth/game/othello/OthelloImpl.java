package kth.game.othello;

import static java.util.UUID.randomUUID;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import kth.game.othello.board.Board;
import kth.game.othello.board.Node;
import kth.game.othello.player.Player;
import kth.game.othello.player.Player.Type;
import kth.game.othello.player.movestrategy.MoveStrategy;
import kth.game.othello.score.Score;

/**
 * Author: Lina och Oskar Date: 2013-11-19 Time: 13:35
 */

public class OthelloImpl extends Observable implements Othello, Observer {

	private final MoveHandler mover;
	private final GameStatusHandler game;
	private final Rules rules;
	private final List<Observer> moveObservers;
	private final List<Observer> gameEndedObservers;
	private final String gameId;

	public OthelloImpl(MoveHandler mover, GameStatusHandler game, Rules rules) {
		this.moveObservers = new ArrayList<>();
		this.gameEndedObservers = new ArrayList<>();
		this.mover = mover;
		this.game = game;
		this.rules = rules;
		gameId = String.valueOf(randomUUID());
		game.addGameFinishedObserver(this);
	}

	@Override
	public Board getBoard() {
		return mover.getBoard();
	}

	@Override
	public List<Node> getNodesToSwap(String playerId, String nodeId) {
		return rules.getNodesToSwap(playerId, nodeId);
	}

	@Override
	public Player getPlayerInTurn() {
		return game.getPlayerInTurn();
	}

	@Override
	public List<Player> getPlayers() {
		return game.getPlayers();
	}

	@Override
	public Score getScore() {
		return game.getScore();
	}

	@Override
	public boolean hasValidMove(String playerId) {
		return game.isActive() && rules.hasValidMove(playerId);
	}

	@Override
	public boolean isActive() {
		return game.isActive();
	}

	@Override
	public boolean isMoveValid(String playerId, String nodeId) {
		return rules.isMoveValid(playerId, nodeId);
	}

	@Override
	public List<Node> move() {
		Player playerInTurn = game.getPlayerInTurn();
		if (!game.isActive() || !playerInTurn.getType().equals(Type.COMPUTER)) {
			return null;
		}
		if (!rules.hasValidMove(playerInTurn.getId())) {
			game.setNextPlayerInTurn();
			playerInTurn = game.getPlayerInTurn();

			if (!game.isActive()) {
				return null;
			}
		}

		String playerId = playerInTurn.getId();
		MoveStrategy strategy = playerInTurn.getMoveStrategy();
		Node moveToNode = strategy.move(playerId, rules, getBoard());
		return move(playerInTurn.getId(), moveToNode.getId());
	}

	@Override
	public List<Node> move(String playerId, String nodeId) throws IllegalArgumentException {
		if (getPlayerInTurn().getId().equals(playerId)) {
			List<Node> nodesToSwap = mover.move(playerId, nodeId);
			game.setNextPlayerInTurn();
			notifyMoveObservers(nodesToSwap);
			return nodesToSwap;
		} else
			throw new IllegalArgumentException();
	}

	@Override
	public void start() {
		game.start();
	}

	@Override
	public void start(String playerId) {
		game.start(playerId);
	}

	@Override
	public void addGameFinishedObserver(Observer observer) {
		gameEndedObservers.add(observer);
	}

	@Override
	public void addMoveObserver(Observer observer) {
		moveObservers.add(observer);
	}

	private void notifyMoveObservers(List<Node> movedNodes) {
		for (Observer obs : moveObservers) {
			obs.update(this, movedNodes);
		}
	}

	private void notifyGameFinishedObservers() {
		for (Observer obs : gameEndedObservers) {
			obs.update(this, null);
		}
	}

	@Override
	public String getId() {
		return gameId;
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof GameStatusHandler) {
			if (!((GameStatusHandler) o).isActive())
				notifyGameFinishedObservers();
		}

	}
}
