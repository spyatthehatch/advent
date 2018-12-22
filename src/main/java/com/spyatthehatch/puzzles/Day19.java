package com.spyatthehatch.puzzles;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.spyatthehatch.objects.assembly.Instruction;
import com.spyatthehatch.objects.assembly.Registers;
import com.spyatthehatch.util.ResourceReader;

/**
 * Day nineteen solutions.
 * 
 * @author Bill Everton
 * @version Advent 2018
 */
public class Day19 extends AbstractDay {
	/*
	
	
	*/
	
	private Map<Integer, Instruction> instructionSet;
	
	private static final int IP_REGISTER = 5;
	
	/**
	 * Puzzle resource.
	 */
	public static final String RESOURCE = "puzzles/day19-list.txt";
	
	/**
	 * Constructor for Day 19 puzzles.
	 */
	public Day19() {
		this.dayNum = "19";
		final ResourceReader reader = new ResourceReader(RESOURCE);
		final List<String> input = reader.toList();
		this.instructionSet = new HashMap<Integer, Instruction>();
		
		for(int i=0; (i + 1) < input.size(); i++){
			final String s = input.get(i + 1);
			this.instructionSet.put(i, new Instruction(s.split(" ")));
		}
		
		LOGGER.trace("Read " + this.instructionSet.size() + " instructions.");
	}
	
	/**
	 * Puzzle one solution.
	 */
	public void puzzleOne() {
		/*
		
		
		*/
		
		int pointer = 0;
		Registers registers = new Registers();
		
		while(pointer >= 0 && pointer < this.instructionSet.size()){
			Instruction instruction = this.instructionSet.get(pointer);
			
			LOGGER.trace(instruction.toString());
			LOGGER.trace("BEF: " + registers.toString());
			registers = Instruction.execute(instruction, registers);
			LOGGER.trace("AFT: " + registers.toString());
			
			pointer = registers.getValue(IP_REGISTER) + 1;
			registers.updateRegister(IP_REGISTER, pointer);
			
			LOGGER.trace("Pointer value: " + pointer);
			LOGGER.trace("");
		}
		
		final int value = registers.getValue(0);
		LOGGER.info("Day 19, Puzzle 1: register 0 value is " + value);
		this.solutionOne = String.valueOf(value);
	}
	
	/**
	 * Puzzle two solution.
	 */
	public void puzzleTwo() {
		/*
		
		
		*/
		
		this.solutionTwo = "bar";
	}
}