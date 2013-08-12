package com.flux.linked.list;

import java.util.Iterator;

public class CycledListWithHeader<T> extends CycledList<T> {

	private Entry<T> header;
	private Entry<T> pointer;

	public CycledListWithHeader() {
		init();
	}

	public void addFirst(T value) {
		if (value != null) {
			Entry<T> newEntry = new Entry<T>(value, header.getNext());
			header.setNext(newEntry);
		}
	}

	public Iterator<T> iterator() {
		return new CycledListWithHeadeIterator<T>(header);
	}

	private void init() {
		header = new Entry<T>(null, header);
	}

	protected final class CycledListWithHeadeIterator<T> implements Iterator<T> {

		private Entry<T> next;

		protected CycledListWithHeadeIterator(Entry<T> header) {
			this.next = header.getNext();
		}

		@Override
		public boolean hasNext() {
			return next != header;
		}

		@Override
		public T next() {
			if (next != header) {
				T result = next.getValue();
				next = next.getNext();
				return result;
			}else{
				throw new IndexOutOfBoundsException("You've reached end of the list");
			}
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub

		}

	}

}
