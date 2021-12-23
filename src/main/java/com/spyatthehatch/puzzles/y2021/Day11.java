package com.spyatthehatch.puzzles.y2021;

import java.util.List;
import java.util.Stack;

import com.spyatthehatch.puzzles.AbstractDay;
import com.spyatthehatch.util.ResourceReader;

/**
 * Day Eleven solutions.
 * 
 * @author Bill Everton
 * @version Advent 2021
 */
public class Day11 extends AbstractDay {
	/**
	 * 
	 */
	private int[][] grid;
	
	private boolean[][] flashed;
	
	private int width;
	
	private int height;
	
	private int count;
	
	private Stack<Point>flashPoints;
	
	/**
	 * Puzzle resource.
	 */
	public static final String RESOURCE = "puzzles/2021/day11-list.txt";
	
	/**
	 * Constructor for Day Eleven puzzles.
	 */
	public Day11() {
		this.dayNum = "11";
		LOGGER.debug("Day " + this.dayNum + ": <Title>");
		this.year = "2021";
	}
	
	/**
	 * Puzzle one solution.
	 */
	public void puzzleOne() {
		init();
		
		for(int i=0; i < 100; i++){
			incrementAll();
		
			while(!this.flashPoints.isEmpty()){
				Point p = this.flashPoints.pop();
				incrementAdjacent(p.x, p.y);
			}
			
			resetFlashed();
		}
		
		this.solutionOne = String.valueOf(this.count);
	}
	
	/**
	 * Puzzle two solution.
	 */
	public void puzzleTwo() {
		init();
		
		int step = 0;
		boolean allFlashed = false;
		
		while(!allFlashed){
			incrementAll();
			
			while(!this.flashPoints.isEmpty()){
				Point p = this.flashPoints.pop();
				incrementAdjacent(p.x, p.y);
			}
			
			allFlashed = resetFlashed();
			step++;
		}
		
		this.solutionTwo = String.valueOf(step);
	}
	
	public void init(){
		final ResourceReader reader = new ResourceReader(RESOURCE);
		final List<String>list = reader.toList();
		
		this.count = 0;
		this.width = list.get(0).length();
		this.height = list.size();
		this.grid = new int[width][height];
		this.flashed = new boolean[width][height];
		this.flashPoints = new Stack<Point>();
		
		for(int y=0; y < this.height; y++){
			final String s = list.get(y);
			
			for(int x=0; x < this.width; x++){
				this.grid[x][y] = Character.getNumericValue(s.charAt(x));
				this.flashed[x][y] = false;
			}
		}
	}
	
	/**
	 * 
	 */
	public void incrementAll(){
		for(int y=0; y < this.height; y++){
			for(int x=0; x < this.width; x++){
				increment(x, y);
			}
		}
	}
	
	/**
	 * 
	 * @param x
	 * @param y
	 */
	public void incrementAdjacent(final int x, final int y){
		final int left = x - 1;
		final int right = x + 1;
		final int above = y - 1;
		final int below = y + 1;

		increment(left, above);
		increment(x, above);
		increment(right, above);
		increment(left, y);
		increment(right, y);
		increment(left, below);
		increment(x, below);
		increment(right, below);
	}
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean isValid(final int x, final int y){
		return x >= 0 && x < this.width && y >= 0 && y < this.height;
	}
	
	/**
	 * 
	 * @param x
	 * @param y
	 */
	public void increment(final int x, final int y){
		if(isValid(x, y)){
			if(this.grid[x][y] == 9){
				this.flashPoints.push(new Point(x, y));
				this.flashed[x][y] = true;
			}
			this.grid[x][y]++;
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean resetFlashed(){
		int flashes = 0;
		
		for(int y=0; y < this.height; y++){
			for(int x=0; x < this.width; x++){
				if(this.flashed[x][y]){
					this.grid[x][y] = 0;
					this.flashed[x][y] = false;
					this.count++;
					flashes++;
				}
			}
		}
		
		if(flashes == 100){
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 
	 * @author Billy
	 *
	 */
	public class Point{
		public int x;
		public int y;
		
		public Point(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
}