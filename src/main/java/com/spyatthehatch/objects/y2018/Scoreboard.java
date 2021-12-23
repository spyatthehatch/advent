package com.spyatthehatch.objects.y2018;

import java.util.ArrayList;
import java.util.List;

/**
 * Scoreboard object.
 * 
 * @author Bill Everton
 * @version Advent 2018
 */
public class Scoreboard {
	/**
	 * List of recipe scores.
	 */
	private List<Integer> scoreboard;
	
	/**
	 * The index to the recipe Elf One is pointing to.
	 */
	private int elfOne;
	
	/**
	 * The index to the recipe Elf Two is pointing to.
	 */
	private int elfTwo;
	
	/**
	 * Constructor.
	 * 
	 * @param recipeOne Score of the first recipe.
	 * @param recipeTwo Score of the second recipe.
	 */
	public Scoreboard(final int recipeOne, final int recipeTwo){
		this.scoreboard = new ArrayList<Integer>();
		this.scoreboard.add(recipeOne);
		this.scoreboard.add(recipeTwo);
		elfOne = 0;
		elfTwo = 1;
	}
	
	/**
	 * Combine Elf One and Elf Two's recipes and add them to the scoreboard.
	 * 
	 * @return Size of the scoreboard.
	 */
	public int combineRecipes() {
		final int elfOneScore = this.scoreboard.get(this.elfOne);
		final int elfTwoScore = this.scoreboard.get(this.elfTwo);
		int recipeScore = elfOneScore + elfTwoScore;
		if(recipeScore < 10){
			this.scoreboard.add(recipeScore);
		} else {
			this.scoreboard.add(1);
			this.scoreboard.add(recipeScore % 10);
		}
		
		this.elfOne += elfOneScore + 1;
		this.elfTwo += elfTwoScore + 1;
		
		while(this.elfOne > this.scoreboard.size() - 1){
			this.elfOne -= this.scoreboard.size();
		}
		
		while(this.elfTwo > this.scoreboard.size() - 1){
			this.elfTwo -= this.scoreboard.size();
		}
		
		return this.scoreboard.size();
	}
	
	/**
	 * Get the scores between the provided indices as a String.
	 * 
	 * @param startIndex Starting index.
	 * @param endIndex End index.
	 * @return String of scores.
	 */
	public String getScores(final int startIndex, final int endIndex){
		final StringBuilder sb = new StringBuilder();
		
		if(startIndex >= 0 && startIndex < this.scoreboard.size() &&
			endIndex >= 0 && endIndex <= this.scoreboard.size()){
			
			for(int i = startIndex; i < endIndex; i++){
				sb.append(this.scoreboard.get(i));
			}
		}
		
		return sb.toString();
	}
}