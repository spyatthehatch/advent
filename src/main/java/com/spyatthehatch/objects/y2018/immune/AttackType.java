package com.spyatthehatch.objects.immune;

/**
 * Enum for attack types.
 * 
 * @author Bill Everton
 * @version Advent 2018
 */
public enum AttackType {
	FIRE("fire"), SLASHING("slashing"), BLUDGEONING("bludgeoning"),
	RADIATION("radiation"), COLD("cold");
	
	/**
	 * Text of the different possible enum.
	 */
	private String text;
	
	/**
	 * Private constructor.
	 * 
	 * @param text Text of enum.
	 */
	private AttackType(final String text){
		this.text = text;
	}
	
	/**
	 * Get the text of an enum.
	 * 
	 * @return Text of enum.
	 */
	public String getText(){
		return this.text;
	}
	
	/**
	 * Get the AttackType for a given String.
	 * 
	 * @param s String to get an AttackType enum for.
	 * @return AttackType enum.  If the provided String is invalid, return null.
	 */
	public static AttackType fromString(final String s){
		for(final AttackType type : AttackType.values()){
			if(type.text.equalsIgnoreCase(s)){
				return type;
			}
		}
		
		return null;
	}
}