package com.spyatthehatch.objects.y2018.assembly;

import java.util.HashMap;
import java.util.Map;

/**
 * Opcode enum.
 * 
 * @author Bill Everton
 * @version Advent 2018
 */
public enum Opcode {
	/**
	 * Using process of elimination:
	 * Opcode 0:  ADDI
	 * Opcode 1:  BANI
	 * Opcode 2:  GTIR
	 * Opcode 3:  BORR
	 * Opcode 4:  EQRR
	 * Opcode 5:  BORI
	 * Opcode 6:  GTRR
	 * Opcode 7:  SETR
	 * Opcode 8:  MULI
	 * Opcode 9:  SETI
	 * Opcode 10: BANR
	 * Opcode 11: GTRI
	 * Opcode 12: EQIR
	 * Opcode 13: EQRI
	 * Opcode 14: ADDR
	 * Opcode 15: MULR
	 */
	
	ADDR(14), ADDI(0), MULR(15), MULI(8), BANR(10), BANI(1), BORR(3), BORI(5),
	SETR(7), SETI(9), GTIR(2), GTRI(11), GTRR(6), EQIR(12), EQRI(13),
	EQRR(4);
	
	/**
	 * Integer value of enum.
	 */
	private int value;
	
	/**
	 * Mapping of integer value to cardinal.
	 */
	private static Map<Integer, Opcode> map = new HashMap<Integer, Opcode>();
	
	/*
	 * Build the mapping on init.
	 */
	static {
		for(Opcode opcode : Opcode.values()){
			map.put(opcode.value, opcode);
		}
	}
	
	/**
	 * Get an Opcode from an integer value.
	 * 
	 * @param opcode Integer value of opcode to construct.
	 * @return Opcode object.
	 */
	public static Opcode valueOf(final int opcode){
		return (Opcode) map.get(opcode);
	}
	
	/**
	 * Private constructor.
	 * 
	 * @param value Value.
	 */
	private Opcode(int value){
		this.value = value;
	}
	
	/**
	 * Get the value of this Opcode object.
	 * @return Value.
	 */
	public int getValue(){
		return this.value;
	}
}