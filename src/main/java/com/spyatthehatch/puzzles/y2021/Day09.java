package com.spyatthehatch.puzzles.y2021;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.spyatthehatch.puzzles.AbstractDay;
import com.spyatthehatch.util.ResourceReader;

/**
 * Day Nine solutions.
 * 
 * @author Bill Everton
 * @version Advent 2021
 */
public class Day09 extends AbstractDay {
	/**
	 * 
	 */
	private int[][] heightMap;
	
	private int width;
	
	private int height;
	
	/**
	 * Puzzle resource.
	 */
	public static final String RESOURCE = "puzzles/2021/day09-list.txt";
	
	/**
	 * Constructor for Day Nine puzzles.
	 */
	public Day09() {
		LOGGER.debug("Day 9: Smoke Basin");
		this.year = "2021";
		this.dayNum = "9";

		final ResourceReader reader = new ResourceReader(RESOURCE);
		final List<String>lines = reader.toList();		
		this.width = lines.get(0).length();
		this.height = lines.size();
		this.heightMap = new int[this.width][this.height];
		
		for(int y=0; y < this.height; y++){
			final String line = lines.get(y);			
			for(int x=0; x < this.width; x++){
				this.heightMap[x][y] = Character.getNumericValue(line.charAt(x));
			}
		}
	}
	
	/**
	 * Puzzle one solution.
	 */
	public void puzzleOne() {	
		int riskLevel = 0;
		int[][] map = this.heightMap.clone();
		
		for(int y=0; y < this.height; y++){
			for(int x=0; x < this.width; x++){
				final List<Point> points = new ArrayList<Point>();
				
				if(isValid(x, y-1)){
					points.add(new Point(x, y-1));
				}
				
				if(isValid(x, y+1)){
					points.add(new Point(x, y+1));
				}
				
				if(isValid(x-1, y)){
					points.add(new Point(x-1, y));
				}
				
				if(isValid(x+1, y)){
					points.add(new Point(x+1, y));
				}
				
				boolean lowest = true;
				for(final Point p : points){
					if(map[p.getX()][p.getY()] <= map[x][y]){
						lowest = false;
						break;
					}
				}
				
				if(lowest){
					riskLevel += (map[x][y] + 1);
				}
			}
		}
		
		this.solutionOne = String.valueOf(riskLevel);
	}
	
	/**
	 * Puzzle two solution.
	 */
	public void puzzleTwo() {
		int[][] map = this.heightMap.clone();
		final List<Integer>basinSizes = new ArrayList<Integer>();
		
		for(int y=0; y < this.height; y++){
			for(int x=0; x < this.width; x++){
				int size = 0;
				final Queue<Point> neighbors = new LinkedList<Point>();
				Point p = new Point(x, y);
								
				while(p != null){
					if(map[p.getX()][p.getY()] < 9){
						size++;
						map[p.getX()][p.getY()] = 10;
						
						if(isValid(p.getX(), p.getY()-1)){
							neighbors.add(new Point(p.getX(), p.getY()-1));
						}
						
						if(isValid(p.getX(), p.getY()+1)){
							neighbors.add(new Point(p.getX(), p.getY()+1));
						}
						
						if(isValid(p.getX()-1, p.getY())){
							neighbors.add(new Point(p.getX()-1, p.getY()));
						}
						
						if(isValid(p.getX()+1, p.getY())){
							neighbors.add(new Point(p.getX()+1, p.getY()));
						}
					}
					
					p = neighbors.poll();
				}
				
				basinSizes.add(size);
			}
		}
		
		Collections.sort(basinSizes);
		Collections.reverse(basinSizes);
		
		int answer = basinSizes.get(0) * basinSizes.get(1) * basinSizes.get(2);			
		this.solutionTwo = String.valueOf(answer);
	}
	
	public boolean isValid(int x, int y){
		return (x >= 0 && x < this.width && y >= 0 && y < this.height);
	}
	
	public class Point {
		int x;
		int y;
		
		public Point(int x, int y){
			this.x = x;
			this.y = y;
		}
		
		public int getX(){
			return this.x;
		}
		
		public int getY(){
			return this.y;
		}
	}
}
