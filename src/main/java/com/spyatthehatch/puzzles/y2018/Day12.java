package com.spyatthehatch.puzzles.y2018;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.spyatthehatch.objects.y2018.PlantRule;
import com.spyatthehatch.puzzles.AbstractDay;
import com.spyatthehatch.util.ResourceReader;

/**
 * Day twelve solutions.
 * 
 * @author Bill Everton
 * @version Advent 2018
 */
public class Day12 extends AbstractDay {
	/*
	--- Day 12: Subterranean Sustainability ---
	The year 518 is significantly more underground than your history books
	implied. Either that, or you've arrived in a vast cavern network under the
	North Pole.
	
	After exploring a little, you discover a long tunnel that contains a row of
	small pots as far as you can see to your left and right. A few of them
	contain plants - someone is trying to grow things in these
	geothermally-heated caves.
	*/
	
	/**
	 * Puzzle resource.
	 */
	public static final String RESOURCE = "puzzles/2018/day12-list.txt";
	
	/**
	 * HashMap of rules, by pattern.
	 */
	private Map<Byte, Boolean> rules = new HashMap<Byte, Boolean>();
	
	/**
	 * Initial state of plants, as provided.
	 */
	private boolean[] state;
	
	/**
	 * Constructor for Day 12 puzzles.
	 */
	public Day12() {
		this.dayNum = "12";
		final ResourceReader reader = new ResourceReader(RESOURCE);
		final List<String> input = reader.toList();

		final String[] split = input.get(0).split(" ");
		
		// Add 40 pots of padding (-10 to +30).
		this.state = new boolean[split[2].length() + 40];
		
		// Pad the first 10 (0-9).
		for(int i=0; i<10; i++){
			this.state[i] = false;
		}
		
		// Insert State 0 for 10-109.
		for(int i=10; (i-10) < split[2].length(); i++){
			if(split[2].charAt(i-10) == '#'){
				this.state[i] = true;
			} else {
				this.state[i] = false;
			}
		}
		
		// Pad the last 30 (110-139).
		for(int i=this.state.length-30; i<this.state.length; i++){
			this.state[i] = false;
		}
		
		for(int i=2; i<input.size(); i++){
			final PlantRule pr = new PlantRule(input.get(i));
			this.rules.put(pr.getPattern(), pr.getResult());
		}
		
		LOGGER.trace("State size: " + this.state.length);
	}
	
