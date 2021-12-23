package com.spyatthehatch.puzzles.y2018;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.spyatthehatch.Constants;
import com.spyatthehatch.objects.y2018.Step;
import com.spyatthehatch.objects.y2018.Worker;
import com.spyatthehatch.puzzles.AbstractDay;
import com.spyatthehatch.util.ListUtils;
import com.spyatthehatch.util.ResourceReader;

/**
 * Day seven solutions.
 * 
 * @author Bill Everton
 * @version Advent 2018
 */
public class Day07 extends AbstractDay {
	/*
	--- Day 7: The Sum of Its Parts ---
	You find yourself standing on a snow-covered coastline; apparently, you
	landed a little off course. The region is too hilly to see the North Pole
	from here, but you do spot some Elves that seem to be trying to unpack
	something that washed ashore. It's quite cold out, so you decide to risk
	creating a paradox by asking them for directions.
	
	"Oh, are you the search party?" Somehow, you can understand whatever Elves
	from the year 1018 speak; you assume it's Ancient Nordic Elvish. Could the
	device on your wrist also be a translator? "Those clothes don't look very
	warm; take this." They hand you a heavy coat.
	
	"We do need to find our way back to the North Pole, but we have higher
	priorities at the moment. You see, believe it or not, this box contains
	something that will solve all of Santa's transportation problems - at least,
	that's what it looks like from the pictures in the instructions." It doesn't
	seem like they can read whatever language it's in, but you can: "Sleigh kit.
	Some assembly required."
	
	"'Sleigh'? What a wonderful name! You must help us assemble this 'sleigh' at
	once!" They start excitedly pulling more parts out of the box.
	*/
	
	/**
	 * Puzzle resource, list of steps.
	 */
	public static final String RESOURCE = "puzzles/2018/day7-list.txt";
	
	/**
	 * HashMap of steps, by letter.
	 */
	private Map<String, Step> stepMap = new HashMap<String, Step>();
	
	/**
	 * List of the final order of steps.
	 */
	private List<Step>order = new ArrayList<Step>();
	
	/**
	 * Constructor for Day 7 puzzles.
	 */
	public Day07() {
		this.dayNum = "7";
		final ResourceReader reader = new ResourceReader(RESOURCE);
		final List<String> input = reader.toList();
		
		for(String s : input) {
			final String[] split = s.split(" ");
			final String name = split[7];
			final String wait = split[1];
			
			Step step = this.stepMap.get(name);
			if(step == null) {
				step = new Step(name);
				this.stepMap.put(name, step);
			}
			
			Step waitStep = this.stepMap.get(wait);
			if(waitStep == null) {
				waitStep = new Step(wait);
				this.stepMap.put(wait, waitStep);
			}
			
			step.addToWaitList(waitStep);
		}
		
		LOGGER.trace("Map size is " + this.stepMap.size());
	}
	
	/**
	 * Puzzle one solution.
	 */
	public void puzzleOne() {
		/*
		The instructions specify a series of steps and requirements about which
		steps must be finished before others can begin (your puzzle input). Each
		step is designated by a single letter. For example, suppose you have the
		following instructions:

		Step C must be finished before step A can begin.
		Step C must be finished before step F can begin.
		Step A must be finished before step B can begin.
		Step A must be finished before step D can begin.
		Step B must be finished before step E can begin.
		Step D must be finished before step E can begin.
		Step F must be finished before step E can begin.
		Visually, these requirements look like this:
		
		
		  -->A--->B--
		 /    \      \
		C      -->D----->E
		 \           /
		  ---->F-----
		Your first goal is to determine the order in which the steps should be
		completed. If more than one step is ready, choose the step which is first
		alphabetically. In this example, the steps would be completed as follows:
		
		Only C is available, and so it is done first.
		Next, both A and F are available. A is first alphabetically, so it is
		done next.
		Then, even though F was available earlier, steps B and D are now also
		available, and B is the first alphabetically of the three.
		After that, only D and F are available. E is not available because only
		some of its prerequisites are complete. Therefore, D is completed next.
		F is the only choice, so it is done next.
		Finally, E is completed.
		So, in this example, the correct order is CABDFE.
		
		In what order should the steps in your instructions be completed?
		*/
		
		final List<String> letters = new ArrayList<String>();
		final List<String> alphabet = new ArrayList<String>();
		
		for(int i=0; i<Constants.LETTERS.length; i++) {
			letters.add(String.valueOf(Constants.LETTERS[i]).toUpperCase());
			alphabet.add(String.valueOf(Constants.LETTERS[i]).toUpperCase());
		}

		this.order = new ArrayList<Step>();
		
		while(!letters.isEmpty()) {
			for(final String letter : alphabet) {
				final List<Step> waitSteps = this.stepMap.get(letter)
					.getWaitList();
						
				if((waitSteps.isEmpty() || this.order.containsAll(waitSteps)) && 
					!this.order.contains(this.stepMap.get(letter))){
					
					this.order.add(this.stepMap.get(letter));
					letters.remove(letter);
					break;
				}
			}
		}
		
		final StringBuilder sb = new StringBuilder();
		for(Step s : this.order) {
			sb.append(s.getName());
			LOGGER.trace(s.toString());
		}
		
		this.solutionOne = sb.toString();
		LOGGER.debug("Day 7, Puzzle 1: Order is  " + this.solutionOne);
	}
	
