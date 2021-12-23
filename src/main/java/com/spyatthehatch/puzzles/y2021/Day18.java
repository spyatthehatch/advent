package com.spyatthehatch.puzzles.y2021;

import java.util.List;
import java.util.Stack;

import com.spyatthehatch.objects.y2021.ElementPair;
import com.spyatthehatch.puzzles.AbstractDay;
import com.spyatthehatch.util.ResourceReader;

/**
 * Day Fifteen solutions.
 * 
 * @author Bill Everton
 * @version Advent 2021
 */
public class Day18 extends AbstractDay {
	
	private List<String> numbers;
	
	/**
	 * Puzzle resource.
	 */
	public static final String RESOURCE = "puzzles/2021/day18-list.txt";
	
	/**
	 * Puzzle resource.
	 */
	public static final String TEST = "puzzles/2021/day18-testlist.txt";
	
	/**
	 * Constructor for Day Fifteen puzzles.
	 */
	public Day18() {
		this.dayNum = "18";
		LOGGER.debug("Day " + this.dayNum + ": Snailfish");
		this.year = "2021";
	}
	
	/**
	 * Puzzle one solution.
	 */
	public void puzzleOne() {
		String s = "[[[[1,1],[2,2]],[3,3]],[4,4]]";
		System.out.println(s);
		
		ElementPair ep = new ElementPair(s);
		System.out.println("toString():" + ep.toString());
		System.out.println("Mag: " + ep.magnitude());
		
//		init();
//		
//		String augend = this.numbers.get(0);
//		
//		for(int i=1; i < this.numbers.size(); i++){
//			String sum = add(augend, this.numbers.get(i));
//			
//			boolean changed;
//			do {
//				changed = false;
//				
//				if(willExplode(sum)){
//					sum = explode(sum);
//					changed = true;
//				}
//				
//				else if(willSplit(sum)){
//					sum = split(sum);
//					changed = true;
//				}
//			
//				augend = sum;
//			} while (changed);
//		}
//		
//		final ElementPair ep = new ElementPair(augend);
//		this.solutionOne = String.valueOf(ep.magnitude());
	}
	
	/**
	 * Puzzle two solution.
	 */
	public void puzzleTwo() {
	
		this.solutionTwo = String.valueOf(0);
	}
	
	/**
	 * 
	 */
	public void init(){
		final ResourceReader reader = new ResourceReader(RESOURCE);
		this.numbers = reader.toList();
	}
	
	public String add(final String a, final String b){
		final StringBuilder sb = new StringBuilder();
		sb.append("[");
		sb.append(a);
		sb.append(",");
		sb.append(b);
		sb.append("]");
		
		return sb.toString();
	}
	
	/**
	 * 
	 * @param s
	 * @return
	 */
	public boolean willSplit(final String s){
		final String addend = s.replaceAll("\\[", " ").replaceAll("\\]", " ");
		final String[] parts = addend.split(",");
		
		for(int i=0; i < parts.length; i++){
			int num = Integer.valueOf(parts[i].trim());
			if(num > 9){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 
	 * @param s
	 * @return
	 */
	public String split(final String s){
		final String numbers = s.replaceAll("\\[", " ").replaceAll("\\]", " ");
		final String[] parts = numbers.split(",");
		int index = 0;
		int num = 0;
		
		for(int i=0; i < parts.length; i++){
			num = Integer.valueOf(parts[i].trim());
			if(num > 9){
				System.out.println("Num:" + num);
				index = s.indexOf(String.valueOf(num));
				System.out.println("Index:" + index);
				break;
			}
		}

		final String prefix = s.substring(0, index);
		final String suffix = s.substring(index +2, s.length());
		final int augend = (int)Math.floor((float)num/2);
		final int addend = (int)Math.ceil((float)num/2);
		
		System.out.println("Aug:" + augend +", add:" + addend);
		
		final StringBuilder sb = new StringBuilder();
		sb.append(prefix);
		sb.append("[");
		sb.append(augend);
		sb.append(",");
		sb.append(addend);
		sb.append("]");
		sb.append(suffix);
		
		return sb.toString();
	}
	
	/**
	 * 
	 * @param s
	 * @return
	 */
	public boolean willExplode(final String s){
		final Stack<Character> brackets = new Stack<Character>();
		
		for(int i=0; i < s.length(); i++){
			char c = s.charAt(i);
			if(c == '['){
				brackets.push(c);
				if(brackets.size() > 4){	
					return true;
				}
			} else if (c ==']'){
				brackets.pop();
			}
		}
		return false;
	}
	
	/**
	 * 
	 * @param s
	 * @return
	 */
	public String explode(final String s){
		final Stack<Character> brackets = new Stack<Character>();
		Integer left = null;
		Integer right = null;
		
		for(int i=0; i < s.length(); i++){
			char c = s.charAt(i);
			
			switch(c){
				case '[':
					brackets.push(c);
					break;
				case ']':
					brackets.pop();
					break;
				case ',':
					// Do nothing.
					break;
				default:
					int num = Integer.valueOf(c);
					break;
			}
			
			
			
			
		}
		
		return "";
	}
}