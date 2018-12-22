package com.spyatthehatch.objects;

/**
 * Region object.
 * 
 * @author Bill Everton
 * @version Advent 2018
 */
public class Region {
	/**
	 * Enum of different region types;
	 */
	public enum Type {ROCKY, NARROW, WET};	
	
	/**
	 * X Coefficient when Y=0.
	 */
	public static final int X_COEFFICIENT = 16807;
	
	/**
	 * Y Coefficient when X=0.
	 */
	public static final int Y_COEFFICIENT = 48271;
	
	/**
	 * Erosion modulo.
	 */
	public static final int EROSION_MODULO = 20183;
	
	/**
	 * X coordinate.
	 */
	private int x;
	
	/**
	 * Y coordinate.
	 */
	private int y;
	
	/**
	 * Type of this region.
	 */
	private Type type;
	
	/**
	 * Geologic index.
	 */
	private int geologicIndex;
	
	/**
	 * Erosion level.
	 */
	private int erosionLevel;
	
	/**
	 * Constructor.
	 * 
	 * @param x X coordinate.
	 * @param y Y coordinate.
	 * @param depth Depth of the cavern.
	 * @param geologicIndex Geologic index of this region.
	 */
	public Region(final int x, final int y, final int depth, final int
		geologicIndex){
		
		this.x = x;
		this.y = y;
		this.geologicIndex = geologicIndex;
		this.erosionLevel = (this.geologicIndex + depth) % EROSION_MODULO;
		
		final int modulo = this.erosionLevel % 3;
		
		switch(modulo){
			case 0:
				this.type = Type.ROCKY;
				break;
			case 1:
				this.type = Type.WET;
				break;
			case 2:
				this.type = Type.NARROW;
				break;
		}
	}

	/**
	 * @return The risk identified with this region.
	 */
	public int getRisk(){
		switch(this.type){
			case ROCKY:
				return 0;
			case WET:
				return 1;
			case NARROW:
				return 2;
			default:
				return -1;
		}
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

	/**
	 * @return the type
	 */
	public Type getType() {
		return this.type;
	}

	/**
	 * @return the geologicIndex
	 */
	public int getGeologicIndex() {
		return this.geologicIndex;
	}

	/**
	 * @return the erosionLevel
	 */
	public int getErosionLevel() {
		return this.erosionLevel;
	}
}