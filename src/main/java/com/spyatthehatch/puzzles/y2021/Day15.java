package com.spyatthehatch.puzzles.y2021;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.spyatthehatch.objects.y2021.Point;
import com.spyatthehatch.objects.y2021.Position;
import com.spyatthehatch.puzzles.AbstractDay;
import com.spyatthehatch.util.ResourceReader;

/**
 * Day Fifteen solutions.
 * 
 * @author Bill Everton
 * @version Advent 2021
 */
public class Day15 extends AbstractDay {
	private int[][] grid;
	
	private int width;
	
	private int height;

	private List<Position> positions;
	
	private Map<Point, Integer> candidates;
	
	/**
	 * Puzzle resource.
	 */
	public static final String RESOURCE = "puzzles/2021/day15-list.txt";
	
	/**
	 * Puzzle resource.
	 */
	public static final String TEST = "puzzles/2021/day15-testlist.txt";
	
	/**
	 * Constructor for Day Fifteen puzzles.
	 */
	public Day15() {
		this.dayNum = "15";
		LOGGER.debug("Day " + this.dayNum + ": <Title>");
		this.year = "2021";
	}
	
	/**
	 * Puzzle one solution.
	 */
	public void puzzleOne() {
		init();
		this.positions = new ArrayList<Position>();
		this.candidates = new HashMap<Point, Integer>();
		
		Position current = new Position(0, 0, 0);
		
		while(!(current.getX() == (this.width - 1) && current.getY() == (this.height - 1))){
			final int x = current.getX();
			final int y = current.getY();
			final int risk = current.getRisk();
					
			validateAndAdd(x + 1, y, risk);
			validateAndAdd(x - 1, y, risk);
			validateAndAdd(x, y + 1, risk);
			validateAndAdd(x, y - 1, risk);
			
			Collections.sort(positions);
			current = positions.remove(0);
		}
		
		this.solutionOne = String.valueOf(current.getRisk());
	}
	
	/**
	 * Puzzle two solution.
	 */
	public void puzzleTwo() {
		int[][] bigGrid = new int[this.width * 5][this.height * 5];
		
		for(int down = 0; down < 5; down++){
			for(int across = 0; across < 5; across++){
				int diff = down + across;
			
				for(int y=0; y < this.height; y++){
					for(int x=0; x < this.width; x++){
						int risk = this.grid[x][y] + diff;
						
						if(risk >= 10){
							risk -= 9;
						}

						bigGrid[across * this.width + x][down * this.height + y] = risk;
					}
				}
			}
		}
		
		this.grid = bigGrid;
		this.height = this.height * 5;
		this.width = this.width * 5;
		
		this.positions = new ArrayList<Position>();
		this.candidates = new HashMap<Point, Integer>();
		
		Position current = new Position(0, 0, 0);
		
		while(!(current.getX() == (this.width - 1) && current.getY() == (this.height - 1))){
			final int x = current.getX();
			final int y = current.getY();
			final int risk = current.getRisk();
					
			validateAndAdd(x + 1, y, risk);
			validateAndAdd(x - 1, y, risk);
			validateAndAdd(x, y + 1, risk);
			validateAndAdd(x, y - 1, risk);
			
			Collections.sort(positions);
			current = positions.remove(0);
		}
		
		this.solutionTwo = String.valueOf(current.getRisk());
	}
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @param currentRisk
	 */
	public void validateAndAdd(int x, int y, int currentRisk){
		if(isValid(x, y)){
			int newRisk = this.grid[x][y] + currentRisk;
			Point p = new Point(x, y);
			Integer candidateRisk = candidates.get(p);
			
			if(candidateRisk == null || candidateRisk > newRisk){
				this.positions.add(new Position(x, y, newRisk));
				this.candidates.put(p, newRisk);
			}
		}
	}
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean isValid(int x, int y){
		return x >= 0 && x < this.width && y >= 0 && y < this.height;
	}
	
	/**
	 * 
	 */
	public void init(){
		final ResourceReader reader = new ResourceReader(RESOURCE);
		final List<String> rawList = reader.toList();
		this.height = rawList.size();
		this.width = rawList.get(0).length();
		
		this.grid = new int[this.width][this.height];
		
		for(int y=0; y < this.height; y++){
			final String s = rawList.get(y);
			for(int x=0; x < this.width; x++){
				this.grid[x][y] = Integer.valueOf(s.substring(x, x + 1)); 
			}
		}
	}
}