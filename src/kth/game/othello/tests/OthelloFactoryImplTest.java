package kth.game.othello.tests;

import static org.junit.Assert.assertEquals;

import java.util.List;

import junit.framework.Assert;
import kth.game.othello.Othello;
import kth.game.othello.OthelloFactory;
import kth.game.othello.OthelloFactoryImpl;
import kth.game.othello.board.Node;
import kth.game.othello.player.PlayerImpl;
import kth.game.othello.player.Player;
import kth.game.othello.player.Player.Type;

import org.junit.Test;

public class OthelloFactoryImplTest {

	@Test
	public void createComputerGameOnClassicalBoardTest() {
		OthelloFactory factory = new OthelloFactoryImpl();
		Othello game = factory.createComputerGameOnClassicalBoard();
		List<Node> nodes = game.getBoard().getNodes();
		assertEquals(nodes.get(27).getOccupantPlayerId(), "1");
		assertEquals(nodes.get(28).getOccupantPlayerId(), "2");
		assertEquals(nodes.get(35).getOccupantPlayerId(), "2");
		assertEquals(nodes.get(36).getOccupantPlayerId(), "1");
		assertEquals(nodes.get(3).getOccupantPlayerId(), null);
		assertEquals(nodes.get(0).getOccupantPlayerId(), null);
		assertEquals(nodes.get(63).getOccupantPlayerId(), null);
		assertEquals(nodes.get(54).getOccupantPlayerId(), null);
		assertEquals(nodes.size(), 64);
	}
	
}
