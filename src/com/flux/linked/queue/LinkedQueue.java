package com.flux.linked.queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedQueue<T> implements Iterable<T> {

	private Entry<T> first = null;
	private Entry<T> last = null;

	private int size = 0;

	public LinkedQueue() {
		first = new Entry<T>(null, null);
		last = first;
	}

	public void put(T newElement) {
		if (last == first) {
			first.setValue(newElement);
			last = new Entry<T>(null, null);
			first.setNext(last);
		} else {
			last.setValue(newElement);
			Entry<T> newEntry = new Entry<T>(null, null);
			last.setNext(newEntry);
			last = newEntry;
		}
		size++;
	}

	public T pop() {
		T result = null;

		if (first != last) {
			result = first.getValue();
			first = first.getNext();
			size--;
		}
		return result;
	}

	@Override
	public Iterator<T> iterator() {
		return new LinkedQueueIterator<T>(first, last);
	}

	@Override
	public boolean equals(Object object) {
		if (object == null) {
			return false;
		} else if (!(object instanceof LinkedQueue)) {
			return false;
		} else if (object == this) {
			return true;
		}

		Iterator<T> thisIterator = this.iterator();
		Iterator<T> objectIterator = ((LinkedQueue) object).iterator();
		
		while (thisIterator.hasNext() && objectIterator.hasNext()) {
			if (!thisIterator.next().equals(objectIterator.next())) {
				return false;
			}
		}
		
		return true;

	}

	private static class Entry<T> {
		private T value;
		private Entry<T> next;

		private Entry(T value, Entry<T> next) {
			this.value = value;
			this.next = next;
		}

		private T getValue() {
			return this.value;
		}

		public void setValue(T value) {
			this.value = value;
		}

		private Entry getNext() {
			return this.next;
		}

		public void setNext(Entry next) {
			this.next = next;
		}

	}

	private static class LinkedQueueIterator<T> implements Iterator<T> {

		private Entry<T> current = null;
		private Entry<T> last;

		public LinkedQueueIterator(Entry<T> first, Entry<T> last) {
			this.current = first;
			this.last = last;
		}

		@Override
		public boolean hasNext() {
			return current != last;
		}

		// TODO add check for comodification!
		@Override
		public T next() {
			T result = null;

			if (current != last) {
				result = current.getValue();
				current = current.getNext();
			} else {
				throw new NoSuchElementException();
			}

			return result;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

	}
}
