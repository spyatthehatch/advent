package com.spyatthehatch.puzzles;

/**
 * Interface for all Day objects to implement.
 * 
 * @author Bill Everton
 * @version Advent 2018
 */
public interface Day {
	/**
	 * Calculate the solution to puzzle one.
	 */
	public void puzzleOne();
	
	/**
	 * Calculate the solution to puzzle two.
	 */
	public void puzzleTwo();
	
	/**
	 * Output the solutions to this day's puzzles.
	 */
	public void report();
}