package com.spyatthehatch.puzzles.y2021;

import java.util.ArrayList;
import java.util.List;

import com.spyatthehatch.objects.y2021.BingoCard;
import com.spyatthehatch.puzzles.AbstractDay;
import com.spyatthehatch.util.ResourceReader;

/**
 * Day Four solutions.
 * 
 * @author Bill Everton
 * @version Advent 2021
 */
public class Day04 extends AbstractDay {
	/**
	 * The list as a sequence of called spots.
	 */
	private List<Integer> balls;
	
	/**
	 * The list of Bingo cards.
	 */
	private List<BingoCard> cards;
	
	/**
	 * Puzzle resource.
	 */
	public static final String RESOURCE = "puzzles/2021/day04-list.txt";
	
	/**
	 * Constructor for Day 4 puzzles.
	 */
	public Day04() {
		LOGGER.debug("Day 4: Giant Squid");
		this.year = "2021";
		this.dayNum = "4";
		final ResourceReader reader = new ResourceReader(RESOURCE);
		
		this.balls = new ArrayList<Integer>();
		this.cards = new ArrayList<BingoCard>();
		
		/**
		 * Read the first line, which is the sequence of called spots (like balls
		 * in Bingo).
		 */
		String line = reader.toString();
		final String[] s = line.split(",");
		
		for(int i=0; i<s.length; i++){
			this.balls.add(Integer.valueOf(s[i]));
		}
		
		/**
		 * Expect the line after the spot sequence to be white space (but not
		 * null) before the list of Bingo cards.
		 */
		line = reader.toString();
		
		/**
		 * Get the list of Bingo cards.
		 */
		while(line != null){
			int[][] grid = new int[5][5];
			
			for(int y=0; y<5; y++){
				line = reader.toString();
				
				grid[0][y] = Integer.valueOf(line.substring(0,2).trim());
				grid[1][y] = Integer.valueOf(line.substring(3,5).trim());
				grid[2][y] = Integer.valueOf(line.substring(6,8).trim());
				grid[3][y] = Integer.valueOf(line.substring(9,11).trim());
				grid[4][y] = Integer.valueOf(line.substring(12,14).trim());
			}
			
			final BingoCard bc = new BingoCard(grid);
			this.cards.add(bc);
			
			/**
			 * Read the next white space line, which will be null if this is the
			 * last element in the list.
			 */
			line = reader.toString();
		}
		
		System.out.println("Read in " + this.cards.size() + " bingo cards.");
	}
	
	/**
	 * Puzzle one solution.
	 */
	public void puzzleOne() {
		int answer = 0;
		final List<BingoCard>bingoCards = new ArrayList<BingoCard>(this.cards);
		
		for(int b : this.balls){
			for(BingoCard bc : bingoCards){
				if(bc.mark(b)){
					int score = bc.calculate();
					answer = score * b;
					this.solutionOne = String.valueOf(answer);
					return;
				}
			}
		}
		
		this.solutionOne = String.valueOf(answer);
	}
	
	/**
	 * Puzzle two solution.
	 */
	public void puzzleTwo() {
		int score = 0;
		final List<BingoCard>bingoCards = new ArrayList<BingoCard>(this.cards);
		
		for(int b: this.balls){
			for(BingoCard bc : bingoCards){
				if(!bc.hasWon() && bc.mark(b)){
					score = bc.calculate() * b;
				}
			}
		}
		
		this.solutionTwo = String.valueOf(score);
	}
}