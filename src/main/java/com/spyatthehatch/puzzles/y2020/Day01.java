package com.spyatthehatch.puzzles.y2020;

import java.util.List;

import com.spyatthehatch.puzzles.AbstractDay;
import com.spyatthehatch.util.ResourceReader;

/**
 * Day One solutions.
 * 
 * @author Bill Everton
 * @version Advent 2020
 */
public class Day01 extends AbstractDay {
	private int[] entries;
	
	/**
	 * Puzzle resource.
	 */
	public static final String RESOURCE = "puzzles/2020/day01-list.txt";
	
	/**
	 * Constructor for Day One puzzles.
	 */
	public Day01() {	
		LOGGER.debug("Day 1: Report Repair");
		this.year = "2020";
		this.dayNum = "1";
		final ResourceReader reader = new ResourceReader(RESOURCE);
		final List<String> list = reader.toList();
		this.entries = new int[list.size()];
		
		for(int i=0; i < list.size(); i++){
			this.entries[i] = Integer.valueOf(list.get(i));
		}
	}
	
	/**
	 * Puzzle one solution.
	 */
	public void puzzleOne() {
		int answer = 0;
		
		for(int i=0; i < this.entries.length; i++){
			for(int j=0; j < this.entries.length; j++){
				if (this.entries[i] + this.entries[j] == 2020){
					answer = this.entries[i] * this.entries[j];
					break;
				}
			}
		}
		
		this.solutionOne = String.valueOf(answer);
	}
	
	/**
	 * Puzzle two solution.
	 */
	public void puzzleTwo() {
		int answer = 0;
		
		for(int i=0; i < this.entries.length; i++){
			for(int j=0; j < this.entries.length; j++){
				for(int k=0; k < this.entries.length; k++){
					if (this.entries[i] + this.entries[j] + this.entries[k] == 2020){
						answer = this.entries[i] * this.entries[j] * this.entries[k];
						break;
					}
				}
			}
		}
		
		this.solutionTwo = String.valueOf(answer);
	}
}