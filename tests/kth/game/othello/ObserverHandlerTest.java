package kth.game.othello;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

import kth.game.othello.board.Node;

import org.junit.Test;

public class ObserverHandlerTest {

	@Test
	public void notifyMoveObservers() {
		OthelloImpl othello = mock(OthelloImpl.class);
		ObserverHandler handler = new ObserverHandler(othello);
		List<Node> list = new ArrayList<Node>();
		Observer observer = mock(Observer.class);
		Observer observer2 = mock(Observer.class);
		handler.addMoveObserver(observer);
		handler.addMoveObserver(observer2);
		handler.notifyMoveObservers(list);

		verify(observer).update(othello, list);
		verify(observer2).update(othello, list);
	}

	@Test
	public void notifyGameFinishedObservers() {
		OthelloImpl othello = mock(OthelloImpl.class);
		ObserverHandler handler = new ObserverHandler(othello);
		Observer observer = mock(Observer.class);
		Observer observer2 = mock(Observer.class);
		handler.addGameFinishedObserver(observer2);
		handler.addGameFinishedObserver(observer);
		handler.notifyGameFinishedObservers();

		verify(observer).update(othello, null);
		verify(observer2).update(othello, null);
	}
}
