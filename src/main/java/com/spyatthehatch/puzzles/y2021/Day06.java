package com.spyatthehatch.puzzles.y2021;

import com.spyatthehatch.puzzles.AbstractDay;
import com.spyatthehatch.util.ResourceReader;

/**
 * Day Six solutions.
 * 
 * @author Bill Everton
 * @version Advent 2021
 */
public class Day06 extends AbstractDay {
	private String[] fish;
	
	/**
	 * Puzzle resource.
	 */
	public static final String RESOURCE = "puzzles/2021/day06-list.txt";
	
	/**
	 * Constructor for Day 6 puzzles.
	 */
	public Day06() {
		LOGGER.debug("Day 6: Lanternfish");
		this.year = "2021";
		this.dayNum = "6";

		final ResourceReader reader = new ResourceReader(RESOURCE);
		final String rawString = reader.toString();
		this.fish = rawString.split(",");
	}
	
	/**
	 * Puzzle one solution.
	 */
	public void puzzleOne() {	
		long[] fishCount = new long[9];
		
		for(int i=0; i < fishCount.length; i++){
			fishCount[i] = 0;
		}
		
		for(int i=0; i < fish.length; i++){
			int age = Integer.valueOf(fish[i]);
			fishCount[age]++;
		}
		
		for (int i=0; i<80; i++){
			final long births = fishCount[0];
			fishCount[0] = fishCount[1];
			fishCount[1] = fishCount[2];
			fishCount[2] = fishCount[3];
			fishCount[3] = fishCount[4];
			fishCount[4] = fishCount[5];
			fishCount[5] = fishCount[6];
			fishCount[6] = fishCount[7] + births;
			fishCount[7] = fishCount[8];
			fishCount[8] = births;
		}
		
		int totalCount = 0;
		for(int i=0; i<fishCount.length; i++){
			totalCount += fishCount[i];
		}
		System.out.println("Alternative counting method: " + totalCount);
		
		this.solutionOne = String.valueOf(totalCount);
	}
	
	/**
	 * Puzzle two solution.
	 */
	public void puzzleTwo() {
		long[] fishCount = new long[9];
		
		for(int i=0; i < fishCount.length; i++){
			fishCount[i] = 0;
		}
		
		for(int i=0; i < fish.length; i++){
			int age = Integer.valueOf(fish[i]);
			fishCount[age]++;
		}
		
		for (int i=0; i<256; i++){
			final long births = fishCount[0];
			fishCount[0] = fishCount[1];
			fishCount[1] = fishCount[2];
			fishCount[2] = fishCount[3];
			fishCount[3] = fishCount[4];
			fishCount[4] = fishCount[5];
			fishCount[5] = fishCount[6];
			fishCount[6] = fishCount[7] + births;
			fishCount[7] = fishCount[8];
			fishCount[8] = births;
		}
		
		long totalCount = 0;
		for(int i=0; i<fishCount.length; i++){
			totalCount += fishCount[i];
		}
		System.out.println("Alternative counting method: " + totalCount);
		
		this.solutionTwo = String.valueOf(totalCount);
	}
	
	public class Lanternfish {
		public int daysToReproduce;
		
		public Lanternfish(){
			this.daysToReproduce = 8;
		}
		
		public Lanternfish(final int days){
			this.daysToReproduce = days;
		}
		
		public boolean nextDay() {
			if(this.daysToReproduce == 0){
				this.daysToReproduce = 6;
				return true;
			} else {
				this.daysToReproduce--;
				return false;
			}
		}
	}
}
