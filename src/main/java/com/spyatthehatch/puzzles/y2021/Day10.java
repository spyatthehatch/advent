package com.spyatthehatch.puzzles.y2021;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import com.spyatthehatch.puzzles.AbstractDay;
import com.spyatthehatch.util.ResourceReader;

/**
 * Day Ten solutions.
 * 
 * @author Bill Everton
 * @version Advent 2021
 */
public class Day10 extends AbstractDay {
	/**
	 * 
	 */
	private List<String> lines;
		
	/**
	 * Puzzle resource.
	 */
	public static final String RESOURCE = "puzzles/2021/day10-list.txt";
	
	/**
	 * Constructor for Day Ten puzzles.
	 */
	public Day10() {
		this.dayNum = "10";
		LOGGER.debug("Day " + this.dayNum + ": <Title>");
		this.year = "2021";
		

		final ResourceReader reader = new ResourceReader(RESOURCE);
		this.lines = reader.toList();
	}
	
	/**
	 * Puzzle one solution.
	 */
	public void puzzleOne() {	
		long score = 0;
		
		for(final String line : this.lines){
			boolean valid = true;
			final Stack<Character> stack = new Stack<Character>();
			
			for(int i=0; i < line.length() && valid; i++){
				final char token = line.charAt(i);
				long points = 0;
				char pop;
				
				switch(token){
					case '(':
					case '{':
					case '[':
					case '<':
						stack.push(token);
						break;
					case ')':
						pop = stack.pop();
						
						if(pop != '('){
							valid = false;
							points = 3;
						}
						break;
					case '}':
						pop = stack.pop();
						
						if(pop != '{'){
							valid = false;
							points = 1197;
						}
						break;
					case ']':
						pop = stack.pop();
						
						if(pop != '['){
							valid = false;
							points = 57;
						}
						break;
					case '>':
						pop = stack.pop();
						
						if(pop != '<'){
							valid = false;
							points = 25137;
						}
						break;
					default:
						System.out.println("Unknown characater.");
				}
				
				if(!valid){
					score += points;
				}
			}
		}
		
		this.solutionOne = String.valueOf(score);
	}
	
	/**
	 * Puzzle two solution.
	 */
	public void puzzleTwo() {
		final List<Long>scores = new ArrayList<Long>();
		
		for(final String line : this.lines){
			boolean valid = true;
			final Stack<Character> stack = new Stack<Character>();
						
			for(int i=0; i < line.length() && valid; i++){
				char token = line.charAt(i);
				char pop;
				
				switch(token){
					case '(':
					case '{':
					case '[':
					case '<':
						stack.push(token);
						break;
					case ')':
						pop = stack.pop();
						if(pop != '('){
							valid = false;
						}
						break;
					case '}':
						pop = stack.pop();
						if(pop != '{'){
							valid = false;
						}
						break;
					case ']':
						pop = stack.pop();
						if(pop != '['){
							valid = false;
						}
						break;
					case '>':
						pop = stack.pop();
						if(pop != '<'){
							valid = false;
						}
						break;
					default:
						System.out.println("Unknown characater.");
				}
				
				if(!valid){
					break;
				}
			}
			
			if(valid){
				scores.add(calculateScore(stack));
			}
		}
		
		Collections.sort(scores);
		int middle = Math.floorDiv(scores.size(), 2);
		this.solutionTwo = String.valueOf(scores.get(middle));
	}
	
	/**
	 * 
	 * @param stack
	 * @return
	 */
	public long calculateScore(Stack<Character> stack){
		long score = 0;
		
		while(!stack.isEmpty()){
			char pop = stack.pop();
			long points = 0;
			
			switch(pop){
			case '(':
				points = 1;
				break;
			case '[':
				points = 2;
				break;
			case '{':
				points = 3;
				break;
			case '<':
				points = 4;
				break;
			}
			
			score = (score * 5) + points;
		}
		
		return score;
	}
}
