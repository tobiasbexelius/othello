package kth.game.othello;

import java.util.List;

import kth.game.othello.player.Player;


/**
 * This class handles retrieval logic for the players and keeps tabs on which
 * player is in turn.
 * 
 */
class PlayerHandler {

	private List<Player> players;
	private Player playerInTurn;

	public PlayerHandler(List<Player> players) {
		this.players = players;
	}

	public Player getPlayerInTurn() {
		return playerInTurn;
	}

	public List<Player> getPlayers() {
		return players;
	}

	/**
	 * Retrieves a specific player in the game.
	 * 
	 * @param playerId
	 *            the id of the player to be retrieved
	 * @return the player with the specified id. Returns null if no player with
	 *         the specified id exists
	 */
	public Player getPlayer(String id) {
		for (Player p : players) {
			if (p.getId().equals(id))
				return p;
		}
		return null;
	}

	public void setPlayerInTurn(Player player) {
		playerInTurn = player;
	}
}
