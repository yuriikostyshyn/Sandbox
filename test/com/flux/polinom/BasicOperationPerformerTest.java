package com.flux.polinom;

import org.junit.Test;

import com.flux.linked.list.CycledListWithHeader;

import static org.junit.Assert.assertEquals;

public class BasicOperationPerformerTest {

	private static final CycledListWithHeader<Coefficient> firstPolinom = createFirstPolinom();
	private static final CycledListWithHeader<Coefficient> secondPolinom = createSecondPolinom();
	private static final CycledListWithHeader<Coefficient> resultPolinom = createResultPolinom();

	@Test
	public void shouldAddSortedPolinoms() {
		CycledListWithHeader<Coefficient>[] polinomsToAdd = new CycledListWithHeader[]{firstPolinom, secondPolinom};
		CycledListWithHeader<Coefficient> actualResult = BasicOperationsPerformer.addSortedPolinoms(polinomsToAdd);
		
		assertEquals(resultPolinom, actualResult);
		
	}

	private static CycledListWithHeader<Coefficient> createFirstPolinom() {
		CycledListWithHeader<Coefficient> result = new CycledListWithHeader<Coefficient>();

		result.addFirst(new Coefficient(new int[] { 1, 0, 0 }, 3.3));
		result.addFirst(new Coefficient(new int[] { 2, 1, 1 }, 2.1));
		result.addFirst(new Coefficient(new int[] { 3, 2, 1 }, 7.3));
		result.addFirst(new Coefficient(new int[] { 3, 2, 2 }, 3.5));
		result.addFirst(new Coefficient(new int[] { 4, 2, 3 }, 2.55));
		result.addFirst(new Coefficient(new int[] { 5, 3, 4 }, 3));
		return result;
	}

	private static CycledListWithHeader<Coefficient> createSecondPolinom() {
		CycledListWithHeader<Coefficient> result = new CycledListWithHeader<Coefficient>();

		result.addFirst(new Coefficient(new int[] { 1, 0, 0 }, 3.9));
		result.addFirst(new Coefficient(new int[] { 2, 2, 1 }, 5.1));
		result.addFirst(new Coefficient(new int[] { 3, 2, 1 }, 7.7));
		result.addFirst(new Coefficient(new int[] { 4, 2, 1 }, 3.2));
		result.addFirst(new Coefficient(new int[] { 4, 2, 3 }, 2.55));
		result.addFirst(new Coefficient(new int[] { 5, 3, 4 }, 3));
		return result;
	}

	private static CycledListWithHeader<Coefficient> createResultPolinom() {
		CycledListWithHeader<Coefficient> result = new CycledListWithHeader<Coefficient>();

		result.addFirst(new Coefficient(new int[] { 1, 0, 0 }, 7.2));
		result.addFirst(new Coefficient(new int[] { 2, 1, 1 }, 2.1));
		result.addFirst(new Coefficient(new int[] { 2, 2, 1 }, 5.1));
		result.addFirst(new Coefficient(new int[] { 3, 2, 1 }, 15));
		result.addFirst(new Coefficient(new int[] { 3, 2, 2 }, 3.5));
		result.addFirst(new Coefficient(new int[] { 4, 2, 1 }, 3.2));
		result.addFirst(new Coefficient(new int[] { 4, 2, 3 }, 5.1));
		result.addFirst(new Coefficient(new int[] { 5, 3, 4 }, 6));
		return result;
	}
}
