package kth.game.othello;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import kth.game.othello.board.Board;
import kth.game.othello.board.Node;
import kth.game.othello.player.Player;
import kth.game.othello.score.Score;
import kth.game.othello.score.ScoreImpl;

public class OthelloImpl extends Observable implements Othello {

	private BoardHandler boardHandler;
	private Random random;
	private PlayerHandler playerHandler;
	private RuleHandler ruleHandler;
	private MoveHandler moveHandler;
	private ObserverHandler observerHandler;
	private ScoreImpl score;
	private String id;

	public OthelloImpl(List<Player> players, Board board) {
		boardHandler = new BoardHandler(board);
		playerHandler = new PlayerHandler(players);
		ruleHandler = new RuleHandler(boardHandler, playerHandler);
		moveHandler = new MoveHandler(boardHandler, ruleHandler);
		random = new Random();
		score = new ScoreImpl(players);
		score.observeNodesOnBoard(board);
		id = players.get(0).getId() + " vs " + players.get(1).getId();
		observerHandler = new ObserverHandler(this);
	}

	/**
	 * This constructor is only used for testing. Do NOT use this in your
	 * project.
	 */
	private OthelloImpl(List<Player> players, Board board, RuleHandler ruleHandler, MoveHandler moveHandler,
			BoardHandler boardHandler, PlayerHandler playerHandler) {
		this.boardHandler = boardHandler;
		this.playerHandler = playerHandler;
		this.ruleHandler = ruleHandler;
		this.moveHandler = moveHandler;
		id = players.get(0).getId() + " vs " + players.get(1).getId();
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
		if (!ruleHandler.isActive()) {
			observerHandler.notifyGameFinishedObservers();
			return false;
		}
		return true;
	}

	@Override
	public boolean isMoveValid(String playerId, String nodeId) {
		return ruleHandler.isMoveValid(playerId, nodeId);
	}

	@Override
	public List<Node> move() throws IllegalArgumentException {
		List<Node> swappedNodes = moveHandler.move();
		ruleHandler.swapPlayerInTurn();
		observerHandler.notifyMoveObservers(swappedNodes);
		return swappedNodes;
	}

	@Override
	public List<Node> move(String playerId, String nodeId) throws IllegalArgumentException {
		List<Node> swappedNodes = moveHandler.move(playerId, nodeId);
		ruleHandler.swapPlayerInTurn();
		observerHandler.notifyMoveObservers(swappedNodes);
		return swappedNodes;
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

	@Override
	public Score getScore() {
		return score;
	}

	@Override
	public void addGameFinishedObserver(Observer observer) {
		observerHandler.addGameFinishedObserver(observer);
	}

	@Override
	public void addMoveObserver(Observer observer) {
		observerHandler.addMoveObserver(observer);
	}

	@Override
	public String getId() {
		return id;
	}
}