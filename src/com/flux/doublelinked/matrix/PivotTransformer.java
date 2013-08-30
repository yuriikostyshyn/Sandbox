package com.flux.doublelinked.matrix;

import java.math.BigDecimal;
import java.math.MathContext;

public class PivotTransformer {
	public static SparsedMatrix transformMatrix(SparsedMatrix matrix, int rowId, int columnId) {
		SparsedMatrix result = new SparsedMatrix();

		BigDecimal pivotValue = matrix.getValue(rowId, columnId);
		if (pivotValue != null) {
			BigDecimal alpha = new BigDecimal(1.0).divide(pivotValue, new MathContext(5));
			matrix.setValue(rowId, columnId, new BigDecimal(1.0));
		}
		return result;
	}
}
