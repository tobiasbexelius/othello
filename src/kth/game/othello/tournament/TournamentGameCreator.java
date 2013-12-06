package kth.game.othello.tournament;

import java.util.ArrayList;
import java.util.List;

import kth.game.othello.Othello;
import kth.game.othello.OthelloCreator;
import kth.game.othello.OthelloCreatorImpl;
import kth.game.othello.OthelloFactory;
import kth.game.othello.board.BoardCreator;
import kth.game.othello.board.BoardCreatorImpl;
import kth.game.othello.board.NodeCreator;
import kth.game.othello.board.NodeCreatorImpl;
import kth.game.othello.board.factory.BoardFactory;
import kth.game.othello.player.Player;
import kth.game.othello.player.PlayerCreator;
import kth.game.othello.player.PlayerCreatorImpl;

/**
 * The responsibility of this class is to create an Othello game with two
 * players and a quadratic board.
 * 
 */

class TournamentGameCreator {

	private OthelloFactory othelloFactory;
	private BoardFactory boardFactory;

	public TournamentGameCreator() {
		NodeCreator nodeCreator = new NodeCreatorImpl();
		BoardCreator boardCreator = new BoardCreatorImpl();
		boardFactory = new BoardFactory(nodeCreator, boardCreator);
		PlayerCreator playerCreator = new PlayerCreatorImpl();
		OthelloCreator othelloCreator = new OthelloCreatorImpl();
		othelloFactory = new OthelloFactory(othelloCreator, boardFactory, playerCreator);
	}

	/**
	 * Creates an Othello game with a quadratic 8x8 board and two players.
	 * 
	 * @param player1
	 *            The first player of the game
	 * @param player2
	 *            The second player of the game
	 * @return and Othello game
	 */
	public Othello createGameWithPlayers(Player player1, Player player2) {
		List<Player> players = new ArrayList<Player>();
		players.add(player1);
		players.add(player2);
		Othello othello = othelloFactory.createGame(boardFactory.getQuadraticBoard(8, players), players);
		return othello;
	}
}
