package kth.game.othello.tournament;

import java.util.Queue;

/**
 * The responsibility of this class is to take a list of players and schedule
 * them to play against each other in some way. The class should return a queue
 * of tournament matches were the queue decides which order the matches are to
 * be played in.
 * 
 */
interface Schedule {

	/**
	 * Returns the schedule in form of a queue of matches to be played.
	 * 
	 * @return the queue of matches to be played.
	 */
	public Queue<Match> getSchedule();
	
	/**
	 * Creates a schedule in form of a queue for the matches to be played. 
	 */
	public void createSchedule();
}
