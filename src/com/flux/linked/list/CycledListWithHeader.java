package com.flux.linked.list;

import java.util.Iterator;
import java.util.ListIterator;

import com.flux.linked.queue.LinkedQueue;

public class CycledListWithHeader<T> extends CycledList<T> implements Iterable<T> {

	private Entry<T> header;

	public CycledListWithHeader() {
		init();
	}

	@Override
	public void addFirst(T value) {
		if (value != null) {
			Entry<T> newEntry = new Entry<T>(value, header.getNext());
			header.setNext(newEntry);
		}
	}

	public CycledListIterator<T> iterator() {
		return new CycledListWithHeadeIterator<T>(header);
	}


	private void init() {
		header = new Entry<T>(null, null);
		header.setNext(header);
	}


	//Designed for polinom addition purposes
	protected final class CycledListWithHeadeIterator<T> implements CycledListIterator<T> {

		private Entry<T> previous;
		private Entry<T> current;

		protected CycledListWithHeadeIterator(Entry<T> header) {
			this.previous = header;
			this.current = header;
		}

		@Override
		public boolean hasNext() {
			return current.getNext() != header;
		}

		@Override
		public T next() {
			if (current.getNext() != header) {
				previous = current;
				current = current.getNext();
				T result = current.getValue();
				return result;
			} else {
				throw new IndexOutOfBoundsException("You've reached end of the list");
			}
		}

		@Override
		public void remove() {
			previous.setNext(current.getNext());
			current = current.getNext();
		}

		@Override
		public void add(T elementToAdd) {
			Entry<T> newEntry = new Entry<T>(elementToAdd, current);
			previous.setNext(newEntry);
			current = newEntry;
		}

		public void addAfter(T elementToAdd){
			Entry<T> newEntry = new Entry<T>(elementToAdd, current.getNext());
			current.setNext(newEntry);
			previous = current;
			current = newEntry;
		}

	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		} else if (!(obj instanceof CycledListWithHeader)) {
			return false;
		} else if (!(this == obj)) {
			return false;
		}

		Iterator<T> thisIterator = this.iterator();
		Iterator<T> objectIterator = ((CycledListWithHeader) obj).iterator();

		while (thisIterator.hasNext() && objectIterator.hasNext()) {
			if (!thisIterator.next().equals(objectIterator.next())) {
				return false;
			}
		}

		if (thisIterator.hasNext() || objectIterator.hasNext()) {
			return false;
		}

		return true;
	}

	@Override
	public CycledListWithHeader<T> clone() {
		CycledListWithHeader<T> result = new CycledListWithHeader<T>();

		CycledListWithHeader<T> intermediateResult = new CycledListWithHeader<T>();

		// FIXME we need faster and more elegant way to perform clone
		for (T element : this) {
			intermediateResult.addFirst(element);
		}

		for (T element : intermediateResult) {
			result.addFirst(element);
		}

		return result;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		for (T element : this) {
			result.append(element.toString() + ", ");
		}
		return result.toString();
	}

}
