package kth.game.othello.player;

import kth.game.othello.player.movestrategy.MoveStrategy;

/**
 * Author: Lina och Oskar
 */
class ComputerPlayerImpl extends PlayerBaseImpl {

	public ComputerPlayerImpl(String moveStrategy, MoveStrategy name) {
		super(moveStrategy, Type.COMPUTER, name);
	}
}
