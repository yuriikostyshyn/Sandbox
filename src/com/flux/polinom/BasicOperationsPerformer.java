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

	public static CycledListWithHeader<Coefficient> multiplyPolinomAndCoefficient(CycledListWithHeader<Coefficient> polinom, Coefficient coefficient) {
		if(polinom != null){
			for(Coefficient element:polinom){
				element.mu
			}
		}
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
