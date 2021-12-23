package com.spyatthehatch.objects;

/**
 * 
 * @author Billy
 *
 */
public class Point {
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
	public Point (final int x, final int y){
		this.x = x;
		this.y = y;
	}

	public void incrementX(final int x){
		this.x += x;
	}
	
	public void incrementY(final int y){
		this.y += y;
	}
	
	public void decrementX(){
		this.x--;
	}
	
	public void decrementY(){
		this.y--;
	}
	
	public void incrementX(){
		this.x++;
	}
	
	public void incrementY(){
		this.y++;
	}
	
	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}
}