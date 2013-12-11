package kth.game.othello.player;

import static java.util.UUID.randomUUID;

import kth.game.othello.player.movestrategy.MoveStrategy;

public class PlayerBaseImpl implements Player {

	private final String name;
	private final Type type;
	private final String playerID;
	private MoveStrategy moveStrategy;

	PlayerBaseImpl(String name, Type type, MoveStrategy moveStrategy) {
		this.moveStrategy = moveStrategy;
		this.name = name;
		this.type = type;
		playerID = String.valueOf(randomUUID());
	}

	@Override
	public String getId() {
		return playerID;
	}

	@Override
	public MoveStrategy getMoveStrategy() {
		return moveStrategy;
	}

	@Override
	public void setMoveStrategy(MoveStrategy moveStrategy) {
		this.moveStrategy = moveStrategy;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Type getType() {
		return type;
	}
}
