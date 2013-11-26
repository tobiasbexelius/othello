package kth.game.othello.board;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class NodeImpl extends Observable implements Node {

	private String occupantPlayerId;
	private int x, y;
	private List<Observer> observers;

	public NodeImpl(int x, int y, String occupantPlayerId) {
		this.occupantPlayerId = occupantPlayerId;
		this.x = x;
		this.y = y;
		observers = new ArrayList<Observer>();
	}
	
	@Override
	public void addObserver(Observer observer) {
		observers.add(observer);
	}

	@Override
	public String getId() {
		return Integer.toString(hashCode());
	}

	@Override
	public String getOccupantPlayerId() {
		return occupantPlayerId;
	}

	@Override
	public int getXCoordinate() {
		return x;
	}

	@Override
	public int getYCoordinate() {
		return y;
	}

	@Override
	public boolean isMarked() {
		if(occupantPlayerId == null) {
			return false;
		}
		return true;
	}
	
	@Override
	public int compareTo(Node other) {
		if (this.y < other.getYCoordinate())
			return -1;
		if (this.y > other.getYCoordinate())
			return 1;
		if (this.x < other.getXCoordinate())
			return -1;
		if (this.x > other.getXCoordinate())
			return 1;
		return 0;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((occupantPlayerId == null) ? 0 : occupantPlayerId.hashCode());
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object o) {
		Node other = (Node) o;
		if (other.getXCoordinate() == getXCoordinate() && other.getYCoordinate() == getYCoordinate()
				&& getId().equals(other.getId()))
			return true;
		return false;
	}

	public void updateOccupantPlayer(String playerId) {
		String oldOccupant = occupantPlayerId;
		occupantPlayerId = playerId;
		notifyObservers(oldOccupant);
	}
	
	@Override
	public void notifyObservers(Object obj) {
		for(Observer observer : observers) {
			observer.update(this, obj);
		}
	}
	
	@Override
	public String toString() {
		return "[X: " + x + ", Y: " + y + "]";
	}
}
