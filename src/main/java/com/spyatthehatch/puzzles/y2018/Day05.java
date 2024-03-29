package com.spyatthehatch.puzzles.y2018;

import org.apache.commons.lang3.StringUtils;

import com.spyatthehatch.Constants;
import com.spyatthehatch.puzzles.AbstractDay;
import com.spyatthehatch.util.ResourceReader;

/**
 * Day five solutions.
 * 
 * @author Bill Everton
 * @version Advent 2018
 */
public class Day05 extends AbstractDay {
	/*
	--- Day 5: Alchemical Reduction ---
	You've managed to sneak in to the prototype suit manufacturing lab. The Elves
	are making decent progress, but are still struggling with the suit's size
	reduction capabilities.
	
	While the very latest in 1518 alchemical technology might have solved their
	problem eventually, you can do better. You scan the chemical composition of the
	suit's material and discover that it is formed by extremely long polymers (one
	of which is available as your puzzle input).
	*/
	
	/**
	 * Puzzle resource, list of polymers.
	 */
	public static final String RESOURCE = "puzzles/2018/day5-list.txt";
	
	/**
	 * Input string of polymers.
	 */
	final String input;
	
	/**
	 * Constructor for Day 5 puzzles.
	 */
	public Day05() {
		this.dayNum = "5";
		final ResourceReader reader = new ResourceReader(RESOURCE);
		this.input = reader.toList().get(0);
	}
	
	/**
	 * Puzzle one solution.
	 */
	public void puzzleOne() {
		/*
		The polymer is formed by smaller units which, when triggered, react with
		each other such that two adjacent units of the same type and opposite
		polarity are destroyed. Units' types are represented by letters; units'
		polarity is represented by capitalization. For instance, r and R are
		units with the same type but opposite polarity, whereas r and s are
		entirely different types and do not react.

		For example:
		
		In aA, a and A react, leaving nothing behind.
		In abBA, bB destroys itself, leaving aA. As above, this then destroys
		itself, leaving nothing.
		In abAB, no two adjacent units are of the same type, and so nothing
		happens.
		In aabAAB, even though aa and AA are of the same type, their polarities
		match, and so nothing happens.
		Now, consider a larger example, dabAcCaCBAcCcaDA:
		
		dabAcCaCBAcCcaDA  The first 'cC' is removed.
		dabAaCBAcCcaDA    This creates 'Aa', which is removed.
		dabCBAcCcaDA      Either 'cC' or 'Cc' are removed (the result is the same).
		dabCBAcaDA        No further actions can be taken.
		After all possible reactions, the resulting polymer contains 10 units.
		
		How many units remain after fully reacting the polymer you scanned?
		(Note: in this puzzle and others, the input is large; if you copy/paste
		your input, make sure you get the whole thing.)	
		*/
		
		final int length = react(this.input);
		LOGGER.debug("Day 5, Puzzle 1: length is " + length);
		this.solutionOne = String.valueOf(length);
	}
	
	/**
	 * Puzzle two solution.
	 */
	public void puzzleTwo() {
		/*
		--- Part Two ---
		Time to improve the polymer.
		
		One of the unit types is causing problems; it's preventing the polymer
		from collapsing as much as it should. Your goal is to figure out which
		unit type is causing the most problems, remove all instances of it
		(regardless of polarity), fully react the remaining polymer, and measure
		its length.
		
		For example, again using the polymer dabAcCaCBAcCcaDA from above:
		
		Removing all A/a units produces dbcCCBcCcD. Fully reacting this polymer
		produces dbCBcD, which has length 6.
		Removing all B/b units produces daAcCaCAcCcaDA. Fully reacting this
		polymer produces daCAcaDA, which has length 8.
		Removing all C/c units produces dabAaBAaDA. Fully reacting this polymer
		produces daDA, which has length 4.
		Removing all D/d units produces abAcCaCBAcCcaA. Fully reacting this polymer
		produces abCBAc, which has length 6.
		In this example, removing all C/c units was best, producing the answer 4.
		
		What is the length of the shortest polymer you can produce by removing
		all units of exactly one type and fully reacting the result?
		*/
		
		int size[] = new int[Constants.LETTERS.length];
		
		for(int i=0; i<Constants.LETTERS.length; i++) {
			String result = this.input.replace(Character
				.toString(Constants.LETTERS[i]), "");
			result = result.replace(Character.toString(Constants.LETTERS[i])
				.toUpperCase(), "");
			
			int length = react(result);
			
			LOGGER.trace("React result for polymer " + Constants.LETTERS[i] + ": "
				+ length);
			size[i] = length;
		}
		
		int smallest = this.input.length();
		
		for(int i=0; i<Constants.LETTERS.length; i++) {
			LOGGER.trace("Letter " + Constants.LETTERS[i] + " size: " + size[i]);
			
			if(size[i] < smallest) {
				smallest = size[i];
			}
		}
		
		LOGGER.debug("Day 5, Puzzle 2: the smallest result is: " + smallest);
		this.solutionTwo = String.valueOf(smallest);
	}
	
	/**
	 * React the polymer string by removing oppositely charged like elements.
	 * 
	 * @param s Input string.
	 * @return the length of the resulting polymer.
	 */
	public int react(final String s) {
		boolean done = false;
		String result = s;
		
		while(!done) {
			boolean changed = false;
			
			for(int i=0; i<result.length() - 1; i++) {
				final String letter = Character.toString(result.charAt(i));
				final char inverse = StringUtils.swapCase(letter).charAt(0);
				final char next = result.charAt(i + 1);
				
				if(inverse == next) {
					final StringBuilder sb = new StringBuilder(result);
					sb.deleteCharAt(i);
					sb.deleteCharAt(i);
					result = sb.toString();
					
					changed = true;
					break;
				}
			}
			
			if(changed == false) {
				done = true;
			}
		}
		
		return result.length();
	}
}