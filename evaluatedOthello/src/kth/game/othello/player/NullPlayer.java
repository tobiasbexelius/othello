package kth.game.othello.player;

import kth.game.othello.player.movestrategy.MoveStrategy;

/**
 * Author: Lina och Oskar Date: 2013-12-01 Time: 02:08
 */
public class NullPlayer implements Player {
	private final Type type;

	public NullPlayer(Type type) {
		this.type = type;
	}

	@Override
	public String getId() {
		return null;
	}

	@Override
	public MoveStrategy getMoveStrategy() {
		return null;
	}

	@Override
	public void setMoveStrategy(MoveStrategy moveStrategy) {

	}

	@Override
	public String getName() {
		return null;
	}

	@Override
	public Type getType() {
		return type;
	}
}
