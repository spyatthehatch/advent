package com.spyatthehatch.puzzles;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.spyatthehatch.objects.fighter.*;
import com.spyatthehatch.util.ResourceReader;

/**
 * Day fifteen solutions.
 * 
 * @author Bill Everton
 * @version Advent 2018
 */
public class Day15 extends AbstractDay {
	/*
	
	
	*/
	
	/**
	 * Puzzle resource.
	 */
	public static final String RESOURCE = "puzzles/day15-list.txt";
	
	/**
	 * 
	 */
	private boolean[][] map;
	
	/**
	 * 
	 */
	private List<Fighter> fighters;
	
	/**
	 * 
	 */
	private List<Fighter> goblins;
	
	/**
	 * 
	 */
	private List<Fighter> elves;
	
	private int width;
	
	private int height;
	
	/**
	 * Constructor for Day 15 puzzles.
	 */
	public Day15() {
		this.dayNum = "15";
		final ResourceReader reader = new ResourceReader(RESOURCE);
		final List<String> input = reader.toList();
		this.fighters = new ArrayList<Fighter>();
		this.elves = new ArrayList<Fighter>();
		this.goblins = new ArrayList<Fighter>();

		this.height = input.size();
		this.width = input.get(0).length();
		this.map = new boolean[this.width][this.height];
		
		for(int y=0; y < this.height; y++){
			final String s = input.get(y);
			
			for(int x=0; x < this.width; x++){
				final char c = s.charAt(x);
				
				if(c == '#'){
					this.map[x][y] = false;
				} else if (c == 'G'){
					final Fighter g = new Goblin(x,y);
					this.fighters.add(g);
					this.goblins.add(g);
					this.map[x][y] = false;
				} else if (c == 'E'){
					final Fighter e = new Elf(x,y);
					this.fighters.add(e);
					this.elves.add(e);
					this.map[x][y] = false;
				} else {
					this.map[x][y] = true;
				}
			}
		}
	}
	
	/**
	 * Puzzle one solution.
	 */
	public void puzzleOne() {
		/*
		
		
		*/
		boolean enemiesRemain = true;
		int roundsCompleted = 0;
		while(enemiesRemain){
			Collections.sort(this.fighters);
			
			for(final Fighter fighter : this.fighters){
				List<Fighter> enemies = null;
				
				if(fighter instanceof Elf){
					enemies = this.goblins;
				} else if(fighter instanceof Goblin){
					enemies = this.elves;
				} else {
					LOGGER.warn("Unknown instance of Fighter object!");
				}
				
				// If there are no more enemies, combat is over.
				if(enemies == null || enemies.isEmpty()){
					enemiesRemain = false;
					break;
				}
				
				// Get a list of all squares adjacent and available to each enemy.
				List<Square> adjacents = getAdjacentSquares(enemies);
				
				// Determine if each adjacent/available square has a path to it.
				
				
				/*
				 * Determine the nearest adjacent/available squares, by Manhattan
				 * distance.
				 */
				
				
				// Chose the path based on reading order, and move the Fighter.
				
				
				/*
				 * Attack an adjacent enemy, if there is one.  If multiple enemies
				 * are adjacent, attack the one with the least health.
				 */
			
				break;
			}
			enemiesRemain = false;

			roundsCompleted++;
		}
		
		// Get the sum of remaining health, to determine outcome.
		int healthSum = 0;
		for(final Fighter f: this.fighters){
			healthSum += f.getHealth();
		}
		final int outcome = roundsCompleted * healthSum;
		
		LOGGER.debug("Day 15, Puzzle 1: outcome is " + outcome);
		this.solutionOne = String.valueOf(outcome);
	}
	
	/**
	 * Puzzle two solution.
	 */
	public void puzzleTwo() {
		/*
		
		
		*/
		
		this.solutionTwo = "bar";
	}
	
	private List<Square> getAdjacentSquares(final List<Fighter>enemies){
		final List<Square> adjacents = new ArrayList<Square>();
		
		for(final Fighter f : enemies){
			adjacents.addAll(getAdjacentSquares(f));
		}
		
		return adjacents;
	}
	
	private List<Square> getAdjacentSquares(final Fighter fighter){
		final List<Square> adjacents = new ArrayList<Square>();
		
		final int x = fighter.getX();
		final int y = fighter.getY();
		
		if(y-1 >= 0 && this.map[x][y-1]){
			adjacents.add(new Square(x,y-1));
		}
		
		if(x-1 >= 0 && this.map[x-1][y]){
			adjacents.add(new Square(x-1,y));
		}
		
		if(x+1 < this.width && this.map[x+1][y]){
			adjacents.add(new Square(x+1,y));
		}
		
		if(y+1 < this.height && this.map[x][y+1]){
			adjacents.add(new Square(x,y+1));
		}
		
		return adjacents;
	}
}