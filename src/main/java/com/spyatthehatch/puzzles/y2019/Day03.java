package com.spyatthehatch.puzzles.y2019;

import java.util.ArrayList;
import java.util.List;

import com.spyatthehatch.Constants;
import com.spyatthehatch.objects.y2018.Point;
import com.spyatthehatch.objects.y2019.Segment;
import com.spyatthehatch.objects.y2019.Segment.Direction;
import com.spyatthehatch.puzzles.AbstractDay;
import com.spyatthehatch.util.CoordinateUtils;
import com.spyatthehatch.util.ResourceReader;

/**
 * Day three solutions.
 * 
 * @author Bill Everton
 * @version Advent 2019
 */
public class Day03 extends AbstractDay {
	/*

	*/
	
	/**
	 * Puzzle resource.
	 */
	public static final String RESOURCE = "puzzles/2019/day03-list.txt";
	
	/**
	 * 
	 */
	private List<Segment> redSegments = new ArrayList<Segment>();
	
	/**
	 * 
	 */
	private List<Segment> blueSegments = new ArrayList<Segment>();
	
	/**
	 * 
	 */
	private final static Point ORIGIN = new Point(0,0);
	
	/**
	 * Constructor for Day 3 puzzles.
	 */
	public Day03() {
		LOGGER.debug("Day 3: Crossed Wires");
		this.dayNum = "3";
		
		final ResourceReader reader = new ResourceReader(RESOURCE);
		final List<String> rawList = reader.toList();
		final String[] reds = rawList.get(0).split(Constants.COMMA);
		final String[] blues = rawList.get(1).split(Constants.COMMA);
		final List<String> redWires = new ArrayList<String>();
		final List<String> blueWires = new ArrayList<String>();
				
		for(String s : reds){
			redWires.add(s);
		}
		
		for (final String s : blues){
			blueWires.add(s);
		}
		
		Point currentPoint = ORIGIN;
		for(final String s : redWires){
			final char direction = s.charAt(0);
			final int distance = Integer.valueOf(s.substring(1, s.length()));
			final Point nextPoint = nextPoint(currentPoint, direction, distance);
			this.redSegments.add(new Segment(currentPoint, nextPoint));
			currentPoint = nextPoint;
		}
		
		currentPoint = ORIGIN;
		for(final String s : blueWires){
			final char direction = s.charAt(0);
			final int distance = Integer.valueOf(s.substring(1, s.length()));
			final Point nextPoint = nextPoint(currentPoint, direction, distance);			
			this.blueSegments.add(new Segment(currentPoint, nextPoint));
			currentPoint = nextPoint;
		}
	}
	
	/**
	 * Puzzle one solution.
	 */
	public void puzzleOne() {
		/*

		*/

		final List<Point> intersections = new ArrayList<Point>();
				
		for(Segment r : this.redSegments){
			for(Segment b : this.blueSegments){
				Point intersection = r.intersection(b);
				if(intersection != null){
					intersections.add(intersection);
				}
			}
		}

		int shortest = Integer.MAX_VALUE;
		for(final Point intersection : intersections){
			final int distance = CoordinateUtils.getManhattanDistance(0, 0,
				intersection.getX(), intersection.getY());
			if(distance < shortest){
				shortest = distance;
			}
		}
		
		this.solutionOne = String.valueOf(shortest);
	}
	
	/**
	 * Puzzle two solution.
	 */
	public void puzzleTwo() {
		/*

		*/		
		int redDistance = 0;
		int shortestCombined = Integer.MAX_VALUE;
		
		for(int i=0; i < this.redSegments.size(); i++){
			final Segment r = this.redSegments.get(i);
			redDistance += r.length();
			
			int blueDistance = 0;
			for(int j=0; j < this.blueSegments.size(); j++){
				final Segment b = this.blueSegments.get(j);
				blueDistance += b.length();
				
				final Point intersection = r.intersection(b);
				if(intersection != null){
					int rDist, bDist;
					
					if(r.getDirection() == Direction.UP){
						rDist = redDistance - r.length() + CoordinateUtils.getManhattanDistance(r.getStart().getX(),
							r.getStart().getY(), intersection.getX(), intersection.getY());
					} else if(r.getDirection() == Direction.DOWN){
						rDist = redDistance - r.length() + CoordinateUtils.getManhattanDistance(r.getEnd().getX(),
							r.getEnd().getY(), intersection.getX(), intersection.getY());
					} else if(r.getDirection() == Direction.RIGHT){
						rDist = redDistance - r.length() + CoordinateUtils.getManhattanDistance(r.getStart().getX(),
							r.getStart().getY(), intersection.getX(), intersection.getY());
					} else if(r.getDirection() == Direction.LEFT){
						rDist = redDistance - r.length() + CoordinateUtils.getManhattanDistance(r.getEnd().getX(),
							r.getEnd().getY(), intersection.getX(), intersection.getY());
					} else {
						LOGGER.warn("Received unknown direction.");
						throw new RuntimeException("Unknown direction.");
					}
					
					if(b.getDirection() == Direction.UP){
						bDist = blueDistance - b.length() + CoordinateUtils.getManhattanDistance(b.getStart().getX(),
							b.getStart().getY(), intersection.getX(), intersection.getY());
					} else if(b.getDirection() == Direction.DOWN){
						bDist = blueDistance - b.length() + CoordinateUtils.getManhattanDistance(b.getEnd().getX(),
							b.getEnd().getY(), intersection.getX(), intersection.getY());
					} else if(b.getDirection() == Direction.RIGHT){
						bDist = blueDistance - b.length() + CoordinateUtils.getManhattanDistance(b.getStart().getX(),
							b.getStart().getY(), intersection.getX(), intersection.getY());
					} else if(b.getDirection() == Direction.LEFT){
						bDist = blueDistance - b.length() + CoordinateUtils.getManhattanDistance(b.getEnd().getX(),
							b.getEnd().getY(), intersection.getX(), intersection.getY());
					} else {
						LOGGER.warn("Received unknown direction.");
						throw new RuntimeException("Unknown direction.");
					}
					
					if(rDist + bDist < shortestCombined){
						shortestCombined = rDist + bDist;
					}
				}
			}
		}		
			
		this.solutionTwo = String.valueOf(shortestCombined);
	}
	
	/**
	 * 
	 * @param currentPoint
	 * @param direction
	 * @param distance
	 * @return
	 */
	public Point nextPoint(final Point currentPoint, final char direction, final int distance){		
		if(direction == 'U'){
			return new Point(currentPoint.getX(), currentPoint.getY() + distance);
		} else if (direction == 'D') {
			return new Point(currentPoint.getX(), currentPoint.getY() - distance);
		} else if (direction == 'R') {
			return new Point(currentPoint.getX() + distance, currentPoint.getY());
		} else if (direction == 'L'){
			return new Point(currentPoint.getX() - distance, currentPoint.getY());
		} else {
			LOGGER.warn("Received unknown direction from script: " + direction);
			throw new RuntimeException("Received unknown direction from script: " + direction);
		}
	}
}

