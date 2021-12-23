package com.spyatthehatch.objects.y2021;

import java.util.ArrayList;
import java.util.List;

public class Packet {
	/**
	 * 
	 */
	private int version;
	
	/**
	 * 
	 */
	private int type;
	
	/**
	 * 
	 */
	private int value;
	
	/**
	 * 
	 */
	private List<Packet> subpackets;
	
	/**
	 * 
	 * @param version
	 * @param type
	 */
	public Packet(final int version, final int type){
		this.version = version;
		this.type = type;
		this.value = 0;
		this.subpackets = new ArrayList<Packet>();
	}
	
	/**
	 * 
	 * @param p
	 */
	public void addSubpacket(final Packet p){
		this.subpackets.add(p);
	}
	
	/**
	 * 
	 * @return
	 */
	public int getVersion(){
		return this.version;
	}
	
	public int getSum(){
		int sum = this.version;
		
		for(final Packet p : this.subpackets){
			sum += p.getSum();
		}
		
		return sum;
	}
	
	public void setValue(final int value){
		this.value = value;
	}
	
	public int getValue(){
		return this.value;
	}
	
	@Override
	public String toString(){
		return "Packet ver:" + this.version + ", sum:" + getSum() + ", value:" + this.value;
	}
}