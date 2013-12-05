package kth.game.othello.score;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import kth.game.othello.board.Board;
import kth.game.othello.board.Node;
import kth.game.othello.player.Player;

public class ScoreImpl extends Observable implements Score, Observer {

	private List<ScoreItem> playersScores;

	public ScoreImpl(List<Player> players) {
		playersScores = new ArrayList<ScoreItem>();
		for (Player player : players) {
			playersScores.add(new ScoreItem(player.getId(), 0));
		}
	}

	/**
	 * Takes a board and starts observing all nodes on the board. If the board
	 * has nodes already occupied, the occupying player will be awarded with
	 * this score (one for each occupied node).
	 * 
	 * @param board
	 *            The board with nodes which will be observed.
	 */
	public void observeNodesOnBoard(Board board) {
		for (Node node : board.getNodes()) {
			if (node.isMarked()) {
				incrementScore(node.getOccupantPlayerId());
			}
			node.addObserver(this);
		}
	}

	@Override
	public List<ScoreItem> getPlayersScore() {
		Collections.sort(playersScores);
		return playersScores;
	}

	@Override
	public int getPoints(String playerId) {
		for (ScoreItem item : playersScores) {
			if (item.getPlayerId().equals(playerId)) {
				return item.getScore();
			}
		}
		return -1;
	}

	private void incrementScore(String playerId) {
		for (ScoreItem item : playersScores) {
			if (item.getPlayerId().equals(playerId)) {
				item.incrementScore();
			}
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		Node node = (Node) o;
		String oldOccupant = (String) arg;
		String newOccupant = node.getOccupantPlayerId();
		System.out.println(oldOccupant + "<-Old " + newOccupant + "<-new ");
		updateNodeOccupantScores(oldOccupant, newOccupant);
	}

	/**
	 * Updates the scores for two node occupants. It notifies all observers of
	 * the change.
	 * 
	 * @param oldOccupant
	 *            The old occupant of the node. Decrease this score.
	 * @param newOccupant
	 *            The new occupant of the node. Increase this score
	 */
	private void updateNodeOccupantScores(String oldOccupant, String newOccupant) {
		List<String> changedPlayers = new ArrayList<String>();
		for (ScoreItem item : playersScores) {
			if (item.getPlayerId().equals(oldOccupant)) {
				item.decrementScore();
				changedPlayers.add(oldOccupant);
			}
			if (item.getPlayerId().equals(newOccupant)) {
				item.incrementScore();
				changedPlayers.add(newOccupant);
			}
		}
		setChanged();

		notifyObservers(changedPlayers);
	}

}
