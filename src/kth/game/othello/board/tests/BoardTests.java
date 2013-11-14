package kth.game.othello.board.tests;

import java.util.List;

import junit.framework.Assert;

import kth.game.othello.board.Board;
import kth.game.othello.board.Node;
import kth.game.othello.board.BoardImpl;

import org.junit.Test;

public class BoardTests {

	@Test
	public void testCreation() {
		Board board = new BoardImpl();
		List<Node> nodes = board.getNodes();
		Assert.assertEquals("0", nodes.get(0).getId());
		Assert.assertEquals("63", nodes.get(nodes.size()-1).getId());
		Assert.assertEquals("32", nodes.get(32).getId());
		Assert.assertEquals("45", nodes.get(45).getId());
		Assert.assertEquals(0, nodes.get(0).getXCoordinate());
		Assert.assertEquals(0, nodes.get(0).getYCoordinate());
		Assert.assertEquals(0, nodes.get(32).getXCoordinate());
		Assert.assertEquals(4, nodes.get(32).getYCoordinate());
	}

}
