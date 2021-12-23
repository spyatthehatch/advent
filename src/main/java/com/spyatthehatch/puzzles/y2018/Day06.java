package com.spyatthehatch.puzzles.y2018;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.spyatthehatch.objects.y2018.Coordinate;
import com.spyatthehatch.puzzles.AbstractDay;
import com.spyatthehatch.util.ResourceReader;

/**
 * Day six solutions.
 * 
 * @author Bill Everton
 * @version Advent 2018
 */
public class Day06 extends AbstractDay {
	/*
	--- Day 6: Chronal Coordinates ---
	The device on your wrist beeps several times, and once again you feel like
	you're falling.
	
	"Situation critical," the device announces. "Destination indeterminate.
	Chronal interference detected. Please specify new target coordinates."
	
	The device then produces a list of coordinates (your puzzle input). Are they
	places it thinks are safe or dangerous? It recommends you check manual page
	729. The Elves did not give you a manual.
	
	If they're dangerous, maybe you can minimize the danger by finding the
	coordinate that gives the largest distance from the other points.
	*/
	
	/**
	 * Puzzle resource, list of coordinates.
	 */
	public static final String RESOURCE = "puzzles/2018/day6-list.txt";
	
	/**
	 * List of Coordinate objects.
	 */
	private List<Coordinate> coords = new ArrayList<Coordinate>();
	
	/**
	 * Map of Coordinate objects, by Id.
	 */
	private Map<Integer, Coordinate>coordMap = new HashMap<Integer,
		Coordinate>();
	
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
			Coordinate c = new Coordinate(s);
			this.coords.add(c);
			this.coordMap.put(i, c);
			i++;
		}
	}
	
	/**
	 * Puzzle one solution.
	 */
	public void puzzleOne() {
		/*
		Using only the Manhattan distance, determine the area around each
		coordinate by counting the number of integer X,Y locations that are
		closest to that coordinate (and aren't tied in distance to any other
		coordinate).

		Your goal is to find the size of the largest area that isn't infinite.
		For example, consider the following list of coordinates:
		
		1, 1
		1, 6
		8, 3
		3, 4
		5, 5
		8, 9
		
		If we name these coordinates A through F, we can draw them on a grid,
		putting 0,0 at the top left:
		
		..........
		.A........
		..........
		........C.
		...D......
		.....E....
		.B........
		..........
		..........
		........F.
		
		This view is partial - the actual grid extends infinitely in all
		directions. Using the Manhattan distance, each location's closest
		coordinate can be determined, shown here in lowercase:
		
		aaaaa.cccc
		aAaaa.cccc
		aaaddecccc
		aadddeccCc
		..dDdeeccc
		bb.deEeecc
		bBb.eeee..
		bbb.eeefff
		bbb.eeffff
		bbb.ffffFf
		
		Locations shown as . are equally far from two or more coordinates, and so
		they don't count as being closest to any.
		
		In this example, the areas of coordinates A, B, C, and F are infinite -
		while not shown here, their areas extend forever outside the visible
		grid. However, the areas of coordinates D and E are finite: D is closest
		to 9 locations, and E is closest to 17 (both including the coordinate's
		location itself). Therefore, in this example, the size of the largest
		area is 17.
		
		What is the size of the largest area that isn't infinite?
		*/
		
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
		for(final Map.Entry<Integer, Coordinate> entry : this.coordMap
			.entrySet()) {
			
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
		/*
		--- Part Two ---
		On the other hand, if the coordinates are safe, maybe the best you can do
		is try to find a region near as many coordinates as possible.
		
		For example, suppose you want the sum of the Manhattan distance to all of
		the coordinates to be less than 32. For each location, add up the
		distances to all of the given coordinates; if the total of those
		distances is less than 32, that location is within the desired region.
		Using the same coordinates as above, the resulting region looks like
		this:
		
		..........
		.A........
		..........
		...###..C.
		..#D###...
		..###E#...
		.B.###....
		..........
		..........
		........F.
		
		In particular, consider the highlighted location 4,3 located at the top
		middle of the region. Its calculation is as follows, where abs() is the
		absolute value function:
		
		Distance to coordinate A: abs(4-1) + abs(3-1) =  5
		Distance to coordinate B: abs(4-1) + abs(3-6) =  6
		Distance to coordinate C: abs(4-8) + abs(3-3) =  4
		Distance to coordinate D: abs(4-3) + abs(3-4) =  2
		Distance to coordinate E: abs(4-5) + abs(3-5) =  3
		Distance to coordinate F: abs(4-8) + abs(3-9) = 10
		Total distance: 5 + 6 + 4 + 2 + 3 + 10 = 30
		
		Because the total distance to all coordinates (30) is less than 32, the
		location is within the region.
		
		This region, which also includes coordinates D and E, has a total size of
		16.
		
		Your actual region will need to be much larger than this example, though,
		instead including all locations with a total distance of less than 10000.
		
		What is the size of the region containing all locations which have a
		total distance to all given coordinates of less than 10000?
		*/
		
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