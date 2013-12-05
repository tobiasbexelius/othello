package kth.game.othello;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

import kth.game.othello.board.Node;

public class ObserverHandler {
	private List<Observer> moveObservers;
	private List<Observer> gameFinishedObservers;
	private OthelloImpl othello;

	public ObserverHandler(OthelloImpl othello) {
		moveObservers = new ArrayList<Observer>();
		gameFinishedObservers = new ArrayList<Observer>();
		this.othello = othello;
	}

	public void notifyMoveObservers(List<Node> swapped) {
		for (Observer observer : moveObservers) {
			observer.update(othello, swapped);
		}
	}

	public void notifyGameFinishedObservers() {
		for (Observer observer : gameFinishedObservers) {
			observer.update(othello, null);
		}
	}

	public void addMoveObserver(Observer observer) {
		moveObservers.add(observer);
	}

	public void addGameFinishedObserver(Observer observer) {
		gameFinishedObservers.add(observer);
	}
}
