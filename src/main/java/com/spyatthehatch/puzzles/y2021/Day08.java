package com.spyatthehatch.puzzles.y2021;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.spyatthehatch.puzzles.AbstractDay;
import com.spyatthehatch.util.ResourceReader;

/**
 * Day Six solutions.
 * 
 * @author Bill Everton
 * @version Advent 2021
 */
public class Day08 extends AbstractDay {
	/**
	 * 
	 */
	private List<String>rawList;
	
	/**
	 * 
	 */
	private List<String>outputs;
	
	/**
	 * Puzzle resource.
	 */
	public static final String RESOURCE = "puzzles/2021/day08-list.txt";
	
	/**
	 * Constructor for Day Seven puzzles.
	 */
	public Day08() {
		LOGGER.debug("Day 8: Seven Segment Search");
		this.year = "2021";
		this.dayNum = "8";

		final ResourceReader reader = new ResourceReader(RESOURCE);
		this.rawList = reader.toList();
	}
	
	/**
	 * Puzzle one solution.
	 */
	public void puzzleOne() {	
		int answer = 0;
		
		this.outputs = new ArrayList<String>();
		
		for(String line : this.rawList){
			String[] out = line.split(" ");
			outputs.add(out[11].trim());
			outputs.add(out[12].trim());
			outputs.add(out[13].trim());
			outputs.add(out[14].trim());
		}
		
		System.out.println("Outputs list size: " + outputs.size());
		
		for(String o : outputs){
			int length = o.length();
			
			if(length == 2 || length == 3 || length == 4 || length == 7){
				answer++;
			}
		}
		
		this.solutionOne = String.valueOf(answer);
	}
	
	/**
	 * Puzzle two solution.
	 */
	public void puzzleTwo() {
		int answer = 0;
		
		for(final String s : this.rawList){
			DisplaySet ds = new DisplaySet(s);
			answer += ds.getOutput();
		}
		
		this.solutionTwo = String.valueOf(answer);
	}
	
	/**
	 * 
	 * @author Billy
	 *
	 */
	public class DisplaySet {
		public Map<Integer, List<Character>> set;
		public String[] outputs;
		public int value;
		
		/**
		 * 
		 * @param input
		 */
		public DisplaySet(final String input){
			this.outputs = new String[4];
			this.set = new HashMap<Integer, List<Character>>();
			
			final String[] codes = input.split(" ");
			this.outputs[0] = codes[11].trim();
			this.outputs[1] = codes[12].trim();
			this.outputs[2] = codes[13].trim();
			this.outputs[3] = codes[14].trim();
			
			final List<String>unknowns = new ArrayList<String>();
			
			for (int i=0; i<10; i++){
				unknowns.add(codes[i].trim());
			}
			
			// Do a first pass to find the Easies: 1, 4, 7, and 8.
			for(final String code : unknowns){

				final int length = code.length();
				
				// If the length is 2, this is the code for "1".
				if(length == 2){
					this.set.put(1, createCharList(code));
					continue;
				}
				
				// If the length is 3, this is the code for "7".
				if(length == 3){
					this.set.put(7, createCharList(code));
					continue;
				}
				
				// If the length is 4, this is the code for "4".
				if(length == 4){
					this.set.put(4, createCharList(code));
					continue;
				}
				
				// If the length is 7, this is the code for "8".
				if(length == 7){
					this.set.put(8, createCharList(code));
					continue;
				}
			}
				
			for(final String code : unknowns){				
				// Only check the codes with a length of 6.
				if(code.length() != 6){
					continue;
				} else {
					// Get the difference between this unknown code and "8".
					final List<Character> chars = createCharList(code);
					char diff = 'h';
					
					for(char c : this.set.get(8)){
						if(!chars.contains(c)){
							diff = c;
							break;
						}
					}
					
					// If the diff is contained within "4", this is a "9".
					if(!this.set.get(4).contains(diff)){
						this.set.put(9, chars);
						continue;
					}
					
					// If the diff is contained with "7", this is a "6".
					else if(this.set.get(7).contains(diff)){
						this.set.put(6, chars);
						continue;
					}
					
					// Else this is a "0".
					else {
						this.set.put(0, chars);
						continue;
					}
				}
			}
				
			for(final String code : unknowns){
				// Only check the codes with a length of 5.
				if(code.length() != 5){
					continue;
				} else {
					final List<Character> chars = createCharList(code);
					List<Character>diff = new ArrayList<Character>();
					
					for(char c : this.set.get(6)){
						if(!chars.contains(c)){
							diff.add(c);
						}
					}
					
					// If the diff is only one character, this is a "5".
					if(diff.size() == 1){
						this.set.put(5,  chars);
						continue;
					}
					
					diff.clear();
					
					for(char c : this.set.get(7)){
						if(!chars.contains(c)){
							diff.add(c);
						}
					}
					
					if(diff.isEmpty()){
						this.set.put(3,  chars);
						continue;
					} else {
						this.set.put(2, chars);
						continue;
					}
				}
			}
		}
		
		/**
		 * 
		 * @param code
		 * @return
		 */
		private List<Character> createCharList(final String code){
			final List<Character> chars = new ArrayList<Character>();
			for(int i=0; i < code.length(); i++){
				chars.add(code.charAt(i));
			}
			return chars;
		}
		
		/**
		 * 
		 * @return
		 */
		public int getOutput(){
			int output = 0;
			
			output += 1000 * getValue(this.outputs[0]);
			output += 100 * getValue(this.outputs[1]);
			output += 10 * getValue(this.outputs[2]);
			output += getValue(this.outputs[3]);
			
			this.value = output;
			return output;
		}
		
		/**
		 * 
		 * @param s
		 * @return
		 */
		private int getValue(final String s){		
			for(int i=0; i < 10; i++){
				
				boolean flag = true;
				
				
				List<Character> chars = this.set.get(i);
				
				if(chars.size() == s.length()){
					for(int j=0; j < s.length() && flag; j++){
						if(chars.contains(s.charAt(j))){
							continue;
						} else {
							flag = false;
						}
					}
					
					if(flag){
						return i;
					}
				}
			}
			
			LOGGER.error("Unable to determine digit.");
			return 0;
		}
	}
}
