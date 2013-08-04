package com.flux.linked.list;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CycledListTest {
	private CycledList<String> underTest;
	
	@Before
	public void setUp(){
		underTest = new CycledList<String>();
	}
	
	@Test
	public void shouldAddNewLastElement(){
		String newElement = "newElement";
		String newElement2 = "newElement2";
		
		underTest.addLast(newElement);
		underTest.addLast(newElement2);
		
		String actual = underTest.getLast();
		
		assertEquals(newElement2, actual);
		
	}
	
	@Test
	public void shouldAddNewFirstElement(){
		String newElement = "newElement";
		String newElement2 = "newElement2";
		
		underTest.addLast(newElement);
		underTest.addLast(newElement2);
		
		String actual = underTest.getFirst();
		
		assertEquals(newElement, actual);
		
	}
	
	@Test
	public void shouldMergeTwoLists(){
		String newElement = "newElement";
		String newElement2 = "newElement2";
		String newElement3 = "newElement3";
		String newElement4 = "newElement4";
		
		underTest.addLast(newElement);
		underTest.addLast(newElement2);
		
		CycledList<String> listToAdd = new CycledList<String>();
		
		listToAdd.addLast(newElement3);
		listToAdd.addLast(newElement4);
		
		underTest.addAll(listToAdd);
		
		String actualFirst = underTest.getFirst();
		String actualLast = underTest.getLast();
		
		assertEquals(newElement, actualFirst);
		assertEquals(newElement4, actualLast);
	}
}
