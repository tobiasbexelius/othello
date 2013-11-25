package kth.game.othello.score;

/**
 * An instance of this class contains the score of a player.
 */
public class ScoreItem implements Comparable<ScoreItem>{

	private String playerId;
	private int score;

	public ScoreItem(String playerId, int score) {
		this.playerId = playerId;
		this.score = score;
	}

	/**
	 * @return the playerId
	 */
	public String getPlayerId() {
		return playerId;
	}

	/**
	 * @return the score
	 */
	public int getScore() {
		return score;
	}
	
	public void incrementScore() {
		score ++;
	}
	
	public void decrementScore() {
		score --;
	}

	@Override
	public int compareTo(ScoreItem o) {
		if(o.getScore() < getScore()) {
			return 1;
		} else if(o.getScore() > getScore()) {
			return -1;
		} else {
			return 0;
		}
	}

}
