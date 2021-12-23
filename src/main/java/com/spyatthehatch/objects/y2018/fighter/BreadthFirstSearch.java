package com.spyatthehatch.objects.y2018.fighter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * Breadth First Search Algorithm, given a map.
 * 
 * @author Bill Everton
 * @version Advent 2018
 */
public class BreadthFirstSearch {
	/**
	 * Map of chars denoting open space and objects.
	 */
	private char[][] map;
	
	/**
	 * Starting square.
	 */
	private Square start;
	
	/**
	 * Constructor.
	 * 
	 * @param map Map of chars denoting open space and objects.
	 * @param start Starting square.
	 */
	public BreadthFirstSearch(final char[][] map, final Square start){
		this.map = map;
		this.start = start;
	}
	
	/**
	 * Get the distance of the shortest path to the goal square using the
	 * Breadth First Search algorithm.
	 * 
	 * @param goal Final square to reach.
	 * @return distance from start to goal in steps.
	 */
	public int getShortestPath(final Square goal){
		if(this.start.equals(goal)){
			return 0;
		}
		
		final Queue<Square> queue = new LinkedList<Square>();
		final List<Square> explored = new ArrayList<Square>();
		final Map<Square, Square>parents = new HashMap<Square, Square>();

		queue.add(this.start);
		explored.add(this.start);
		
		while(!queue.isEmpty()){
			Square current = queue.remove();
			
			if(current.equals(goal)){
				final List<Square> path = new ArrayList<Square>();
				Square square = goal;
				while(square != null){
					path.add(square);
					square = parents.get(square);
				}
				Collections.reverse(path);

				return path.size();
				
			} else {
				List<Square> neighbors = current.getOpenNeighbors(this.map);			
				neighbors.removeAll(explored);				
				queue.addAll(neighbors);
				explored.addAll(neighbors);
				
				for(final Square n : neighbors){
					parents.put(n, current);
				}
			}
		}
		
		return Integer.MAX_VALUE;
	}
}