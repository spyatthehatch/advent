package com.spyatthehatch.puzzles.y2019;

import java.util.ArrayList;
import java.util.List;

import com.spyatthehatch.objects.y2018.Computer;
import com.spyatthehatch.puzzles.AbstractDay;
import com.spyatthehatch.util.ResourceReader;

/**
 * Day five solutions.
 * 
 * @author Bill Everton
 * @version Advent 2019
 */
public class Day05 extends AbstractDay {
	/*

	*/
	
	/**
	 * Puzzle resource.
	 */
	public static final String RESOURCE = "puzzles/2019/day05-list.txt";
	
	private int[] origArray;
	
	/**
	 * Constructor for Day 5 puzzles.
	 */
	public Day05() {
		LOGGER.debug("Day 5: Sunny with a Chance of Asteroids");
		this.dayNum = "5";
		final ResourceReader reader = new ResourceReader(RESOURCE);
		final String s = reader.toString();
		final String[] stringArray = s.split(",");
		
		this.origArray = new int[stringArray.length];
		for(int i=0; i < stringArray.length; i++){
			this.origArray[i] = Integer.valueOf(stringArray[i]);
		}
		
		LOGGER.trace("Read in " + this.origArray.length + " values.");
	}
	
	/**
	 * Puzzle one solution.
	 */
	public void puzzleOne() {
		/*

		*/
		Computer computer = new Computer(this.origArray);
		final int output = computer.processScript(1);
				
		this.solutionOne = String.valueOf(output);
		LOGGER.debug("Day 5, Puzzle 1 solution: " + output);
	}
	
	/**
	 * Puzzle two solution.
	 */
	public void puzzleTwo() {
		/*

		*/
			
//		this.solutionTwo = String.valueOf();
		LOGGER.debug("Day 5, Puzzle 2 solution: ");
	}
}