package com.spyatthehatch.objects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Sample object.
 * 
 * @author Bill Everton
 * @version Advent 2018
 */
public class Sample {
	/**
	 * Map of integer opcodes to operation names.
	 */
	private static Map<Integer, List<String>> possibles =
		new HashMap<Integer, List<String>>();
	
	/**
	 * Given state of Registers before.
	 */
	private Registers before;
	
	/**
	 * Given instruction.
	 */
	private Instruction instruction;
	
	/**
	 * Given state of Registers after.
	 */
	private Registers after;
	
	/**
	 * Constructor.
	 * 
	 * @param before state of Registers before operation.
	 * @param instruction Instruction.
	 * @param after end state of Registers.
	 */
	public Sample(final Registers before, final Instruction instruction, final
		Registers after){
		
		this.before = before;
		this.instruction = instruction;
		this.after = after;
	}
	
	/**
	 * Execute all operations on the this sample.
	 * 
	 * @return Number of operations that matched provided outcome.
	 */
	public int executeAllOpcodes(){
		final int opNumber = this.instruction.getOpcode().getValue();
		final List<String> opList = new ArrayList<String>();
		
		if(Instruction.addr(this.instruction, this.before).equals(this.after)){
			opList.add("addr");
		}
		
		if(Instruction.addi(this.instruction, this.before).equals(this.after)){
			opList.add("addi");
		}
		
		if(Instruction.mulr(this.instruction, this.before).equals(this.after)){
			opList.add("mulr");
		}

		if(Instruction.muli(this.instruction, this.before).equals(this.after)){
			opList.add("muli");
		}
		
		if(Instruction.banr(this.instruction, this.before).equals(this.after)){
			opList.add("banr");
		}
		
		if(Instruction.bani(this.instruction, this.before).equals(this.after)){
			opList.add("bani");
		}
		
		if(Instruction.borr(this.instruction, this.before).equals(this.after)){
			opList.add("borr");
		}
		
		if(Instruction.bori(this.instruction, this.before).equals(this.after)){
			opList.add("bori");
		}
		
		if(Instruction.setr(this.instruction, this.before).equals(this.after)){
			opList.add("setr");
		}
		
		if(Instruction.seti(this.instruction, this.before).equals(this.after)){
			opList.add("seti");
		}
		
		if(Instruction.gtir(this.instruction, this.before).equals(this.after)){
			opList.add("gtir");
		}
		
		if(Instruction.gtri(this.instruction, this.before).equals(this.after)){
			opList.add("gtri");
		}
		
		if(Instruction.gtrr(this.instruction, this.before).equals(this.after)){
			opList.add("gtrr");
		}
		
		if(Instruction.eqir(this.instruction, this.before).equals(this.after)){
			opList.add("eqir");
		}
		
		if(Instruction.eqri(this.instruction, this.before).equals(this.after)){
			opList.add("eqri");
		}
		
		if(Instruction.eqrr(this.instruction, this.before).equals(this.after)){
			opList.add("eqrr");
		}
		
		addPossible(opNumber, opList);
		return opList.size();
	}
	
	/**
	 * Add operation name as a possible operation for this opcode.
	 * 
	 * @param opNumber Opcode number.
	 * @param list List of operation names as Strings.
	 */
	private void addPossible(final int opNumber, List<String> list){
		final List<String> possibleOpList = possibles.get(opNumber);
		
		if(possibleOpList == null) {
			possibles.put(opNumber, list);
		} else {
			possibleOpList.retainAll(list);
		}
	}
	
	/**
	 * Create string of possible opcode/operation mappings.
	 * 
	 * @param opNumber Opcode number to output operations.
	 * @return List of operation names as Strings.
	 */
	public static String outputPossibles(final int opNumber){
		final StringBuilder sb = new StringBuilder();
		final List<String> opList = possibles.get(opNumber);
		
		sb.append("Opcode " + opNumber + " possible operations:");
		for(final String op : opList){
			sb.append(op + " ");
		}
		
		return sb.toString();
	}
}