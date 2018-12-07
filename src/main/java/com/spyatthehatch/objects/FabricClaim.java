package com.spyatthehatch.objects;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * A fabric claim object.
 * 
 * @author Bill Everton
 * @version Advent 2018
 */
public class FabricClaim {	
	/**
	 * ID.
	 */
	private int id;
	
	/**
	 * X position.
	 */
	private int xPos;
	
	/**
	 * Y position.
	 */
	private int yPos;
	
	/**
	 * Width.
	 */
	private int width;
	
	/**
	 * Height.
	 */
	private int height;
	
	/**
	 * Constructor.
	 * 
	 * @param s Input string.
	 */
	public FabricClaim(final String s) {
		String[] split = s.split(" ");
		this.id = Integer.valueOf(StringUtils.remove(split[0], "#"));
		this.xPos = Integer.valueOf(StringUtils.substringBefore(split[2],"," ));
		this.yPos = Integer.valueOf(StringUtils.remove(StringUtils.substringAfter(split[2], ","), ":"));
		this.width = Integer.valueOf(StringUtils.substringBefore(split[3], "x"));
		this.height = Integer.valueOf(StringUtils.substringAfter(split[3], "x"));
	}

	/**
	 * @return the id.
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * @return the xPos.
	 */
	public int getxPos() {
		return this.xPos;
	}

	/**
	 * @return the yPos.
	 */
	public int getyPos() {
		return this.yPos;
	}

	/**
	 * @return the width.
	 */
	public int getWidth() {
		return this.width;
	}

	/**
	 * @return the height.
	 */
	public int getHeight() {
		return this.height;
	}
	
	/**
	 * Determine if the coordinate provided is within this fabric claim.
	 * 
	 * @param x X Coordinate.
	 * @param y Y Coordinate.
	 * @return true, if the X,Y coordinate is within the fabric claim.  False,
	 * otherwise.
	 */
	public boolean isCoordinateClaimed(int x, int y) {
		if(x > this.xPos && x <= (this.xPos + this.width) &&
			y > this.yPos && y <= (this.yPos + this.height)) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}