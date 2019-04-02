package queuepackage;

import java.util.*;

public class Queue {

	private LinkedList list;
	
	public Queue() {
		list = new LinkedList();
	}
	
	public boolean isEmpty() {
		return list.size() == 0;
	}
	
	public void enqueue(Object o) {
		if (o != null)
			list.addLast(o);
	}
		
	public Object dequeue() {
		if (!this.isEmpty())
			return list.removeFirst();
		throw new EmptyQueueException("Empty Queue");
	}
}
	
