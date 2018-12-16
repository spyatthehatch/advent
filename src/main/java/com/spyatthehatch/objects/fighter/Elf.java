package com.spyatthehatch.objects.fighter;

/**
 * 
 * @author Billy
 *
 */
public class Elf extends CombatUnit {
	/**
	 * 
	 * @param x
	 * @param y
	 */
	public Elf(final int x, final int y){
		super(x,y);
	}
	
//	public void attack(Fighter f){
//		if(f instanceof Goblin){
//			Goblin g = (Goblin)f;
//			g.decrementHealth();
//		}
//	}
	
	@Override
	public String toString(){
		return "Elf position: " + x + "," + y + " Health:" + this.health;
	}
}