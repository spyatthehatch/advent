package com.spyatthehatch.puzzles.y2021;

import java.util.ArrayList;
import java.util.List;

import com.spyatthehatch.puzzles.AbstractDay;
import com.spyatthehatch.util.ResourceReader;

/**
 * Day Two solutions.
 * 
 * @author Bill Everton
 * @version Advent 2021
 */
public class Day02 extends AbstractDay {
	/*
	--- Day 2: Dive! ---
	
	Now, you need to figure out how to pilot this thing.

	It seems like the submarine can take a series of commands like forward 1,
	down 2, or up 3:

		forward X increases the horizontal position by X units.
		down X increases the depth by X units.
		up X decreases the depth by X units.
		
	Note that since you're on a submarine, down and up affect your depth, and
	so they have the opposite result of what you might expect.

	The submarine seems to already have a planned course (your puzzle input).
	You should probably figure out where it's going.
	*/
	
	/**
	 * List of Commands.
	 */
	private List<Command>commands;
	
	/**
	 * Puzzle resource.
	 */
	public static final String RESOURCE = "puzzles/2021/day02-list.txt";
	
	/**
	 * Constructor for Day Two puzzles.
	 */
	public Day02() {
		LOGGER.debug("Day 2: Dive!");
		this.year = "2021";
		this.dayNum = "2";
		final ResourceReader reader = new ResourceReader(RESOURCE);
		final List<String> rawList = reader.toList();
		this.commands = new ArrayList<Command>();
		
		for(final String s : rawList){
			Command c = new Command(s);
			this.commands.add(c);
		}
	}
	
	/**
	 * Puzzle one solution.
	 */
	public void puzzleOne() {
		/*
		Your horizontal position and depth both start at 0.
		
		After following these instructions, you would have a horizontal position
		of 15 and a depth of 10. (Multiplying these together produces 150.)

		Calculate the horizontal position and depth you would have after
		following the planned course. What do you get if you multiply your final
		horizontal position by your final depth?
		*/
		
		int x = 0;
		int y = 0;
		
		/*
		 * Go through each of the commands, keeping track of the X and Y
		 * coordinates.
		 */
		for(final Command c : this.commands){
			switch (c.direction){
				case "forward":
					x += c.distance;
					break;
				case "down":
					y -= c.distance;
					break;
				case "up":
					y += c.distance;
					break;
			}
		}
		
		int answer = (-1) * x * y;
		this.solutionOne = String.valueOf(answer);
	}
	
	/**
	 * Puzzle two solution.
	 */
	public void puzzleTwo() {
		/*
		--- Part Two ---
		
		Based on your calculations, the planned course doesn't seem to make any
		sense. You find the submarine manual and discover that the process is
		actually slightly more complicated.

		In addition to horizontal position and depth, you'll also need to track a
		third value, aim, which also starts at 0. The commands also mean
		something entirely different than you first thought:

			down X increases your aim by X units.
			up X decreases your aim by X units.
			forward X does two things:
				It increases your horizontal position by X units.
				It increases your depth by your aim multiplied by X.
				
		Again note that since you're on a submarine, down and up do the opposite
		of what you might expect: "down" means aiming in the positive direction.

		Using this new interpretation of the commands, calculate the horizontal
		position and depth you would have after following the planned course.
		What do you get if you multiply your final horizontal position by your
		final depth?
		 */
		
		int x = 0;
		int y = 0;
		int aim = 0;
		
		/*
		 * Go through each of the commands, with the slight adjustment that up
		 * and down affect aim and forward affects position.
		 */
		for(Command c : this.commands){
			switch (c.direction){
				case "forward":
					x += c.distance;
					y += aim * c.distance;
					break;
				case "down":
					aim -= c.distance;
					break;
				case "up":
					aim += c.distance;
					break;
			}
		}
		
		int answer = (-1) * x * y;
		this.solutionTwo = String.valueOf(answer);
	}

	/**
	 * Inner class for a Command object.
	 */
	public class Command {
		/**
		 * Direction portion of the command.
		 */
		public String direction;
		
		/**
		 * Distance portion of the command.
		 */
		public int distance;
		
		/**
		 * Constructor.
		 * 
		 * @param s String of the command, in the form of direction (legal
		 * directions are UP, DOWN, or FOWARD) and an integer for distance.
		 * Examples: UP 5, DOWN 15, or FORWARD 18.
		 */
		public Command(final String s){
			final String[] tuple = s.split(" ");
			this.direction = tuple[0];
			this.distance = Integer.valueOf(tuple[1]);
		}
	}
}
