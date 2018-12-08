package com.spyatthehatch.objects;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Coordinate object.
 * 
 * @author Bill Everton
 * @version Advent 2018
 */
public class Coordinate {
	/**
	 * X coordinate.
	 */
	private int x;
	
	/**
	 * Y coordinate.
	 */
	private int y;
	
	/**
	 * Number of points this Coordinate has within its area.
	 */
	private int pointCount;
	
	/**
	 * Constructor.
	 * 
	 * @param s String to parse to create Coordinate object from.
	 * @param i Assigned Id.
	 */
	public Coordinate(final String s, int id) {
		this.x = Integer.valueOf(StringUtils.substringBefore(s,"," ));
		this.y = Integer.valueOf(StringUtils.substringAfter(s, " "));
		this.pointCount = 0;
	}
	
	/**
	 * @return the x.
	 */
	public int getX() {
		return this.x;
	}

	/**
	 * @return the y.
	 */
	public int getY() {
		return this.y;
	}

	/**
	 * Increment the point count.
	 */
	public void incrementPointCount() {
		this.pointCount++;
	}
	
	/**
	 * @return the point count.
	 */
	public int getPointCount() {
		return this.pointCount;
	}
	
	/**
	 * Calculate the Manhattan distance between this Coordinate and the
	 * provided x,y position.
	 * 
	 * @param x X component.
	 * @param y Y component.
	 * @return Manhanttan distance.
	 */
	public int getManhattanDistance(int x, int y) {
		return Math.abs(x - this.x) + Math.abs(y -this.y);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}