package com.spyatthehatch.objects.y2018;

import java.util.ArrayList;
import java.util.List;

/**
 * MarbleGame logic.
 * 
 * @author Bill Everton
 * @version 2018 Advent
 */
public class MarbleGame {
	/**
	 * Number of players for this game.
	 */
	private int players;
	
	/**
	 * The last marble value;
	 */
	private int lastMarbleValue;
	
	/**
	 * The scores by player.
	 */
	private long[] scores;
	
	/**
	 * List of all marbles in the game.
	 */
	private List<Marble> marbles;
	
	/**
	 * Constructor.
	 * 
	 * @param players Number of players in the game.
	 * @param value Value of the last marble played.
	 */
	public MarbleGame(final int players, final int value){
		this.players = players;
		this.lastMarbleValue = value;
		this.scores = new long[this.players];
		this.marbles = new ArrayList<Marble>();
	}
	
	/**
	 * Calcuate the highest score for this MarbleGame.
	 * 
	 * @return high score.
	 */
	public long calculateHighScore(){
		int turn = 0;
		Marble current = new Marble(turn);
		current.setLeft(current);
		current.setRight(current);
		this.marbles.add(current);
		turn++;
		
		while(turn <= this.lastMarbleValue){
			if(turn % 23 == 0){
				int playerIndex = turn % this.players;
				Marble m = current.getSevenCounterClockwise();
				this.scores[playerIndex] += turn;
				this.scores[playerIndex] += m.getValue();
				current = m.getOneClockwise();
				m.remove();
				this.marbles.remove(m);
			} else {
				Marble m = new Marble(turn);
				Marble left = current.getOneClockwise();
				Marble right = current.getTwoClockwise();
				m.insert(left, right);
				this.marbles.add(m);
				current = m;
			}
			
			turn++;
		}
		
		// Search through the scores array and find the highest score.
		long high = 0;
		for(int i=0; i<this.scores.length; i++) {
			if(this.scores[i] > high) {
				high = this.scores[i];
			}
		}
		
		return high;
	}
}