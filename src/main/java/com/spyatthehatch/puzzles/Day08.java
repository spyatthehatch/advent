package com.spyatthehatch.puzzles;

import java.util.ArrayList;
import java.util.List;

import com.spyatthehatch.objects.Node;
import com.spyatthehatch.util.ResourceReader;

/**
 * Day eight solutions.
 * 
 * @author Bill Everton
 * @version Advent 2018
 */
public class Day08 extends AbstractDay {
	/*
	--- Day 8: Memory Maneuver ---
	The sleigh is much easier to pull than you'd expect for something its
	weight. Unfortunately, neither you nor the Elves know which way the North
	Pole is from here.
	
	You check your wrist device for anything that might help. It seems to have
	some kind of navigation system! Activating the navigation system produces
	more bad news: "Failed to start navigation system. Could not read software
	license file."
	
	The navigation system's license file consists of a list of numbers (your
	puzzle input). The numbers define a data structure which, when processed,
	produces some kind of tree that can be used to calculate the license number.
	
	The tree is made up of nodes; a single, outermost node forms the tree's
	root, and it contains all other nodes in the tree (or contains nodes that
	contain nodes, and so on).
	*/
	
	/**
	 * Puzzle resource, list of fabric claims.
	 */
	public static final String RESOURCE = "puzzles/day8-list.txt";
	
	/**
	 * Input of numbers.
	 */
	private int[] input;
	
	/**
	 * Root node.
	 */
	private Node root;
	
	/**
	 * Running index for processing the input numbers.
	 */
	private int index;
	
	/**
	 * List of nodes.
	 */
	private List<Node> nodeList = new ArrayList<Node>();
	
	/**
	 * Constructor for Day 8 puzzles.
	 */
	public Day08() {
		this.dayNum = "8";
		final ResourceReader reader = new ResourceReader(RESOURCE);
		final List<String> input = reader.toList();
		final String line = input.get(0);
		String[] rawInput = line.split(" ");
		LOGGER.trace("Read in " + rawInput.length + " numbers.");
		this.input =  new int[rawInput.length];
		
		for(int i=0; i<rawInput.length; i++) {
			this.input[i] = Integer.valueOf(rawInput[i]);
		}
	}
	
	/**
	 * Puzzle one solution.
	 */
	public void puzzleOne() {
		/*
		Specifically, a node consists of:

		A header, which is always exactly two numbers:
		The quantity of child nodes.
		The quantity of metadata entries.
		Zero or more child nodes (as specified in the header).
		One or more metadata entries (as specified in the header).
		Each child node is itself a node that has its own header, child nodes,
		and metadata. For example:
		
		2 3 0 3 10 11 12 1 1 0 1 99 2 1 1 2
		A----------------------------------
		    B----------- C-----------
		                     D-----
		                     
		In this example, each node of the tree is also marked with an underline
		starting with a letter for easier identification. In it, there are four
		nodes:
		
		A, which has 2 child nodes (B, C) and 3 metadata entries (1, 1, 2).
		B, which has 0 child nodes and 3 metadata entries (10, 11, 12).
		C, which has 1 child node (D) and 1 metadata entry (2).
		D, which has 0 child nodes and 1 metadata entry (99).
		
		The first check done on the license file is to simply add up all of the
		metadata entries. In this example, that sum is 1+1+2+10+11+12+2+99=138.
		
		What is the sum of all metadata entries?
		*/
		
		this.index = 0;
		final int tail = this.input.length - 1;
		final int childCount = this.input[this.index++];
		final int metadataCount = this.input[this.index++];
		this.root = new Node(childCount, metadataCount);
		this.nodeList.add(root);
		
		for(int i=0; i<metadataCount; i++){
			root.addMetadata(this.input[tail - i]);
		}
		
		root.getChildren().addAll(processNodes(childCount));
		
		LOGGER.trace("Root " + root.toString());
		
		int sum = 0;
		for(final Node n : this.nodeList){
			for(Integer data : n.getMetadata()) {
				sum += data;
			}
		}
		
		LOGGER.debug("Day 8, Puzzle 1: metadata total is " + sum + ".");
		this.solutionOne = String.valueOf(sum);
	}

	/**
	 * Puzzle two solution.
	 */
	public void puzzleTwo() {
		/*
		--- Part Two ---
		The second check is slightly more complicated: you need to find the value
		of the root node (A in the example above).
		
		The value of a node depends on whether it has child nodes.
		
		If a node has no child nodes, its value is the sum of its metadata
		entries. So, the value of node B is 10+11+12=33, and the value of node D
		is 99.
		
		However, if a node does have child nodes, the metadata entries become
		indexes which refer to those child nodes. A metadata entry of 1 refers to
		the first child node, 2 to the second, 3 to the third, and so on. The
		value of this node is the sum of the values of the child nodes referenced
		by the metadata entries. If a referenced child node does not exist, that
		reference is skipped. A child node can be referenced multiple time and
		counts each time it is referenced. A metadata entry of 0 does not refer
		to any child node.
		
		For example, again using the above nodes:
		
		Node C has one metadata entry, 2. Because node C has only one child node,
		2 references a child node which does not exist, and so the value of node
		C is 0.
		Node A has three metadata entries: 1, 1, and 2. The 1 references node A's
		first child node, B, and the 2 references node A's second child node, C.
		Because node B has a value of 33 and node C has a value of 0, the value
		of node A is 33+33+0=66.
		So, in this example, the value of the root node is 66.
		
		What is the value of the root node?
		*/
		
		final int sum = root.calculateValue();
		LOGGER.debug("Day 8, Puzzle 2: root value is " + sum + ".");
		this.solutionTwo = String.valueOf(sum);
	}
	
	/**
	 * Process the nodes.
	 * 
	 * @param count Count of node siblings in this block.
	 * @return List of sibling nodes for the given block.
	 */
	public List<Node> processNodes(int count){
		final List<Node> siblings = new ArrayList<Node>();
		
		for(int i=0; i<count; i++){
			final int childCount = this.input[this.index++];
			final int metadataCount = this.input[this.index++];
			final Node n = new Node(childCount, metadataCount);
			this.nodeList.add(n);
			siblings.add(n);

			n.getChildren().addAll(processNodes(childCount));
			
			for(int data=0; data<metadataCount; data++){
				n.addMetadata(this.input[index++]);
			}
			
			LOGGER.trace("Node " + n.toString());
		}
		
		return siblings;
	}
}