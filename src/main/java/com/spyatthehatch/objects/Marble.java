package com.spyatthehatch.objects;

/**
 * Marble object.
 * 
 * @author Bill Everton
 * @version Advent 2018
 */
public class Marble {
	/**
	 * Marble to the left, counter-clockwise.
	 */
	private Marble left;
	
	/**
	 * Marble to the right, clockwise.
	 */
	private Marble right;
	
	/**
	 * The value of this marble.
	 */
	private final int value;
	
	/**
	 * Constructor.
	 * 
	 * @param value Value of this Marble.
	 */
	public Marble (final int value){
		this.value = value;
	}

	/**
	 * @return the Marble to the left of this Marble.
	 */
	public Marble getLeft() {
		return this.left;
	}

	/**
	 * @return the Marble to the right of this Marble.
	 */
	public Marble getRight() {
		return this.right;
	}

	/**
	 * @return the value of this Marble.
	 */
	public int getValue() {
		return this.value;
	}

	/**
	 * @param left Marble to set as left.
	 */
	public void setLeft(final Marble left) {
		this.left = left;
	}

	/**
	 * @param right Marble to set as right.
	 */
	public void setRight(final Marble right) {
		this.right = right;
	}
	
	/**
	 * @return The Marble one Marble clockwise away.
	 */
	public Marble getOneClockwise() {
		return this.right;
	}
	
	/**
	 * @return The Marble two Marbles clockwise away.
	 */
	public Marble getTwoClockwise() {
		return this.right.getRight();
	}
	
	/**
	 * @return The Marble seven Marbles counter-clockwise away.
	 */
	public Marble getSevenCounterClockwise() {
		return this.left.getLeft().getLeft().getLeft().getLeft().getLeft()
			.getLeft();
	}
	
	/**
	 * Remove this Marble node from the linked list.
	 */
	public void remove(){
		final Marble left = this.left;
		final Marble right = this.right;
		left.setRight(right);
		right.setLeft(left);
		this.left = null;
		this.right = null;
	}
	
	/**
	 * Insert this Marblet between two Marbles.
	 * 
	 * @param left Left Marble.
	 * @param right Right Marble.
	 */
	public void insert(final Marble left, final Marble right){
		right.setLeft(this);
		left.setRight(this);
		this.right = right;
		this.left = left;
	}
}