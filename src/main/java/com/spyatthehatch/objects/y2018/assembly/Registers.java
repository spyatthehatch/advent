package com.spyatthehatch.objects.y2018.assembly;

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
	 * The number of registers.
	 */
	public static int REGISTERS_COUNT = 6;
	
	/**
	 * Map of registers.
	 */
	private Map<Integer, Integer> registers;

	/**
	 * Default constructor.  Initialize the registers to all zeros.
	 */
	public Registers(){
		this.registers = new HashMap<Integer, Integer>();
		for(int i=0; i<REGISTERS_COUNT; i++){
			this.registers.put(i, 0);
		}
	}
	
	/**
	 * Deep copy of another Registers object.
	 * 
	 * @param other Registers object to copy.
	 */
	public Registers(final Registers other){
		this();
		for(int i=0; i<REGISTERS_COUNT; i++){
			Integer value = other.registers.get(i);
			if(value != null){
				this.registers.put(i, value);
			} else {
				this.registers.put(i, 0);
			}
		}
	}

	/**
	 * Constructor.
	 * 
	 * @param s String from input.
	 */
	public Registers(final String s){
		this();
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
	
	public String toString(){
		final StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<REGISTERS_COUNT; i++){
			sb.append(this.registers.get(i) + " ");
		}
		return sb.toString();
	}
}