	/**
	 * Puzzle two solution.
	 */
	public void puzzleTwo() {
		/*
		--- Part Two ---
		As you're about to begin construction, four of the Elves offer to help.
		"The sun will set soon; it'll go faster if we work together." Now, you
		need to account for multiple people working on steps simultaneously. If
		multiple steps are available, workers should still begin them in
		alphabetical order.
		
		Each step takes 60 seconds plus an amount corresponding to its letter:
		A=1, B=2, C=3, and so on. So, step A takes 60+1=61 seconds, while step Z
		takes 60+26=86 seconds. No time is required between steps.
		
		To simplify things for the example, however, suppose you only have help
		from one Elf (a total of two workers) and that each step takes 60 fewer
		seconds (so that step A takes 1 second and step Z takes 26 seconds).
		Then, using the same instructions as above, this is how each second would
		be spent:
		
		Second   Worker 1   Worker 2   Done
		   0        C          .        
		   1        C          .        
		   2        C          .        
		   3        A          F       C
		   4        B          F       CA
		   5        B          F       CA
		   6        D          F       CAB
		   7        D          F       CAB
		   8        D          F       CAB
		   9        D          .       CABF
		  10        E          .       CABFD
		  11        E          .       CABFD
		  12        E          .       CABFD
		  13        E          .       CABFD
		  14        E          .       CABFD
		  15        .          .       CABFDE
		  
		Each row represents one second of time. The Second column identifies how
		many seconds have passed as of the beginning of that second. Each worker
		column shows the step that worker is currently doing (or . if they are
		idle). The Done column shows completed steps.
		
		Note that the order of the steps has changed; this is because steps now
		take time to finish and multiple workers can begin multiple steps
		simultaneously.
		
		In this example, it would take 15 seconds for two workers to complete
		these steps.
		
		With 5 workers and the 60+ second step durations described above, how
		long will it take to complete all of the steps?
		*/
		
		final int OFFSET = 61;
		final int WORKER_COUNT = 5;
		
		final Map<String, Integer> values = new HashMap<String, Integer>();
		final List<Worker> workers = new ArrayList<Worker>();
		
		for(Step s : this.order){
			s.buildDependencies();
		}
		
		for (Step s : this.order){
			LOGGER.trace(s.toString());
		}
		
		for(int i=0; i<Constants.LETTERS.length; i++) {
			values.put(String.valueOf(Constants.LETTERS[i]).toUpperCase(),
				OFFSET + i);
		}
		
		for(int i=0; i<WORKER_COUNT; i++) {
			workers.add(new Worker());
		}
		
		final int orderSize = this.order.size();
		boolean done = false;
		int next = 0;
		int time = 0;
		
		
		while(!done) {
			final List<Step> inProgress = new ArrayList<Step>();

			// Get a list of what's in progress right now.
			for(final Worker worker : workers) {
				inProgress.add(worker.getJob());
			}
			
			for (final Worker worker : workers) {
				if(next < orderSize){
					Step step = this.order.get(next);
					
					// Check that this step hasn't been completed already.
					while(step.isComplete() && (next + 1) < orderSize){
						LOGGER.trace("Step " + step.getName() +
							" is already complete!");
						step = this.order.get(++next);
					}
					
					// If this worker is ready to do work...
					if(worker.isReady()){
						/*
						 * If this step isn't waiting on any steps that are in
						 * progress, assign it!
						 */
						if(!ListUtils.containsAny(inProgress, step.getWaitList()) &&
							!ListUtils.containsAny(inProgress, step)){
							
							LOGGER.trace("Assigning Step " + step.getName() +"!");
							worker.setJob(step);
							worker.setTimeRemaining(values.get(step.getName()));
							inProgress.add(step);
							next++;
						} else {
							/*
							 * This step is waiting on steps that are in progress,
							 * look ahead.	
							 */
							int ahead = next + 1;
							if(ahead < orderSize){
								Step aheadStep = this.order.get(ahead);
								
								/*
								 * While the ahead step is complete or must wait on
								 * dependencies.
								 */
								while(aheadStep.isComplete() || 
										(ListUtils.containsAny(inProgress, aheadStep)) ||
										(ListUtils.containsAny(inProgress,
											aheadStep.getDependencies())) &&
											(ahead + 1) < orderSize){
									aheadStep = this.order.get(++ahead);
								}
								
								/*
								 * The aheadStep is either...
								 * 1. Not complete and not waiting on dependencies
								 * 2. Or, the last step.
								 */
								if(!aheadStep.isComplete() && !ListUtils
										.containsAny(inProgress, aheadStep
										.getDependencies())){
									
									LOGGER.trace("Identified Step " + aheadStep
										.getName() + 
										" as a step that can be assigned now!");
									
									worker.setJob(aheadStep);
									worker.setTimeRemaining(values.get(aheadStep
										.getName()));
									inProgress.add(aheadStep);
								}
							}
						}
					}
				}
			}
						
			// Decrement time.
			for(Worker worker : workers) {
				Step s = worker.getJob();
				if(worker.decrementTime() && s != null){
					LOGGER.trace("Step " + s.getName() + " is complete!");
					s.setComplete();
				}
			}
			
			time++;
			
			// Check if we're done.
			for(Step s : this.order){
				if(!s.isComplete()){
					done = false;
					break;
				}
				done = true;
			}
			
			if(done){
				LOGGER.trace("We're done!");
			}
		}
		
		LOGGER.debug("Day 7, Puzzle 2: time is " + time);
		this.solutionTwo = String.valueOf(time);
	}
}