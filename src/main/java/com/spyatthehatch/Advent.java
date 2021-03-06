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
		days.add(new Day09());
		days.add(new Day10());
		days.add(new Day11());
		days.add(new Day12());
		days.add(new Day13());
		days.add(new Day14());
		days.add(new Day15());
		days.add(new Day16());
		days.add(new Day17());
		days.add(new Day18());
		days.add(new Day19());
		days.add(new Day20());
		days.add(new Day21());
		days.add(new Day22());
		days.add(new Day23());
		days.add(new Day24());
		days.add(new Day25());
		
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