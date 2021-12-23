package com.spyatthehatch.puzzles.y2021;

import com.spyatthehatch.objects.y2021.Point;
import com.spyatthehatch.puzzles.AbstractDay;

/**
 * Day Fifteen solutions.
 * 
 * @author Bill Everton
 * @version Advent 2021
 */
public class Day17 extends AbstractDay {
	/**
	 * 
	 */
	private Point upperLeft;
	
	/**
	 * 
	 */
	private Point upperRight;
	
	/**
	 * 
	 */
	private Point lowerLeft;
	
	/**
	 * 
	 */
	private Point lowerRight;
	
	/**
	 * Puzzle resource.
	 */
	public static final String RESOURCE = "puzzles/2021/day17-list.txt";
	
	/**
	 * Puzzle resource.
	 */
	public static final String TEST = "puzzles/2021/day17-testlist.txt";
	
	/**
	 * Constructor for Day Fifteen puzzles.
	 */
	public Day17() {
		this.dayNum = "17";
		LOGGER.debug("Day " + this.dayNum + ": Trick Shot");
		this.year = "2021";
	}
	
	/**
	 * Puzzle one solution.
	 */
	public void puzzleOne() {
		init();
		
		int max = 0;
		int solution = 0;
		
		for(int y = this.lowerLeft.getY(); y < 5000; y++){			
			if(y % 100 == 0){
				System.out.println("Checking yVelocity:" + y);
			}
			
			for(int x=1; x <= this.upperRight.getX(); x++){
				int xPos = 0;
				int yPos = 0;
				int xVelocity = x;
				int yVelocity = y;
				int maxHeight = 0;
				
				
				while(!overshotTargetArea(xPos, yPos) &&
					!inTargetArea(xPos, yPos) && !underTargetArea(xPos, yPos)){
					
					xPos = xPos + xVelocity;
					yPos = yPos + yVelocity;
					
					if(xVelocity != 0){
						xVelocity--;
					} else {
						if(xPos < this.upperLeft.getX()){
							break;
						}
					}
					yVelocity--;
					
					if(yPos > maxHeight){
						maxHeight = yPos;
					}
				}
				
				if(inTargetArea(xPos, yPos)){
					solution++;
					
					if(maxHeight > max){
						max = maxHeight;
					}
				}
			}
		}
		
		this.solutionOne = String.valueOf(max);
		this.solutionTwo = String.valueOf(solution);
	}
	
	/**
	 * Puzzle two solution.
	 */
	public void puzzleTwo() {
		
	}
	
	/**
	 * 
	 */
	public void init(){
		this.upperLeft = new Point(241, -63);
		this.upperRight = new Point(273, -63);
		this.lowerLeft = new Point(241, -97);
		this.lowerRight = new Point(273, -97);
	}
	
	/**
	 * 
	 * @param p
	 * @return
	 */
	public boolean inTargetArea(final int x, final int y){		
		return x >= this.upperLeft.getX() && x <= this.upperRight.getX() &&
			y <= this.upperLeft.getY() && y >= this.lowerLeft.getY();
	}
	
	/**
	 * 
	 * @param p
	 * @return
	 */
	public boolean overshotTargetArea(final int x, final int y){
		return x > this.upperRight.getX() && y < this.lowerRight.getY();
	}
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean underTargetArea(final int x, final int y){
		return y < this.lowerLeft.getY();
	}
}