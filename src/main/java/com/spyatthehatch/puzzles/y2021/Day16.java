package com.spyatthehatch.puzzles.y2021;

import java.util.ArrayList;
import java.util.List;

import com.spyatthehatch.objects.y2021.Packet;
import com.spyatthehatch.puzzles.AbstractDay;
import com.spyatthehatch.util.BinaryUtils;
import com.spyatthehatch.util.ResourceReader;

/**
 * Day Fifteen solutions.
 * 
 * @author Bill Everton
 * @version Advent 2021
 */
public class Day16 extends AbstractDay {
	/**
	 * 
	 */
	public String binary;
	
	/**
	 * 
	 */
	private int ip;
	
	/**
	 * 
	 */
	public List<Packet>packets;
	
	/**
	 * Puzzle resource.
	 */
	public static final String RESOURCE = "puzzles/2021/day16-list.txt";
	
	/**
	 * Puzzle resource.
	 */
	public static final String TEST = "puzzles/2021/day16-testlist.txt";
	
	/**
	 * Constructor for Day Fifteen puzzles.
	 */
	public Day16() {
		this.dayNum = "16";
		LOGGER.debug("Day " + this.dayNum + ": Packet Decoder");
		this.year = "2021";
	}
	
	/**
	 * Puzzle one solution.
	 */
	public void puzzleOne() {
		init();
		
		while(this.ip < this.binary.length()){
			Packet p = processPacket();
			this.packets.add(p);
		}
		
		for(Packet p : this.packets){
			System.out.println(p.toString());
		}
		
		this.solutionOne = String.valueOf(0);
	}
	
	/**
	 * Puzzle two solution.
	 */
	public void puzzleTwo() {
		
		this.solutionTwo = String.valueOf(0);
	}
	
	public void init(){
		final ResourceReader reader = new ResourceReader(TEST);
		final String hex = reader.toString();
		this.binary = BinaryUtils.hexToBinary(hex);
		this.packets = new ArrayList<Packet>();
		this.ip = 0;
		
		System.out.println("Hex length: " + hex.length());
		System.out.println("Binary length: " + this.binary.length());
	}
	
	/**
	 * 
	 * @return
	 */
	public Packet processPacket(){
		final int version = BinaryUtils.binaryToDecimal(this.binary.substring(this.ip, this.ip + 3));
		final int type = BinaryUtils.binaryToDecimal(this.binary.substring(this.ip + 3, this.ip + 6));
		final Packet p = new Packet(version, type);
				
		this.ip += 6;
		// This is a literal value.
		if(type == 4){
			final StringBuilder sb = new StringBuilder();
			
			// Keep reading in 5 bits (1 bit for continue/stop, 4 bits for value).
			while(BinaryUtils.binaryToDecimal(this.binary.substring(this.ip, this.ip + 1)) == 1){
				sb.append(this.binary.substring(this.ip + 1, this.ip + 5));
				this.ip += 5;
			}
			
			sb.append(this.binary.substring(this.ip + 1, this.ip + 5));
			p.setValue(BinaryUtils.binaryToDecimal(sb.toString()));
			
			/*
			 *  Advance the pointer to the next hexadecimal value, then to the
			 *  start of the next hexadecimal.
			 */
			this.ip += 5;
		} else {
			// This is an operator.
			System.out.println("We shouldn't get here yet.");
			
		}
		
		this.ip += 4 - (this.ip % 4);
		return p;
	}
}