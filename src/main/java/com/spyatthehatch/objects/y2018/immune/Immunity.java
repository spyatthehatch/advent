package com.spyatthehatch.objects.y2018.immune;

/**
 * Immunity object.
 * 
 * @author Bill Everton
 * @version Advent 2018
 */
public class Immunity extends Attacker {
	/**
	 * Constructor.
	 * 
	 * @param s String to create Immunity object from.
	 */
	public Immunity(final String s){
		super(s);
		this.name = "Immunity";
	}
}