	/**
	 * Puzzle one solution.
	 */
	public void puzzleOne() {
		/*
		The pots are numbered, with 0 in front of you. To the left, the pots are
		numbered -1, -2, -3, and so on; to the right, 1, 2, 3.... Your puzzle
		input contains a list of pots from 0 to the right and whether they do (#)
		or do not (.) currently contain a plant, the initial state. (No other
		pots currently contain plants.) For example, an initial state of
		#..##.... indicates that pots 0, 3, and 4 currently contain plants.
		
		Your puzzle input also contains some notes you find on a nearby table:
		someone has been trying to figure out how these plants spread to nearby
		pots. Based on the notes, for each generation of plants, a given pot has
		or does not have a plant based on whether that pot (and the two pots on
		either side of it) had a plant in the last generation. These are written
		as LLCRR => N, where L are pots to the left, C is the current pot being
		considered, R are the pots to the right, and N is whether the current
		pot will have a plant in the next generation. For example:
		
		A note like ..#.. => . means that a pot that contains a plant but with no
		plants within two pots of it will not have a plant in it during the next
		generation.
		A note like ##.## => . means that an empty pot with two plants on each
		side of it will remain empty in the next generation.
		A note like .##.# => # means that a pot has a plant in a given generation
		if, in the previous generation, there were plants in that pot, the one
		immediately to the left, and the one two pots to the right, but not in
		the ones immediately to the right and two to the left.
		It's not clear what these plants are for, but you're sure it's important,
		so you'd like to make sure the current configuration of plants is
		sustainable by determining what will happen after 20 generations.
		
		For example, given the following input:
		
		initial state: #..#.#..##......###...###
		
		...## => #
		..#.. => #
		.#... => #
		.#.#. => #
		.#.## => #
		.##.. => #
		.#### => #
		#.#.# => #
		#.### => #
		##.#. => #
		##.## => #
		###.. => #
		###.# => #
		####. => #
		
		For brevity, in this example, only the combinations which do produce a
		plant are listed. (Your input includes all possible combinations.) Then,
		the next 20 generations will look like this:
		
		                 1         2         3     
		       0         0         0         0     
		 0: ...#..#.#..##......###...###...........
		 1: ...#...#....#.....#..#..#..#...........
		 2: ...##..##...##....#..#..#..##..........
		 3: ..#.#...#..#.#....#..#..#...#..........
		 4: ...#.#..#...#.#...#..#..##..##.........
		 5: ....#...##...#.#..#..#...#...#.........
		 6: ....##.#.#....#...#..##..##..##........
		 7: ...#..###.#...##..#...#...#...#........
		 8: ...#....##.#.#.#..##..##..##..##.......
		 9: ...##..#..#####....#...#...#...#.......
		10: ..#.#..#...#.##....##..##..##..##......
		11: ...#...##...#.#...#.#...#...#...#......
		12: ...##.#.#....#.#...#.#..##..##..##.....
		13: ..#..###.#....#.#...#....#...#...#.....
		14: ..#....##.#....#.#..##...##..##..##....
		15: ..##..#..#.#....#....#..#.#...#...#....
		16: .#.#..#...#.#...##...#...#.#..##..##...
		17: ..#...##...#.#.#.#...##...#....#...#...
		18: ..##.#.#....#####.#.#.#...##...##..##..
		19: .#..###.#..#.#.#######.#.#.#..#.#...#..
		20: .#....##....#####...#######....#.#..##.
		
		The generation is shown along the left, where 0 is the initial state. The
		pot numbers are shown along the top, where 0 labels the center pot,
		negative-numbered pots extend to the left, and positive pots extend
		toward the right. Remember, the initial state begins at pot 0, which is
		not the leftmost pot used in this example.
		
		After one generation, only seven plants remain. The one in pot 0 matched
		the rule looking for ..#.., the one in pot 4 matched the rule looking
		for .#.#., pot 9 matched .##.., and so on.
		
		In this example, after 20 generations, the pots shown as # contain
		plants, the furthest left of which is pot -2, and the furthest right of
		which is pot 34. Adding up all the numbers of plant-containing pots after
		the 20th generation produces 325.
		
		After 20 generations, what is the sum of the numbers of all pots which
		contain a plant?
		*/
		
		List<Boolean> startState = new ArrayList<Boolean>();		
		for(int i=0; i<this.state.length; i++) {
			startState.add(new Boolean(this.state[i]));
		}
		
		// Calculate 20 generations.
		for(int i=0; i<20; i++) {
			final List<Boolean> nextState = new ArrayList<Boolean>();
			nextState.add(new Boolean(false));
			nextState.add(new Boolean(false));
			
			// Evaluate each pot.
			for(int pot=2; pot<startState.size()-2; pot++){				
				// Calculate the pattern
				nextState.add(new Boolean(determineNextState(startState, pot)));
			}
			
			nextState.add(new Boolean(false));
			nextState.add(new Boolean(false));
			
			startState = nextState;
		}
		
		// Add up the points.
		int sum = 0;
		int value = -10;
		for(int i=0; i<startState.size(); i++){
			if(startState.get(i).booleanValue()) {
				sum += value;;
			}
			
			value++;
		}
		
		LOGGER.trace("Size: " + startState.size());
		LOGGER.debug("Day 12, Puzzle 1: the sum is " + sum);
		this.solutionOne = String.valueOf(sum);
	}
	
