package kth.game.othello;

import java.util.ArrayList;
import java.util.List;

import kth.game.othello.board.Board;
import kth.game.othello.board.factory.BoardFactory;
import kth.game.othello.player.Player;
import kth.game.othello.player.PlayerCreator;

public class OthelloFactory {

	private final BoardFactory boardFactory;
	private final OthelloCreator othelloCreator;
	private final PlayerCreator playerCreator;

	public OthelloFactory(OthelloCreator othelloCreator, BoardFactory boardFactory, PlayerCreator playerCreator) {
		this.othelloCreator = othelloCreator;
		this.boardFactory = boardFactory;
		this.playerCreator = playerCreator;
	}

	/**
	 * Create an Othello game on an original board with two computer.
	 * 
	 * @return An Othello game
	 */
	public Othello createComputerGameOnClassicalBoard() {
		List<Player> players = new ArrayList<>();
		players.add(playerCreator.createComputerPlayer("Computer1"));
		players.add(playerCreator.createComputerPlayer("Computer2"));
		return othelloCreator.createOthello(boardFactory.getQuadraticBoard(8, players), players);
	}

	/**
	 * @param board
	 *            the board
	 * @param players
	 *            the players of the game
	 * @return An Othello game
	 */
	public Othello createGame(Board board, List<Player> players) {
		return othelloCreator.createOthello(board, players);
	}

	/**
	 * Create an Othello game on an original board with two humans.
	 * 
	 * @return An Othello game
	 */
	public Othello createHumanGameOnOriginalBoard() {
		List<Player> players = new ArrayList<>();
		players.add(playerCreator.createHumanPlayer("Human1"));
		players.add(playerCreator.createHumanPlayer("Human2"));
		Board board = boardFactory.getQuadraticBoard(8, players);
		return othelloCreator.createOthello(board, players);
	}

	/**
	 * Creates an Othello game on an original board with one computer playing
	 * against one human. The computer will be the first player in the list of
	 * players.
	 * 
	 * @return An Othello game
	 */
	public Othello createHumanVersusComputerGameOnOriginalBoard() {
		List<Player> players = new ArrayList<>();
		players.add(playerCreator.createComputerPlayer("Computer"));
		players.add(playerCreator.createHumanPlayer("Human"));
		Board board = boardFactory.getQuadraticBoard(8, players);
		return othelloCreator.createOthello(board, players);
	}

}
