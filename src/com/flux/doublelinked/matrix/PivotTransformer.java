package com.flux.doublelinked.matrix;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.NoSuchElementException;

import com.flux.doublelinked.matrix.SparsedMatrix.Node;

public class PivotTransformer {
	public static SparsedMatrix transformMatrix(SparsedMatrix matrix, int rowId, int columnId) {
		SparsedMatrix result = new SparsedMatrix(matrix.getWidth(), matrix.getHeight());
		// TODO copy matrix
		Node pivotRowHeader = getRowHeader(rowId, matrix);
		Node pivotColumnHeader = getColumnHeader(columnId, matrix);
		Node[] ptr = new Node[matrix.getWidth()];
		Node[] columnHeaders = new Node[matrix.getWidth()];
		Node[] rowHeaders = new Node[matrix.getHeight()];
		initColumnHeadersReferences(columnHeaders, matrix.getHeader());
		initRowHeadersReferences(rowHeaders, matrix.getHeader());

		BigDecimal pivotValue = matrix.getValue(rowId, columnId);

		init(pivotValue, pivotRowHeader, ptr, matrix.getHeader());

		pivotColumnHeader = pivotColumnHeader.getTop();
		while (pivotColumnHeader.getColumn() >= 0) {
			int i = pivotColumnHeader.getRow();
			if (i != rowId) {
				Node p = rowHeaders[i];
				Node p1 = p.getLeft();
				pivotRowHeader = pivotRowHeader.getLeft();
				while (pivotRowHeader.getColumn() > 0) {
					int j = pivotRowHeader.getColumn();
					while (p1.getColumn() > j) {
						p = p1;
						p1 = p.getLeft();
					}
					if (p1.getColumn() == j) {
						// TODO pivotal transformation
					} else {
						// TODO insert new element
					}

					if (j == columnId) {
						break;
					} else if (j < 0) {
						pivotColumnHeader.setValue(pivotColumnHeader.getValue().divide(pivotValue.negate()));
					}
					pivotRowHeader = pivotRowHeader.getLeft();
				}
			}
			pivotColumnHeader = pivotColumnHeader.getTop();
		}

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

	private static void initColumnHeadersReferences(Node[] columnHeaders, Node header) {
		Node current = header.getLeft();
		while (current.getColumn() >= 0) {
			columnHeaders[current.getColumn()] = current;
			current = current.getLeft();
		}
	}

	private static void initRowHeadersReferences(Node[] rowHeaders, Node header) {
		Node current = header.getTop();
		while (current.getRow() >= 0) {
			rowHeaders[current.getRow()] = current;
			current = current.getTop();
		}
	}

	private static void init(BigDecimal pivotValue, Node rowHeader, Node[] ptr, Node header) {
		rowHeader = rowHeader.getLeft();
		Node currentHeaderElement = header.getLeft();
		while (rowHeader.getColumn() >= 0) {
			setModifiedPivotRowElementValue(pivotValue, rowHeader);
			currentHeaderElement = initReferenceToColumnHeader(ptr, rowHeader, currentHeaderElement);
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