	/**
	 * Puzzle two solution.
	 */
	public void puzzleTwo() {
		/*
		--- Part Two ---
		You realize that 20 generations aren't enough. After all, these plants
		will need to last another 1500 years to even reach your timeline, not to
		mention your future.
		
		After fifty billion (50000000000) generations, what is the sum of the
		numbers of all pots which contain a plant?
		*/
		
		List<Boolean> startState = new ArrayList<Boolean>();
		long startingIndex = -10;
		long[] sums = new long[10];
		
		for(int i=0; i<this.state.length; i++) {
			startState.add(new Boolean(this.state[i]));
		}
		
		// Loop through the iterations.
		for(long i=0; i<=10000000L; i++){
			final List<Boolean> nextState = new ArrayList<Boolean>();
			
			nextState.add(new Boolean(false));
			nextState.add(new Boolean(false));
			
			for(int pot=2; pot < startState.size()-2; pot++){
				nextState.add(new Boolean(determineNextState(startState, pot)));
			}
			
			/*
			 *  Clean up the list.  Trend seems to be shifting towards positive
			 *  pots over time.
			 *  
			 *  Always only keep 5 empty pots on the positive end.
			 *  
			 *  If the first twenty pots don't have plants, remove the first ten
			 *  from the list.
			 */
			int lastPlant = 0;
			for(int pot=nextState.size()-1; pot > 0; pot--){
				if(nextState.get(pot).booleanValue()){
					lastPlant=pot;
					break;
				}
			}
			
			if(nextState.size()-1 - lastPlant > 5){
				for(int index=lastPlant+1; index < nextState.size()-1; index++){
					nextState.remove(index);
				}
			}
			
			// Add five empty pots to the end.
			for(int pot=0; pot < 5; pot++){
				nextState.add(new Boolean(false));
			}
			
			boolean noPlants = true;
			for(int pot=0; pot<20; pot++){
				if(nextState.get(pot).booleanValue()){
					noPlants = false;
					break;
				}
			}
			
			if(noPlants){
				for (int count=0; count < 10; count++){
					nextState.remove(0);
				}
				startingIndex += 10;
			}
			
			startState = nextState;
			
			if(i%1000000 == 0 && i != 0){
				// Add up the points.
				long sum = 0;
				long value = startingIndex;
				for(int j=0; j<startState.size(); j++){
					if(startState.get(j).booleanValue()) {
						sum += value;
					}
					
					value++;
				}
				
				sums[(int)i/1000000 - 1] = sum;
				
				/*
				 * Originally, ran this with intention of summing the 50B
				 * generations, but after 4 hours it had only completed 5B
				 * iterations.  Clearly, brute force is not the way to win this
				 * one.
				 * 
				 * Output every 1M iteration to identify trend.  The pots were
				 * increasing in sum by exactly 23,000,000 every iteration.
				 * 
				 * 5M iteration sum: 115,000,381
				 * 6M iteration sum: 138,000,381 (23M delta)
				 * 7M iteration sum: 161,000,381 (23M delta)
				 * 8M iteration sum: 184,000,381 (23M delta)
				 * ...
				 * 
				 * Formula to determine final sum:
				 * 49,995 iterations * 23M delta + (sum at 5M) - one iteration.
				 * One iteration = 23M delta / 1M = 23.
				 * 
				 * (49,995 * 23,000,000) + 115,000,381 - 23 = 1,150,000,000,358.
				 */
				LOGGER.trace("Calculating iteration " + i + ", Size:" +
					startState.size() + ", startIndex:" + startingIndex + ", sum:" +
					sum);
			}
		}
		
		long delta = sums[1] - sums[0];
		for(int i=2; i<sums.length-1; i++){
			if(sums[i+1] - sums[i] != delta){
				LOGGER.warn("Trend of " + delta + " delta has not continued.");
			}
		}
		
		final long sum = 49995 * delta + sums[4] - (delta/1000000);
		
		LOGGER.debug("Day 12, Puzzle 2: the sum is " + sum);
		this.solutionTwo = String.valueOf(sum);
	}

	
	/**
	 * Given a list and an index within it, determine if that pot will be a
	 * plant in the next generation.
	 * 
	 * @param state List of current state of pots.
	 * @param pot Index of current pot to be evaluated.
	 * @return True, if the next generation will result in a plant.  False,
	 * otherwise.
	 */
	private boolean determineNextState(final List<Boolean> state, final int pot){
		// Calculate the pattern.
		byte pattern = 0x0;
		if(state.get(pot - 2).booleanValue()){
			pattern += 16;
		}
		
		if(state.get(pot - 1).booleanValue()){
			pattern += 8;
		}
		
		if(state.get(pot).booleanValue()){
			pattern += 4;
		}
		
		if(state.get(pot + 1).booleanValue()){
			pattern += 2;
		}
		
		if(state.get(pot + 2).booleanValue()){
			pattern += 1;
		}
		
		return this.rules.get(pattern);
	}
}