package com.spyatthehatch.objects;

/**
 * Cart object.
 * 
 * @author Bill Everton
 * @version Advent 2018
 */
public class Cart extends Point{	
	/**
	 * Current heading.
	 */
	private Direction currentHeading;
	
	/**
	 * Next turn.
	 */
	private NextTurn nextTurn;
	
	/**
	 * Boolean to denote this cart has crashed.  True, if crashed.  False,
	 * otherwise.
	 */
	private boolean hasCrashed;
	
	/**
	 *	Enum for possible directions.
	 */
	public enum Direction {UP, DOWN, LEFT, RIGHT};
	
	/**
	 * Enum for next turn choices.
	 */
	public enum NextTurn {LEFT, FORWARD, RIGHT};
	
	/**
	 * Constructor.
	 * 
	 * @param x X position.
	 * @param y Y position.
	 * @param dir Current heading.
	 */
	public Cart (final int x, final int y, final char dir){
		super(x, y);
		this.nextTurn = NextTurn.LEFT;
		this.hasCrashed = false;
		
		if(dir == '<') {
			this.currentHeading = Direction.LEFT;
		} else if(dir == '^') {
			this.currentHeading = Direction.UP;
		} else if(dir == '>') {
			this.currentHeading = Direction.RIGHT; 
		} else if(dir == 'v') {
			this.currentHeading = Direction.DOWN;
		}
	}
	
	/**
	 * Constructor.
	 * 
	 * @param x X position.
	 * @param y Y position.
	 * @param heading Current heading.
	 */
	public Cart (final int x, final int y, final Direction heading){
		super(x, y);
		this.nextTurn = NextTurn.LEFT;
		this.hasCrashed = false;
		this.currentHeading = heading;
	}

	/**
	 * Update the position of this cart.
	 */
	public void updatePosition(){
		switch (this.currentHeading) {
			case LEFT:
				this.decrementX();
				break;
			case RIGHT:
				this.incrementX();
				break;
			case DOWN:
				this.incrementY();
				break;
			case UP:
				this.decrementY();
				break;				
		}
	}
	
	/**
	 * Update the heading of this cart.
	 * 
	 * @param track Provided track to be used how to update heading.
	 */
	public void updateHeading(final char track){
		switch(track) {
			case '\\':
				if(this.currentHeading == Direction.UP){
					this.currentHeading = Direction.LEFT;
				} else if(this.currentHeading == Direction.RIGHT){
					this.currentHeading = Direction.DOWN;
				} else if(this.currentHeading == Direction.DOWN){
					this.currentHeading = Direction.RIGHT;
				} else if(this.currentHeading == Direction.LEFT){
					this.currentHeading = Direction.UP;
				}
				break;
			case '/':
				if(this.currentHeading == Direction.UP){
					this.currentHeading = Direction.RIGHT;
				} else if(this.currentHeading == Direction.RIGHT){
					this.currentHeading = Direction.UP;
				} else if(this.currentHeading == Direction.DOWN){
					this.currentHeading = Direction.LEFT;
				} else if(this.currentHeading == Direction.LEFT){
					this.currentHeading = Direction.DOWN;
				}
				break;
			case '+':
				if(this.currentHeading == Direction.UP){
					if(this.nextTurn == NextTurn.LEFT){
						this.currentHeading = Direction.LEFT;
						this.nextTurn = NextTurn.FORWARD;
					} else if(this.nextTurn == NextTurn.FORWARD) {
						this.currentHeading = Direction.UP;
						this.nextTurn = NextTurn.RIGHT;
					} else if(this.nextTurn == NextTurn.RIGHT){
						this.currentHeading = Direction.RIGHT;
						this.nextTurn = NextTurn.LEFT;
					}
				} else if(this.currentHeading == Direction.RIGHT){
					if(this.nextTurn == NextTurn.LEFT){
						this.currentHeading = Direction.UP;
						this.nextTurn = NextTurn.FORWARD;
					} else if(this.nextTurn == NextTurn.FORWARD) {
						this.currentHeading = Direction.RIGHT;
						this.nextTurn = NextTurn.RIGHT;
					} else if(this.nextTurn == NextTurn.RIGHT){
						this.currentHeading = Direction.DOWN;
						this.nextTurn = NextTurn.LEFT;
					}
				} else if(this.currentHeading == Direction.DOWN){
					if(this.nextTurn == NextTurn.LEFT){
						this.currentHeading = Direction.RIGHT;
						this.nextTurn = NextTurn.FORWARD;
					} else if(this.nextTurn == NextTurn.FORWARD) {
						this.currentHeading = Direction.DOWN;
						this.nextTurn = NextTurn.RIGHT;
					} else if(this.nextTurn == NextTurn.RIGHT){
						this.currentHeading = Direction.LEFT;
						this.nextTurn = NextTurn.LEFT;
					}
				} else if(this.currentHeading == Direction.LEFT){
					if(this.nextTurn == NextTurn.LEFT){
						this.currentHeading = Direction.DOWN;
						this.nextTurn = NextTurn.FORWARD;
					} else if(this.nextTurn == NextTurn.FORWARD) {
						this.currentHeading = Direction.LEFT;
						this.nextTurn = NextTurn.RIGHT;
					} else if(this.nextTurn == NextTurn.RIGHT){
						this.currentHeading = Direction.UP;
						this.nextTurn = NextTurn.LEFT;
					}
				}
				break;
		}
	}
	
	/**
	 * @return the currentHeading
	 */
	public Direction getCurrentHeading() {
		return this.currentHeading;
	}

	/**
	 * @return the nextTurn
	 */
	public NextTurn getNextTurn() {
		return this.nextTurn;
	}
	
	/**
	 * Set this cart as crashed.
	 */
	public void setCrashed(){
		this.hasCrashed = true;
	}
	
	/**
	 * Get the crash status of this cart.
	 * 
	 * @return True, if crashed.  False, otherwise.
	 */
	public boolean hasCrashed(){
		return this.hasCrashed;
	}
	
	@Override
	public String toString() {
		return "x:" + this.getX() +" y:" + this.getY() + " heading:" +
			this.currentHeading + " next:" + this.nextTurn;
	}
}