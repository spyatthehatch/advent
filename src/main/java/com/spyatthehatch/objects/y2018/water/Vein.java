package com.spyatthehatch.objects.y2018.water;

import java.util.ArrayList;
import java.util.List;

import com.spyatthehatch.objects.y2018.Point;

public class Vein {
	
	private List<Point> points;
	
	private int minX;
	
	private int minY;
	
	private int maxX;
	
	private int maxY;
		
	public Vein(final String s){
		this.points = new ArrayList<Point>();
		final String[] components = s.split(", ");
		final String[] single = components[0].split("=");
		final String[] multiple = components[1].split("=");
		final String[] ends = multiple[1].split("\\.\\.");
		final int start = Integer.valueOf(ends[0]);
		final int stop = Integer.valueOf(ends[1]);
		
		int x = 0;
		int y = 0;
		if(single[0].equals("x")){
			x = Integer.valueOf(single[1]);
			this.minX = x;
			this.maxX = x;
			this.minY = start;
			this.maxY = stop;
			
			for(y=start; y <= stop; y++){
				points.add(new Point(x, y));
			}
		} else {
			y = Integer.valueOf(single[1]);
			
			this.minY = y;
			this.maxY = y;
			this.minX = start;
			this.maxX = stop;
			
			for(x=start; x <= stop; x++){
				points.add(new Point(x, y));
			}
		}
	}

	/**
	 * @return the minX
	 */
	public int getMinX() {
		return minX;
	}

	/**
	 * @return the minY
	 */
	public int getMinY() {
		return minY;
	}

	/**
	 * @return the maxX
	 */
	public int getMaxX() {
		return maxX;
	}

	/**
	 * @return the maxY
	 */
	public int getMaxY() {
		return maxY;
	}
	
	public List<Point> getPoints(){
		return this.points;
	}
}