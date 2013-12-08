package kth.game.othello.player;

import kth.game.othello.player.movestrategy.MoveStrategy;

public class PlayerCreatorImpl implements PlayerCreator {
	private static int id = 0;

	@Override
	public Player createComputerPlayer(String name) {
		id++;
		return new PlayerImpl(Integer.toString(id), name, Player.Type.COMPUTER);
	}

	@Override
	public Player createComputerPlayer(String name, MoveStrategy moveStrategy) {
		id++;
		return new PlayerImpl(Integer.toString(id), name, Player.Type.COMPUTER, moveStrategy);
	}

	@Override
	public Player createHumanPlayer(String name) {
		id++;
		return new PlayerImpl(Integer.toString(id), name, Player.Type.HUMAN);
	}

}
