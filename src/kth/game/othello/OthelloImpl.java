package kth.game.othello;

import java.util.List;
import java.util.Random;

import kth.game.othello.board.Board;
import kth.game.othello.board.Node;
import kth.game.othello.player.Player;

public class OthelloImpl implements Othello {

	private BoardHandler boardHandler;
	private Random random;
	private PlayerHandler playerHandler;
	private RuleHandler ruleHandler;
	private MoveHandler moveHandler;

	public OthelloImpl(Player player1, Player player2, Board board) {
		boardHandler = new BoardHandler(board);
		playerHandler = new PlayerHandler(player1, player2); 
		ruleHandler = new RuleHandler(boardHandler, playerHandler);
		moveHandler = new MoveHandler(boardHandler, playerHandler, ruleHandler);
		random = new Random();
	}

	@Override
	public Board getBoard() {
		return boardHandler.getBoard();
	}

	@Override
	public List<Node> getNodesToSwap(String playerId, String nodeId) {
		return ruleHandler.getNodesToSwap(playerId, nodeId);
	}

	@Override
	public Player getPlayerInTurn() {
		return playerHandler.getPlayerInTurn();
	}

	@Override
	public List<Player> getPlayers() {
		return playerHandler.getPlayers();
	}

	@Override
	public boolean hasValidMove(String playerId) {
		return ruleHandler.hasValidMove(playerId);
	}

	@Override
	public boolean isActive() {
		return ruleHandler.isActive();
	}

	@Override
	public boolean isMoveValid(String playerId, String nodeId) {
		return ruleHandler.isMoveValid(playerId, nodeId);
	}

	@Override
	public List<Node> move() throws IllegalArgumentException {
		return moveHandler.move();
	}

	@Override
	public List<Node> move(String playerId, String nodeId) throws IllegalArgumentException {
		return moveHandler.move(playerId, nodeId);
	}

	@Override
	public void start() {
		int index = random.nextInt(getPlayers().size());
		playerHandler.setPlayerInTurn(getPlayers().get(index));
	}

	@Override
	public void start(String playerId) {
		playerHandler.setPlayerInTurn(playerHandler.getPlayer(playerId));
	}
	
}