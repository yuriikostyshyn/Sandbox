package com.flux.polinom;

import org.junit.Test;

import com.flux.linked.list.CycledListWithHeader;

import static org.junit.Assert.assertEquals;

public class BasicOperationPerformerTest {

	private static final CycledListWithHeader<Coefficient> firstPolinom = createFirstPolinom();
	private static final CycledListWithHeader<Coefficient> secondPolinom = createSecondPolinom();
	private static final CycledListWithHeader<Coefficient> resultPolinom = createResultPolinom();
	private static final CycledListWithHeader<Coefficient> firstPolinomForMultiplication = createFirstPolinomForMultiplication();
	private static final CycledListWithHeader<Coefficient> secondPolinomForMultiplication = createSecondPolinomForMultiplication();
	private static final CycledListWithHeader<Coefficient> resultPolinomForMultiplication = createResultPolinomForMultiplication();

	@Test
	public void shouldAddSortedPolinoms() {
		CycledListWithHeader<Coefficient>[] polinomsToAdd = new CycledListWithHeader[]{firstPolinom, secondPolinom};
		CycledListWithHeader<Coefficient> actualResult = BasicOperationsPerformer.addSortedPolinoms(polinomsToAdd);
		
		assertEquals(resultPolinom.toString(), actualResult.toString());
		
	}

	@Test
	public void shouldMultiplySortedPolinoms() {
		CycledListWithHeader<Coefficient>[] polinomsToMultiply = new CycledListWithHeader[]{firstPolinomForMultiplication, secondPolinomForMultiplication};
		CycledListWithHeader<Coefficient> actualResult = BasicOperationsPerformer.multiplyPolinoms(polinomsToMultiply);
		
		assertEquals(resultPolinomForMultiplication.toString(), actualResult.toString());
		
	}

	
	private static CycledListWithHeader<Coefficient> createFirstPolinom() {
		CycledListWithHeader<Coefficient> result = new CycledListWithHeader<Coefficient>();

		result.addFirst(new Coefficient(new int[] { 1, 0, 0 }, 3.4));
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

		result.addFirst(new Coefficient(new int[] { 1, 0, 0 }, 7.3));
		result.addFirst(new Coefficient(new int[] { 2, 1, 1 }, 2.1));
		result.addFirst(new Coefficient(new int[] { 2, 2, 1 }, 5.1));
		result.addFirst(new Coefficient(new int[] { 3, 2, 1 }, 15));
		result.addFirst(new Coefficient(new int[] { 3, 2, 2 }, 3.5));
		result.addFirst(new Coefficient(new int[] { 4, 2, 1 }, 3.2));
		result.addFirst(new Coefficient(new int[] { 4, 2, 3 }, 5.1));
		result.addFirst(new Coefficient(new int[] { 5, 3, 4 }, 6));
		return result;
	}
	
	private static CycledListWithHeader<Coefficient> createFirstPolinomForMultiplication() {
		CycledListWithHeader<Coefficient> result = new CycledListWithHeader<Coefficient>();

		result.addFirst(new Coefficient(new int[] { 1, 0, 0 }, 3.4));
		result.addFirst(new Coefficient(new int[] { 3, 2, 2 }, 3.5));
		return result;
	}

	private static CycledListWithHeader<Coefficient> createSecondPolinomForMultiplication() {
		CycledListWithHeader<Coefficient> result = new CycledListWithHeader<Coefficient>();

		result.addFirst(new Coefficient(new int[] { 1, 0, 0 }, 3.9));
		result.addFirst(new Coefficient(new int[] { 2, 2, 1 }, 5.1));
		return result;
	}
	
	private static CycledListWithHeader<Coefficient> createResultPolinomForMultiplication() {
		CycledListWithHeader<Coefficient> result = new CycledListWithHeader<Coefficient>();

		result.addFirst(new Coefficient(new int[] { 2, 0, 0 }, 13.26));
		result.addFirst(new Coefficient(new int[] { 3, 2, 1 }, 17.34));
		result.addFirst(new Coefficient(new int[] { 4, 2, 2 }, 13.65));
		result.addFirst(new Coefficient(new int[] { 5, 4, 3 }, 17.85));
		return result;
	}
	
}
