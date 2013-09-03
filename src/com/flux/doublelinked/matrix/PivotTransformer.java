package com.flux.doublelinked.matrix;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.NoSuchElementException;

import com.flux.doublelinked.matrix.SparsedMatrix.Node;

public class PivotTransformer {
	public static SparsedMatrix transformMatrix(SparsedMatrix matrix, int rowId, int columnId) {
		SparsedMatrix result = new SparsedMatrix(matrix.getWidth(), matrix.getHeight());
		//TODO copy matrix
		Node p0 = getRowHeader(rowId, matrix);
		Node q0 = getColumnHeader(columnId, matrix);
		Node[] ptr = new Node[matrix.getWidth()];

		BigDecimal pivotValue = matrix.getValue(rowId, columnId);

		init(pivotValue, p0, ptr, matrix.getHeader());

		return result;
	}

	private static Node getRowHeader(int rowId, SparsedMatrix matrix) {
		Node current = matrix.getHeader().getTop();

		while (current.getRow() > rowId) {
			current = current.getTop();
		}

		if (current.getRow() == rowId) {
			return current;
		} else {
			throw new NoSuchElementException();
		}
	}

	private static Node getColumnHeader(int columnId, SparsedMatrix matrix) {
		Node current = matrix.getHeader().getLeft();

		while (current.getColumn() > columnId) {
			current = current.getLeft();
		}

		if (current.getColumn() == columnId) {
			return current;
		} else {
			throw new NoSuchElementException();
		}
	}

	private static void init(BigDecimal pivotValue, Node rowHeader, Node[] ptr, Node header) {
		Node current = rowHeader.getLeft();
		Node currentHeaderElement = header.getLeft();
		while (current.getColumn() > 0) {
			setModifiedPivotRowElementValue(pivotValue, current);
			currentHeaderElement = initReferenceToColumnHeader(ptr, current, currentHeaderElement);
		}
	}

	private static Node initReferenceToColumnHeader(Node[] ptr, Node current, Node currentHeaderElement) {
	    while (currentHeaderElement.getColumn() > current.getColumn()) {
	    	currentHeaderElement = currentHeaderElement.getLeft(); // hmm. Maybe we don't need next condition statement
	    }
	    if (currentHeaderElement.getColumn() == current.getColumn()) {
	    	ptr[currentHeaderElement.getColumn()] = currentHeaderElement;
	    }
	    return currentHeaderElement;
    }

	private static void setModifiedPivotRowElementValue(BigDecimal pivotValue, Node current) {
	    current.setValue(current.getValue().divide(pivotValue, new MathContext(5)));
    }
}
