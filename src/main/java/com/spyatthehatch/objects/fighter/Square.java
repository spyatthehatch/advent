package com.spyatthehatch.objects.fighter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.apache.commons.lang3.builder.EqualsBuilder;
import com.spyatthehatch.util.CoordinateUtils;

/**
 * Square object, a grid square on a map.
 * 
 * @author Bill Everton
 * @version Advent 2018
 */
public class Square implements Comparable<Square>{
	/**
	 * Char denoting an open space on the map.  All other chars denote space
	 * that is taken or unavailable.
	 */
	public static final char OPEN = '.';
	
	/**
	 * X coordinate.
	 */
	private int x;
	
	/**
	 * Y Coordinate.
	 */
	private int y;
	
	/**
	 * Constructor.
	 * 
	 * @param x X coordinate.
	 * @param y Y coordinate.
	 */
	public Square (final int x, final int y){
		this.x = x;
		this.y = y;
	}

	/**
	 * @return the x coordinate.
	 */
	public int getX() {
		return this.x;
	}

	/**
	 * @return the y coordinate.
	 */
	public int getY() {
		return this.y;
	}
	
	/**
	 * Get a list of this Square object's open neighboring squares.
	 * 
	 * @param map Map of objects.
	 * @return List of Squares that are open.
	 */
	public List<Square> getOpenNeighbors(final char[][] map){
		
		final int width = map[0].length;
		final int height = map.length;
		final List<Square> neighbors = new ArrayList<Square>();
		
		if(this.y-1 >= 0 && map[this.x][this.y-1] == OPEN){
			neighbors.add(new Square(this.x,this.y-1));
		}
		
		if(this.x-1 >= 0 && map[(this.x-1)][this.y] == OPEN){
			neighbors.add(new Square(this.x-1,this.y));
		}
		
		if(this.x+1 < width && map[(this.x+1)][this.y] == OPEN){
			neighbors.add(new Square(this.x+1,this.y));
		}
		
		if(this.y+1 < height && map[this.x][(this.y+1)] == OPEN){
			neighbors.add(new Square(this.x,this.y+1));
		}
		
		return neighbors;
	}
	
	/**
	 * Get a list of this Square object's taken neighboring squares.
	 * 
	 * @param map Map of objects.
	 * @return List of Squares that are taken.
	 */
	public List<Square> getTakenNeighbors(final char[][] map){
		
		final int width = map[0].length;
		final int height = map.length;
		List<Square> neighbors = new ArrayList<Square>();
		
		if(this.y-1 >= 0 && map[this.x][this.y-1] != OPEN){
			neighbors.add(new Square(this.x,this.y-1));
		}
		
		if(this.x-1 >= 0 && map[this.x-1][this.y] != OPEN){
			neighbors.add(new Square(this.x-1,this.y));
		}
		
		if(this.x+1 < width && map[this.x+1][this.y] != OPEN){
			neighbors.add(new Square(this.x+1,this.y));
		}
		
		if(this.y+1 < height && map[this.x][this.y+1] != OPEN){
			neighbors.add(new Square(this.x,this.y+1));
		}
		
		return neighbors;
	}
	
	public int compareTo(final Square s){
		return CoordinateUtils.readingOrder(this.x, this.y, s.x, s.y);
	}
	
	@Override
	public String toString(){
		return "(" + this.x + "," + y + ")";
	}
	
	@Override
	public int hashCode(){
		return Objects.hash(this.x, this.y);
	}
	
	@Override
	public boolean equals(final Object other){
		if(this == other){
			return true;
		}
		
		if(other == null){
			return false;
		}
		
		if(this.getClass() != other.getClass()){
			return false;
		} else {
			Square s = (Square)other;
			
			return new EqualsBuilder()
				.append(this.x, s.x)
				.append(this.y, s.y)
				.isEquals();
		}
	}
}