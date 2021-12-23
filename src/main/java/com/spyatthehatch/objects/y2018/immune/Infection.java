package com.spyatthehatch.objects.y2018.immune;

/**
 * Infection object.
 * 
 * @author Bill Everton
 * @version Advent 2018
 */
public class Infection extends Attacker {
	/**
	 * Constructor.
	 * 
	 * @param s String to create Infection object from.
	 */
	public Infection(final String s){
		super(s);
		this.name = "Infection";
	}
}