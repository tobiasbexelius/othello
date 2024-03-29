package kth.game.othello.player;

import kth.game.othello.player.movestrategy.GreedyMoveStrategy;
import kth.game.othello.player.movestrategy.MoveStrategy;

public class PlayerImpl implements Player {

	private String id, name;
	private Type type;
	private MoveStrategy strategy;

	public PlayerImpl(String id, String name, Type type) {
		this.id = id;
		this.name = name;
		this.type = type;
		strategy = new GreedyMoveStrategy();
	}

	public PlayerImpl(String id, String name, Type type, MoveStrategy strategy) {
		this.id = id;
		this.strategy = strategy;
		this.name = name;
		this.type = type;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Type getType() {
		return type;
	}

	@Override
	public MoveStrategy getMoveStrategy() {
		return strategy;
	}

	@Override
	public void setMoveStrategy(MoveStrategy moveStrategy) {
		strategy = moveStrategy;
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Player))
			return false;
		return id.equals(((Player) o).getId());
	}
}
