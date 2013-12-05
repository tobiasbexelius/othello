package kth.game.othello.tournament;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import kth.game.othello.Othello;
import kth.game.othello.OthelloCreatorImpl;
import kth.game.othello.board.BoardCreator;
import kth.game.othello.board.BoardCreatorImpl;
import kth.game.othello.board.NodeCreator;
import kth.game.othello.board.NodeCreatorImpl;
import kth.game.othello.board.factory.BoardFactory;
import kth.game.othello.player.Player;
import kth.game.othello.player.PlayerCreator;
import kth.game.othello.player.PlayerCreatorImpl;
import kth.game.othello.score.ScoreItem;
import kth.game.othello.view.swing.OthelloView;
import kth.game.othello.view.swing.OthelloViewFactory;

public class Tournament implements Observer {
	private Map<String, Integer> highScore;
	private List<Player> players;
	private boolean graphicalView;

	public Tournament(List<Player> players, boolean graphicalView) {
		highScore = new HashMap<String, Integer>();
		for (Player player : players) {
			highScore.put(player.getId(), 0);
		}
		this.graphicalView = graphicalView;
		this.players = players;
	}

	public static void main(String[] args) {
		PlayerCreator pc = new PlayerCreatorImpl();
		Player player1 = pc.createComputerPlayer("computer 1 ");
		Player player2 = pc.createComputerPlayer("computer 2 ");
		List<Player> players = new ArrayList<Player>();
		players.add(player1);
		players.add(player2);
		Tournament tournament = new Tournament(players, false);
		tournament.playOneRound();
		tournament.playOneRound();
		String winner = tournament.getHighestScoringPlayer();
		if (winner == null) {
			System.out.println("the tournament was a draw");
		} else {
			System.out.println("Player " + winner + " won the tournament");
		}
		// System.exit(0);
	}

	public void playOneRound() {
		int j = 0;
		for (Player player : players) {
			for (int i = j + 1; i < players.size(); i++) {
				playOneGame(player, players.get(i));
			}
			j++;
		}
	}

	private void playOneGame(Player player1, Player player2) {
		Othello othello = createGameWithPlayers(player1, player2);
		othello.addGameFinishedObserver(this);
		OthelloView othelloView = OthelloViewFactory.create(othello, 5, 5);
		// othello.start();
		othelloView.start();
	}

	private Othello createGameWithPlayers(Player player1, Player player2) {
		NodeCreator nc = new NodeCreatorImpl();
		BoardCreator bc = new BoardCreatorImpl();
		BoardFactory bf = new BoardFactory(nc, bc);

		List<Player> players = new ArrayList<Player>();
		players.add(player1);
		players.add(player2);

		OthelloCreatorImpl oc = new OthelloCreatorImpl();
		Othello othello = oc.createOthello(bf.getQuadraticBoard(8, players), players);
		return othello;
	}

	@Override
	public void update(Observable o, Object arg) throws RuntimeException {
		if (!(o instanceof Othello))
			throw new RuntimeException("The observed object is not a Othello game");
		Othello othello = (Othello) o;
		List<ScoreItem> scores = othello.getScore().getPlayersScore();
		String winner = null;
		int highestScore = -1;
		for (ScoreItem score : scores) {
			if (score.getScore() > highestScore) {
				winner = score.getPlayerId();
				highestScore = score.getScore();
			} else if (score.getScore() == highestScore) {
				winner = null;
			}
		}
		if (winner == null) {
			for (ScoreItem score : scores) {
				highScore.put(score.getPlayerId(), 1 + highScore.get(score.getPlayerId()));
			}
		} else {
			System.out.println("is highscore null? : " + (highScore == null));
			highScore.put(winner, 2 + highScore.get(winner));
		}
	}

	public String getHighestScoringPlayer() {
		String highest = null;
		int highestScore = -1;
		for (String player : highScore.keySet()) {
			if (highestScore < highScore.get(player)) {
				highest = player;
				highestScore = highScore.get(player);
			} else if (highestScore == highScore.get(player)) {
				highest = null;
			}
		}
		System.out.println("Highest score: " + highestScore);
		return highest;
	}

}
