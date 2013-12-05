package kth.game.othello.tournament;

import kth.game.othello.Othello;
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

public class Tournament {
	
	public Tournament() {
		NodeCreator nc = new NodeCreatorImpl();
		BoardCreator bc = new BoardCreatorImpl(); 
		PlayerCreator pc = new PlayerCreatorImpl();
		BoardFactory bf = new BoardFactory(nc, bc);
		OthelloCreatorImpl oc  = new OthelloCreatorImpl();
		OthelloFactory of = new OthelloFactory(oc, bf, pc);
		
		Othello othello = of.createComputerGameOnClassicalBoard();
		OthelloView othelloView = OthelloViewFactory.create(othello, 10, 10);
		othello.start();
		othelloView.start();
			}
	public static void main(String[] args) {
		Tournament t = new Tournament();
	}
	
	
}
