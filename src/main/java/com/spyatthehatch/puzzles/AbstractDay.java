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
		LOGGER.info("Day " + dayNum + ", Puzzle 1 solution: " + solutionOne);
		LOGGER.info("Day " + dayNum + ", Puzzle 2 solution: " + solutionTwo);
	}
}