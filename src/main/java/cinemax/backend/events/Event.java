package cinemax.backend.events;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

public class Event<T> {
	private List<Consumer<T>> listeners = new LinkedList<>();
	
	public void register(Consumer<T> listener) {
		listeners.add(listener);
	}
	
	public void unregister(Consumer<T> listener) {
		listeners.remove(listener);
	}
	
	public void raiseEvent(T data) {
		for(Consumer<T> l : listeners) {
			l.accept(data);
		}
	}
	
	public void unregisterAll() {
		listeners.clear();
	}
}
