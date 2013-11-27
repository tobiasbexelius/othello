package kth.game.othello.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import kth.game.othello.Rules;
import kth.game.othello.board.Board;
import kth.game.othello.board.Node;
import kth.game.othello.player.movestrategy.MoveStrategy;
import kth.game.othello.player.movestrategy.RandomMoveStrategy;

import org.junit.Test;
import org.mockito.Mockito;

public class RandomMoveStrategyTest {

	@Test
	public void makeValidMove() {
		Rules ruleHandler = Mockito.mock(Rules.class);
		Board board = Mockito.mock(Board.class);
	
		Node node1 = newMockedNode(5,2,null);
		Node node2 = newMockedNode(4,2,null);
		
		List<Node> nodes = new ArrayList<Node>();
	
		nodes.add(node1);
		nodes.add(node2);
		
		Mockito.when(board.getNodes()).thenReturn(nodes);
		Mockito.when(ruleHandler.isMoveValid(Mockito.anyString(), Mockito.anyString())).thenReturn(true);

		MoveStrategy moveStrategy = new RandomMoveStrategy();
		assertNotNull(moveStrategy.move("player2", ruleHandler, board));
		assertNotNull(moveStrategy.move("player2", ruleHandler, board));
	}
	
	@Test
	public void tryToMoveWhenNoValidMoveExists() {
		Rules ruleHandler = Mockito.mock(Rules.class);
		Board board = Mockito.mock(Board.class);
	
		Node node1 = newMockedNode(5,2,null);
		Node node2 = newMockedNode(4,2,null);
		
		List<Node> nodes = new ArrayList<Node>();
		
		nodes.add(node1);
		nodes.add(node2);
		
		Mockito.when(board.getNodes()).thenReturn(nodes);
		Mockito.when(ruleHandler.isMoveValid(Mockito.anyString(), Mockito.anyString())).thenReturn(false);

		MoveStrategy moveStrategy = new RandomMoveStrategy();
		assertNull(moveStrategy.move("player1", ruleHandler, board));
		assertNull(moveStrategy.move("player1", ruleHandler, board));
	}
	
	private Node newMockedNode(int x, int y, String occupantPlayer) {
		Node node = Mockito.mock(Node.class);
		Mockito.when(node.getXCoordinate()).thenReturn(x);
		Mockito.when(node.getYCoordinate()).thenReturn(y);
		Mockito.when(node.getOccupantPlayerId()).thenReturn(occupantPlayer);
		if(occupantPlayer == null) {
			Mockito.when(node.isMarked()).thenReturn(false);
		} else {
			Mockito.when(node.isMarked()).thenReturn(true);
		}
		return node;
	}

}
