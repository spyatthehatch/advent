package com.spyatthehatch.objects.y2018;

import org.apache.commons.lang3.StringUtils;

import com.spyatthehatch.util.CoordinateUtils;

public class Nanobot {
	private long x;
	
	private long y;
	
	private long z;
	
	private long range;
	
	public Nanobot(final String s){
		final String[] coords = StringUtils.substringBefore(
			StringUtils.substringAfter(s, "=<"),">,").split(",");
		this.x = Long.valueOf(coords[0]);
		this.y = Long.valueOf(coords[1]);
		this.z = Long.valueOf(coords[2]);
		this.range = Long.valueOf(StringUtils.substringAfter(s, "r="));
	}

	public Nanobot(final long x, final long y, final long z){
		this.x = x;
		this.y = y;
		this.z = z;
		this.range = CoordinateUtils.getManhattanDistanace(x, y, z, 0, 0, 0);
	}
	
	/**
	 * @return the x
	 */
	public long getX() {
		return x;
	}

	/**
	 * @return the y
	 */
	public long getY() {
		return y;
	}

	/**
	 * @return the z
	 */
	public long getZ() {
		return z;
	}

	/**
	 * @return the range
	 */
	public long getRange() {
		return range;
	}
	
	public long getDistance(final Nanobot other){
		return CoordinateUtils.getManhattanDistanace(this.x, this.y, this.z,
			other.x, other.y, other.z);
	}
	
	public boolean inRange(final long x, final long y, final long z){
		return (CoordinateUtils.getManhattanDistanace(x, y, z, this.x, this.y,
			this.z) <= this.range);
	}
	
	public String toString(){
		return "(" + this.x + "," + this.y + "," + this.z + "), range:" +
			this.range;
	}
}