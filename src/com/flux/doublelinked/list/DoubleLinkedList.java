package com.flux.doublelinked.list;

import java.util.Iterator;

public class DoubleLinkedList<T> {
	private Entry<T> header;

	public DoubleLinkedList() {
		header = new Entry<T>(null, null, null);
		header.setPrevious(header);
		header.setNext(header);
	}

	public void addFirst(T newElement) {
		Entry<T> newEntry = new Entry<T>(newElement, header, header.getNext());
		header.getNext().setPrevious(newEntry);
		header.setNext(newEntry);
	}

	public void addLast(T newElement) {
		Entry<T> newEntry = new Entry<T>(newElement, header.getPrevious(), header);
		header.getPrevious().setNext(newEntry);
		header.setPrevious(newEntry);
	}

	protected final class DoubleLinkedListIterator implements Iterator<T> {

		private Entry<T> next;

		protected DoubleLinkedListIterator() {
			this.next = header;
		}

		@Override
		public boolean hasNext() {
			return next != header;
		}

		@Override
		public T next() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub

		}

	}

	protected static class Entry<T> {
		private T value;
		private Entry<T> previous;
		private Entry<T> next;

		protected Entry(T value, Entry<T> previous, Entry<T> next) {
			this.value = value;
			this.next = next;
		}

		protected T getValue() {
			return this.value;
		}

		protected void setValue(T value) {
			this.value = value;
		}

		protected Entry<T> getPrevious() {
			return previous;
		}

		protected void setPrevious(Entry<T> previous) {
			this.previous = previous;
		}

		protected Entry<T> getNext() {
			return this.next;
		}

		protected void setNext(Entry<T> next) {
			this.next = next;
		}

	}
}
