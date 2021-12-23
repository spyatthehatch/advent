package com.spyatthehatch.objects.y2018;

/**
 * Plant Rule object.
 * @author Bill Everton
 * @version Advent 2018
 */
public class PlantRule {
	/**
	 * Pattern as a byte.
	 */
	private byte pattern = 0x0;
	
	/**
	 * Result of the pattern match.  True, to become a plant.  False, otherwise.
	 */
	private boolean result;
	
	/**
	 * Constructor.
	 * 
	 * @param s String from input.
	 */
	public PlantRule(final String s){
		final String[] split = s.split(" ");
		
		for(int i=0; i<split[0].length(); i++){			
			if(split[0].charAt(i) == '#'){
				this.pattern += Math.pow(2, split[0].length() - i -1);
			}
		}
		
		if(split[2].equals("#")){
			this.result = true;
		} else {
			this.result = false;
		}
	}
	
	/**
	 * @return the pattern
	 */
	public byte getPattern() {
		return this.pattern;
	}

	/**
	 * @return the result
	 */
	public boolean getResult() {
		return this.result;
	}

	@Override
	public String toString(){
		return "Pattern:" + this.pattern +", result:" + this.result;
	}
}