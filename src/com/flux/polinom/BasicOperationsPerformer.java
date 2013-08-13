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

				while (iterator.hasNext()) {
					Coefficient currentElement = iterator.next();
					while (resultIterator.hasNext()) {
						Coefficient currentResultElement = resultIterator.next();
						int comparationResult = currentElement.compareTo(currentResultElement);
						if (comparationResult < 0) {
							continue;
						} else if (comparationResult > 0) {
							resultIterator.add(currentResultElement);
							break;
						} else if (comparationResult == 0) {
							currentResultElement.addCoefficientValue(currentElement.getCoefficientValue());
							break;
						}
					}
				}

			}
			return result;
		}
		return null;
	}
}
