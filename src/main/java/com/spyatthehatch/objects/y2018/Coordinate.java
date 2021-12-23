package com.spyatthehatch.objects.y2018;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.spyatthehatch.util.CoordinateUtils;

/**
 * Coordinate object.
 * 
 * @author Bill Everton
 * @version Advent 2018
 */
public class Coordinate extends Point{
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
	public Coordinate(final String s) {
		super(Integer.valueOf(StringUtils.substringBefore(s,"," )),
			Integer.valueOf(StringUtils.substringAfter(s, " ")));
		this.pointCount = 0;
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
	 * @param xPos X component.
	 * @param yPos Y component.
	 * @return Manhanttan distance.
	 */
	public int getManhattanDistance(int xPos, int yPos) {
		return CoordinateUtils.getManhattanDistance(this.getX(), this.getY(),
			xPos, yPos);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}