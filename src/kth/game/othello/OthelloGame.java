package kth.game.othello;

import java.util.ArrayList;
import java.util.List;

import kth.game.othello.board.Board;
import kth.game.othello.board.Node;
import kth.game.othello.board.OthelloBoard;
import kth.game.othello.player.Player;

public class OthelloGame implements Othello{

	private Board board;
	private List<Player> players;
	
	public OthelloGame(Player player1, Player player2) {
		board = new OthelloBoard();
		players = new ArrayList<Player>();
		players.add(player1);
		players.add(player2);
	}
	
	@Override
	public Board getBoard() {
		return board;
	}

	@Override
	public List<Node> getNodesToSwap(String playerId, String nodeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Player getPlayerInTurn() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Player> getPlayers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasValidMove(String playerId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isActive() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isMoveValid(String playerId, String nodeId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Node> move() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Node> move(String playerId, String nodeId) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void start(String playerId) {
		// TODO Auto-generated method stub
		
	}

}
