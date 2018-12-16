package com.spyatthehatch.objects.fighter;

import com.spyatthehatch.util.CoordinateUtils;

public abstract class CombatUnit implements Fighter {
	protected int x;
	
	protected int y;
	
	protected int health;
	
	protected static final int ATTACK = 3;
	
	protected CombatUnit(final int x, final int y){
		this.x = x;
		this.y = y;
		this.health = 200;
	}
	
	public int compareTo(final Fighter f){
		return CoordinateUtils.readingOrder(this.x, this.y, f.getX(), f.getY());
	}
	
	public boolean attack (final Fighter f){
		if(f instanceof CombatUnit){
			CombatUnit c = (CombatUnit)f;
			c.health -= ATTACK;
			
			if(c.health <= 0){
				return true;
			}
		}
		
		return false;
	}
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	
	public int getHealth(){
		return this.health;
	}
}