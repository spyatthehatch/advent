package com.spyatthehatch.puzzles.y2018;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.spyatthehatch.objects.y2018.Acres;
import com.spyatthehatch.puzzles.AbstractDay;
import com.spyatthehatch.util.ResourceReader;

/**
 * Day eighteen solutions.
 * 
 * @author Bill Everton
 * @version Advent 2018
 */
public class Day18 extends AbstractDay {
	/*
	--- Day 18: Settlers of The North Pole ---
	On the outskirts of the North Pole base construction project, many Elves are
	collecting lumber.
	*/
	
	/**
	 * Puzzle resource.
	 */
	public static final String RESOURCE = "puzzles/2018/day18-list.txt";
	
	/**
	 * Given width of acres.
	 */
	public static final int ACRES_WIDTH = 50;
	
	/**
	 * Given height of acres.
	 */
	public static final int ACRES_HEIGHT = 50;
	
	/**
	 * Map created from puzzle input.
	 */
	private char[][] map = new char[ACRES_WIDTH][ACRES_HEIGHT];
		
	/**
	 * Constructor for Day 18 puzzles.
	 */
	public Day18() {
		this.dayNum = "18";
		final ResourceReader reader = new ResourceReader(RESOURCE);
		final List<String> input = reader.toList();
		
		for(int y=0; y<ACRES_HEIGHT; y++){
			final String s = input.get(y);
			
			for(int x=0; x<ACRES_WIDTH; x++){
				map[x][y] = s.charAt(x);
			}
		}
	}
	
	/**
	 * Puzzle one solution.
	 */
	public void puzzleOne() {
		/*
		The lumber collection area is 50 acres by 50 acres; each acre can be
		either open ground (.), trees (|), or a lumberyard (#). You take a scan
		of the area (your puzzle input).

		Strange magic is at work here: each minute, the landscape looks entirely
		different. In exactly one minute, an open acre can fill with trees, a
		wooded acre can be converted to a lumberyard, or a lumberyard can be
		cleared to open ground (the lumber having been sent to other projects).
		
		The change to each acre is based entirely on the contents of that acre as
		well as the number of open, wooded, or lumberyard acres adjacent to it at
		the start of each minute. Here, "adjacent" means any of the eight acres
		surrounding that acre. (Acres on the edges of the lumber collection area
		might have fewer than eight adjacent acres; the missing acres aren't
		counted.)
		
		In particular:
		
		An open acre will become filled with trees if three or more adjacent
		acres contained trees. Otherwise, nothing happens.
		An acre filled with trees will become a lumberyard if three or more
		adjacent acres were lumberyards. Otherwise, nothing happens.
		An acre containing a lumberyard will remain a lumberyard if it was
		adjacent to at least one other lumberyard and at least one acre
		containing trees. Otherwise, it becomes open.
		These changes happen across all acres simultaneously, each of them using
		the state of all acres at the beginning of the minute and changing to
		their new form by the end of that same minute. Changes that happen during
		the minute don't affect each other.
		
		For example, suppose the lumber collection area is instead only 10 by 10
		acres with this initial configuration:
		
		Initial state:
		.#.#...|#.
		.....#|##|
		.|..|...#.
		..|#.....#
		#.#|||#|#|
		...#.||...
		.|....|...
		||...#|.#|
		|.||||..|.
		...#.|..|.
		
		After 1 minute:
		.......##.
		......|###
		.|..|...#.
		..|#||...#
		..##||.|#|
		...#||||..
		||...|||..
		|||||.||.|
		||||||||||
		....||..|.
		
		After 2 minutes:
		.......#..
		......|#..
		.|.|||....
		..##|||..#
		..###|||#|
		...#|||||.
		|||||||||.
		||||||||||
		||||||||||
		.|||||||||
		
		After 3 minutes:
		.......#..
		....|||#..
		.|.||||...
		..###|||.#
		...##|||#|
		.||##|||||
		||||||||||
		||||||||||
		||||||||||
		||||||||||
		
		After 4 minutes:
		.....|.#..
		...||||#..
		.|.#||||..
		..###||||#
		...###||#|
		|||##|||||
		||||||||||
		||||||||||
		||||||||||
		||||||||||
		
		After 5 minutes:
		....|||#..
		...||||#..
		.|.##||||.
		..####|||#
		.|.###||#|
		|||###||||
		||||||||||
		||||||||||
		||||||||||
		||||||||||
		
		After 6 minutes:
		...||||#..
		...||||#..
		.|.###|||.
		..#.##|||#
		|||#.##|#|
		|||###||||
		||||#|||||
		||||||||||
		||||||||||
		||||||||||
		
		After 7 minutes:
		...||||#..
		..||#|##..
		.|.####||.
		||#..##||#
		||##.##|#|
		|||####|||
		|||###||||
		||||||||||
		||||||||||
		||||||||||
		
		After 8 minutes:
		..||||##..
		..|#####..
		|||#####|.
		||#...##|#
		||##..###|
		||##.###||
		|||####|||
		||||#|||||
		||||||||||
		||||||||||
		
		After 9 minutes:
		..||###...
		.||#####..
		||##...##.
		||#....###
		|##....##|
		||##..###|
		||######||
		|||###||||
		||||||||||
		||||||||||
		
		After 10 minutes:
		.||##.....
		||###.....
		||##......
		|##.....##
		|##.....##
		|##....##|
		||##.####|
		||#####|||
		||||#|||||
		||||||||||
		
		After 10 minutes, there are 37 wooded acres and 31 lumberyards.
		Multiplying the number of wooded acres by the number of lumberyards gives
		the total resource value after ten minutes: 37 * 31 = 1147.
		
		What will the total resource value of the lumber collection area be after
		10 minutes?
		*/
		
		final int TEN_MINUTES = 10;
		Acres acres = new Acres(map);
		
		for(int minute=1; minute<=TEN_MINUTES; minute++){
			acres.update();
		}
		
		final int value = acres.getValue();
		LOGGER.debug("Day 18, Puzzle 1: resource value is " + value + ".");
		this.solutionOne = String.valueOf(value);
	}
	
	/**
	 * Puzzle two solution.
	 */
	public void puzzleTwo() {
		/*
		--- Part Two ---
		This important natural resource will need to last for at least thousands
		of years. Are the Elves collecting this lumber sustainably?

		What will the total resource value of the lumber collection area be after
		1000000000 minutes?
		*/
		
		final int target = 1000000000;
		final int increment = 100000;
		final Acres acres = new Acres(map);
		final Map<Integer, Integer> values = new HashMap<Integer, Integer>();
		final Map<Integer, Integer> minutes = new HashMap<Integer, Integer>();
		int targetValue = 0;
		
		for(int minute=1; minute <= target; minute++){
			acres.update();
			
			if(minute % increment == 0){
				final int value = acres.getValue();
				LOGGER.trace("Minute " + minute + " value:" + value);
				
				final Integer previous = values.put(value, minute);
				minutes.put(minute, value);

				if(previous != null){
					final int delta = minute - previous;
					LOGGER.trace("Pattern detected every " + delta + " minutes.");
					
					targetValue = minutes.get(target % delta);;
					break;
				}
			}
		}
		
		LOGGER.debug("Day 18, Puzzle 2: resource value is " + targetValue + ".");
		this.solutionTwo = String.valueOf(targetValue);
	}
}