package com.spyatthehatch.objects.y2018;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spyatthehatch.objects.y2018.Opcode.Mode;

public class Computer {
	/**
	 * Logger.
	 */
	private static final Logger LOGGER = 
	   LoggerFactory.getLogger(Computer.class);
	

	
	private int[] instructions;
	
	public Computer(int[] instructions){
		this.instructions = instructions;
	}
	
	
	
	
	
	/**
	 * Process all instructions once.
	 * 
	 * @param noun input value at Address 1.
	 * @param verb input value at Address 2.
	 * @return output value at Address 0.
	 */
	public int processScript(final int input){
		int output = 0;
		int increment = 0;
		
		for(int ip=0; ip < this.instructions.length; ip += increment){
			final Opcode opcode = new Opcode(String.valueOf(this.instructions[ip]));
			
			if(opcode.value() == Opcode.ADD ||
				opcode.value() == Opcode.MULTIPLY){
				
				int firstParam = this.instructions[ip + 1];
				int secondParam = this.instructions[ip + 2];
				int store = this.instructions[ip + 3];
				int firstValue, secondValue;
				
				if(opcode.firstMode() == Mode.POSITION){
					firstValue = this.instructions[firstParam];
				} else {
					firstValue = firstParam;
				}
				
				if(opcode.secondMode() == Mode.POSITION){
					secondValue = this.instructions[secondParam];
				} else {
					secondValue = secondParam;
				}
				
				if(opcode.value() == Opcode.ADD){
					this.instructions[store] = firstValue + secondValue;
				} else {
					this.instructions[store] = firstValue * secondValue;
				}
				
			} else if (opcode.value() == Opcode.INPUT){
				this.instructions[ip + 1] = input;
			} else if (opcode.value() == Opcode.OUTPUT){
				output = this.instructions[ip + 1];
				LOGGER.debug("Instruction output: " + output);
			} else if (opcode.value() == Opcode.HALT){
				LOGGER.debug("Halting application on instruction: " + ip);
			} else {
				throw new RuntimeException("Received unknown opcode.");
			}
			
			increment = opcode.getIncrement();
		}
		
		return output;
	}
}
