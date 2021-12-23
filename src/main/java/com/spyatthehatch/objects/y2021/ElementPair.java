package com.spyatthehatch.objects.y2021;

import java.util.Stack;

/**
 * 
 * @author Billy
 *
 */
public class ElementPair {
	/**
	 * 
	 */
	private ElementPair leftPairs = null;
	
	/**
	 * 
	 */
	private ElementPair rightPairs = null;
	
	/**
	 * 
	 */
	private int leftValue;
	
	/**
	 * 
	 */
	private int rightValue;
	
	/**
	 * 
	 * @param s
	 */
	public ElementPair(final String number){
		System.out.println("Creating new Element Pair from: " + number);
		
		// Strip off the outer brackets.
		final String s = (number.substring(1, number.length() -1));
		final Stack<Character>brackets = new Stack<Character>();
		
		for(int i=0; i < s.length(); i++){
			final char c = s.charAt(i);
			
			if(c == '['){
				brackets.push(c);
			}
			
			else if(c == ']'){
				brackets.pop();
			}
			
			else if(c == ',' && brackets.isEmpty()){
				final String left = s.substring(0, i);
				final String right = s.substring(i + 1, s.length());

				if(left.length() == 1){
					this.leftValue = Integer.valueOf(left);
				} else {
					this.leftPairs = new ElementPair(left);
				}
				
				if(right.length() == 1){
					this.rightValue = Integer.valueOf(right);
				} else {
					this.rightPairs = new ElementPair(right);
				}
				
//				this.leftPairs = new ElementPair(s.substring(0, i));
//				this.rightPairs = new ElementPair(s.substring(i, s.length()));
			}
		}
	}
	
	/**
	 * 
	 */
	public String toString(){
		final StringBuilder sb = new StringBuilder();
		sb.append("[");
		
		if(this.leftPairs == null){
			sb.append(this.leftValue);
		} else {
			sb.append(this.leftPairs.toString());
		}
		
		sb.append(",");
		
		if(this.rightPairs == null){
			sb.append(this.rightValue);
		} else {
			sb.append(this.rightPairs.toString());
		}
		
		sb.append("]");
		return sb.toString();
	}
	
	/**
	 * 
	 * @return
	 */
	public int magnitude(){
		int magnitude = 0;
		int left = 0;
		int right = 0;
		
		if(this.leftPairs != null){
			left = this.leftPairs.magnitude();
		} else {
			left = this.leftValue;
		}
		
		if(this.rightPairs != null){
			right = this.rightPairs.magnitude();
		} else {
			right = this.rightValue;
		}
		
		magnitude = (3 * left) + (2 * right);
		
		return magnitude;
	}
}