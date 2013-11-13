package kth.game.othello;

import kth.game.othello.player.OthelloPlayer;
import kth.game.othello.player.Player;
import kth.game.othello.player.Player.Type;

public class OthelloGameFactory implements OthelloFactory{

	@Override
	public Othello createComputerGameOnClassicalBoard() {
		Player player1 = new OthelloPlayer("1", "Computer 1", Type.COMPUTER);
		Player player2 = new OthelloPlayer("2", "Computer 2", Type.COMPUTER);
		Othello game = new OthelloGame(player1, player2);
		return game;
	}

	@Override
	public Othello createHumanGameOnOriginalBoard() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Othello createHumanVersusComputerGameOnOriginalBoard() {
		// TODO Auto-generated method stub
		return null;
	}

}
