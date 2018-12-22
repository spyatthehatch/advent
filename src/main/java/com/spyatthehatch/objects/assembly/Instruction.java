package com.spyatthehatch.objects.assembly;

/**
 * Instruction POJO and logic.
 * 
 * @author Bill Everton
 * @version Advent 2018
 */
public class Instruction {
	/**
	 * Opcode of this instruction.
	 */
	private Opcode opcode;
	
	/**
	 * Input A.
	 */
	private int inputA;
	
	/**
	 * Input B.
	 */
	private int inputB;
	
	/**
	 * Output C.
	 */
	private int outputC;
		
	/**
	 * Constructor.
	 * 
	 * @param s String from input.
	 */
	public Instruction(final String s){
		final String[] split = s.split(" ");
		
		this.opcode = Opcode.valueOf(Integer.valueOf(split[0]));
		this.inputA = Integer.valueOf(split[1]);
		this.inputB = Integer.valueOf(split[2]);
		this.outputC = Integer.valueOf(split[3]);
	}

	public Instruction(final String[] array){
		switch(array[0]){
			case "addi":
				this.opcode = Opcode.valueOf(0);
				break;
			case "bani":
				this.opcode = Opcode.valueOf(1);
				break;
			case "gtir":
				this.opcode = Opcode.valueOf(2);
				break;
			case "borr":
				this.opcode = Opcode.valueOf(3);
				break;
			case "eqrr":
				this.opcode = Opcode.valueOf(4);
				break;
			case "bori":
				this.opcode = Opcode.valueOf(5);
				break;
			case "gtrr":
				this.opcode = Opcode.valueOf(6);
				break;
			case "setr":
				this.opcode = Opcode.valueOf(7);
				break;
			case "muli":
				this.opcode = Opcode.valueOf(8);
				break;
			case "seti":
				this.opcode = Opcode.valueOf(9);
				break;
			case "banr":
				this.opcode = Opcode.valueOf(10);
				break;
			case "gtri":
				this.opcode = Opcode.valueOf(11);
				break;
			case "eqir":
				this.opcode = Opcode.valueOf(12);
				break;
			case "eqri":
				this.opcode = Opcode.valueOf(13);
				break;
			case "addr":
				this.opcode = Opcode.valueOf(14);
				break;
			case "mulr":
				this.opcode = Opcode.valueOf(15);
				break;
			default:
				break;
		}
		
		this.inputA = Integer.valueOf(array[1]);
		this.inputB = Integer.valueOf(array[2]);
		this.outputC = Integer.valueOf(array[3]); 
	}
	
	/**
	 * @return the opcode
	 */
	public Opcode getOpcode() {
		return this.opcode;
	}

	/**
	 * @param opcode the opcode to set
	 */
	public void setOpcode(Opcode opcode) {
		this.opcode = opcode;
	}

	/**
	 * @return the inputA
	 */
	public int getInputA() {
		return this.inputA;
	}

	/**
	 * @return the inputB
	 */
	public int getInputB() {
		return this.inputB;
	}

	/**
	 * @return the outputC
	 */
	public int getOutputC() {
		return this.outputC;
	}
	
	/**
	 * Execute the given instruction with the current state of Registers.
	 * @param instruction Instruction to execute.
	 * @param before current state of Registers
	 * @return end state of Registers
	 */
	public static Registers execute(final Instruction instruction, final
		Registers before){
		
		Registers after = null;
		
		switch(instruction.getOpcode()){
			case ADDI:
				after = addi(instruction, before);
				break;
			case BANI:
				after = bani(instruction, before);
				break;
			case GTIR:
				after = gtir(instruction, before);
				break;
			case BORR:
				after = borr(instruction, before);
				break;
			case EQRR:
				after = eqrr(instruction, before);
				break;
			case BORI:
				after = bori(instruction, before);
				break;
			case GTRR:
				after = gtrr(instruction, before);
				break;
			case SETR:
				after = setr(instruction, before);
				break;
			case MULI:
				after = muli(instruction, before);
				break;
			case SETI:
				after = seti(instruction, before);
				break;
			case BANR:
				after = banr(instruction, before);
				break;
			case GTRI:
				after = gtri(instruction, before);
				break;
			case EQIR:
				after = eqir(instruction, before);
				break;
			case EQRI:
				after = eqri(instruction, before);
				break;
			case ADDR:
				after = addr(instruction, before);
				break;
			case MULR:
				after = mulr(instruction, before);
				break;
		}
		return after;
	}
	
