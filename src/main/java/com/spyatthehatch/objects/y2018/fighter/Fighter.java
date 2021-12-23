package com.spyatthehatch.objects.fighter;

/**
 * Fighter interface.
 * 
 * @author Bill Everton
 * @version Advent 2018
 */
public interface Fighter extends Comparable<Fighter> {
	/**
	 * Attack the given Fighter unit.  If the victim's health drops to or below
	 * zero, return true to indicate that it has died.
	 * 
	 * @param f Fighter to attack.
	 * @return true if the victim Fighter has died.  False, otherwise.
	 */
	public boolean attack(Fighter f);
	
	/**
	 * Get the location of this Fighter.
	 * 
	 * @return Location as a Square object.
	 */
	public Square getSquare();
	
	/**
	 * Set the location of this Fighter.
	 * 
	 * @param square new location for Fighter.
	 */
	public void setSquare(Square square);
	
	/**
	 * Get the remaining health for this Fighter.
	 * 
	 * @return Health points.
	 */
	public int getHealth();
	
	/**
	 * Get the Id of this Fighter.
	 * 
	 * @return Id of this Fighter.
	 */
	public int getId();
	
	/**
	 * Get the icon used for this Fighter on the map.
	 * 
	 * @return Icon as a char.
	 */
	public char getIcon();
}