package com.spyatthehatch.puzzles.y2019;

import java.util.ArrayList;
import java.util.List;

import com.spyatthehatch.puzzles.AbstractDay;

/**
 * Day four solutions.
 * 
 * @author Bill Everton
 * @version Advent 2019
 */
public class Day04 extends AbstractDay {
	/*

	*/
	private static final int LOW = 357253;
	
	/**
	 * 
	 */
	private static final int HIGH = 892942;
	
	/**
	 * 
	 */
	private static final int MAX_LENGTH = 6;
	
	/**
	 * 
	 */
	private List<Integer> possibles = new ArrayList<Integer>();
	
	/**
	 * Constructor for Day 2 puzzles.
	 */
	public Day04() {
		LOGGER.debug("Day 4: Secure Container");
		this.dayNum = "4";
	}
	
	/**
	 * Puzzle one solution.
	 */
	public void puzzleOne() {
		/*

		*/
		int count = 0;
		for(int candidate=LOW; candidate <= HIGH; candidate++){
			if(checkCriteria(candidate)){
				this.possibles.add(candidate);
				count++;
			}
		}

		this.solutionOne = String.valueOf(count);
	}
	
	/**
	 * Puzzle two solution.
	 */
	public void puzzleTwo() {
		/*

		*/
		final List<Integer>disqualified = new ArrayList<Integer>();
		
		for(Integer possible : this.possibles){
			if(disqualifyCandidate(possible)){
				disqualified.add(possible);
			}
		}
		
		int size = this.possibles.size() - disqualified.size();
		
		this.solutionTwo = String.valueOf(size);
	}
	
	private boolean checkCriteria(final int candidate){
		final String number = String.valueOf(candidate);
		int last = Integer.valueOf(number.substring(0,1));
		boolean repeat = false;
		
		for (int i=1; i<MAX_LENGTH; i++){
			int decimal = Integer.valueOf(number.substring(i, i+1));
						
			if(decimal > last){
				// Do nothing.
			} else if (decimal == last){
				repeat = true;
			} else if(decimal < last){
				return false;
			}
			
			last = decimal;
		}
		
		return repeat;
	}
	
	/**
	 * 
	 * @param candidate
	 * @return
	 */
	private boolean disqualifyCandidate(final int candidate){
		final String number = String.valueOf(candidate);
		int last = Integer.valueOf(number.substring(0,1));
		int count = 1;
		
		for (int i=1; i<MAX_LENGTH; i++){
			int decimal = Integer.valueOf(number.substring(i, i+1));
						
			if(decimal == last){
				count++;				
			} else {
				if(count == 2){
					return false;
				}
				count = 1;
			}			
			
			last = decimal;		
		}
		
		if(count == 2){
			return false;
		} else {
			return true;
		}
	}
}