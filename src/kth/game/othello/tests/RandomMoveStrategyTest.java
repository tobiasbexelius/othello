package kth.game.othello.tests;

import kth.game.othello.Rules;
import kth.game.othello.board.Board;
import kth.game.othello.board.Node;
import kth.game.othello.player.movestrategy.MoveStrategy;
import kth.game.othello.player.movestrategy.RandomMoveStrategy;

import org.junit.Test;
import org.mockito.Mockito;

public class RandomMoveStrategyTest {

	@Test
	public void test() {
		Rules ruleHandler = Mockito.mock(Rules.class);
		Board board = Mockito.mock(Board.class);
		
		Node node1 = Mockito.mock(Node.class);
		Mockito.when(node1.getXCoordinate()).thenReturn(3);
		Mockito.when(node1.getYCoordinate()).thenReturn(3);
		Mockito.when(node1.getOccupantPlayerId()).thenReturn("player1");
		Node node2 = Mockito.mock(Node.class);
		Mockito.when(node2.getXCoordinate()).thenReturn(4);
		Mockito.when(node2.getYCoordinate()).thenReturn(3);
		Mockito.when(node1.getOccupantPlayerId()).thenReturn("player1");
		Node node3 = Mockito.mock(Node.class);
		Mockito.when(node3.getXCoordinate()).thenReturn(5);
		Mockito.when(node3.getYCoordinate()).thenReturn(3);
		Mockito.when(node1.getOccupantPlayerId()).thenReturn("player1");
		Node node4 = Mockito.mock(Node.class);
		Mockito.when(node4.getXCoordinate()).thenReturn(3);
		Mockito.when(node4.getYCoordinate()).thenReturn(4);
		Mockito.when(node1.getOccupantPlayerId()).thenReturn("player2");
		Node node5 = Mockito.mock(Node.class);
		Mockito.when(node5.getXCoordinate()).thenReturn(4);
		Mockito.when(node5.getYCoordinate()).thenReturn(4);
		Mockito.when(node1.getOccupantPlayerId()).thenReturn("player2");
		Node node6 = Mockito.mock(Node.class);
		Mockito.when(node6.getXCoordinate()).thenReturn(5);
		Mockito.when(node6.getYCoordinate()).thenReturn(4);
		Mockito.when(node1.getOccupantPlayerId()).thenReturn("player2");
		Node node7 = Mockito.mock(Node.class);
		Mockito.when(node7.getXCoordinate()).thenReturn(3);
		Mockito.when(node7.getYCoordinate()).thenReturn(3);
		Mockito.when(node1.getOccupantPlayerId()).thenReturn(null);

		//TODO SHIT 
		MoveStrategy moveStrategy = new RandomMoveStrategy();
	}

}
