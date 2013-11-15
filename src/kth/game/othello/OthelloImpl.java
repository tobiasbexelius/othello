package kth.game.othello;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import kth.game.othello.board.Board;
import kth.game.othello.board.Node;
import kth.game.othello.board.NodeImpl;
import kth.game.othello.player.Player;
import kth.game.othello.player.Player.Type;

public class OthelloImpl implements Othello{

	private Board board;
	private Random random;
	private List<Player> players;
	private Player playerInTurn;
	private int[] dX = {0, 0, 1, -1, 1, -1, -1, 1};
	private int[] dY = {1, -1, 0, 0, 1, -1, 1, -1};
	
	public OthelloImpl(Player player1, Player player2, Board board) {
		this.board = board;
		players = new ArrayList<Player>();
		players.add(player1);
		players.add(player2);
		random = new Random();
	}
	
	@Override
	public Board getBoard() {
		return board;
	}

	@Override
	public List<Node> getNodesToSwap(String playerId, String nodeId) {
		List<Node> swappedNodes = new ArrayList<Node>();
		Node move = getNode(nodeId);
		if(move == null) 
			return swappedNodes;
		
		for(int i = 0; i < dY.length; i++) {
			List<Node> nodesInDirection = getNodesToSwapInDirection(playerId, move, dX[i], dY[i]);
			if(nodesInDirection != null)
				swappedNodes.addAll(nodesInDirection);			
		}
		return swappedNodes;
	}

	@Override
	public Player getPlayerInTurn() {
		return playerInTurn;
	}

	@Override
	public List<Player> getPlayers() {
		return players;
	}

	@Override
	public boolean hasValidMove(String playerId) {
		if(findPossibleMoves(playerId).size() == 0)
			return false;
		return true;
	}

	@Override
	public boolean isActive() {
		if(hasValidMove(players.get(0).getId()) || hasValidMove(players.get(1).getId()))
			return true;
		return false;
	}

	@Override
	public boolean isMoveValid(String playerId, String nodeId) {
		Node move = getNode(nodeId);
		if(move == null) 
			return false;
		if(move.isMarked())
			return false;
		for(int i = 0; i < dY.length; i++) {
			if(canCaptureInDirection(playerId, move, dX[i], dY[i]))
				return true;
		}
		return false;
	}
	
	private boolean canCaptureInDirection(String playerId, Node move, int xDir, int yDir) {
		Node next = getNode(move.getXCoordinate()+xDir, move.getYCoordinate()+yDir);
		
		if(next == null || !next.isMarked() || next.getOccupantPlayerId().equals(playerId))
			return false;

		while(true) {
			next = getNode(next.getXCoordinate()+xDir, next.getYCoordinate()+yDir);
			if(next == null || !next.isMarked())
				return false;
			if(next.getOccupantPlayerId().equals(playerId))
				return true;
		}
	}
	
	private List<Node> getNodesToSwapInDirection(String playerId, Node move, int xDir, int yDir) {
			Node next = getNode(move.getXCoordinate()+xDir, move.getYCoordinate()+yDir);
			if(next == null || !next.isMarked() || next.getOccupantPlayerId().equals(playerId))
				return null;
			List<Node> swappedNodes = new ArrayList<Node>();
			while(!next.getOccupantPlayerId().equals(playerId)) {
				swappedNodes.add(next);
				next = getNode(next.getXCoordinate()+xDir, next.getYCoordinate()+yDir);
				if(next == null || !next.isMarked())
					return null;
			} 
			return swappedNodes;
	}
	
	private Node getNode(String nodeId) {
		for(Node node : board.getNodes()) {
			if(node.getId().equals(nodeId)) {
				return node;
			}
		}
		return null;
	}
	
	private Node getNode(int x, int y) {
		int index = 8*y+x;
		if(index >= board.getNodes().size() || index < 0)
			return null;
		return board.getNodes().get(index);
	}
	
	@Override
	public List<Node> move() throws IllegalArgumentException {
		if(getPlayerInTurn().getType() != Type.COMPUTER)
			throw new IllegalArgumentException();
		
		String playerId = getPlayerInTurn().getId();
		if(!hasValidMove(playerId)) {
			swapPlayerInTurn();
			return new ArrayList<Node>();
		}
		List<Node> possibleMoves = findPossibleMoves(playerId);
		int moveIndex = random.nextInt(possibleMoves.size());
		Node move = possibleMoves.get(moveIndex);
		
		List<Node> nodesToSwap = getNodesToSwap(playerId, move.getId());
		nodesToSwap.add(move);
		List<Node> swappedNodes = swapNodes(nodesToSwap, playerId);

		swapPlayerInTurn();
		
		return swappedNodes;
	}

	@Override
	public List<Node> move(String playerId, String nodeId) throws IllegalArgumentException {
		if(!getPlayerInTurn().getId().equals(playerId) || !isMoveValid(playerId, nodeId))
			throw new IllegalArgumentException();
		
		Node move = getNode(nodeId);
		List<Node> nodesToSwap = getNodesToSwap(playerId, move.getId());
		nodesToSwap.add(move);
		List<Node> swappedNodes = swapNodes(nodesToSwap, playerId);
		swapPlayerInTurn();
		return swappedNodes;
	}
	
	private List<Node> swapNodes(List<Node> nodesToCapture, String playerId) {
		for(Node node : nodesToCapture) {
			occupyNode(node, board.getNodes(), playerId);
		}
		return nodesToCapture;
	}
	
	private void swapPlayerInTurn() {
		String currentPlayerId = getPlayerInTurn().getId();
		
		if(players.get(0).getId().equals(currentPlayerId)) {
			playerInTurn = players.get(1);
		} else {
			playerInTurn = players.get(0);
		}
	}
	
	private void occupyNode(Node node, List<Node> nodes, String occupantPlayerId) {
		int nodeIndex = nodes.indexOf(node);
		if(nodeIndex == -1) {
			System.out.println("Could not find node: " + node.getId());
		}
		nodes.remove(node);
		nodes.add(nodeIndex, new NodeImpl(node.getXCoordinate(), node.getYCoordinate(), true, node.getId(), occupantPlayerId));
	}

	@Override
	public void start() {
		int player = random.nextInt(2);
		playerInTurn = players.get(player);
	}

	@Override
	public void start(String playerId) {
		playerInTurn = getPlayer(playerId);
	}

	private Player getPlayer(String playerId) {
		if(players.get(0).getId().equals(playerId)) {
			return players.get(0);
		} else if (players.get(1).getId().equals(playerId)) {
			return players.get(1);
		} else {
			return null;
		}
	}
	
	private List<Node> findPossibleMoves(String playerId) {
		List<Node> moves = new ArrayList<Node>();
		for(Node node : board.getNodes()) {
			if(!node.isMarked()) {
				if(isMoveValid(playerId, node.getId())) {
					moves.add(node);
				}
			}
		}
		return moves;
	}

}
