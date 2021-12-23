package com.spyatthehatch.puzzles.y2021;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.spyatthehatch.puzzles.AbstractDay;
import com.spyatthehatch.util.ResourceReader;

/**
 * Day Fourteen solutions.
 * 
 * @author Bill Everton
 * @version Advent 2021
 */
public class Day14 extends AbstractDay {
	private String startPolymer;
	
	private Map<String, String> pairs;
	
	private Map<String, Long> pairCounts;

	/**
	 * Puzzle resource.
	 */
	public static final String RESOURCE = "puzzles/2021/day14-list.txt";
	
	/**
	 * Constructor for Day Fourteen puzzles.
	 */
	public Day14() {
		this.dayNum = "14";
		LOGGER.debug("Day " + this.dayNum + ": Polymerization");
		this.year = "2021";
	}
	
	/**
	 * Puzzle one solution.
	 */
	public void puzzleOne() {
		init();
		
		String polymer = this.startPolymer;
		
		// Go through 10 steps of the polymer.
		for(int step=0; step < 10; step++){
			final StringBuilder sb = new StringBuilder();
			
			for(int index=0; index < polymer.length() - 1; index++){
				String substring = polymer.substring(index, index + 2);
				String insert = this.pairs.get(substring);
			
				sb.append(polymer.substring(index, index + 1));
			
				if(insert != null){
					sb.append(insert);
				}
			}
			
			sb.append(polymer.substring(polymer.length() - 1, polymer.length()));
			polymer = sb.toString();
		}
		
		Map<String, Integer> counts = new HashMap<String, Integer>();
		
		for(int index=0; index < polymer.length(); index++){
			String element = polymer.substring(index, index + 1);
			Integer count = counts.get(element);
			
			if(count == null){
				count = new Integer(1);
			} else {
				count += 1;
			}
			
			counts.put(element, count);
		}
		
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		
		for(Map.Entry<String, Integer> set : counts.entrySet()){	
			if(set.getValue() > max){
				max = set.getValue();
			}
			
			if(set.getValue() < min){
				min = set.getValue();
			}
		}
		
		int quantity = max - min;
		
		this.solutionOne = String.valueOf(quantity);
	}
	
	/**
	 * Puzzle two solution.
	 */
	public void puzzleTwo() {
		this.pairCounts = new HashMap<String, Long>();
		String polymer = this.startPolymer;
		
		// Build the initial hashmap of pair counts.
		for(int index=0; index < polymer.length() - 1; index++){
			String pair = polymer.substring(index, index + 2);
			Long count = this.pairCounts.get(pair);
			
			if(count == null){
				count = new Long(1);
			} else {
				count += 1;
			}
			
			this.pairCounts.put(pair, count);
		}
		
		// Do 40 steps of the polymer growth.
		for(int step=1; step <=40; step++){
			Map<String, Long> newCounts = new HashMap<String, Long>();
			
			/*
			 *  Iterate through each of the key pairs.  An element insert pair like
			 *  NA -> B will result in NB and AB, with the count of NA.  It's possible
			 *  to have NF -> B result in NB and FB (another set of NB pairs), so
			 *  check if the pair exists already.
			 */
			for(Map.Entry<String, Long> set : this.pairCounts.entrySet()){
				String pair = set.getKey();
				String insert = this.pairs.get(pair);
				long count = set.getValue();
				String childPair1 = pair.substring(0, 1) + insert;
				String childPair2 = insert + pair.substring(1, 2);
				
				Long childPair1Count = newCounts.get(childPair1);
				if(childPair1Count == null){
					childPair1Count = count;
				} else {
					childPair1Count += count;
				}
				
				Long childPair2Count = newCounts.get(childPair2);
				if(childPair2Count == null){
					childPair2Count = count;
				} else {
					childPair2Count += count;
				}
				
				newCounts.put(childPair1, childPair1Count);
				newCounts.put(childPair2, childPair2Count);
			}
			
			this.pairCounts = newCounts;
		}
		
		/* Create a new hashmap of element counts.  Add the last element of the
		 * original polymer because it will never be different.
		 */
		Map<String, Long>elementCount = new HashMap<String, Long>();
		elementCount.put(this.startPolymer.substring(this.startPolymer.length() - 1, this.startPolymer.length()), new Long(1));
		
		// Count the elements by summing the first element of the pairs.
		for(Map.Entry<String, Long> set : this.pairCounts.entrySet()){
			String element = set.getKey().substring(0, 1);
			Long count = elementCount.get(element);
			
			if(count == null){
				count = new Long(set.getValue());
			} else {
				count += set.getValue();
			}
			
			elementCount.put(element, count);
		}
		
		// Figure out the min/max values.
		long min = Long.MAX_VALUE;
		long max = Long.MIN_VALUE;
		
		for(Map.Entry<String, Long> set : elementCount.entrySet()){
			if(set.getValue() > max){
				max = set.getValue();
			}
			
			if(set.getValue() < min){
				min = set.getValue();
			}
			
			System.out.println(set.getKey() + ":" + set.getValue());
		}
		
		long quantity = max - min;
		
		this.solutionTwo = String.valueOf(quantity);
	}
		
	/**
	 * 
	 */
	public void init(){
		final ResourceReader reader = new ResourceReader(RESOURCE);
		List<String> rawList = reader.toList();
		this.pairs = new HashMap<String, String>();
		
		this.startPolymer = rawList.get(0);
		for(int i=2; i < rawList.size(); i++){
			final String s[] = rawList.get(i).split(" ");
			this.pairs.put(s[0].trim(), s[2].trim());
		}
		
		System.out.println("Starting Polymer:" + this.startPolymer);
		System.out.println("Read in " + this.pairs.size() + " chain pair rules.");
	}
}