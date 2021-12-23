package com.spyatthehatch.objects.y2018;

public class Opcode {
	private int value;
	
	public enum Mode {POSITION, IMMEDIATE};
	
	private Mode firstMode;
	
	private Mode secondMode;
	
	/**
	 * Add opcode.
	 */
	public static final int ADD = 1;
	
	/**
	 * Multiply opcode.
	 */
	public static final int MULTIPLY = 2;
	
	public static final int INPUT = 3;
	
	public static final int OUTPUT = 4;
	/**
	 * Halt opcode.
	 */
	public static final int HALT = 99;
	
	public Opcode(final String s){
		String op = s.substring(s.length() - 1);
		this.value = Integer.valueOf(op);
		
		final int modes = Integer.valueOf(s.substring(0, s.length() - 2));
		
		if(modes % 2 == 0){
			this.firstMode = Mode.POSITION;
		} else {
			this.firstMode = Mode.IMMEDIATE;
		}
		
		if(modes < 9){
			this.secondMode = Mode.POSITION;
		} else {
			this.secondMode = Mode.IMMEDIATE;
		}
	}
	
	public int value(){
		return this.value;
	}
	
	public Mode firstMode(){
		return this.firstMode;
	}
	
	public Mode secondMode(){
		return this.secondMode;
	}
	
	public int getIncrement(){
		if(value == ADD || value == MULTIPLY){
			return 4;
		} else if(value == INPUT || value == OUTPUT){
			return 2;
		} else if(value == HALT){
			return 1;
		} else {
			throw new RuntimeException("Received unknown opcode value.");
		}
	}
}
