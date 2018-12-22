package com.spyatthehatch.objects.fighter;

/**
 * Elf object.
 * 
 * @author Bill Everton
 * @version Advent 2018
 */
public class Elf extends CombatUnit {
	/**
	 * Char representing all Elf objects on the map.
	 */
	public static final char TAKEN = 'E';
	
	/**
	 * Constructor.
	 * 
	 * @param x X coordinate.
	 * @param y Y coordinate.
	 */
	public Elf(final int x, final int y){
		super(x,y);
	}
	
	/**
	 * Set the attack value for this Elf.
	 * 
	 * @param attack New attack value for each hit.
	 */
	public void setAttack(final int attack){
		this.attack = attack;
	}
	
	/**
	 * Get the char icon used for this object on the map.
	 */
	public char getIcon(){
		return TAKEN;
	}
	
	@Override
	public String toString(){
		return "Elf id:" + this.id + " position:" + this.location.toString() +
			" health:" + this.health + " attack:" + this.attack;
	}
}