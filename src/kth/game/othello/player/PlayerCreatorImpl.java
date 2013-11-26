package kth.game.othello.player;

import kth.game.othello.player.movestrategy.MoveStrategy;

public class PlayerCreatorImpl implements PlayerCreator {

	@Override
	public Player createComputerPlayer(String name) {
		return new PlayerImpl(name, name, Player.Type.COMPUTER);
	}

	@Override
	public Player createComputerPlayer(String name, MoveStrategy moveStrategy) {
		return new PlayerImpl(name, name, Player.Type.COMPUTER, moveStrategy);
	}

	@Override
	public Player createHumanPlayer(String name) {
		return new PlayerImpl(name, name, Player.Type.HUMAN);
	}

}
