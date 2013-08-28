package com.flux.doublelinked.matrix;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.NoSuchElementException;

import com.flux.doublelinked.matrix.SparsedMatrix.ColumnIterator;

public class SparsedMatrix {

	private Node header;

	public SparsedMatrix() {
		header = new Node(-1, -1, null, null, null);
		header.setTop(header);
		header.setLeft(header);
	}

	public void insertNewRow(int rowId) {
		DimensionIterator<BigDecimal> iterator = iterator(Dimension.VERTICAL);
		while (iterator.hasNext()) {
			iterator.next();
			if (iterator.rowId() < rowId) {
				iterator.addBefore(rowId, -1, null);
			}
		}
	}

	public void insertNewRow(int rowId, DimensionIterator<BigDecimal> rowIterator) {

	}

	public void insertNewColumn(int columnId) {
		DimensionIterator<BigDecimal> iterator = iterator(Dimension.HORISONTAL);
		while (iterator.hasNext()) {
			iterator.next();
			if (iterator.columnId() < columnId) {
				iterator.addBefore(-1, columnId, null);
			}
		}
	}

	public void insertNewColumn(int columnId, DimensionIterator<BigDecimal> columnIterator) {

	}

	// TODO DimensionIterator

	protected DimensionIterator<BigDecimal> iterator(Dimension dimension) {
		return iterator(dimension, -1);
	}

	public DimensionIterator<BigDecimal> iterator(Dimension dimension, int id) {
		return dimension.equals(Dimension.HORISONTAL) ? new ColumnIterator(id) : new RowIterator(id);
	}

	protected class RowIterator implements DimensionIterator<BigDecimal> {
		private Node start;
		private Node previous;
		private Node current;

		protected RowIterator(int id) {
			if (id < 0) {
				this.start = header;
				this.current = header;
				this.previous = header;
			} else {
				Node that = header.getLeft();
				while (that.getColumn() != id) {
					if (that.getColumn() > id) {
						that = that.getLeft();
					} else {
						throw new NoSuchElementException();
					}
				}
				start = that;
			}
		}

		@Override
		public boolean hasNext() {
			return current.getTop() != start;
		}

		@Override
		public BigDecimal next() {
			if (hasNext()) {
				previous = current;
				current = current.getTop();
				return current.getValue();
			}

			return null;
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub

		}

		@Override
		public void add(int row, int column, BigDecimal newElement) {
			Node newNode = new Node(row, column, newElement, current.getTop(), null);
			current.setTop(newNode);
			previous = current;
			current = newNode;
		}

		@Override
		public void addBefore(int row, int column, BigDecimal newElement) {
			if (previous != start && current != start) {
				Node newNode = new Node(row, column, newElement, current, null);
				previous.setTop(newNode);
				previous = newNode;
			} else {
				throw new UnsupportedOperationException("you can't use this method before start of iteration");
			}
		}

		@Override
		public int rowId() {
			return current.getRow();
		}

		@Override
		public int columnId() {
			return current.getColumn();
		}
	}

	protected class ColumnIterator implements DimensionIterator<BigDecimal> {

		private Node start;
		private Node previous;
		private Node current;

		protected ColumnIterator(int id) {
			if (id < 0) {
				this.start = header;
				this.current = header;
				this.previous = header;
			} else {
				Node that = header.getLeft();
				while (that.getColumn() != id) {
					if (that.getColumn() > id) {
						that = that.getLeft();
					} else {
						throw new NoSuchElementException();
					}
				}

				start = that;
			}
		}

		@Override
		public boolean hasNext() {
			return current.getLeft() != start;
		}

		@Override
		public BigDecimal next() {
			if (hasNext()) {
				if (previous == null) {
					previous = current;
				}
				current = current.getLeft();
				return current.getValue();
			}

			return null;
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub

		}

		@Override
		public void add(int row, int column, BigDecimal newElement) {
			Node newNode = new Node(row, column, newElement, current.getLeft(), null);
			current.setLeft(newNode);
			previous = current;
			current = newNode;
		}

		@Override
		public void addBefore(int row, int column, BigDecimal newElement) {
			if (previous != start && current != start) {
				Node newNode = new Node(row, column, newElement, current, null);
				previous.setLeft(newNode);
				previous = newNode;
			} else {
				throw new UnsupportedOperationException("you can't use this method before start of iteration");
			}
		}

		@Override
		public int rowId() {
			return current.getRow();
		}

		@Override
		public int columnId() {
			return current.getColumn();
		}
	}

	private static final class Node {
		private int row;
		private int column;
		private Node top;
		private Node left;
		private BigDecimal value;

		public Node(int row, int column, BigDecimal value, Node top, Node left) {
			this.row = row;
			this.column = column;
			this.value = value;
			this.top = top;
			this.left = left;
		}

		public int getRow() {
			return row;
		}

		public void setRow(int row) {
			this.row = row;
		}

		public int getColumn() {
			return column;
		}

		public void setColumn(int column) {
			this.column = column;
		}

		public Node getTop() {
			return top;
		}

		public void setTop(Node top) {
			this.top = top;
		}

		public Node getLeft() {
			return left;
		}

		public void setLeft(Node left) {
			this.left = left;
		}

		public BigDecimal getValue() {
			return value;
		}

		public void setValue(BigDecimal value) {
			this.value = value;
		}
	}

	public enum Dimension {
		HORISONTAL(1), VERTICAL(2);

		private final int dimensionNumber;

		private Dimension(int dimensionNumber) {
			this.dimensionNumber = dimensionNumber;
		}

		public int getDimensionNumber() {
			return dimensionNumber;
		}
	}
}
