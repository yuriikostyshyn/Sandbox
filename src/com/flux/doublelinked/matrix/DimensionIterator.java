package com.flux.doublelinked.matrix;

import java.util.Iterator;

public interface DimensionIterator<T> extends Iterator<T> {

	public boolean hasNext();

	public T next();

	public void remove();

	public void add(int row, int column, T newElement); // add new element after element last returned by method next

	public void addBefore(int row, int column, T newElement);

}
