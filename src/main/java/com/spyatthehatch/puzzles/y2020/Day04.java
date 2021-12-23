package com.spyatthehatch.puzzles.y2020;

import java.util.ArrayList;
import java.util.List;

import com.spyatthehatch.puzzles.AbstractDay;
import com.spyatthehatch.util.ResourceReader;

/**
 * Day One solutions.
 * 
 * @author Bill Everton
 * @version Advent 2020
 */
public class Day04 extends AbstractDay {

	
	/**
	 * Puzzle resource.
	 */
	public static final String RESOURCE = "puzzles/2020/day04-list.txt";
	
	/**
	 * Constructor for Day One puzzles.
	 */
	public Day04() {	
		LOGGER.debug("Day 4: Passport Processing");
		this.year = "2020";
		this.dayNum = "4";
		
		final ResourceReader reader = new ResourceReader(RESOURCE);

	}
	
	/**
	 * Puzzle one solution.
	 */
	public void puzzleOne() {
		int answer = 0;
		
		this.solutionOne = String.valueOf(answer);
	}
	
	/**
	 * Puzzle two solution.
	 */
	public void puzzleTwo() {
		int answer = 0;
		
		this.solutionTwo = String.valueOf(answer);
	}
}