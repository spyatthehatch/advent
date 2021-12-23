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
public class Day02 extends AbstractDay {
	List<PasswordPair> pairs;
	/**
	 * Puzzle resource.
	 */
	public static final String RESOURCE = "puzzles/2020/day02-list.txt";
	
	/**
	 * Constructor for Day One puzzles.
	 */
	public Day02() {	
		LOGGER.debug("Day 2: Password Philosophy");
		this.year = "2020";
		this.dayNum = "2";
		
		final ResourceReader reader = new ResourceReader(RESOURCE);
		List<String>rawList = reader.toList();
		this.pairs = new ArrayList<PasswordPair>();
		
		for(final String s : rawList){
			this.pairs.add(new PasswordPair(s));
		}
	}
	
	/**
	 * Puzzle one solution.
	 */
	public void puzzleOne() {
		int valid = 0;
		
		for(final PasswordPair p : this.pairs){
			if(p.isValid()){
				valid++;
			}
		}
		
		this.solutionOne = String.valueOf(valid);
	}
	
	/**
	 * Puzzle two solution.
	 */
	public void puzzleTwo() {
		int valid = 0;
		
		for(final PasswordPair p : this.pairs){
			if(p.checkIndices()){
				valid++;
			}
		}
		
		this.solutionTwo = String.valueOf(valid);
	}
	
	public class PasswordPair{
		public int min = 0;
		public int max = 0;
		public char token;
		public String password = null;
		
		public PasswordPair(final String s){
			String[] triple = s.split(" ");
			this.password = triple[2].trim();
			String[] minMax = triple[0].split("-");
			this.min = Integer.valueOf(minMax[0].trim());
			this.max = Integer.valueOf(minMax[1].trim());
			this.token = triple[1].trim().charAt(0);
		}
		
		public boolean isValid(){
			int count = 0;
			for (int i=0; i < this.password.length(); i++){
				if(this.password.charAt(i) == this.token){
					count++;
				}
			}
			
			if(count >= min && count <= max){
				return true;
			} else {
				return false;
			}
		}
		
		public boolean checkIndices(){
			int count = 0;
			
			if(this.password.charAt(this.min - 1) == this.token){
				count++;
			}
			
			if(this.password.charAt(this.max - 1) == this.token){
				count++;
			}
			
			if(count == 1){
				return true;
			} else {
				return false;
			}
		}
		
		@Override
		public String toString(){
			return "(Min: " + this.min + ", max:" + this.max + ", token:" +
				this.token + ", password:" + this.password + ")";
		}
	}
}