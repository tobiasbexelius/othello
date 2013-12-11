package kth.game.othello.score;

import java.util.*;

import kth.game.othello.board.Node;
import kth.game.othello.score.calculatorstrategy.CalculatorStrategy;

/**
 * Author: Lina och Oskar Date: 2013-11-19 Time: 17:13
 */
public class ScoreImpl extends Observable implements Observer, Score {

	private final List<Observer> observers;
	private final CalculatorStrategy scoreCalculator;
	private final List<Node> gameNodes;
	private final List<String> gamePlayerIds;

	/**
	 * Controls the score of the players, listens to updates and notifies its
	 * Observers when a change is made.
	 * 
	 * @param gameNodes
	 *            is the list of nodes from the active game board
	 * @param gamePlayerIds
	 *            is the list of players in the active game board
	 * @param scoreCalculator
	 *            is the chosen Strategy for calculating the score
	 */
	public ScoreImpl(List<Node> gameNodes, List<String> gamePlayerIds, CalculatorStrategy scoreCalculator) {
		this.observers = new ArrayList<>();
		this.gameNodes = gameNodes;
		this.gamePlayerIds = gamePlayerIds;
		this.scoreCalculator = scoreCalculator;
	}

	@Override
	public void addObserver(Observer observer) {
		observers.add(observer);
	}

	@Override
	public List<ScoreItem> getPlayersScore() {
		List<ScoreItem> playerScoreItems = new ArrayList<>();
		for (String p : gamePlayerIds) {
			playerScoreItems.add(new ScoreItem(p, getPoints(p)));
		}
		Collections.sort(playerScoreItems, new ScoreItemComparatorDecreasing());
		return playerScoreItems;
	}

	@Override
	public int getPoints(String playerId) {
		return scoreCalculator.getPlayerPoints(gameNodes, playerId);
	}

	private void notifyObservers(List<String> playerIdsChanged) {
		for (Observer obs : observers) {
			obs.update(this, playerIdsChanged);
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		notifyObservers(gamePlayerIds);
	}
}
