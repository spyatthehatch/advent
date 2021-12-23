package com.spyatthehatch.puzzles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Abstract class for all Day classes to extend.
 * 
 * @author Bill Everton
 * @version Advent 2018
 */
public abstract class AbstractDay implements Day {
	/**
	 * Logger.
	 */
	protected static final Logger LOGGER = 
	   LoggerFactory.getLogger(AbstractDay.class);
	
	/**
	 * The Advent year for this puzzle.
	 */
	protected String year = null;
	
	/**
	 * Day number for this day.
	 */
	protected String dayNum = null;
	
	/**
	 * Solution to puzzle one.
	 */
	protected String solutionOne = null;
	
	/**
	 * Solution to puzzle two.
	 */
	protected String solutionTwo = null;
	
	/**
	 * Output the solutions to this day's puzzles.
	 */
	public void report() {
		LOGGER.info(this.year + " Day " + this.dayNum + ", Puzzle 1 solution: " + this.solutionOne);
		LOGGER.info(this.year + " Day " + this.dayNum + ", Puzzle 2 solution: " + this.solutionTwo);
	}
}