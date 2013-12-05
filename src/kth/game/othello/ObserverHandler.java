package kth.game.othello;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

class ObserverHandler {
	private Observable observedObject;
	private Map<String, List<Observer>> observers;

	public ObserverHandler(Observable observedObject) {
		this.observedObject = observedObject;
		observers = new HashMap<String, List<Observer>>();
	}

	public void notifyObservers(String type, Object argument) {
		if (observers.get(type) != null) {
			for (Observer observer : observers.get(type)) {
				observer.update(observedObject, argument);
			}
		}
	}

	public void addObserver(String type, Observer observer) {
		if (observers.get(type) == null) {
			observers.put(type, new ArrayList<Observer>());
		}
		observers.get(type).add(observer);
	}
}
