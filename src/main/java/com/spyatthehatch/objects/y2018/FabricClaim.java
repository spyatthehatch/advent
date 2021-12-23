package com.spyatthehatch.objects;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * A fabric claim object.
 * 
 * @author Bill Everton
 * @version Advent 2018
 */
public class FabricClaim extends Point {	
	/**
	 * Id.
	 */
	private int id;
		
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
		super(Integer.valueOf(StringUtils.substringBefore(s.split(" ")[2],"," )),
			Integer.valueOf(StringUtils
				.remove(StringUtils.substringAfter(s.split(" ")[2], ","), ":")));
		String[] split = s.split(" ");
		this.id = Integer.valueOf(StringUtils.remove(split[0], "#"));
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
		return this.getX();
	}

	/**
	 * @return the yPos.
	 */
	public int getyPos() {
		return this.getY();
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
		if(x > this.getX() && x <= (this.getX() + this.width) &&
			y > this.getY() && y <= (this.getY() + this.height)) {
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