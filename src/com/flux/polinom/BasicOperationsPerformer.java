package com.flux.polinom;

import java.util.Iterator;
import java.util.ListIterator;

import com.flux.linked.list.CycledListIterator;
import com.flux.linked.list.CycledListWithHeader;

public class BasicOperationsPerformer {

	public static CycledListWithHeader<Coefficient> addSortedPolinoms(CycledListWithHeader<Coefficient>[] polinoms) {
		if (polinoms != null && polinoms.length > 0) {
			CycledListWithHeader<Coefficient> result = polinoms[polinoms.length - 1].clone();
			if (polinoms.length == 2) {
				CycledListIterator<Coefficient> iterator = polinoms[0].iterator();
				CycledListIterator<Coefficient> resultIterator = result.iterator();

				addPolinoms(iterator, resultIterator);

			}
			return result;
		}
		return null;
	}

	public static CycledListWithHeader<Coefficient> multiplyPolinoms(CycledListWithHeader<Coefficient>[] polinoms) {
		CycledListWithHeader<Coefficient> result = null;
		if (polinoms != null && polinoms.length == 2) {
			Iterator<Coefficient> firstIterator = polinoms[0].iterator();
			for (Coefficient first : polinoms[0]) {
				for (Coefficient second : polinoms[1]) {
					if (result == null) {
						result = new CycledListWithHeader<Coefficient>();
						result.addFirst(multiplyCoefficients(first, second));
					} else {
						addCoefficientToResult(result, multiplyCoefficients(first, second));
					}
				}
			}

		}
		return result;

	}

	private static void addPolinoms(CycledListIterator<Coefficient> iterator, CycledListIterator<Coefficient> resultIterator) {
		while (iterator.hasNext()) {
			Coefficient currentElement = iterator.next();
			addCoefficientToResult(resultIterator, currentElement);
		}
	}

	private static void addCoefficientToResult(CycledListWithHeader<Coefficient> polinom, Coefficient coefficient) {
		addCoefficientToResult(polinom.iterator(), coefficient);
	}

	private static void addCoefficientToResult(CycledListIterator<Coefficient> iterator, Coefficient coefficient) {
		while (iterator.hasNext()) {
			Coefficient currentResultElement = iterator.next();
			int comparationResult = coefficient.compareTo(currentResultElement);
			if (comparationResult < 0) {
				if (iterator.hasNext()) {
					continue;
				} else {
					iterator.addAfter(coefficient);
				}
			} else if (comparationResult > 0) {
				iterator.add(coefficient);
				break;
			} else if (comparationResult == 0) {
				currentResultElement.addCoefficientValue(coefficient.getCoefficientValue());
				if (currentResultElement.getCoefficientValue() == 0.0) {
					iterator.remove();
				}
				break;
			}
		}
	}

	private static Coefficient multiplyCoefficients(Coefficient first, Coefficient second) {
		double resultCoefficientValue = first.getCoefficientValue() * second.getCoefficientValue();
		int newPowersLength = Math.max(first.getPowers().length, second.getPowers().length);

		int[] longer = first.getPowers();
		int[] shorter = second.getPowers();
		if (longer.length < shorter.length) {
			longer = second.getPowers();
			shorter = first.getPowers();
		}

		int[] resultPowers = new int[newPowersLength];
		for (int i = 0; i < shorter.length; i++) {
			resultPowers[i] = longer[i] + shorter[i];
		}

		for (int i = shorter.length; i < longer.length; i++) {
			resultPowers[i] = longer[i];
		}

		return new Coefficient(resultPowers, resultCoefficientValue);
	}
}
