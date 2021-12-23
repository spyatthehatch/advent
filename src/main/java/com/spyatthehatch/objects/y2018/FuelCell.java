package com.spyatthehatch.objects.y2018;

/**
 * FuelCell object.
 * 
 * @author Bill Everton
 * @version Advent 2018
 */
public class FuelCell extends Point{
//	/**
//	 * X coordinate.
//	 */
//	private int x;
//	
//	/**
//	 * Y coordinate.
//	 */
//	private int y;
	
	/**
	 * Id for this fuel cell.
	 */
	private int id;
	
	/**
	 * Serial number (puzzle input).
	 */
	private int serialNumber;
	
	/**
	 * Power level of this fuel cell.
	 */
	private int powerLevel;
	
	/**
	 * Constructor.
	 * 
	 * @param x coordinate.
	 * @param y coordinate.
	 * @param serialNumber Serial number (puzzle input).
	 */
	public FuelCell(final int x, final int y, final int serialNumber){
		super(x, y);
//		this.x = x;
//		this.y = y;
		this.serialNumber = serialNumber;
		this.id = this.getX() + 10;
		
		final int intermed = ((this.id * this.getY()) + this.serialNumber) * this.id;
		final String asString = String.valueOf(intermed);
		
		int hundredsValue = 0;
		if(asString.length() > 2) {
			hundredsValue = Integer.valueOf(String.valueOf(asString
				.charAt(asString.length() - 3)));
		}
		
		this.powerLevel = hundredsValue - 5;
	}
	
	/**
	 * Get the power level for this fuel cell.
	 * 
	 * @return power level.
	 */
	public int getPowerLevel() {
		return this.powerLevel;
	}
}