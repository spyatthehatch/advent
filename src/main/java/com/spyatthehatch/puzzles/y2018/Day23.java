package com.spyatthehatch.puzzles.y2018;

import java.util.ArrayList;
import java.util.List;

import com.spyatthehatch.objects.y2018.Nanobot;
import com.spyatthehatch.puzzles.AbstractDay;
import com.spyatthehatch.util.ResourceReader;

/**
 * Day twenty three solutions.
 * 
 * @author Bill Everton
 * @version Advent 2018
 */
public class Day23 extends AbstractDay {
	/*
	
	
	*/
	
	/**
	 * Puzzle resource.
	 */
	public static final String RESOURCE = "puzzles/2018/day23-list.txt";
	
	private List<Nanobot> bots = new ArrayList<Nanobot>();
	
	/**
	 * Constructor for Day 23 puzzles.
	 */
	public Day23() {
		this.dayNum = "23";
		final ResourceReader reader = new ResourceReader(RESOURCE);
		final List<String> input = reader.toList();
		
		for(final String s : input){
			bots.add(new Nanobot(s));
		}
		
		LOGGER.trace("Read in " + bots.size() + " nanobots.");	
	}
	
	/**
	 * Puzzle one solution.
	 */
	public void puzzleOne() {
		/*
		
		
		*/
		long maxRange = Long.MIN_VALUE;
		Nanobot strongBot = null;
		for(final Nanobot bot : this.bots){
			if(bot.getRange() > maxRange){
				strongBot = bot;
				maxRange = bot.getRange();
			}
		}
		
		LOGGER.trace("Longest range is " + strongBot.toString());
		
		int botsInRange = 0;
		for(final Nanobot bot : this.bots){
			if(bot.getDistance(strongBot) <= maxRange){
				botsInRange++;
			}
		}
		
		LOGGER.debug("Day 23, Puzzle 1: there are " + botsInRange +
			" bots in range.");
		this.solutionOne = String.valueOf(botsInRange);
	}
	
	/**
	 * Puzzle two solution.
	 */
	public void puzzleTwo() {
		/*
		
		
		*/
		int[][][] grid;
		long minX = Long.MAX_VALUE;
		long minY = Long.MAX_VALUE;
		long minZ = Long.MAX_VALUE;
		long maxX = Long.MIN_VALUE;
		long maxY = Long.MIN_VALUE;
		long maxZ = Long.MIN_VALUE;
		
		for(final Nanobot bot : bots){
			final long x = bot.getX();
			final long y = bot.getY();
			final long z = bot.getZ();
			
			if(x < minX){
				minX = x;
			}
			
			if(x > maxX){
				maxX = x;
			}
			
			if(y < minY){
				minY = y;
			}
			
			if(y > maxY){
				maxY = y;
			}
			
			if(z < minZ){
				minZ = z;
			}
			
			if(z > maxZ){
				maxZ = z;
			}
		}
		
		LOGGER.trace("X:" + minX + " to " + maxX);
		LOGGER.trace("Y:" + minY + " to " + maxY);
		LOGGER.trace("Z:" + minZ + " to " + maxZ);
		
		final int xRange = (int)(maxX - minX);
		final int yRange = (int)(maxY - minY);
		final int zRange = (int)(maxZ - minZ);
		final long cent = xRange * yRange * zRange / 100L;
		
		LOGGER.trace("There are " + (long)(xRange * yRange * zRange) + " points to calculate.");
		
		grid = new int[xRange][yRange][zRange];
		
		LOGGER.trace("Calculating points...");
		
		long count = 0;
		int percent = 1;
		for(int x=0; x <= maxX; x++){
			for(int y=0; y <= maxY; y++){
				for(int z=0; z <= maxZ; z++){
					grid[x][y][z] = 0;
					
					for(final Nanobot bot : this.bots){	
						if(bot.inRange(x, y, z)){
							grid[x][y][z]++;
						}
					}

					if(count % cent == 0){
						LOGGER.trace("Percent complete:" + percent++ + "%.");
					}
				}
			}
		}

		LOGGER.trace("Point calculations complete.  Finding high values...");
		
		int high = Integer.MIN_VALUE;
		for(int x=0; x < maxX; x++){
			for(int y=0; y < maxY; y++){
				for(int z=0; z < maxZ; z++){
					if(grid[x][y][z] > high){
						high = grid[x][y][z];
					}
				}
			}
		}
		
		LOGGER.trace("Finding closest point with highest value.");
		
		List<Nanobot>highest = new ArrayList<Nanobot>();
		for(int x=0; x < maxX; x++){
			for(int y=0; y < maxY; y++){
				for(int z=0; z < maxZ; z++){
					if(grid[x][y][z] == high){
						highest.add(new Nanobot(x, y, z));
					}
				}
			}
		}
		
		long shortest = Long.MAX_VALUE;
		for(final Nanobot bot : highest){
			if(bot.getRange() < shortest){
				shortest = bot.getRange();
			}
		}
		
		LOGGER.debug("Day 23, Puzzle 2: shortest range is " + shortest);
		this.solutionTwo = String.valueOf(shortest);
	}
}