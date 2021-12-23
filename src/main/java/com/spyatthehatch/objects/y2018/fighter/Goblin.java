package com.spyatthehatch.objects.y2018.fighter;

/**
 * Goblin object.
 * 
 * @author Bill Everton
 * @version Advent 2018
 */
public class Goblin extends CombatUnit {
	/**
	 * Char representing all Goblin objects on the map.
	 */
	public static final char TAKEN = 'G';
	
	/**
	 * Constructor.
	 * 
	 * @param x X coordinate.
	 * @param y Y coordinate.
	 */
	public Goblin (int x, int y){
		super (x,y);
	}

	/**
	 * Get the char icon used for this object on the map.
	 */
	public char getIcon(){
		return TAKEN;
	}
	
	public String toString(){
		return "Goblin id:" + this.id + " position:" + this.location.toString() +
			" health:" + this.health;
	}
}