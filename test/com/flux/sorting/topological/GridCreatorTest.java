package com.flux.sorting.topological;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;

public class GridCreatorTest {
	private static final int EXPECTED_GRID_SIZE = 9;
	private static final int[] EXPECTED_COUNTS = new int[] { 0, 1, 1, 1, 2, 2, 1, 2, 0 };
	private GridCreator underTest;

	@Before
	public void setUp() {
		underTest = new GridCreator();
	}

	@Test
	public void shouldReturnGridFromGivenRelations() {
		List<Relation> relations = createRelationsList();

		Grid actualGrid = underTest.createGridFromRelations(relations);

		assertEquals(EXPECTED_GRID_SIZE, actualGrid.getCount().length);
		assertEquals(EXPECTED_GRID_SIZE, actualGrid.getTop().length);
		
		assertArrayEquals(EXPECTED_COUNTS, actualGrid.getCount());
		

	}

	private List<Relation> createRelationsList() {
		List<Relation> result = new ArrayList<Relation>();
		result.add(new Relation(9, 2));
		result.add(new Relation(3, 7));
		result.add(new Relation(7, 5));
		result.add(new Relation(5, 8));
		result.add(new Relation(8, 6));
		result.add(new Relation(4, 6));
		result.add(new Relation(1, 3));
		result.add(new Relation(7, 4));
		result.add(new Relation(9, 5));
		result.add(new Relation(2, 8));
		return result;
	}

}
