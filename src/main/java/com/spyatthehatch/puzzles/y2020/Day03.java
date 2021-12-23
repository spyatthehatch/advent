package com.spyatthehatch.puzzles.y2020;

import java.util.ArrayList;
import java.util.List;

import com.spyatthehatch.puzzles.AbstractDay;
import com.spyatthehatch.util.ResourceReader;

/**
 * Day One solutions.
 * 
 * @author Bill Everton
 * @version Advent 2020
 */
public class Day03 extends AbstractDay {
	/**
	 * 
	 */
	private List<String>treeList;
	
	/**
	 * Puzzle resource.
	 */
	public static final String RESOURCE = "puzzles/2020/day03-list.txt";
	
	/**
	 * Constructor for Day One puzzles.
	 */
	public Day03() {	
		LOGGER.debug("Day 3: Toboggan Trajectory");
		this.year = "2020";
		this.dayNum = "3";
		
		final ResourceReader reader = new ResourceReader(RESOURCE);
		this.treeList = reader.toList();

	}
	
	/**
	 * Puzzle one solution.
	 */
	public void puzzleOne() {
		int treeCount = 0;
		int x = 0;
		final int width = this.treeList.get(0).length();
		
		for(final String treeLine : this.treeList){
			if(treeLine.charAt(x % width) == '#'){
				treeCount++;
			}
			
			x += 3;
		}
		
		this.solutionOne = String.valueOf(treeCount);
	}
	
	/**
	 * Puzzle two solution.
	 */
	public void puzzleTwo() {
		final int width = this.treeList.get(0).length();
		long oneByOne = 0;
		long threeByOne = Integer.valueOf(this.solutionOne);
		long fiveByOne = 0;
		long sevenByOne = 0;
		long oneByTwo = 0;
		int x = 0;
		
		for(final String treeLine : this.treeList){
			if(treeLine.charAt(x % width) == '#'){
				oneByOne++;
			}
			x++;
		}	
		
		x = 0;
		for(final String treeLine : this.treeList){
			if(treeLine.charAt(x % width) == '#'){
				fiveByOne++;
			}
			x += 5;
		}
		
		x = 0;
		for(final String treeLine : this.treeList){
			if(treeLine.charAt(x % width) == '#'){
				sevenByOne++;
			}
			x += 7;
		}
		
		x = 0;
		for(int y = 0; y < this.treeList.size(); y += 2){
			if(this.treeList.get(y).charAt(x % width) == '#'){
				oneByTwo++;
			}
			x++;
		}
	
		long answer = oneByOne * threeByOne * fiveByOne * sevenByOne * oneByTwo;
		this.solutionTwo = String.valueOf(answer);
	}
}