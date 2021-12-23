package com.spyatthehatch.objects.y2018.water;

/**
 * Enum for soil types.
 * 
 * @author Bill Everton
 * @version Advent 2018
 */
public enum SoilType {
	SAND('.'), CLAY('#'), WATER('~'), OVERFLOW('|');
	
	/**
	 * Char of the different possible enum.
	 */
	private char icon;
	
	/**
	 * Private constructor.
	 * 
	 * @param icon Char of enum.
	 */
	private SoilType(final char icon){
		this.icon = icon;
	}
	
	/**
	 * Get the icon of an enum.
	 * 
	 * @return Char icon of enum.
	 */
	public char getIcon(){
		return this.icon;
	}
	
	/**
	 * Get the SoilType for a given char.
	 * 
	 * @param s String to get an SoilType enum for.
	 * @return SoilType enum.  If the provided char is invalid, return null.
	 */
	public static SoilType fromString(final char c){
		for(final SoilType type : SoilType.values()){
			if(type.icon == c){
				return type;
			}
		}
		
		return null;
	}
}