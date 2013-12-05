package kth.game.othello.score;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

import kth.game.othello.board.Board;
import kth.game.othello.board.BoardImpl;
import kth.game.othello.board.Node;
import kth.game.othello.board.NodeImpl;
import kth.game.othello.player.Player;
import kth.game.othello.player.PlayerImpl;

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
		assertEquals(0, score.getPoints("player1"));
		assertEquals(0, score.getPoints("player2"));
		assertEquals(0, score.getPoints("player3"));
	}

	@Test
	public void initialScoresAfterObservingBoard() {
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

	@Test
	public void getPointsTest() {
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

		NodeImpl node = Mockito.mock(NodeImpl.class);
		Mockito.when(node.getOccupantPlayerId()).thenReturn("player1");

		ScoreImpl score = new ScoreImpl(players);
		assertEquals(0, score.getPoints("player1"));
		assertEquals(0, score.getPoints("player2"));
		assertEquals(0, score.getPoints("player3"));

		score.update(node, null);
		assertEquals(1, score.getPoints("player1"));
		score.update(node, null);
		assertEquals(2, score.getPoints("player1"));

		Mockito.when(node.getOccupantPlayerId()).thenReturn("player2");
		score.update(node, null);
		score.update(node, null);
		score.update(node, null);
		score.update(node, null);
		assertEquals(4, score.getPoints("player2"));
		assertEquals(2, score.getPoints("player1"));
		assertEquals(0, score.getPoints("player3"));
	}

	@Test
	public void getPlayerScoresWith3Players() {
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
		assertEquals(3, score.getPlayersScore().size());
	}

	@Test
	public void getPlayerScoresWith6Players() {
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
		Player player4 = Mockito.mock(PlayerImpl.class);
		Mockito.when(player1.getId()).thenReturn("player4");
		Player player5 = Mockito.mock(PlayerImpl.class);
		Mockito.when(player2.getId()).thenReturn("player5");
		Player player6 = Mockito.mock(PlayerImpl.class);
		Mockito.when(player3.getId()).thenReturn("player6");
		players.add(player4);
		players.add(player5);
		players.add(player6);

		Score score = new ScoreImpl(players);
		assertEquals(6, score.getPlayersScore().size());
	}

	@Test
	public void updateAndNotifyObservers() {
		List<Player> players = new ArrayList<Player>();
		Player player1 = Mockito.mock(PlayerImpl.class);
		Mockito.when(player1.getId()).thenReturn("player1");
		Player player2 = Mockito.mock(PlayerImpl.class);
		Mockito.when(player2.getId()).thenReturn("player2");
		players.add(player1);
		players.add(player2);

		Observer observer = Mockito.mock(Observer.class);

		NodeImpl node = Mockito.mock(NodeImpl.class);
		Mockito.when(node.getOccupantPlayerId()).thenReturn("player1");

		ScoreImpl score = new ScoreImpl(players);
		assertEquals(0, score.getPoints("player1"));
		score.addObserver(observer);
		score.update(node, null);
		assertEquals(1, score.getPoints("player1"));
		Mockito.verify(observer).update(score, null);
	}

}
