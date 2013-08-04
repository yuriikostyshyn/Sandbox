package com.flux.sorting.topological;

public class Relation {
	private int predecessor;
	private int successor;
	
	public Relation(int predecessor, int successor){
		this.predecessor = predecessor;
		this.successor = successor;
	}

	public int getPredecessor() {
		return predecessor;
	}

	public void setPredecessor(int predecessor) {
		this.predecessor = predecessor;
	}

	public int getSuccessor() {
		return successor;
	}

	public void setSuccessor(int successor) {
		this.successor = successor;
	}
	
}
