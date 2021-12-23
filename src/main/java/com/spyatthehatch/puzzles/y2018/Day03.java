package com.spyatthehatch.puzzles.y2018;

import java.util.ArrayList;
import java.util.List;

import com.spyatthehatch.objects.y2018.FabricClaim;
import com.spyatthehatch.puzzles.AbstractDay;
import com.spyatthehatch.util.ResourceReader;

/**
 * Day three solutions.
 * 
 * @author Bill Everton
 * @version Advent 2018
 */
public class Day03 extends AbstractDay {
	/*
	--- Day 3: No Matter How You Slice It ---
	The Elves managed to locate the chimney-squeeze prototype fabric for Santa's
	suit (thanks to someone who helpfully wrote its box IDs on the wall of the
	warehouse in the middle of the night). Unfortunately, anomalies are still
	affecting them - nobody can even agree on how to cut the fabric.
	*/
	
	/**
	 * Puzzle resource, list of fabric claims.
	 */
	public static final String RESOURCE = "puzzles/2018/day3-list.txt";
	
	/**
	 * ArrayList of provided fabric claims.
	 */
	private List<FabricClaim>claims = new ArrayList<FabricClaim>();
	
	/**
	 * Enum for the status of a square inch of fabric.
	 */
	private enum status {OPEN, TAKEN, CONTESTED};
	
	/**
	 * Width of the overall size of fabric.
	 */
	private static final int MAX_WIDTH = 1001;
	
	/**
	 * Height of the overall size of fabric.
	 */
	private static final int MAX_HEIGHT = 1001;
	
	/**
	 * Matrix to record the status of individual square inches of fabric.
	 */
	private status[][] claimStatus = new status[MAX_WIDTH][MAX_HEIGHT];
	
	/**
	 * Constructor for Day 3 puzzles.
	 */
	public Day03() {
		this.dayNum = "3";
		final ResourceReader reader = new ResourceReader(RESOURCE);
		final List<String>input = reader.toList();
		
		for(final String s : input) {
			this.claims.add(new FabricClaim(s));
		}
	}
	
	/**
	 * Puzzle one solution.
	 */
	public void puzzleOne() {
		/*
		The whole piece of fabric they're working on is a very large square - at
		least 1000 inches on each side.

		Each Elf has made a claim about which area of fabric would be ideal for
		Santa's suit. All claims have an ID and consist of a single rectangle
		with edges parallel to the edges of the fabric. Each claim's rectangle
		is defined as follows:
		
		The number of inches between the left edge of the fabric and the left
		edge of the rectangle.
		The number of inches between the top edge of the fabric and the top edge
		of the rectangle.
		The width of the rectangle in inches.
		The height of the rectangle in inches.
		A claim like #123 @ 3,2: 5x4 means that claim ID 123 specifies a
		rectangle 3 inches from the left edge, 2 inches from the top edge, 5
		inches wide, and 4 inches tall. Visually, it claims the square inches of
		fabric represented by # (and ignores the square inches of fabric
		represented by .) in the diagram below:
		
		...........
		...........
		...#####...
		...#####...
		...#####...
		...#####...
		...........
		...........
		...........
		
		The problem is that many of the claims overlap, causing two or more
		claims to cover part of the same areas. For example, consider the
		following claims:
		
		#1 @ 1,3: 4x4
		#2 @ 3,1: 4x4
		#3 @ 5,5: 2x2
		
		Visually, these claim the following areas:
		
		........
		...2222.
		...2222.
		.11XX22.
		.11XX22.
		.111133.
		.111133.
		........
		
		The four square inches marked with X are claimed by both 1 and 2. (Claim
		3, while adjacent to the others, does not overlap either of them.)
		
		If the Elves all proceed with their own plans, none of them will have
		enough fabric. How many square inches of fabric are within two or more claims?
		*/
		
		LOGGER.debug("Calculating contested square inches of fabric...");
		
		for(int x=0; x < MAX_WIDTH; x++) {
			for(int y=0; y < MAX_HEIGHT; y++) {
				claimStatus[x][y] = status.OPEN;
			}
		}
		
		int contested = 0;
		
		for(int x=0; x < MAX_WIDTH; x++) {
			for(int y=0; y < MAX_HEIGHT; y++) {
				for(final FabricClaim claim : this.claims) {
					boolean claimed = claim.isCoordinateClaimed(x, y);
					
					if(claimed) {
						if(claimStatus[x][y] == status.OPEN) {
							claimStatus[x][y] = status.TAKEN;
						} else {
							claimStatus[x][y] = status.CONTESTED;
							contested++;
							break;
						}
					}
				}
			}
		}
		
		LOGGER.debug("Day 3, Puzzle 1: contested square inches are " + contested + ".");
		this.solutionOne = String.valueOf(contested);
	}
	
	/**
	 * Puzzle two solution.
	 */
	public void puzzleTwo() {
		/*
		--- Part Two ---
		Amidst the chaos, you notice that exactly one claim doesn't overlap by
		even a single square inch of fabric with any other claim. If you can
		somehow draw attention to it, maybe the Elves will be able to make
		Santa's suit after all!
		
		For example, in the claims above, only claim 3 is intact after all claims
		are made.
		
		What is the ID of the only claim that doesn't overlap?
		*/
		
		LOGGER.debug("Searching for uncontested claim...");
		
		for(final FabricClaim claim : this.claims){
			boolean contested = false;
			
			for(int x = claim.getxPos(); x <= claim.getxPos()+ claim.getWidth(); x++) {
				for(int y = claim.getyPos(); y <= claim.getyPos() + claim.getHeight(); y++) {
					if(claimStatus[x][y] == status.CONTESTED) {
						contested = true;
						break;
					}
				}
				
				if(contested){
					break;
				}
			}
			
			if(!contested) {
				LOGGER.debug("Day 3, Puzzle 2: claim " + claim.getId() + " is not contested!");
				this.solutionTwo = String.valueOf(claim.getId());
			}
		}
	}
}