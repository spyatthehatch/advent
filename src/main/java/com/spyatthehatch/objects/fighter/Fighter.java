package com.spyatthehatch.objects.fighter;

public interface Fighter extends Comparable<Fighter> {
	public boolean attack(Fighter f);
	
	public int getX();
	
	public int getY();
	
	public int getHealth();
}