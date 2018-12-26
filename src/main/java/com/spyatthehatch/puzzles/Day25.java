package com.spyatthehatch.puzzles;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.spyatthehatch.objects.Constellation;
import com.spyatthehatch.objects.FourDPoint;
import com.spyatthehatch.util.ResourceReader;

/**
 * Day twenty five solutions.
 * 
 * @author Bill Everton
 * @version Advent 2018
 */
public class Day25 extends AbstractDay {
	/*
	
	
	*/
	
	/**
	 * Puzzle resource.
	 */
	public static final String RESOURCE = "puzzles/day25-list.txt";
	
	public List<FourDPoint> points = new ArrayList<FourDPoint>();
	
	/**
	 * Constructor for Day 25 puzzles.
	 */
	public Day25() {
		this.dayNum = "25";
		final ResourceReader reader = new ResourceReader(RESOURCE);
		final List<String> input = reader.toList();
		
		for(final String s : input){
			this.points.add(new FourDPoint(s));
		}
	}
	
	/**
	 * Puzzle one solution.
	 */
	public void puzzleOne() {
		/*
		
		
		*/
		final Map<FourDPoint, Constellation> constellations = new HashMap<FourDPoint, Constellation>();
		
		for(final FourDPoint point : this.points){
			constellations.put(point, new Constellation(point));
		}
		
		for(final FourDPoint point : this.points){
			
		}
		
		
		this.solutionOne = "foo";
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