package kth.game.othello.board;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Author: Lina och Oskar Date: 2013-11-23 Time: 14:34
 */
public class BoardImpl implements Board {
	private final HashMap<String, NodeImpl> nodeHashMap = new HashMap<>();
	private List<NodeImpl> listOfNodeImpls = new ArrayList<>();

	public BoardImpl(List<NodeImpl> theListOfNodeImpls) {
		Collections.sort(theListOfNodeImpls);
		listOfNodeImpls = theListOfNodeImpls;
		buildHashMap();
	}

	private void buildHashMap() {
		for (NodeImpl node : listOfNodeImpls) {
			String key = String.valueOf(node.getXCoordinate()) + ", " + "" + String.valueOf(node.getYCoordinate());
			nodeHashMap.put(key, node);
		}
	}

	@Override
	public Node getNode(int x, int y) {
		return nodeHashMap.get(String.valueOf(x) + ", " + String.valueOf(y));
	}

	@Override
	public List<Node> getNodes() {
		return new ArrayList<Node>(listOfNodeImpls);
	}

	public Node getNodeById(String nodeId) {
		return nodeHashMap.get(nodeId);
	}

	public boolean hasNode(String nodeId) {
		return nodeHashMap.containsKey(nodeId);
	}

	@Override
	public boolean hasNode(int x, int y) {
		return hasNode(String.valueOf(x) + ", " + String.valueOf(y));
	}

	@Override
	public int getMaxX() {
		int maxX = 0;
		for (NodeImpl n : listOfNodeImpls) {
			if (n.getXCoordinate() > maxX)
				maxX = n.getXCoordinate();
		}
		return maxX;
	}

	@Override
	public int getMaxY() {
		int maxY = 0;
		for (NodeImpl n : listOfNodeImpls) {
			if (n.getYCoordinate() > maxY)
				maxY = n.getYCoordinate();
		}
		return maxY;
	}

}
