package com.spyatthehatch.objects.y2018;

import java.util.Objects;
import org.apache.commons.lang3.builder.EqualsBuilder;

import com.spyatthehatch.util.CoordinateUtils;

/**
 * Four dimemsional point.
 * 
 * @author Bill Everton
 * @version Advent 2018
 *
 */
public class FourDPoint extends Point {	
	/**
	 * Z coordinate.
	 */
	private int z;
	
	/**
	 * 4th coordinate.
	 */
	private int t;
	
	/**
	 * Constructor.
	 * 
	 * @param s String from puzzle input.
	 */
	public FourDPoint(final String s) {
		super(Integer.valueOf(s.split(",")[0]), Integer
			.valueOf(s.split(",")[1]));
		final String[] split = s.split(",");
		this.z = Integer.valueOf(split[2]);
		this.t = Integer.valueOf(split[3]);
	}
	
	/**
	 * Get the Manhattan distance to another FourDPoint.
	 * 
	 * @param other FourDPoint to get distance to.
	 * @return Manhattan distance.
	 */
	public int distanceTo(final FourDPoint other){
		return CoordinateUtils.getManhattanDistance(this.getX(), this.getY(),
			this.z, this.t, other.getX(), other.getY(), other.z, other.t);
	}
	
	@Override
	public int hashCode(){
		return Objects.hash(this.getX(), this.getY(), this.z, this.t);
	}
	
	@Override
	public boolean equals(final Object o){
		if(o == null){
			return false;
		}
		
		if(o == this){
			return true;
		}
		
		if(this.getClass() != o.getClass()){
			return false;
		} else {
			final FourDPoint point = (FourDPoint)o;
			
			return new EqualsBuilder()
				.append(this.getX(), point.getX())
				.append(this.getY(), point.getY())
				.append(this.z, point.z)
				.append(this.t, point.t)
				.isEquals();
		}
	}
}