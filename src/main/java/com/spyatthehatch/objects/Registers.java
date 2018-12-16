package com.spyatthehatch.objects;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.builder.EqualsBuilder;

/**
 * Registers object.
 * 
 * @author Bill Everton
 * @version Advent 2018
 */
public class Registers {
	/**
	 * Map of registers.
	 */
	private Map<Integer, Integer> registers = new HashMap<Integer, Integer>();
	
	/**
	 * Deep copy of another Registers object.
	 * 
	 * @param other Registers object to copy.
	 */
	public Registers(final Registers other){
		for(int i=0; i<4; i++){
			this.registers.put(i, other.registers.get(i));
		}
	}
	
	/**
	 * Constructor.
	 * 
	 * @param s String from input.
	 */
	public Registers(final String s){
		final String commaDelim = s.replace("[", "").replace("]", "")
			.replace(" ", "");
		final String[] splits = commaDelim.split(",");
		
		for(int i=0; i<4; i++){
			this.registers.put(i, Integer.valueOf(splits[i]));
		}
	}
	
	/**
	 * Update a register.
	 * 
	 * @param registerNum Register number to update.
	 * @param value Value to update.
	 */
	public void updateRegister(final int registerNum, final int value){
		this.registers.put(registerNum, value);
	}
	
	/**
	 * Get the value of a register.
	 * 
	 * @param registerNum Register to get value from.
	 * @return value.
	 */
	public Integer getValue(final int registerNum){
		return this.registers.get(registerNum);
	}
	
	@Override
	public boolean equals(final Object other){
		if(this == other){
			return true;
		}
		
		if(other == null){
			return false;
		}
		
		if(this.getClass() != other.getClass()){
			return false;
		} else {
			Registers r = (Registers)other;
			
			return new EqualsBuilder()
				.append(this.registers, r.registers)
				.isEquals();
		}
	}
}