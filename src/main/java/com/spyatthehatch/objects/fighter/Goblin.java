package com.spyatthehatch.objects.fighter;

/**
 * 
 * @author Billy
 *
 */
public class Goblin extends CombatUnit {
	/**
	 * 
	 * @param x
	 * @param y
	 */
	public Goblin (int x, int y){
		super (x,y);
	}
	
	public String toString(){
		return "Goblin position: " + x + "," + y + " Health:" + this.health;
	}
}