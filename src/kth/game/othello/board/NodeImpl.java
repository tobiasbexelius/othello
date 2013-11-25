package kth.game.othello.board;

import java.util.Observer;

public class NodeImpl implements Node {

	private String id, occupantPlayerId;
	private int x, y;

	public NodeImpl(int x, int y, String id, String occupantPlayerId) {
		this.id = id;
		this.occupantPlayerId = occupantPlayerId;
		this.x = x;
		this.y = y;
	}

	@Override
	public String getId() {
		return id;
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
	public boolean equals(Object o) {
		Node other = (Node) o;
		if (other.getXCoordinate() == getXCoordinate() && other.getYCoordinate() == getYCoordinate()
				&& getId().equals(other.getId()))
			return true;
		return false;
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
	public void addObserver(Observer observer) {
		// TODO Auto-generated method stub
		
	}


}
