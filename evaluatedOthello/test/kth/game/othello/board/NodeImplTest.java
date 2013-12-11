package kth.game.othello.board;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

/**
 * Author: Lina och Oskar Date: 2013-11-28 Time: 02:21
 */
public class NodeImplTest {

	@Test
	public void testCompareTo_LessThanX() {
		Map<String, NodeImpl> nodeHashMap = new HashMap<>();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				NodeImpl nodeImpl = new NodeImpl(i, j);
				nodeHashMap.put(nodeImpl.getId(), nodeImpl);
			}
		}
		int expected = -1;
		int actual = nodeHashMap.get("1, 1").compareTo(nodeHashMap.get("2, 2"));
		assertEquals(expected, actual);
	}

	@Test
	public void testCompareTo_LessThanY() {
		Map<String, NodeImpl> nodeHashMap = new HashMap<>();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				NodeImpl nodeImpl = new NodeImpl(i, j);
				nodeHashMap.put(nodeImpl.getId(), nodeImpl);
			}
		}
		int expected = -1;
		int actual = nodeHashMap.get("1, 1").compareTo(nodeHashMap.get("1, 2"));
		assertEquals(expected, actual);
	}

	@Test
	public void testCompareTo_GreaterThanX() {
		Map<String, NodeImpl> nodeHashMap = new HashMap<>();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				NodeImpl nodeImpl = new NodeImpl(i, j);
				nodeHashMap.put(nodeImpl.getId(), nodeImpl);
			}
		}
		int expected = 1;
		int actual = nodeHashMap.get("3, 1").compareTo(nodeHashMap.get("2, 2"));
		assertEquals(expected, actual);
	}

	@Test
	public void testCompareTo_GreaterThanY() {
		Map<String, NodeImpl> nodeHashMap = new HashMap<>();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				NodeImpl nodeImpl = new NodeImpl(i, j);
				nodeHashMap.put(nodeImpl.getId(), nodeImpl);
			}
		}
		int expected = 1;
		int actual = nodeHashMap.get("2, 3").compareTo(nodeHashMap.get("2, 2"));
		assertEquals(expected, actual);
	}

	@Test
	public void testCompareTo_Equals() {
		Map<String, NodeImpl> nodeHashMap = new HashMap<>();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				NodeImpl nodeImpl = new NodeImpl(i, j);
				nodeHashMap.put(nodeImpl.getId(), nodeImpl);
			}
		}
		int expected = 0;
		int actual = nodeHashMap.get("2, 3").compareTo(nodeHashMap.get("2, 3"));
		assertEquals(expected, actual);
	}
}
