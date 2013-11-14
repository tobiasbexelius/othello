package kth.game.othello.player;

public class PlayerImpl implements Player {
	
	private String id, name;
	private Type type;
	
	public PlayerImpl(String id, String name, Type type) {
		this.id = id;
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

}
