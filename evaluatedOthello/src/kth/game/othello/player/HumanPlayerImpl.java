package kth.game.othello.player;

import kth.game.othello.player.movestrategy.MoveStrategy;

class HumanPlayerImpl extends PlayerBaseImpl {

	public HumanPlayerImpl(String name) {
		super(name, Type.HUMAN, null);
	}

	@Override
	public MoveStrategy getMoveStrategy() throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setMoveStrategy(MoveStrategy moveStrategy) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
}
