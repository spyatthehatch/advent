package com.spyatthehatch.puzzles.y2021;

import java.util.ArrayList;
import java.util.List;

import com.spyatthehatch.puzzles.AbstractDay;
import com.spyatthehatch.util.ResourceReader;

/**
 * Day One solutions.
 * 
 * @author Bill Everton
 * @version Advent 2021
 */
public class Day01 extends AbstractDay {
	/*
	--- Day 1: Sonar Sweep ---
	
	You're minding your own business on a ship at sea when the overboard alarm
	goes off! You rush to see if you can help. Apparently, one of the Elves
	tripped and accidentally sent the sleigh keys flying into the ocean!
	
	Before you know it, you're inside a submarine the Elves keep ready for
	situations like this. It's covered in Christmas lights (because of course it
	is), and it even has an experimental antenna that should be able to track
	the keys if you can boost its signal strength high enough; there's a little
	meter that indicates the antenna's signal strength by displaying 0-50 stars.
	
	Your instincts tell you that in order to save Christmas, you'll need to get
	all fifty stars by December 25th.
	
	Collect stars by solving puzzles. Two puzzles will be made available on each
	day in the Advent calendar; the second puzzle is unlocked when you complete
	the first. Each puzzle grants one star. Good luck!
	*/

	/**
	 * List of depth measurements.
	 */
	private List<Integer> depths;
	
	/**
	 * Puzzle resource.
	 */
	public static final String RESOURCE = "puzzles/2021/day01-list.txt";
	
	/**
	 * Constructor for Day One puzzles.
	 */
	public Day01() {	
		LOGGER.debug("Day 1: Sonar Sweep");
		this.year = "2021";
		this.dayNum = "1";
		final ResourceReader reader = new ResourceReader(RESOURCE);
		final List<String> rawList = reader.toList();
		this.depths = new ArrayList<Integer>();
		
		for(final String s : rawList){
			this.depths.add(Integer.valueOf(s));
		}
	}
	
	/**
	 * Puzzle one solution.
	 */
	public void puzzleOne() {
		/*
		As the submarine drops below the surface of the ocean, it automatically
		performs a sonar sweep of the nearby sea floor. On a small screen, the
		sonar sweep report (your puzzle input) appears: each line is a
		measurement of the sea floor depth as the sweep looks further and further
		away from the submarine.
		
		This report indicates that, scanning outward from the submarine, the
		sonar sweep found depths of 199, 200, 208, 210, and so on.
		
		The first order of business is to figure out how quickly the depth
		increases, just so you know what you're dealing with - you never know if
		the keys will get carried into deeper water by an ocean current or a fish
		or something.
		
		To do this, count the number of times a depth measurement increases from
		the previous measurement. (There is no measurement before the first
		measurement.)
		
		How many measurements are larger than the previous measurement?
		 */
		
		int increase = 0;
		int previous = this.depths.get(0);
		int depth = 0;
		
		for(int i=1; i < this.depths.size(); i++){
			depth = this.depths.get(i);
			if(depth > previous){
				increase++;
			}
			previous = depth;
		}
		
		this.solutionOne = String.valueOf(increase);
	}
	
	/**
	 * Puzzle two solution.
	 */
	public void puzzleTwo() {
		/*
		--- Part Two ---
		
		Considering every single measurement isn't as useful as you expected:
		there's just too much noise in the data.
		
		Start by comparing the first and second three-measurement windows. The
		measurements in the first window are marked A (199, 200, 208); their sum
		is 199 + 200 + 208 = 607. The second window is marked B (200, 208, 210);
		its sum is 618. The sum of measurements in the second window is larger
		than the sum of the first, so this first comparison increased.

		Your goal now is to count the number of times the sum of measurements in
		this sliding window increases from the previous sum. So, compare A with
		B, then compare B with C, then C with D, and so on. Stop when there
		aren't enough measurements left to create a new three-measurement sum.

		Consider sums of a three-measurement sliding window. How many sums are
		larger than the previous sum?
		 */
		
		int increase = 0;
		int previous = this.depths.get(0) + this.depths.get(1) + this.depths.get(2);
		int depth;
		
		for(int i=1; i < this.depths.size()-2; i++){
			depth = this.depths.get(i) + this.depths.get(i+1) + this.depths.get(i+2);
			if(depth > previous){
				increase++;
			}
			previous = depth;
		}
		
		this.solutionTwo = String.valueOf(increase);
	}
}