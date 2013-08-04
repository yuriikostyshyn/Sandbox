package com.flux.linked.list;


public class CycledList<T> {
	private Entry<T> pointer = null;
	
	public CycledList(){
		super();
	}
	
	public void addLast(T newElement){
		addNewEntry(newElement, false);
	}
	
	public T getLast(){
		return pointer.getValue();
	}
	

	public void addFirst(T newElement) {
	    addNewEntry(newElement, true);
	    
    }

	private void addNewEntry(T newElement,boolean fromBeginning) {
	    if(pointer != null){
	    	Entry<T> newEntry = new Entry<T>(newElement, pointer.getNext());
	    	pointer.setNext(newEntry);	
	    	if(!fromBeginning){
				pointer = newEntry;
			}
	    } else{
	    	pointer = new CycledList.Entry<T>(newElement, null);
			pointer.setNext(pointer);
	    }
    }

	public T getFirst() {
		return pointer.getNext().getValue();
    }
	
	public void addAll(CycledList<T> listToAdd){
		Entry<T> additionPointer = listToAdd.getPointer();
		Entry<T> first = pointer.getNext();
		pointer.setNext(additionPointer.getNext());
		additionPointer.setNext(first);
		pointer = additionPointer;
	}
	
	public Entry<T> getPointer(){
		return pointer;
	}
	private static class Entry<T> {
		private T value;
		private Entry<T> next;
		
		protected Entry(T value, Entry<T> next){
			this.value = value;
			this.next = next;
		}
		
		private T getValue(){
			return this.value;
		}
		
		public void setValue(T value) {
			this.value = value;
		}

		private Entry<T> getNext(){
			return this.next;
		}

		public void setNext(Entry<T> next) {
			this.next = next;
		}
		
    }
}
