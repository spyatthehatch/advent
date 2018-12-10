package com.spyatthehatch.objects;

import org.apache.commons.lang3.StringUtils;

/**
 * Pixel object.
 * 
 * @author Bill Everton
 * @version Advent 2018
 */
public class Pixel {
	/**
	 * X Position.
	 */
	private int xPosition;
	
	/**
	 * Y Position.
	 */
	private int yPosition;
	
	/**
	 * X Velocity.
	 */
	private int xVelocity;
	
	/**
	 * Y Velocity.
	 */
	private int yVelocity;
	
	/**
	 * Constructor.
	 * 
	 * @param s String denoting pixel information, in the form:
	 * position=< 51781,  41361> velocity=<-5, -4>
	 */
	public Pixel(final String s){
		final String[] split = s.split("> velocity=<");
		final String rawCoord = StringUtils.substringAfter(split[0], "<");
		this.xPosition = Integer.valueOf(StringUtils.substringBefore(rawCoord,
			",").trim());
		this.yPosition = Integer.valueOf(StringUtils.substringAfter(rawCoord,
				",").trim());
		String rawVelocity = StringUtils.substringBefore(split[1], ">");
		this.xVelocity = Integer.valueOf(StringUtils
			.substringBefore(rawVelocity, ",").trim());
		this.yVelocity = Integer.valueOf(StringUtils.substringAfter(rawVelocity,
			",").trim());
	}
	
	/**
	 * Update position based on velocities for one second.
	 */
	public void nextPosition(){
		this.xPosition += this.xVelocity;
		this.yPosition += this.yVelocity;
	}

	/**
	 * Jump many seconds ahead.
	 * 
	 * @param multiplier Number of seconds to jump ahead.
	 */
	public void jump(int multiplier){
		this.xPosition += (this.xVelocity * multiplier);
		this.yPosition += (this.yVelocity * multiplier);
	}
	
	/**
	 * @return the xPosition
	 */
	public int getXPosition() {
		return this.xPosition;
	}

	/**
	 * @return the yPosition
	 */
	public int getYPosition() {
		return this.yPosition;
	}
	
	@Override
	public String toString() {
		return "Position:" + this.xPosition + "," + this.yPosition +
			" Velocity:" + this.xVelocity + "," + this.yVelocity;
	}
}