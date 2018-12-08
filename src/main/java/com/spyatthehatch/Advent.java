package com.spyatthehatch;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spyatthehatch.puzzles.*;

/**
 * Advent app.
 * 
 * @author Bill Everton
 * @version Advent 2018
 */
public class Advent {
	/*
		   -------Part 1--------   -------Part 2--------
	Day       Time  Rank  Score       Time  Rank  Score
	  8   16:36:23  7437      0   17:21:33  7030      0
	  7   00:43:23  1184      0       >24h  8773      0
	  6   01:03:57  1071      0   01:16:34   727      0
	  5   00:58:01  2737      0   01:35:01  2926      0
	  4   02:20:54  2808      0   03:18:33  3182      0
	  3   01:29:12  3102      0   01:39:27  2782      0
	  2   00:29:11  2141      0   00:40:30  1667      0
	  1   00:05:14   777      0   00:54:03  1551      0
	*/
	
	/**
	 * Logger.
	 */
	private static final Logger LOGGER = 
	   LoggerFactory.getLogger(Advent.class);
	
	/**
	 * Working ArrayList of Days.
	 */
	private static List<Day> days = new ArrayList<Day>();
	
	/**
	 * Main entry point of Advent app.
	 * 
	 * @param args Arguments - none.
	 */
	public static void main (String[] args) {		
		LOGGER.info("Begin Advent app.");
		
		days.add(new Day01());
		days.add(new Day02());
		days.add(new Day03());
		days.add(new Day04());
		days.add(new Day05());
		days.add(new Day06());
		days.add(new Day07());
		days.add(new Day08());
		
		for(final Day day : days) {
			day.puzzleOne();
			day.puzzleTwo();
		}
		
		printSummary();
		LOGGER.info("Advent app complete.");
	}
	
	/**
	 * Print the summary for all days.
	 */
	public static void printSummary() {
		LOGGER.info("*** Summary of Advent Puzzles ***");
		
		for (final Day day : days) {
			day.report();
		}
	}
}