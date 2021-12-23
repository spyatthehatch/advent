package com.spyatthehatch.objects;

import java.util.HashSet;
import java.util.Set;

/**
 * Constellation object.
 * 
 * @author Bill Everton
 * @version Advent 2018
 */
public class Constellation {
	/**
	 * Set of FourDPoints that make up this Constellation.
	 */
	public Set<FourDPoint> points;
	
	/**
	 * The minimum Manhattan distance between two FourDPoint objects to be
	 * considered in the same Constellation. 
	 */
	public static final int MIN_DISTANCE = 3;
	
	/**
	 * Constructor.
	 * 
	 * @param point Starting FourDPoint object.
	 */
	public Constellation(final FourDPoint point){
		this.points = new HashSet<FourDPoint>();
		this.points.add(point);
	}
	
	/**
	 * Attempt to add a FourDPoint to this Constellation.
	 * 
	 * @param point FourDPoint to add to this Constellation.
	 * @return True, if the FourDPoint is close enough to be part of this
	 * Constellation; Or, this FourDPoint is already part of this
	 * Constellation.  False, otherwise.
	 */
	public boolean addPoint(final FourDPoint point){
		for(final FourDPoint p : this.points){
			if(p.distanceTo(point) <= MIN_DISTANCE){
				this.points.add(point);
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Join another Constellation to this Constellation.
	 * 
	 * @param c Constellation to add.
	 */
	public void join(final Constellation c){
		this.points.addAll(c.points);
	}
}