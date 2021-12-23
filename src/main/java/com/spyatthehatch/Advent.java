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
//		LOGGER.info("Begin Advent 2020 puzzles.");
//		days.add(new com.spyatthehatch.puzzles.y2020.Day01());
//		days.add(new com.spyatthehatch.puzzles.y2020.Day02());
//		days.add(new com.spyatthehatch.puzzles.y2020.Day03());
//		days.add(new com.spyatthehatch.puzzles.y2020.Day04());
		
		LOGGER.info("Begin Advent 2021 puzzles.");
//		days.add(new com.spyatthehatch.puzzles.y2021.Day01());
//		days.add(new com.spyatthehatch.puzzles.y2021.Day02());
//		days.add(new com.spyatthehatch.puzzles.y2021.Day03());
//		days.add(new com.spyatthehatch.puzzles.y2021.Day04());
//		days.add(new com.spyatthehatch.puzzles.y2021.Day05());
//		days.add(new com.spyatthehatch.puzzles.y2021.Day06());
//		days.add(new com.spyatthehatch.puzzles.y2021.Day07());
//		days.add(new com.spyatthehatch.puzzles.y2021.Day08());
//		days.add(new com.spyatthehatch.puzzles.y2021.Day09());
//		days.add(new com.spyatthehatch.puzzles.y2021.Day10());
//		days.add(new com.spyatthehatch.puzzles.y2021.Day11());
//		days.add(new com.spyatthehatch.puzzles.y2021.Day12());
//		days.add(new com.spyatthehatch.puzzles.y2021.Day13());
//		days.add(new com.spyatthehatch.puzzles.y2021.Day14());
//		days.add(new com.spyatthehatch.puzzles.y2021.Day15());
//		days.add(new com.spyatthehatch.puzzles.y2021.Day16());
//		days.add(new com.spyatthehatch.puzzles.y2021.Day17());
		days.add(new com.spyatthehatch.puzzles.y2021.Day18());
		
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