package com.flux.sorting.topological;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GridCreator {

	public Grid createGridFromRelations(List<Relation> relations) {
		int gridSize = countGridSize(relations);

		Grid result = new Grid(gridSize);

		for (Relation relation : relations) {
			int predecessor = relation.getPredecessor();
			int successor = relation.getSuccessor();
			result.getTop()[predecessor - 1].put(successor - 1); //number of element starts from 1
			result.getCount()[successor - 1]++;
		}

		return result;
	}

	private int countGridSize(List<Relation> relations) {
		Map<Integer, Integer> elementMap = new HashMap<Integer, Integer>();

		for (Relation relation : relations) {
			elementMap.put(relation.getPredecessor(), null);
			elementMap.put(relation.getSuccessor(), null);
		}
		return elementMap.size();
	}
}
