package kth.game.othello.board;

import java.util.List;

public class BoardCreatorImpl implements BoardCreator{

	@Override
	public Board createBoard(List<Node> nodes) {
		return new BoardImpl(nodes);
	}

}
