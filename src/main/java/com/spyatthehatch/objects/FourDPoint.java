package com.spyatthehatch.objects;

import com.spyatthehatch.util.CoordinateUtils;

public class FourDPoint {
	private int x;
	
	private int y;
	
	private int z;
	
	private int t;
	
	public FourDPoint(final String s) {
		final String[] split = s.split(",");
		this.x = Integer.valueOf(split[0]);
		this.y = Integer.valueOf(split[1]);
		this.z = Integer.valueOf(split[2]);
		this.t = Integer.valueOf(split[3]);
	}
	
	public int distanceTo(final FourDPoint other){
		return CoordinateUtils.getManhattanDistance(this.x, this.y, this.z,
			this.t, other.x, other.y, other.z, other.t);
	}
}