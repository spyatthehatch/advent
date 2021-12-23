package com.spyatthehatch.puzzles.y2021;

import java.util.ArrayList;
import java.util.List;

import com.spyatthehatch.puzzles.AbstractDay;
import com.spyatthehatch.util.ResourceReader;

/**
 * Day Four solutions.
 * 
 * @author Bill Everton
 * @version Advent 2021
 */
public class Day05 extends AbstractDay {
	private List<Segment>segments;
	
	/**
	 * Puzzle resource.
	 */
	public static final String RESOURCE = "puzzles/2021/day05-list.txt";
	
	/**
	 * Constructor for Day 5 puzzles.
	 */
	public Day05() {
		LOGGER.debug("Day 5: Hydrothermal Venture");
		this.year = "2021";
		this.dayNum = "5";
		this.segments = new ArrayList<Segment>();
		final ResourceReader reader = new ResourceReader(RESOURCE);
		
		List<String>rawList = reader.toList();
		for(String s : rawList){
			String[] split = s.split(" ");
			String[] start = split[0].split(",");
			String[] end = split[2].split(",");
			final int startX = Integer.valueOf(start[0]);
			final int startY = Integer.valueOf(start[1]);
			final int endX = Integer.valueOf(end[0]);
			final int endY = Integer.valueOf(end[1]);
			final Point startP = new Point(startX, startY);
			final Point endP = new Point(endX, endY);
			this.segments.add(new Segment(startP, endP));
		}
		
		System.out.println("Read in " + this.segments.size() + " segments.");
	}
	
	/**
	 * Puzzle one solution.
	 */
	public void puzzleOne() {
		List<Segment>straight = new ArrayList<Segment>();
		int maxX = 0;
		int maxY = 0;
		
		for(Segment s : this.segments){
			if(s.isStraight()){
				straight.add(s);
				
				if(s.end.getX() > maxX){
					maxX = s.end.getX();
				}
				
				if(s.end.getY() > maxY){
					maxY = s.end.getY();
				}
			}
		}
		
		int[][] grid = new int[maxX + 1][maxY + 1];
		
		for(int x=0; x <= maxX; x++){
			for(int y=0; y <= maxY; y++){
				grid[x][y] = 0;
			}
		}
		
		for(Segment s : straight){
			for(int x=s.start.getX(); x <= s.end.getX(); x++){
				for(int y=s.start.getY(); y <= s.end.getY(); y++){
					grid[x][y] += 1;
				}
			}
		}
		
		int intersections = 0;
		
		for(int x=0; x <= maxX; x++){
			for(int y=0; y <= maxY; y++){
				if(grid[x][y] >= 2){
					intersections++;
				}
			}
		}
		
		this.solutionOne = String.valueOf(intersections);
	}
	
	/**
	 * Puzzle two solution.
	 */
	public void puzzleTwo() {
		int maxX = 0;
		int maxY = 0;
		
		for(Segment s : this.segments){
			if(s.end.getX() > maxX){
				maxX = s.end.getX();
			}
			
			if(s.end.getY() > maxY){
				maxY = s.end.getY();
			}
			
			if(s.start.getY() > maxY){
				maxY = s.start.getY();
			}
		}
				
		int[][] grid = new int[maxX + 1][maxY + 1];
		
		for(int x=0; x <= maxX; x++){
			for(int y=0; y <= maxY; y++){
				grid[x][y] = 0;
			}
		}
		
		for(Segment s : segments){		
			if(s.isStraight()){
				for(int x=s.start.getX(); x <= s.end.getX(); x++){
					for(int y=s.start.getY(); y <= s.end.getY(); y++){
						grid[x][y] += 1;
					}
				}
			} else {			
				for(int x=s.start.getX(); x <=s.end.getX(); x++){
					if(s.start.getY() < s.end.getY()){
						int y = s.start.getY() + (x - s.start.getX());
						grid[x][y] += 1;
					} else {
						int y = s.start.getY() - (x - s.start.getX());
						grid[x][y] += 1;
					}
				}
			}
		}
		
		int intersections = 0;
		
		for(int x=0; x <= maxX; x++){
			for(int y=0; y <= maxY; y++){
				if(grid[x][y] >= 2){
					intersections++;
				}
			}
		}
		
		this.solutionTwo = String.valueOf(intersections);
	}
	
	private class Point {
		private int x;
		private int y;
		
		public Point (int x, int y){
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
	
	private class Segment {
		private Point start;
		private Point end;
		private boolean isVertical;
		private boolean isHorizontal;
		
		public Segment (Point start, Point end){
			this.start = start;
			this.end = end;
			
			if(start.getX() == end.getX()){
				this.isVertical = true;
				this.isHorizontal = false;
				
				if(start.getY() > end.getY()){
					this.end = start;
					this.start = end;
				}
			}
			
			else if(start.getY() ==  end.getY()){
				this.isVertical = false;
				this.isHorizontal = true;
				
				if(start.getX() > end.getX()){
					this.end = start;
					this.start = end;
				}
			}
			
			else {
				this.isVertical = false;
				this.isHorizontal = false;
				
				if(start.getX() > end.getX()){
					this.end = start;
					this.start = end;
				}
			}	
		}
		
		public boolean isStraight(){
			return this.isVertical || this.isHorizontal;
		}
		
		public String toString(){
			return "(" + this.start.getX() + "," + this.start.getY() +"), (" + this.end.getX() + "," + this.end.getY() +")";
		}
	}
}
