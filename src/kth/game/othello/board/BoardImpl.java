package kth.game.othello.board;

import java.util.Collections;
import java.util.List;

public class BoardImpl implements Board {

	private List<Node> nodes;

	public BoardImpl(List<Node> nodes) {
		Collections.sort(nodes);
		this.nodes = nodes;
	}

	@Override
	public List<Node> getNodes() {
		return nodes;
	}

}
