package kth.game.othello.board;

import java.util.ArrayList;
import java.util.List;

public class OthelloBoard implements Board{
	
	private List<Node> nodes;
	
	public OthelloBoard() {
		nodes = new ArrayList<Node>();
	}
	
	@Override
	public List<Node> getNodes() {
		return nodes;
	}

}
