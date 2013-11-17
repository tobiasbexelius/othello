package kth.game.othello.tests;

import junit.framework.Assert;
import kth.game.othello.Othello;
import kth.game.othello.OthelloFactoryImpl;
import kth.game.othello.board.Board;
import kth.game.othello.board.Node;
import kth.game.othello.player.Player;
import kth.game.othello.player.Player.Type;

import org.junit.Test;

public class Demo {

	@Test
	public void demo1() {
		/*
		 * Show that two computer players can start a game and determine who has
		 * won.
		 */
		Othello game = new OthelloFactoryImpl().createComputerGameOnClassicalBoard();
		game.start();
		while (game.isActive()) {
			game.move();
		}
		int player1Nodes = getNumberOfNodesForPlayer(game.getPlayers().get(0), game.getBoard());
		int player2Nodes = getNumberOfNodesForPlayer(game.getPlayers().get(1), game.getBoard());
		Assert.assertEquals(getNumberOfOccupiedNodes(game.getBoard()), player1Nodes + player2Nodes);
		
		if (player1Nodes > player2Nodes) {
			System.out.println("Player " + game.getPlayers().get(0).getName() + " won!");
		} else if (player2Nodes > player1Nodes) {
			System.out.println("Player " + game.getPlayers().get(1).getName() + " won!");
		} else {
			System.out.println("The game ended with a draw");
		}

	}

	private int getNumberOfNodesForPlayer(Player player, Board board) {
		int playerNodes = 0;
		for (Node node : board.getNodes()) {
			if (node.isMarked()) {
				if (node.getOccupantPlayerId().equals(player.getId()))
					playerNodes++;
			}
		}
		return playerNodes;
	}

	private int getNumberOfOccupiedNodes(Board board) {
		int occupiedNodes = 0;
		for (Node node : board.getNodes()) {
			if (node.isMarked()) {
				occupiedNodes++;
			}
		}
		return occupiedNodes;
	}

	@Test
	public void demo2() {
		/*
		 * Show that the model allows for one computer and one human to play
		 * against each other some moves, at least four
		 */
		Othello game = new OthelloFactoryImpl().createHumanVersusComputerGameOnOriginalBoard();
		game.start();
		while (game.isActive()) {
			if (game.getPlayerInTurn().getType() == Type.HUMAN) {
				if (game.hasValidMove(game.getPlayerInTurn().getId()))
					game.move(game.getPlayerInTurn().getId(), someHumanMove(game.getPlayerInTurn().getId(), game));
			} else {
				game.move();
			}
		}
		int player1Nodes = getNumberOfNodesForPlayer(game.getPlayers().get(0), game.getBoard());
		int player2Nodes = getNumberOfNodesForPlayer(game.getPlayers().get(1), game.getBoard());
		Assert.assertEquals(getNumberOfOccupiedNodes(game.getBoard()), player1Nodes + player2Nodes);
		
		if (player1Nodes > player2Nodes) {
			System.out.println("Player " + game.getPlayers().get(0).getName() + " won!");
		} else if (player2Nodes > player1Nodes) {
			System.out.println("Player " + game.getPlayers().get(1).getName() + " won!");
		} else {
			System.out.println("The game ended with a draw");
		}
	}

	private String someHumanMove(String humanId, Othello game) {
		for (Node node : game.getBoard().getNodes()) {
			if (game.isMoveValid(humanId, node.getId())) {
				return node.getId();
			}
		}
		return null;
	}
}
