package kth.game.othello.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import kth.game.othello.BoardHandler;
import kth.game.othello.MoveHandler;
import kth.game.othello.PlayerHandler;
import kth.game.othello.RuleHandler;
import kth.game.othello.board.Node;

import org.junit.Test;
import org.mockito.Mockito;

public class MoveHandlerTest {

	@Test
	public void testMove(){
		
	}
	
	@Test
	public void testFindPossibleMoves(){
		BoardHandler boardHandler = Mockito.mock(BoardHandler.class);
		RuleHandler ruleHandler = Mockito.mock(RuleHandler.class);
		PlayerHandler playerHandler = Mockito.mock(PlayerHandler.class);
		Node node1 = Mockito.mock(Node.class);
		Node node2 = Mockito.mock(Node.class);
		ArrayList<Node> mockedNodes = new ArrayList<Node>();
		mockedNodes.add(node1);
		mockedNodes.add(node2);
		
		
		Mockito.when(boardHandler.getNodes()).thenReturn(mockedNodes);
		Mockito.when(node1.isMarked()).thenReturn(true);
		Mockito.when(node2.isMarked()).thenReturn(false);
		Mockito.when(ruleHandler.isMoveValid(Mockito.anyString(), Mockito.anyString())).thenReturn(true);	
		
		MoveHandler moveHandler = new MoveHandler(boardHandler, playerHandler, ruleHandler);
		List<Node> resultArray = moveHandler.findPossibleMoves("player1");
		assertTrue(resultArray.contains(node2));
		assertFalse(resultArray.contains(node1));
		assertTrue(resultArray.size() == 1);
	}
	
}
