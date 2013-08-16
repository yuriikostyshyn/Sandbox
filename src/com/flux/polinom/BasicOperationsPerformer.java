package com.flux.polinom;

import java.util.Iterator;
import java.util.ListIterator;

import com.flux.linked.list.CycledListWithHeader;

public class BasicOperationsPerformer {

	public static CycledListWithHeader<Coefficient> addSortedPolinoms(CycledListWithHeader<Coefficient>[] polinoms) {
		if (polinoms != null && polinoms.length > 0) {
			CycledListWithHeader<Coefficient> result = polinoms[polinoms.length - 1].clone();
			if (polinoms.length == 2) {
				Iterator<Coefficient> iterator = polinoms[0].iterator();
				ListIterator<Coefficient> resultIterator = result.listIterator();

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
			ListIterator<Coefficient> secondIterator = polinoms[1].listIterator();
			while (firstIterator.hasNext()) {
				Coefficient coefficient = firstIterator.next();
				if (result == null) {
					result = multiplyPolinomAndCoefficient(polinoms[1], coefficient);
				} else {
					CycledListWithHeader<Coefficient> polinomToAdd = multiplyPolinomAndCoefficient(polinoms[1], coefficient);
					result = addSortedPolinoms(new CycledListWithHeader[] { result, polinomToAdd });
				}
			}

		}
		return result;

	}

	private static CycledListWithHeader<Coefficient> multiplyPolinomAndCoefficient(CycledListWithHeader<Coefficient> polinom,
			Coefficient coefficient) {
		CycledListWithHeader<Coefficient> result = polinom.clone();
		if (result != null) {
			for (Coefficient element : result) {
				element.multiplyOnCoefficient(coefficient);
			}
		}
		return result;
	}

	private static void addPolinoms(Iterator<Coefficient> iterator, ListIterator<Coefficient> resultIterator) {
		while (iterator.hasNext()) {
			Coefficient currentElement = iterator.next();
			while (resultIterator.hasNext()) {
				Coefficient currentResultElement = resultIterator.next();
				int comparationResult = currentElement.compareTo(currentResultElement);
				if (comparationResult < 0) {
					continue;
				} else if (comparationResult > 0) {
					resultIterator.add(currentElement);
					break;
				} else if (comparationResult == 0) {
					currentResultElement.addCoefficientValue(currentElement.getCoefficientValue());
					if (currentResultElement.getCoefficientValue() == 0.0) {
						resultIterator.remove();
					}
					break;
				}
			}
		}
	}
}
