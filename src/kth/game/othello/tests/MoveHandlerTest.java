package kth.game.othello.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import kth.game.othello.BoardHandler;
import kth.game.othello.MoveHandler;
import kth.game.othello.PlayerHandler;
import kth.game.othello.RuleHandler;
import kth.game.othello.board.Node;
import kth.game.othello.player.Player;
import kth.game.othello.player.Player.Type;

import org.junit.Test;
import org.mockito.Mockito;

public class MoveHandlerTest {

	@Test
	public void testComputerMoveWithHumanType(){
		BoardHandler boardHandler = Mockito.mock(BoardHandler.class);
		RuleHandler ruleHandler = Mockito.mock(RuleHandler.class);
		PlayerHandler playerHandler = Mockito.mock(PlayerHandler.class);
		Player player = Mockito.mock(Player.class);

		Mockito.when(playerHandler.getPlayerInTurn()).thenReturn(player);
		Mockito.when(player.getType()).thenReturn(Type.HUMAN);

		MoveHandler moveHandler = new MoveHandler(boardHandler, playerHandler, ruleHandler);

		boolean illegalArgumentHappend = false;
		try{
			moveHandler.move();

		}catch(IllegalArgumentException e){
			illegalArgumentHappend =true; 
		}
		assertTrue(illegalArgumentHappend);
	}
	
	@Test
	public void testComputerMoveWithoutValidMove(){
		BoardHandler boardHandler = Mockito.mock(BoardHandler.class);
		RuleHandler ruleHandler = Mockito.mock(RuleHandler.class);
		PlayerHandler playerHandler = Mockito.mock(PlayerHandler.class);
		Player player = Mockito.mock(Player.class);

		Mockito.when(playerHandler.getPlayerInTurn()).thenReturn(player);
		Mockito.when(player.getType()).thenReturn(Type.COMPUTER);
		Mockito.when(ruleHandler.hasValidMove(Mockito.anyString())).thenReturn(false);
		
		MoveHandler moveHandler = new MoveHandler(boardHandler, playerHandler, ruleHandler);
		List<Node> nodes  = moveHandler.move();
		
		assertTrue(nodes.isEmpty());
		
	}
	@Test
	public void testComputerMoveWithValidMove(){
		BoardHandler boardHandler = Mockito.mock(BoardHandler.class);
		RuleHandler ruleHandler = Mockito.mock(RuleHandler.class);
		PlayerHandler playerHandler = Mockito.mock(PlayerHandler.class);
		Player player = Mockito.mock(Player.class);
		Node node1 = Mockito.mock(Node.class);
		
		MoveHandler moveHandler =new MoveHandler(boardHandler, playerHandler, ruleHandler);
		MoveHandler moveHandlerSpy = Mockito.spy(moveHandler);
		
		
		Mockito.when(playerHandler.getPlayerInTurn()).thenReturn(player);
		Mockito.when(player.getType()).thenReturn(Type.COMPUTER);
		Mockito.when(ruleHandler.hasValidMove(Mockito.anyString())).thenReturn(true);
		ArrayList<Node> mockedNodes = Mockito.mock(ArrayList.class);
		//ArrayList<Node> mockedNodes = new ArrayList<Node>();
		mockedNodes.add(node1);
		
		Mockito.doReturn(mockedNodes).when(moveHandlerSpy).findPossibleMoves(Mockito.anyString());
		Mockito.when(mockedNodes.size()).thenReturn(1);
		Mockito.when(mockedNodes.get(Mockito.anyInt())).thenReturn(node1);
		Mockito.when(ruleHandler.getNodesToSwap(Mockito.anyString(),Mockito.anyString())).thenReturn(mockedNodes);
		Mockito.when(boardHandler.swapNodes(Mockito.anyList(),Mockito.anyString())).thenReturn(mockedNodes);
		//Mockito.when(random.nextInt(Mockito.anyInt())).thenReturn(1);
		List<Node> nodes  = moveHandlerSpy.move();
		
		assertEquals(1,nodes.size());
		assertEquals(mockedNodes, nodes);
		
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
