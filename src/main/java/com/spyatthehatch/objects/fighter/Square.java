package com.spyatthehatch.objects.fighter;

/**
 * 
 * @author Billy
 *
 */
public class Square {
	/**
	 * 
	 */
	private int x;
	
	/**
	 * 
	 */
	private int y;
	
	/**
	 * 
	 * @param x
	 * @param y
	 */
	public Square (final int x, final int y){
		this.x = x;
		this.y = y;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return this.x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return this.y;
	}
	
	public String toString(){
		return "(" + this.x + "," + y + ")";
	}
}