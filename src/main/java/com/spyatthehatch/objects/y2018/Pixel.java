package com.spyatthehatch.objects;

import org.apache.commons.lang3.StringUtils;

/**
 * Pixel object.
 * 
 * @author Bill Everton
 * @version Advent 2018
 */
public class Pixel extends Point {	
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
		super(Integer.valueOf(StringUtils.substringBefore(StringUtils
			.substringAfter(s.split("> velocity=<")[0], "<"),",").trim()),
			Integer.valueOf(StringUtils.substringAfter(StringUtils
			.substringAfter(s.split("> velocity=<")[0], "<"),",").trim()));
		final String[] split = s.split("> velocity=<");
		final String rawVelocity = StringUtils.substringBefore(split[1], ">");
		this.xVelocity = Integer.valueOf(StringUtils
			.substringBefore(rawVelocity, ",").trim());
		this.yVelocity = Integer.valueOf(StringUtils.substringAfter(rawVelocity,
			",").trim());
	}
	
	/**
	 * Update position based on velocities for one second.
	 */
	public void nextPosition(){
		this.incrementX(this.xVelocity);
		this.incrementY(this.yVelocity);
	}

	/**
	 * Jump many seconds ahead.
	 * 
	 * @param multiplier Number of seconds to jump ahead.
	 */
	public void jump(final int multiplier){
		this.incrementX(this.xVelocity * multiplier);
		this.incrementY((this.yVelocity * multiplier));
	}
	
	/**
	 * @return the xPosition
	 */
	public int getXPosition() {
		return this.getX();
	}

	/**
	 * @return the yPosition
	 */
	public int getYPosition() {
		return this.getY();
	}
	
	@Override
	public String toString() {
		return "Position:" + this.getX() + "," + this.getX() +
			" Velocity:" + this.xVelocity + "," + this.yVelocity;
	}
}