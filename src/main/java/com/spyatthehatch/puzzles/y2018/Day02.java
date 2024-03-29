package com.spyatthehatch.puzzles.y2018;

import java.util.List;
import org.apache.commons.lang3.StringUtils;

import com.spyatthehatch.Constants;
import com.spyatthehatch.puzzles.AbstractDay;
import com.spyatthehatch.util.ResourceReader;

/**
 * Day two solutions.
 * 
 * @author Bill Everton
 * @version Advent 2018
 */
public class Day02 extends AbstractDay {
	/*
	--- Day 2: Inventory Management System ---
	You stop falling through time, catch your breath, and check the screen on
	the device. "Destination reached. Current Year: 1518. Current Location:
	North Pole Utility Closet 83N10." You made it! Now, to find those anomalies.

	Outside the utility closet, you hear footsteps and a voice. "...I'm not sure
	either. But now that so many people have chimneys, maybe he could sneak in
	that way?" Another voice responds, "Actually, we've been working on a new
	kind of suit that would let him fit through tight spaces like that. But, I
	heard that a few days ago, they lost the prototype fabric, the design plans,
	everything! Nobody on the team can even seem to remember important details
	of the project!"

	"Wouldn't they have had enough fabric to fill several boxes in the
	warehouse? They'd be stored together, so the box IDs should be similar. Too
	bad it would take forever to search the warehouse for two similar box
	IDs..." They walk too far away to hear any more.
	*/
	
	/**
	 * Puzzle resource, list of IDs.
	 */
	public static final String RESOURCE = "puzzles/2018/day2-list.txt";
	
	/**
	 * ArrayList of provided IDs.
	 */
	private List<String>input;
	
	/**
	 * Constructor for Day 2 puzzles.
	 */
	public Day02() {
		this.dayNum = "2";
		final ResourceReader reader = new ResourceReader(RESOURCE);
		this.input = reader.toList();
	}
	
	/**
	 * Puzzle one solution.
	 */
	public void puzzleOne() {
		/*
		Late at night, you sneak to the warehouse - who knows what kinds of
		paradoxes you could cause if you were discovered - and use your fancy
		wrist device to quickly scan every box and produce a list of the likely
		candidates (your puzzle input).

		To make sure you didn't miss any, you scan the likely candidate boxes
		again, counting the number that have an ID containing exactly two of any
		letter and then separately counting those with exactly three of any
		letter. You can multiply those two counts together to get a rudimentary
		checksum and compare it to what your device predicts.

		For example, if you see the following box IDs:

		abcdef contains no letters that appear exactly two or three times.
		bababc contains two a and three b, so it counts for both.
		abbcde contains two b, but no letter appears exactly three times.
		abcccd contains three c, but no letter appears exactly two times.
		aabcdd contains two a and two d, but it only counts once.
		abcdee contains two e.
		ababab contains three a and three b, but it only counts once.
		
		Of these box IDs, four of them contain a letter which appears exactly
		twice, and three of them contain a letter which appears exactly three
		times. Multiplying these together produces a checksum of 4 * 3 = 12.
		
		What is the checksum for your list of box IDs?
		*/
		
		LOGGER.debug("Day 2, Puzzle 1: computing checksum.");
		int twoCount = 0;
		int threeCount = 0;
		
		for (String s : this.input) {
			LOGGER.trace("Checking for twos in " + s + "...");
			for (int i=0; i < Constants.LETTERS.length; i++) {
				if(StringUtils.countMatches(s, Constants.LETTERS[i]) == 2) {
					twoCount++;
					LOGGER.debug("Found two " + Constants.LETTERS[i] + "'s in " + s
						+ ".");
					break;
				}
			}
			
			LOGGER.trace("Checking for threes in " + s + "...");
			for (int i=0; i < Constants.LETTERS.length; i++) {
				if(StringUtils.countMatches(s, Constants.LETTERS[i]) == 3) {
					threeCount++;
					LOGGER.debug("Found three " + Constants.LETTERS[i] + "'s in " + s
						+ ".");
					break;
				}
			}
		}
		
		final int checksum = twoCount * threeCount;
		this.solutionOne = String.valueOf(checksum);
		LOGGER.debug("Day 2, Puzzle 1: checksum is " + checksum + ".");
	}
	
	/**
	 * Puzzle two solution.
	 */
	@SuppressWarnings("deprecation")
	public void puzzleTwo() {
		/*
		--- Part Two ---
		Confident that your list of box IDs is complete, you're ready to find the
		boxes full of prototype fabric.

		The boxes will have IDs which differ by exactly one character at the same
		position in both strings. For example, given the following box IDs:
		
		abcde
		fghij
		klmno
		pqrst
		fguij
		axcye
		wvxyz
		
		The IDs abcde and axcye are close, but they differ by two characters (the
		second and fourth). However, the IDs fghij and fguij differ by exactly
		one character, the third (h and u). Those must be the correct boxes.
		
		What letters are common between the two correct box IDs? (In the example
		above, this is found by removing the differing character from either ID,
		producing fgij.)
		*/
		
		LOGGER.debug("Day 2, Puzzle 2: checking for two strings with a difference of 1 character.");
		for (String s: this.input) {
			
			for (String t : this.input) {
				if(s == t) {
					break;
				}
				
				if(StringUtils.getLevenshteinDistance(s, t) == 1) {
					LOGGER.debug("Found the two strings!");
					LOGGER.debug("String 1: " + s);
					LOGGER.debug("String 2: " + t);

					this.solutionTwo = StringUtils.getCommonPrefix(s,t)
						+ StringUtils.difference(s,t).substring(1);
					LOGGER.debug("Day 2, Puzzle 2: common characters are "
						+ this.solutionTwo + ".");
				}
			}
		}
	}
}