package com.flux.linked.list;

import java.util.Iterator;

public interface CycledListIterator<T> extends Iterator<T>{

	public boolean hasNext();

	public T next();

	public void remove();

	public void add(T elementToAdd);

	public void addAfter(T elementToAdd);

}
