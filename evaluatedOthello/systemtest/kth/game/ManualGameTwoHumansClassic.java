package kth.game;

import kth.game.othello.Othello;
import kth.game.othello.OthelloCreator;
import kth.game.othello.OthelloCreatorImpl;
import kth.game.othello.OthelloFactory;
import kth.game.othello.board.BoardCreator;
import kth.game.othello.board.BoardCreatorImpl;
import kth.game.othello.board.NodeCreator;
import kth.game.othello.board.NodeCreatorImpl;
import kth.game.othello.board.factory.BoardFactory;
import kth.game.othello.player.PlayerCreator;
import kth.game.othello.player.PlayerCreatorImpl;
import kth.game.othello.view.swing.OthelloView;
import kth.game.othello.view.swing.OthelloViewFactory;

/**
 * Author: Lina och Oskar Date: 2013-12-08 Time: 15:51
 */
public class ManualGameTwoHumansClassic {

	public static void main(String[] args) {
		NodeCreator nodeCreator = new NodeCreatorImpl();
		BoardCreator boardCreator = new BoardCreatorImpl();
		BoardFactory boardFactory = new BoardFactory(nodeCreator, boardCreator);
		OthelloCreator othelloCreator = new OthelloCreatorImpl();
		PlayerCreator playerCreator = new PlayerCreatorImpl();
		OthelloFactory othelloFactory = new OthelloFactory(othelloCreator, boardFactory, playerCreator);
		Othello othello = othelloFactory.createHumanGameOnOriginalBoard();
		int timeBetweenSwaps = 100;
		int timeBetweenMoves = 500;

		OthelloView othelloView = OthelloViewFactory.create(othello, timeBetweenSwaps, timeBetweenMoves);

		othelloView.start();
	}
}
