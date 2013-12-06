package kth.game.othello.tests;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import kth.game.othello.board.Node;
import kth.game.othello.board.NodeImpl;
import kth.game.othello.player.Player;
import kth.game.othello.player.Player.Type;
import kth.game.othello.player.PlayerImpl;
import kth.game.othello.player.movestrategy.MoveStrategy;
import kth.game.othello.score.ScoreItem;

public class MockCreator {

	public static Node createMockedNode(int x, int y) {
		Node node = mock(NodeImpl.class);
		when(node.getXCoordinate()).thenReturn(x);
		when(node.getYCoordinate()).thenReturn(y);
		when(node.isMarked()).thenReturn(false);
		when(node.getOccupantPlayerId()).thenReturn(null);
		when(node.getId()).thenReturn(x + "," + y);
		return node;
	}

	public static Node createMockedNode(int x, int y, String playerId) {
		Node node = mock(NodeImpl.class);
		when(node.getXCoordinate()).thenReturn(x);
		when(node.getYCoordinate()).thenReturn(y);
		when(node.isMarked()).thenReturn(true);
		when(node.getOccupantPlayerId()).thenReturn(playerId);
		when(node.getId()).thenReturn(x + "," + y);
		return node;
	}

	public static List<Player> createMockedHumanPlayers(int number) {
		List<Player> players = new ArrayList<Player>();
		for (int i = 0; i < number; i++) {
			players.add(createMockedHumanPlayer("player" + i, "player" + i));
		}
		return players;
	}

	public static List<Player> createMockedComputerPlayers(int number) {
		List<Player> players = new ArrayList<Player>();
		for (int i = 0; i < number; i++) {
			players.add(createMockedComputerPlayer("player" + i, "player" + i, null));
		}
		return players;
	}

	public static Player createMockedHumanPlayer(String id, String name) {
		Player player = mock(PlayerImpl.class);
		when(player.getType()).thenReturn(Type.HUMAN);
		when(player.getId()).thenReturn(id);
		when(player.getName()).thenReturn(name);
		return player;
	}

	public static Player createMockedComputerPlayer(String id, String name, MoveStrategy strategy) {
		Player player = mock(PlayerImpl.class);
		when(player.getType()).thenReturn(Type.COMPUTER);
		when(player.getId()).thenReturn(id);
		when(player.getName()).thenReturn(name);
		when(player.getMoveStrategy()).thenReturn(strategy);
		return player;
	}

	public static ScoreItem createMockedScoreItem(String playerId, int score) {
		ScoreItem item = mock(ScoreItem.class);
		when(item.getPlayerId()).thenReturn(playerId);
		when(item.getScore()).thenReturn(score);
		return item;
	}
}
