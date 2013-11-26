package kth.game.othello.score;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import kth.game.othello.board.Node;
import kth.game.othello.player.Player;

public class ScoreImpl extends Observable implements Score, Observer{

	private List<ScoreItem> playersScores;
	
	public ScoreImpl() {
		playersScores = new ArrayList<ScoreItem>();
	}
	
	public void generateScoreItems(List<Player> players) {
		for(Player player : players) {
			playersScores.add(new ScoreItem(player.getId(), 0));
		}
	}
	
	@Override
	public List<ScoreItem> getPlayersScore() {
		Collections.sort(playersScores);
		return playersScores;
	}

	@Override
	public int getPoints(String playerId) {
		for(ScoreItem item : playersScores) {
			if(item.getPlayerId().equals(playerId)) {
				return item.getScore();
			}
		}
		return -1;
	}
	
	public void incrementScore(String playerId) {
		for(ScoreItem item : playersScores) {
			if(item.getPlayerId().equals(playerId)) {
				item.incrementScore();
			}
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		System.out.println("Updated Board!");
		Node node = (Node) o;
		String oldOccupant = (String) arg;
		String newOccupant = node.getOccupantPlayerId();
		for(ScoreItem item : playersScores) {
			if(item.getPlayerId().equals(oldOccupant)) {
				item.decrementScore();
			}
			if(item.getPlayerId().equals(newOccupant)) {
				item.incrementScore();
			}
		}
	}

}
