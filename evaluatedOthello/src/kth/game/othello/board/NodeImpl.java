package kth.game.othello.board;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Author: Lina och Oskar Date: 2013-11-21 Time: 22:55
 */

public class NodeImpl extends Observable implements Node, Comparable<Node> {

	private final List<Observer> observers;
	private final String nodeID;
	private final int xCoordinate;
	private final int yCoordinate;
	private String occupantId;

	public NodeImpl(int xCoord, int yCoord, String occupantID) {
		observers = new ArrayList<>();
		xCoordinate = xCoord;
		yCoordinate = yCoord;
		this.occupantId = occupantID;
		this.nodeID = String.valueOf(xCoordinate) + ", " + String.valueOf(yCoordinate);
	}

	public NodeImpl(int xCoord, int yCoord) {
		this(xCoord, yCoord, null);
	}

	@Override
	public void addObserver(Observer observer) {
		observers.add(observer);
	}

	public void setOccupantPlayer(String playerId) {
		notifyObservers(occupantId);
		occupantId = playerId;
	}

	void notifyObservers(String oldOccupantId) {
		if (observers != null) {
			for (Observer obs : observers) {
				obs.update(this, oldOccupantId);
			}
		}
	}

	@Override
	public String getId() {
		return nodeID;
	}

	@Override
	public String getOccupantPlayerId() {
		return occupantId;
	}

	@Override
	public int getXCoordinate() {
		return xCoordinate;
	}

	@Override
	public int getYCoordinate() {
		return yCoordinate;
	}

	@Override
	public boolean isMarked() {
		return (occupantId != null);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("NodeImpl{");
		sb.append("    (x, y) = (").append(xCoordinate).append(", ").append(yCoordinate).append("),").append('\'');
		sb.append("    occupantID='").append(occupantId).append('\'');
		sb.append('}');
		return sb.toString();
	}

	@Override
	public int compareTo(Node o) {
		boolean xLarger = this.xCoordinate > o.getXCoordinate();
		boolean yLarger = this.yCoordinate > o.getYCoordinate();
		boolean xEqual = this.xCoordinate == o.getXCoordinate();
		boolean yEqual = this.yCoordinate == o.getYCoordinate();

		if (xLarger) {
			return 1;
		} else if (xEqual && yLarger) {
			return 1;
		} else if (xEqual && yEqual) {
			return 0;
		} else {
			return -1;
		}
	}
}
