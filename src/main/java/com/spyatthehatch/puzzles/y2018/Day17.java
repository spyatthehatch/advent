package com.spyatthehatch.puzzles.y2018;

import java.util.ArrayList;
import java.util.List;

import com.spyatthehatch.objects.y2018.water.Ground;
import com.spyatthehatch.objects.y2018.water.Vein;
import com.spyatthehatch.puzzles.AbstractDay;
import com.spyatthehatch.util.ResourceReader;

/**
 * Day seventeen solutions.
 * 
 * @author Bill Everton
 * @version Advent 2018
 */
public class Day17 extends AbstractDay {
	/*
	
	
	*/
	
	/**
	 * Puzzle resource.
	 */
	public static final String RESOURCE = "puzzles/2018/day17-list.txt";

	private List<Vein> veins = new ArrayList<Vein>();
	
	/**
	 * Constructor for Day 17 puzzles.
	 */
	public Day17() {
		this.dayNum = "17";
		final ResourceReader reader = new ResourceReader(RESOURCE);
		final List<String> input = reader.toList();
		
		for(final String s : input){
			this.veins.add(new Vein(s));
		}
	}
	
	/**
	 * Puzzle one solution.
	 */
	public void puzzleOne() {
		/*
		
		
		*/
		
		final Ground ground = new Ground(this.veins);
		final int tiles = ground.seep();
		ground.print();
		
		LOGGER.debug("Day 17, Puzzle 1: there are " + tiles + " soaked tiles.");
		this.solutionOne = String.valueOf(tiles);
	}
	
	/**
	 * Puzzle two solution.
	 */
	public void puzzleTwo() {
		/*
		
		
		*/
		
		this.solutionTwo = "bar";
	}
}