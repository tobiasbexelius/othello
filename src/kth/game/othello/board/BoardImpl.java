package kth.game.othello.board;

import java.util.ArrayList;
import java.util.List;

public class BoardImpl implements Board {

	private List<Node> nodes;

	public BoardImpl() {
		nodes = new ArrayList<Node>();
	}

	@Override
	public List<Node> getNodes() {
		return nodes;
	}

}