	/**
	 * ADDR instruction, ignore Opcode.
	 * 
	 * @param instruction Instruction to execute (ignoring Opcode).
	 * @param before current state of Registers.
	 * @return end state of Registers.
	 */
	public static Registers addr(final Instruction instruction, final Registers
		before){
		
		final Registers after = new Registers(before);
		final int sum = after.getValue(instruction.getInputA()) + after
			.getValue(instruction.getInputB());
		after.updateRegister(instruction.getOutputC(), sum);
		
		return after;
	}
	
	/**
	 * ADDI instruction, ignore Opcode.
	 * 
	 * @param instruction Instruction to execute (ignoring Opcode).
	 * @param before current state of Registers.
	 * @return end state of Registers.
	 */
	public static Registers addi(final Instruction instruction, final Registers
		before){
		
		final Registers after = new Registers(before);
		final int sum = after.getValue(instruction.getInputA()) +
			instruction.getInputB();
		after.updateRegister(instruction.getOutputC(), sum);
		return after;
	}
	
	/**
	 * MULR instruction, ignore Opcode.
	 * 
	 * @param instruction Instruction to execute (ignoring Opcode).
	 * @param before current state of Registers.
	 * @return end state of Registers.
	 */
	public static Registers mulr(final Instruction instruction, final Registers
		before){
			
		final Registers after = new Registers(before);
		final int product = after.getValue(instruction.getInputA()) * after
			.getValue(instruction.getInputB());
		after.updateRegister(instruction.getOutputC(), product);
		return after;
	}
	
	/**
	 * MULI instruction, ignore Opcode.
	 * 
	 * @param instruction Instruction to execute (ignoring Opcode).
	 * @param before current state of Registers.
	 * @return end state of Registers.
	 */
	public static Registers muli(final Instruction instruction, final Registers
		before){
			
		final Registers after = new Registers(before);
		final int product = after.getValue(instruction.getInputA()) *
			instruction.getInputB();
		after.updateRegister(instruction.getOutputC(), product);
		return after;
	}
	
	/**
	 * BANR instruction, ignore Opcode.
	 * 
	 * @param instruction Instruction to execute (ignoring Opcode).
	 * @param before current state of Registers.
	 * @return end state of Registers.
	 */
	public static Registers banr(final Instruction instruction, final Registers
		before){
		
		final Registers after = new Registers(before);
		final int and = after.getValue(instruction.getInputA()) & after
				.getValue(instruction.getInputB());
			after.updateRegister(instruction.getOutputC(), and);
		return after;
	}
	
	/**
	 * BANI instruction, ignore Opcode.
	 * 
	 * @param instruction Instruction to execute (ignoring Opcode).
	 * @param before current state of Registers.
	 * @return end state of Registers.
	 */
	public static Registers bani(final Instruction instruction, final Registers
		before){
			
		final Registers after = new Registers(before);
		final int and = after.getValue(instruction.getInputA()) &
				instruction.getInputB();
			after.updateRegister(instruction.getOutputC(), and);
		return after;
	}
	
	/**
	 * BORR instruction, ignore Opcode.
	 * 
	 * @param instruction Instruction to execute (ignoring Opcode).
	 * @param before current state of Registers.
	 * @return end state of Registers.
	 */
	public static Registers borr(final Instruction instruction, final Registers
		before){
			
		final Registers after = new Registers(before);
		final int or = after.getValue(instruction.getInputA()) | after
				.getValue(instruction.getInputB());
			after.updateRegister(instruction.getOutputC(), or);
		return after;
	}
	
	/**
	 * BORI instruction, ignore Opcode.
	 * 
	 * @param instruction Instruction to execute (ignoring Opcode).
	 * @param before current state of Registers.
	 * @return end state of Registers.
	 */
	public static Registers bori(final Instruction instruction, final Registers
		before){
			
		final Registers after = new Registers(before);
		final int or = after.getValue(instruction.getInputA()) |
				instruction.getInputB();
		after.updateRegister(instruction.getOutputC(), or);
		return after;
	}
	
