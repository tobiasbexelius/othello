package kth.game.othello;

import java.util.List;

import kth.game.othello.player.Player;

/**
 * This class handles retrieval logic for the players and keeps tabs on which player is in turn.
 * 
 */
public class PlayerHandler {

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
	 * @return the player with the specified id. Returns null if no player with the specified id exists
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

	public Player getPlayer(int playerId) {
		return getPlayer(Integer.toString(playerId));
	}

	/**
	 * Swap the players in turn. If player 1 is in turn, give the turn to player 2 and vice versa.
	 */
	public void swapPlayerInTurn() {
		String currentPlayerId = getPlayerInTurn().getId();
		if (players.get(0).getId().equals(currentPlayerId)) {
			playerInTurn = players.get(1);
		} else {
			playerInTurn = players.get(0);
		}
	}

	/**
	 * Checks if a certain player is in turn
	 * 
	 * @param playerId
	 *            the id of the player to be checked
	 * @return true if the player is in turn, false otherwise
	 */
	public boolean playerIsInTurn(String playerId) {
		if (getPlayerInTurn().getId().equals(playerId))
			return true;
		else
			return false;
	}
}
