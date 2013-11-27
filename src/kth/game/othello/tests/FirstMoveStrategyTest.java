package kth.game.othello.tests;

import java.util.ArrayList;

import kth.game.othello.Rules;
import kth.game.othello.board.Board;
import kth.game.othello.board.Node;
import kth.game.othello.player.movestrategy.FirstMoveStrategy;
import kth.game.othello.player.movestrategy.MoveStrategy;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class FirstMoveStrategyTest {
	
	@Test
	public void test(){
		Rules ruleHandler = Mockito.mock(Rules.class);
		Board board = Mockito.mock(Board.class);
		ArrayList<Node> mockedNodes= new ArrayList<Node>();

		Node node1 = newMockedNode(3, 5, null);
		mockedNodes.add(node1);
		Node node2 = newMockedNode(4, 5, null);
		mockedNodes.add(node2);
		
		Mockito.when(board.getNodes()).thenReturn(mockedNodes);
		Mockito.when(ruleHandler.isMoveValid(Mockito.anyString(), Mockito.anyString())).thenReturn(true);
		MoveStrategy moveStrategy = new FirstMoveStrategy();
		Node firstMove = moveStrategy.move("player1", ruleHandler, board);
		Node secondMove = moveStrategy.move("player1", ruleHandler, board);
		
		Assert.assertEquals(node1, firstMove);
		Assert.assertEquals(node1, secondMove);	
	}
	@Test
	public void testWithNoValidMove(){
		Rules ruleHandler = Mockito.mock(Rules.class);
		Board board = Mockito.mock(Board.class);
		ArrayList<Node> mockedNodes= new ArrayList<Node>();

		Node node1 = newMockedNode(3, 5, "player1");
		mockedNodes.add(node1);
		Node node2 = newMockedNode(4, 5, "player2");
		mockedNodes.add(node2);
		
		Mockito.when(board.getNodes()).thenReturn(mockedNodes);
		Mockito.when(ruleHandler.isMoveValid(Mockito.anyString(), Mockito.anyString())).thenReturn(true);
		MoveStrategy moveStrategy = new FirstMoveStrategy();
		Node firstMove = moveStrategy.move("player1", ruleHandler, board);
		Node secondMove = moveStrategy.move("player1", ruleHandler, board);
		
		Assert.assertNull(firstMove);
		Assert.assertNull(secondMove);
		
	}
	private Node newMockedNode(int x, int y, String occupantPLayer){
		Node node = Mockito.mock(Node.class);
		Mockito.when(node.getXCoordinate()).thenReturn(x);
		Mockito.when(node.getYCoordinate()).thenReturn(y);
		Mockito.when(node.getOccupantPlayerId()).thenReturn(occupantPLayer);
		if(occupantPLayer == null){
			Mockito.when(node.isMarked()).thenReturn(false);
		}else{
			Mockito.when(node.isMarked()).thenReturn(true);
		}
		return node;
	}
}
