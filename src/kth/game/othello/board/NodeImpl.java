package kth.game.othello.board;

public class NodeImpl implements Node {

	private String id, occupantPlayerId;
	private int x, y;
	private boolean marked;
	
	public NodeImpl(int x, int y, boolean marked, String id, String occupantPlayerId) {
		this.marked = marked;
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
		return marked;
	}

}