	/**
	 * SETR instruction, ignore Opcode.
	 * 
	 * @param instruction Instruction to execute (ignoring Opcode).
	 * @param before current state of Registers.
	 * @return end state of Registers.
	 */
	public static Registers setr(final Instruction instruction, final Registers
		before){
			
		final Registers after = new Registers(before);
		final int value = after.getValue(instruction.getInputA());
		after.updateRegister(instruction.getOutputC(), value);
		return after;
	}
	
	/**
	 * SETI instruction, ignore Opcode.
	 * 
	 * @param instruction Instruction to execute (ignoring Opcode).
	 * @param before current state of Registers.
	 * @return end state of Registers.
	 */
	public static Registers seti(final Instruction instruction, final Registers
		before){
			
		final Registers after = new Registers(before);
		after.updateRegister(instruction.getOutputC(), instruction.getInputA());
		return after;
	}
	
	/**
	 * GTIR instruction, ignore Opcode.
	 * 
	 * @param instruction Instruction to execute (ignoring Opcode).
	 * @param before current state of Registers.
	 * @return end state of Registers.
	 */
	public static Registers gtir(final Instruction instruction, final Registers
		before){
			
		final Registers after = new Registers(before);
		
		if(instruction.getInputA() > after.getValue(instruction.getInputB())){
			after.updateRegister(instruction.getOutputC(), 1);
		} else {
			after.updateRegister(instruction.getOutputC(), 0);
		}
		
		return after;
	}
	
	/**
	 * GTRI instruction, ignore Opcode.
	 * 
	 * @param instruction Instruction to execute (ignoring Opcode).
	 * @param before current state of Registers.
	 * @return end state of Registers.
	 */
	public static Registers gtri(final Instruction instruction, final Registers
		before){
			
		final Registers after = new Registers(before);
		
		if(after.getValue(instruction.getInputA()) > instruction.getInputB()){
			after.updateRegister(instruction.getOutputC(), 1);
		} else {
			after.updateRegister(instruction.getOutputC(), 0);
		}
		
		return after;
	}
	
	/**
	 * GTRR instruction, ignore Opcode.
	 * 
	 * @param instruction Instruction to execute (ignoring Opcode).
	 * @param before current state of Registers.
	 * @return end state of Registers.
	 */
	public static Registers gtrr(final Instruction instruction, final Registers
		before){
		
		final Registers after = new Registers(before);
		
		if(after.getValue(instruction.getInputA()) > after.getValue(instruction
			.getInputB())){
			
			after.updateRegister(instruction.getOutputC(), 1);
		} else {
			after.updateRegister(instruction.getOutputC(), 0);
		}
		
		return after;
	}
	
	/**
	 * EQIR instruction, ignore Opcode.
	 * 
	 * @param instruction Instruction to execute (ignoring Opcode).
	 * @param before current state of Registers.
	 * @return end state of Registers.
	 */
	public static Registers eqir(final Instruction instruction, final Registers
		before){
		
		final Registers after = new Registers(before);
		
		if(instruction.getInputA() == after.getValue(instruction.getInputB())){
			after.updateRegister(instruction.getOutputC(), 1);
		} else {
			after.updateRegister(instruction.getOutputC(), 0);
		}
		
		return after;
	}
	
	/**
	 * EQRI instruction, ignore Opcode.
	 * 
	 * @param instruction Instruction to execute (ignoring Opcode).
	 * @param before current state of Registers.
	 * @return end state of Registers.
	 */
	public static Registers eqri(final Instruction instruction, final Registers
		before){
		
		final Registers after = new Registers(before);
		
		if(after.getValue(instruction.getInputA()) == instruction.getInputB()){
			after.updateRegister(instruction.getOutputC(), 1);
		} else {
			after.updateRegister(instruction.getOutputC(), 0);
		}
		
		return after;
	}
	
	/**
	 * EQRR instruction, ignore Opcode.
	 * 
	 * @param instruction Instruction to execute (ignoring Opcode).
	 * @param before current state of Registers.
	 * @return end state of Registers.
	 */
	public static Registers eqrr(final Instruction instruction, final Registers
		before){
		
		final Registers after = new Registers(before);
		
		if(after.getValue(instruction.getInputA()) == after.getValue(instruction
			.getInputB())){
				
				after.updateRegister(instruction.getOutputC(), 1);
			} else {
				after.updateRegister(instruction.getOutputC(), 0);
			}
		
		return after;
	}
	
	public String toString(){
		return "Op:" + this.opcode + " A:" + this.inputA + " B:" + this.inputB +
			" C:" + this.outputC;
	}
}