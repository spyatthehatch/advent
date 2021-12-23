package com.spyatthehatch.objects.y2018.fighter;

/**
 * Abstract class of Combat Unit objects.
 * 
 * @author Bill Everton
 * @version Advent 2018
 */
public abstract class CombatUnit implements Fighter {
	/**
	 * Default attack value for all combat units.
	 */
	protected static final int DEFAULT_ATTACK = 3;
	
	/**
	 * This combat unit's current location.
	 */
	protected Square location;
	
	/**
	 * Health points.
	 */
	protected int health;
	
	/**
	 * Id number.
	 */
	protected int id;
	
	/**
	 * Number of combat units created, used for generating Ids.
	 */
	private static int count = 0;
	
	/**
	 * Attack per hit.
	 */
	protected int attack;
	
	/**
	 * Constructor.
	 * 
	 * @param x X coordinate.
	 * @param y Y coordinate.
	 */
	protected CombatUnit(final int x, final int y){
		this.id = count++;
		this.location = new Square(x, y);
		this.health = 200;
		this.attack = DEFAULT_ATTACK;
	}
	
	/**
	 * Get Id of this CombatUnit object.
	 */
	public int getId(){
		return this.id;
	}
	
	/**
	 * Get current location square.
	 */
	public Square getSquare(){
		return this.location;
	}
	
	/**
	 * Set new location square.
	 */
	public void setSquare(final Square square){
		this.location = square;
	}
	
	/**
	 * Get the health of this CombatUnit.
	 */
	public int getHealth(){
		return this.health;
	}
	
	public int compareTo(final Fighter f){
		return this.location.compareTo(f.getSquare());
	}
	
	public boolean attack (final Fighter f){
		if(f instanceof CombatUnit){
			CombatUnit c = (CombatUnit)f;
			c.health -= this.attack;
			
			if(c.health <= 0){
				return true;
			}
		}
		
		return false;
	}
}