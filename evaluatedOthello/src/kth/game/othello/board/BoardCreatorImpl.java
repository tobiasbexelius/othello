package kth.game.othello.board;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Author: Lina och Oskar Date: 2013-11-23 Time: 13:54
 */
public class BoardCreatorImpl implements BoardCreator {

	@Override
	public Board createBoard(List<Node> nodes) {
		List<Node> defensiveCopy = new ArrayList<>(nodes);
		List<Node> nodesListDuplicatesRemoved = removeDuplicates(defensiveCopy);
		List<NodeImpl> nodeImpls = new ArrayList<>();
		for (Node node : nodesListDuplicatesRemoved) {
			nodeImpls.add((NodeImpl) node);
		}

		return new BoardImpl(nodeImpls);
	}

	private List<Node> removeDuplicates(List<Node> tmpNodes) {
		Set<Node> nodeSet = new HashSet<>();
		for (Node tmpNode : tmpNodes) {
			if (tmpNode.isMarked()) {
				nodeSet.add(tmpNode);
			}
		}
		nodeSet.addAll(tmpNodes);
		return new ArrayList<>(nodeSet);
	}
}
