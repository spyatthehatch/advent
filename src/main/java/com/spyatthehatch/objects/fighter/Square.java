package com.spyatthehatch.objects.fighter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.apache.commons.lang3.builder.EqualsBuilder;

import com.spyatthehatch.objects.Point;
import com.spyatthehatch.util.CoordinateUtils;

/**
 * Square object, a grid square on a map.
 * 
 * @author Bill Everton
 * @version Advent 2018
 */
public class Square extends Point implements Comparable<Square> {
	/**
	 * Char denoting an open space on the map.  All other chars denote space
	 * that is taken or unavailable.
	 */
	public static final char OPEN = '.';
	
	/**
	 * Constructor.
	 * 
	 * @param x X coordinate.
	 * @param y Y coordinate.
	 */
	public Square (final int x, final int y){
		super(x, y);
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
		
		if(this.getY()-1 >= 0 && map[this.getX()][this.getY()-1] == OPEN){
			neighbors.add(new Square(this.getX(),this.getY()-1));
		}
		
		if(this.getX()-1 >= 0 && map[(this.getX()-1)][this.getY()] == OPEN){
			neighbors.add(new Square(this.getX()-1,this.getY()));
		}
		
		if(this.getX()+1 < width && map[(this.getX()+1)][this.getY()] == OPEN){
			neighbors.add(new Square(this.getX()+1,this.getY()));
		}
		
		if(this.getY()+1 < height && map[this.getX()][(this.getY()+1)] == OPEN){
			neighbors.add(new Square(this.getX(),this.getY()+1));
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
		
		if(this.getY()-1 >= 0 && map[this.getX()][this.getY()-1] != OPEN){
			neighbors.add(new Square(this.getX(),this.getY()-1));
		}
		
		if(this.getX()-1 >= 0 && map[this.getX()-1][this.getY()] != OPEN){
			neighbors.add(new Square(this.getX()-1,this.getY()));
		}
		
		if(this.getX()+1 < width && map[this.getX()+1][this.getY()] != OPEN){
			neighbors.add(new Square(this.getX()+1,this.getY()));
		}
		
		if(this.getY()+1 < height && map[this.getX()][this.getY()+1] != OPEN){
			neighbors.add(new Square(this.getX(),this.getY()+1));
		}
		
		return neighbors;
	}
	
	public int compareTo(final Square s){
		return CoordinateUtils.readingOrder(this.getX(), this.getY(), s.getX(),
			s.getY());
	}
	
	@Override
	public String toString(){
		return "(" + this.getX() + "," + this.getY() + ")";
	}
	
	@Override
	public int hashCode(){
		return Objects.hash(this.getX(), this.getY());
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
				.append(this.getX(), s.getX())
				.append(this.getY(), s.getY())
				.isEquals();
		}
	}
}