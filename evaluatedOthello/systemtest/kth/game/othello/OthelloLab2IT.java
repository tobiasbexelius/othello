package kth.game.othello;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import junit.framework.Assert;
import kth.game.othello.board.*;
import kth.game.othello.board.factory.BoardFactory;
import kth.game.othello.player.Player;
import kth.game.othello.player.PlayerCreator;
import kth.game.othello.player.PlayerCreatorImpl;
import kth.game.othello.player.movestrategy.MoveStrategy;
import kth.game.othello.player.movestrategy.RandomMoveStrategy;
import kth.game.othello.player.movestrategy.SimpleFromFirstNodeMoveStrategy;
import kth.game.othello.player.movestrategy.SimpleFromLastNodeMoveStrategy;

import org.junit.Test;

public class OthelloLab2IT {

	// returnade null innan
	private BoardFactory getBoardFactory() {
		NodeCreator nc = new NodeCreatorImpl();
		BoardCreator bc = new BoardCreatorImpl();
		return new BoardFactory(nc, bc);
	}

	// returnade null innan
	private MoveStrategy getNewMoveStrategy() {
		List<MoveStrategy> moveStrategies = new ArrayList<>();

		MoveStrategy randomMove = new RandomMoveStrategy();
		MoveStrategy firstMove = new SimpleFromFirstNodeMoveStrategy();
		MoveStrategy lastMove = new SimpleFromLastNodeMoveStrategy();
		moveStrategies.add(randomMove);
		moveStrategies.add(firstMove);
		moveStrategies.add(lastMove);

		Random ran = new Random();
		int strat = ran.nextInt(3);
		return moveStrategies.get(strat);
	}

	// returnade null innan
	private OthelloFactory getOthelloFactory() {
		OthelloCreator oc = new OthelloCreatorImpl();
		return new OthelloFactory(oc, getBoardFactory(), getPlayerCreator());
	}

	// returnade null innan
	private PlayerCreator getPlayerCreator() {
		return new PlayerCreatorImpl();
	}

	private void makeNumberOfComputerMoves(int numberOfMoves, Othello othello) {
		for (int i = 0; i < numberOfMoves; i++) {
			Assert.assertEquals(Player.Type.COMPUTER, othello.getPlayerInTurn().getType());
			othello.move();
		}
	}

	@Test
	public void studyTheInitialScoreTest() {
		Othello othello = getOthelloFactory().createHumanVersusComputerGameOnOriginalBoard();
		String playerId = othello.getPlayers().get(0).getId();
		othello.start();
		Assert.assertEquals(2, othello.getScore().getPoints(playerId));
	}

	@Test
	public void studyTheScoreAfterAMoveTest() {
		Othello othello = getOthelloFactory().createHumanVersusComputerGameOnOriginalBoard();
		String playerId = othello.getPlayers().get(0).getId();
		othello.start(playerId);
		othello.move(playerId, othello.getBoard().getNode(5, 3).getId());
		Assert.assertEquals(4, othello.getScore().getPoints(playerId));
	}

	@Test
	public void threeComputersOnADiamondBoardTest() {
		BoardFactory boardFactory = getBoardFactory();
		List<Player> players = new ArrayList<>();
		players.add(getPlayerCreator().createComputerPlayer("black"));
		players.add(getPlayerCreator().createComputerPlayer("white"));
		players.add(getPlayerCreator().createComputerPlayer("orange"));
		int boardSize = 11;
		Board board = boardFactory.getDiamondBoard(players, boardSize);
		System.out.println(board.toString());
		Othello othello = getOthelloFactory().createGame(board, players);
		othello.start();
		while (othello.isActive()) {
			othello.move();
		}

		Assert.assertFalse(othello.isActive());

	}

	@Test
	public void twoComputerOnAClassicalBoardTest() {
		Othello othello = getOthelloFactory().createComputerGameOnClassicalBoard();
		othello.start(othello.getPlayers().get(0).getId());

		// Make some moves
		makeNumberOfComputerMoves(10, othello);

		// Change one of the computers strategy
		othello.getPlayers().get(0).setMoveStrategy(getNewMoveStrategy());

		// Make some moves
		makeNumberOfComputerMoves(50, othello);

		Assert.assertFalse(othello.isActive());
	}
}
