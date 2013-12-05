package kth.game.othello.tests;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import kth.game.othello.board.Node;
import kth.game.othello.board.NodeImpl;
import kth.game.othello.player.Player;
import kth.game.othello.player.PlayerImpl;

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

	public static List<Player> createMockedPlayers(int number) {
		List<Player> players = new ArrayList<Player>();
		for (int i = 0; i < number; i++) {
			players.add(new PlayerImpl("player" + i, "player" + i, Player.Type.HUMAN));
		}
		return players;
	}
}
