package com.flux.sorting.topological;

import com.flux.linked.queue.LinkedQueue;

public class Grid {
	private int size;
	private int[] count;
	private LinkedQueue<Integer>[] top;

	public Grid(int size) {
		initGrid(size);
	}

	private void initGrid(int size) {
		this.size = size;
		this.count = new int[size];
		this.top = (LinkedQueue<Integer>[]) new LinkedQueue[size];
		
		for(int i=0; i<size; i++){
			top[i] = new LinkedQueue<Integer>();
		}
	}

	public int[] getCount() {
		return count;
	}

	public void setCount(int[] count) {
		this.count = count;
	}

	public LinkedQueue[] getTop() {
		return top;
	}

	public void setTop(LinkedQueue[] top) {
		this.top = top;
	}

}
