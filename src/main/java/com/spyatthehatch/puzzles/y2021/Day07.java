package com.spyatthehatch.puzzles.y2021;

import com.spyatthehatch.puzzles.AbstractDay;
import com.spyatthehatch.util.ResourceReader;
import com.spyatthehatch.util.TriangularTable;

/**
 * Day Six solutions.
 * 
 * @author Bill Everton
 * @version Advent 2021
 */
public class Day07 extends AbstractDay {
	/**
	 * 
	 */
	private int[] positions;
	
	/**
	 * 
	 */
	private int max = Integer.MIN_VALUE;
	
	/**
	 * 
	 */
	private int min = Integer.MAX_VALUE;
	
	/**
	 * Puzzle resource.
	 */
	public static final String RESOURCE = "puzzles/2021/day07-list.txt";
	
	/**
	 * Constructor for Day Seven puzzles.
	 */
	public Day07() {
		LOGGER.debug("Day 7: The Treachery of Whales");
		this.year = "2021";
		this.dayNum = "7";

		final ResourceReader reader = new ResourceReader(RESOURCE);
		final String input = reader.toString();
		final String s[] = input.split(",");
		
		this.positions = new int[s.length];
		
		for (int i=0; i<s.length; i++){
			this.positions[i] = Integer.valueOf(s[i]);
			if(this.positions[i] > this.max){
				this.max = this.positions[i];
			}
			
			if(this.positions[i] < this.min){
				this.min = this.positions[i];
			}
		}
	}
	
	/**
	 * Puzzle one solution.
	 */
	public void puzzleOne() {	
		int bestCost = Integer.MAX_VALUE;
		
		for(int i=this.min; i<this.max; i++){
			int cost = 0;
			
			for(int j=0; j<this.positions.length; j++){
				cost += Math.abs(this.positions[j] - i);
			}
			
			if(cost < bestCost){
				bestCost = cost;
			}
		}
		
		this.solutionOne = String.valueOf(bestCost);
	}
	
	/**
	 * Puzzle two solution.
	 */
	public void puzzleTwo() {
		int bestCost = Integer.MAX_VALUE;
		
		final TriangularTable table = new TriangularTable(this.max - this.min);
			
		for(int i=this.min; i<this.max; i++){
			int cost = 0;
			
			for(int j=0; j<this.positions.length; j++){
				int distance = Math.abs(this.positions[j] - i);
				cost += table.getValue(distance);
			}
			
			if(cost < bestCost){
				bestCost = cost;
			}
		}
		
		this.solutionTwo = String.valueOf(bestCost);
	}
}
