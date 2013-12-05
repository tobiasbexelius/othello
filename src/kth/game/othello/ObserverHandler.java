package kth.game.othello;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

/**
 * This class's responsibility is to handle groups of observers. When it
 * notifies it's observers, it takes a key to determine which group it shall
 * notify. When adding an observer, the observer has to provide a key which
 * links the observer to a certain group. In it's constructor it takes an object
 * which it will provide it's observers in all notifications.
 * 
 * @author anton
 * 
 */
class ObserverHandler {
	private Observable observedObject;
	private Map<String, List<Observer>> observers;
	private Map<String, Boolean> changeForType;

	public ObserverHandler(Observable observedObject) {
		this.observedObject = observedObject;
		observers = new HashMap<String, List<Observer>>();
		changeForType = new HashMap<String, Boolean>();
	}

	public void notifyObservers(String type, Object argument) {
		if (changeForType.get(type) != null && changeForType.get(type)) {
			if (observers.get(type) != null) {
				for (Observer observer : observers.get(type)) {
					observer.update(observedObject, argument);
				}
			}
			changeForType.put(type, false);
		}
	}

	public void setChanged(String type) {
		if (observers.get(type) == null) {
			observers.put(type, new ArrayList<Observer>());
		}
		changeForType.put(type, true);
	}

	public void addObserver(String type, Observer observer) {
		if (observers.get(type) == null) {
			observers.put(type, new ArrayList<Observer>());
		}
		observers.get(type).add(observer);
	}
}
