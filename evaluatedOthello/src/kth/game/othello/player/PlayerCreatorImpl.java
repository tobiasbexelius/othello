package kth.game.othello.player;

import kth.game.othello.player.movestrategy.MoveStrategy;
import kth.game.othello.player.movestrategy.SimpleFromFirstNodeMoveStrategy;

/**
 * Author: Lina och Oskar Date: 2013-11-21 Time: 22:51
 */
public class PlayerCreatorImpl implements PlayerCreator {
	@Override
	public Player createComputerPlayer(String name) {
		MoveStrategy defaultMoveStrategy = new SimpleFromFirstNodeMoveStrategy();
		return new ComputerPlayerImpl(name, defaultMoveStrategy);
	}

	@Override
	public Player createComputerPlayer(String name, MoveStrategy moveStrategy) {
		return new ComputerPlayerImpl(name, moveStrategy);
	}

	@Override
	public Player createHumanPlayer(String name) {
		return new HumanPlayerImpl(name);
	}

	public Player createNullPlayer(Player player) {
		return new NullPlayer(player.getType());
	}
}
