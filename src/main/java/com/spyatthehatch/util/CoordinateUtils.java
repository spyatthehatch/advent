package com.spyatthehatch.util;

/**
 * 
 * @author Billy
 *
 */
public class CoordinateUtils {
	/**
	 * 
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @return
	 */
	public static int getManhattanDistance(final int x1, final int y1, final int x2,
		final int y2){
		
		return Math.abs(x2 - x1) + Math.abs(y2 - y1);
	}
	
	/**
	 * 
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @return
	 */
	public static int readingOrder(final int x1, final int y1, final int x2,
		final int y2){
		
		final int BEFORE = -1;
		final int AFTER = 1;
		
		if(y1 < y2){
			return BEFORE;
		} else if(y1 > y2){
			return AFTER;
		} else {
			if(x1 < x2){
				return BEFORE;
			} else {
				return AFTER;
			}
		}
	}
}