package com.spyatthehatch.objects;

import java.util.ArrayList;
import java.util.List;

public class Constellation {
	public List<FourDPoint> points;
	
	public static final int MIN_DISTANCE = 3;
	
	public Constellation(final FourDPoint point){
		this.points = new ArrayList<FourDPoint>();
		this.points.add(point);
	}
	
	public boolean addPoint(final FourDPoint point){
		for(final FourDPoint p : this.points){
			if(p.distanceTo(point) <= MIN_DISTANCE){
				this.points.add(point);
				return true;
			}
		}
		
		return false;
	}
	
	public void join(final Constellation c){
		this.points.addAll(c.points);
	}
}