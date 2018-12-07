package com.spyatthehatch.puzzles;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.spyatthehatch.objects.Coordinate;
import com.spyatthehatch.util.ResourceReader;

/**
 * Day six solutions.
 * 
 * @author Bill Everton
 * @version Advent 2018
 */
public class Day06 extends AbstractDay {
	/**
	 * Puzzle resource, list of fabric claims.
	 */
	public static final String RESOURCE = "puzzles/day6-list.txt";
	
	/**
	 * List of Coordinate objects.
	 */
	private List<Coordinate> coords = new ArrayList<Coordinate>();
	
	/**
	 * Map of Coordinate objects, by Id.
	 */
	private Map<Integer, Coordinate>coordMap = new HashMap<Integer, Coordinate>();
	
	/**
	 * The largest x component of the coordinates.
	 */
	private int xMax = 0;
	
	/**
	 * The largest y components of the coordinates.
	 */
	private int yMax = 0;
	
	/**
	 * Constructor for Day 6 puzzles.
	 */
	public Day06() {
		this.dayNum = "6";
		final ResourceReader reader = new ResourceReader(RESOURCE);
		final List<String> input = reader.toList();
		
		int i = 0;
		for(final String s : input) {
			Coordinate c = new Coordinate(s, i);
			this.coords.add(c);
			this.coordMap.put(i, c);
			i++;
		}
	}
	
	/**
	 * Puzzle one solution.
	 */
	public void puzzleOne() {		
		for(final Coordinate c : this.coords) {
			if(c.getX() > this.xMax) {
				this.xMax = c.getX();
			}
			
			if(c.getY() > this.yMax) {
				this.yMax = c.getY();
			}
		}
		LOGGER.trace("Max grid size is: " + this.xMax + "," + this.yMax + ".");
		int[][] grid = new int[this.xMax][this.yMax];

		for (int x=0; x<this.xMax; x++) {
			for (int y=0; y<this.yMax; y++) {
				grid[x][y] = -1;
			}
		}
		
		LOGGER.trace("Calculating Manhattan distances.");
		for (int x=0; x<this.xMax; x++) {
			for (int y=0; y<this.yMax; y++) {
				int closestDistance = Integer.MAX_VALUE;
				
				for(int i=0; i<this.coords.size(); i++) {
					Coordinate c = this.coords.get(i);
					int distance = c.getManhattanDistance(x, y);
					
					if(distance < closestDistance) {
						grid[x][y] = i;
						closestDistance = distance;
					} else if (distance == closestDistance) {
						grid[x][y] = -1;
					}
				}
			}
		}
		
		LOGGER.trace("Removing coordinates with infinite areas.");
		for (int x=0; x<this.xMax; x++) {
			this.coordMap.remove(grid[x][0]);
			this.coordMap.remove(grid[x][this.yMax-1]);
		}
		
		for (int y=0; y<this.yMax; y++) {
			this.coordMap.remove(grid[0][y]);
			this.coordMap.remove(grid[this.xMax-1][y]);
		}
		
		LOGGER.trace("Coords map size: " + this.coordMap.size() + ".");
		
		for (int x=0; x<this.xMax; x++) {
			for (int y=0; y<this.yMax; y++) {
				final int index = grid[x][y];
				
				if(this.coordMap.get(index) == null) {
					grid[x][y] = -1;
				}
			}
		}
		
		LOGGER.trace("Calculating area sizes.");
		for (int x=0; x<this.xMax; x++) {
			for (int y=0; y<this.yMax; y++) {
				final Coordinate c = this.coordMap.get(grid[x][y]);
				
				if(c != null) {
					c.incrementPointCount();
				}
			} 
		}
		
		LOGGER.trace("Finding highest area.");
		int maxArea = 0;
		for(final Map.Entry<Integer, Coordinate> entry : this.coordMap.entrySet()) {
			final Coordinate c = entry.getValue();
			
			if(c.getPointCount() > maxArea) {
				maxArea = c.getPointCount();
			}
		}
		
		LOGGER.debug("Day 6, Puzzle 1 maxArea: " + maxArea);
		this.solutionOne = String.valueOf(maxArea);
	}
	
	/**
	 * Puzzle two solution.
	 */
	public void puzzleTwo() {
		final int SAFE_THRESHOLD = 10000;
		int distances[][] = new int[this.xMax][this.yMax];
		
		LOGGER.trace("Calculating Manhattan distances for all coordinates.");
		for(int x=0; x<this.xMax; x++) {
			for(int y=0; y<this.yMax; y++) {
				for(Coordinate c : this.coords) {
					distances[x][y] += c.getManhattanDistance(x, y);
				}
			}
		}
		
		LOGGER.trace("Counting safe regions.");
		int locations = 0;
		for(int x=0; x<this.xMax; x++) {
			for(int y=0; y<this.yMax; y++) {
				if(distances[x][y] < SAFE_THRESHOLD) {
					locations++;
				}
			}
		}
		
		LOGGER.debug("Day 6, Puzzle 2 locations: " + locations);
		this.solutionTwo = String.valueOf(locations);
	}	
}