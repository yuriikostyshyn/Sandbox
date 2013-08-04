package com.flux.sequential.queue;

public class Queue {
	private String[] values;
	private int size;
	private int base;
	private int top;

	public Queue(String[] values, int size, int base, int top) {
		this.values = values;
		this.size = size;
		this.base = base;
		this.top = top;
	}

	public void put(String newElement) {
		top++; // TODO may cause some problems
		
		if (top <= base + size - 1) {
			values[top] = newElement;
		} else {
			throw new IndexOutOfBoundsException();
		}
	}
	
	public int getBase(){
		return this.base;
	}
	
	public int getTop(){
		return this.top;
	}

	public int getSize(){
		return this.size;
	}
	
	public void setBase(int base) {
		this.base = base;
	}

	public void setTop(int top) {
		this.top = top;	
		
	}

	public void setSize(int size) {
		this.size = size;
    }
}
