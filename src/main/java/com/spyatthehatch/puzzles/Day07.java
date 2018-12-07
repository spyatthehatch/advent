package com.spyatthehatch.puzzles;

import java.util.List;

import com.spyatthehatch.util.ResourceReader;

/**
 * Day seven solutions.
 * 
 * @author Bill Everton
 * @version Advent 2018
 */
public class Day07 extends AbstractDay {
	/**
	 * Puzzle resource, list of fabric claims.
	 */
	public static final String RESOURCE = "puzzles/day7-list.txt";
	
	/**
	 * Constructor for Day 7 puzzles.
	 */
	public Day07() {
		this.dayNum = "7";
		final ResourceReader reader = new ResourceReader(RESOURCE);
		final List<String> input = reader.toList();
	}
	
	/**
	 * Puzzle one solution.
	 */
	public void puzzleOne() {
		
		this.solutionOne = "foo";
	}
	
	/**
	 * Puzzle two solution.
	 */
	public void puzzleTwo() {
		
		this.solutionTwo = "bar";
	}
}