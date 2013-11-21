package kth.game.othello.tests;

import java.util.List;

import junit.framework.Assert;
import kth.game.othello.Othello;
import kth.game.othello.OthelloFactory;
import kth.game.othello.OthelloFactoryImpl;
import kth.game.othello.board.Node;
import kth.game.othello.player.Player;
import kth.game.othello.player.Player.Type;

import org.junit.Test;

public class OthelloLab1IT {

	private Object getNumberOfOccupiedNodes(Othello othello) {
		int occupiedNodesCounter = 0;
		for (Node node : othello.getBoard().getNodes()) {
			if (node.isMarked()) {
				occupiedNodesCounter++;
			}
		}
		return occupiedNodesCounter;
	}

	private OthelloFactory getOthelloFactory() {
		return new OthelloFactoryImpl();
	}

	private void makeAHumanMove(Othello othello, Player human) {
		for (Node node : othello.getBoard().getNodes()) {
			if (othello.isMoveValid(human.getId(), node.getId())) {
				othello.move(human.getId(), node.getId());
				return;
			}
		}
		throw new IllegalStateException();
	}

	@Test
	public void someMovesBetweenAComputerAndHumanTest() {
		Othello othello = getOthelloFactory().createHumanVersusComputerGameOnOriginalBoard();
		Player human = null;
		if (othello.getPlayers().get(0).getType() == Type.COMPUTER) {
			human = othello.getPlayers().get(1);
		} else {
			human = othello.getPlayers().get(0);
		}
		othello.start(human.getId());
		Assert.assertEquals(4, getNumberOfOccupiedNodes(othello));
		makeAHumanMove(othello, human);
		Assert.assertEquals(5, getNumberOfOccupiedNodes(othello));
		othello.move();
		Assert.assertEquals(6, getNumberOfOccupiedNodes(othello));
		Assert.assertEquals(human.getId(), othello.getPlayerInTurn().getId());
		Assert.assertTrue(othello.hasValidMove(human.getId()));
		makeAHumanMove(othello, human);
		Assert.assertEquals(7, getNumberOfOccupiedNodes(othello)); //fail
		othello.move();
		Assert.assertEquals(8, getNumberOfOccupiedNodes(othello));
		makeAHumanMove(othello, human);
		Assert.assertEquals(9, getNumberOfOccupiedNodes(othello));
		othello.move();
		Assert.assertEquals(10, getNumberOfOccupiedNodes(othello));
	}

	@Test
	public void twoComputerOnAClassicalBoardTest() {
		Othello othello = getOthelloFactory().createComputerGameOnClassicalBoard();
		othello.start(othello.getPlayers().get(0).getId());
		while (othello.isActive()) {
			Assert.assertEquals(Type.COMPUTER, othello.getPlayerInTurn().getType());
			othello.move();
		}
	}

}
