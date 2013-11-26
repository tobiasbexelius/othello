package kth.game.othello.tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import kth.game.othello.board.Board;
import kth.game.othello.board.BoardImpl;
import kth.game.othello.board.Node;
import kth.game.othello.board.NodeImpl;
import kth.game.othello.player.Player;
import kth.game.othello.player.PlayerImpl;
import kth.game.othello.score.Score;
import kth.game.othello.score.ScoreImpl;

import org.junit.Test;
import org.mockito.Mockito;

public class ScoreImplTest {

	@Test
	public void constructorTest() {
		List<Player> players = new ArrayList<Player>();
		Player player1 = Mockito.mock(PlayerImpl.class);
		Mockito.when(player1.getId()).thenReturn("player1");
		Player player2 = Mockito.mock(PlayerImpl.class);
		Mockito.when(player2.getId()).thenReturn("player2");
		Player player3 = Mockito.mock(PlayerImpl.class);
		Mockito.when(player3.getId()).thenReturn("player3");
		
		players.add(player1);
		players.add(player2);
		players.add(player3);
		Score score = new ScoreImpl(players);
		
		assertEquals(3, score.getPlayersScore().size());
		assertEquals(0,score.getPoints("player1"));
		assertEquals(0,score.getPoints("player2"));
		assertEquals(0,score.getPoints("player3"));
	}
	
	@Test
	public void observeNodesOnBoardTest() {
		Board board = Mockito.mock(BoardImpl.class);
		List<Node> nodes = new ArrayList<Node>();
		NodeImpl node1 = Mockito.mock(NodeImpl.class);
		Mockito.when(node1.isMarked()).thenReturn(true);
		Mockito.when(node1.getOccupantPlayerId()).thenReturn("player1");
		
		NodeImpl node2 = Mockito.mock(NodeImpl.class);
		Mockito.when(node2.isMarked()).thenReturn(true);
		Mockito.when(node2.getOccupantPlayerId()).thenReturn("player1");
		
		NodeImpl node3 = Mockito.mock(NodeImpl.class);
		Mockito.when(node3.isMarked()).thenReturn(true);
		Mockito.when(node3.getOccupantPlayerId()).thenReturn("player2");
		nodes.add(node1);
		nodes.add(node2);
		nodes.add(node3);
		
		Mockito.when(board.getNodes()).thenReturn(nodes);
		
		List<Player> players = new ArrayList<Player>();
		Player player1 = Mockito.mock(PlayerImpl.class);
		Mockito.when(player1.getId()).thenReturn("player1");
		Player player2 = Mockito.mock(PlayerImpl.class);
		Mockito.when(player2.getId()).thenReturn("player2");
		Player player3 = Mockito.mock(PlayerImpl.class);
		Mockito.when(player3.getId()).thenReturn("player3");
		players.add(player1);
		players.add(player2);
		players.add(player3);
		
		ScoreImpl score = new ScoreImpl(players);
		score.observeNodesOnBoard(board);
		
		assertEquals(2, score.getPoints("player1"));
		assertEquals(1, score.getPoints("player2"));
		assertEquals(0, score.getPoints("player3"));

	}

}
