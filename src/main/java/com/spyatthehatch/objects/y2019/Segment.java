package com.spyatthehatch.objects.y2019;

import com.spyatthehatch.objects.y2018.Point;

/**
 * Segment class.
 * 
 * @author Bill Everton
 *
 */
public class Segment {

	/**
	 * Orientation enumeration.
	 */
	public enum Orientation {HORIZONTAL, VERTICAL};
	
	/**
	 * Direction enumeration.
	 */
	public enum Direction {UP, DOWN, LEFT, RIGHT};
	
	/**
	 * Orientation.
	 */
	private Orientation orientation;
	
	/**
	 * Start point.
	 */
	private Point start;
	
	/**
	 * End point.
	 */
	private Point end;
	
	/**
	 * Length of Segment.
	 */
	private int length;
	
	/**
	 * Direction.
	 */
	private Direction direction;
	
	/**
	 * Constructor.  The constructor will determine direction and orientation
	 * from the start and end points provided.
	 * 
	 * @param start Start Point.
	 * @param end End point.
	 */
	public Segment (final Point start, final Point end){
		this.start = start;
		this.end = end;
		
		if(start.getY() == end.getY()){
			this.orientation = Orientation.HORIZONTAL;
			if(start.getX() < end.getX()){
				this.start = start;
				this.end = end;
				this.direction = Direction.RIGHT;
			} else {
				this.end = start;
				this.start = end;
				this.direction = Direction.LEFT;
			}
			this.length = this.end.getX() - this.start.getX();
			
		} else {
			this.orientation = Orientation.VERTICAL;
			if(start.getY() < end.getY()){
				this.start = start;
				this.end = end;
				this.direction = Direction.UP;
			} else {
				this.end = start;
				this.start = end;
				this.direction = Direction.DOWN;
			}
			this.length = this.end.getY() - this.start.getY();
		}
	}
	
	/**
	 * Get the start Point.  The start point is the point with the larger value
	 * among the two points in the segment, and does not indicate direction.
	 * 
	 * @return Start Point.
	 */
	public Point getStart(){
		return this.start;
	}
	
	/**
	 * Get the end Point.  The end point is the point with the smaller value
	 * among the two points in the segment, and does not indicate direction.
	 * 
	 * @return End Point.
	 */
	public Point getEnd(){
		return this.end;
	}
	
	/**
	 * @return Orientation of the Segment.
	 */
	public Orientation getOrientation(){
		return this.orientation;
	}
	
	/**
	 * @return Direction of the Segment.
	 */
	public Direction getDirection(){
		return this.direction;
	}
	
	/**
	 * @return Length of the Segment.
	 */
	public int length(){
		return this.length;
	}
	
	/**
	 * Get the intersection of two segments, or null if it does not exist.
	 * Assumes that the segments must be orthogonal to have an intersection.
	 * 
	 * @param other Other segment to check intersection with.
	 * @return Point object of intersection between two Segments.
	 */
	public Point intersection(final Segment other){
		// Assume that segments must be orthogonal to intersect.
		if(this.orientation == other.orientation){
			return null;
		}
		
		if(this.orientation == Orientation.HORIZONTAL){
			if(this.start.getX() < other.start.getX() &&
				this.end.getX() > other.start.getX() &&
				this.start.getY() > other.start.getY() &&
				this.start.getY() < other.end.getY()) {
				
				return new Point(other.start.getX(), this.start.getY());
			} else {
				return null;
			}
		} else {
			if(other.start.getX() < this.start.getX() &&
				other.end.getX() > this.start.getX() &&
				other.start.getY() > this.start.getY() &&
				other.start.getY() < this.end.getY()) {

				return new Point(this.start.getX(), other.start.getY());	
				} else {
					return null;
			}
		}
	}
	
	@Override
	public String toString(){
		return this.start.toString() + " to " + this.end.toString();
	}
}
