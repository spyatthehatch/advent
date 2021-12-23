package com.spyatthehatch.puzzles.y2021;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.spyatthehatch.puzzles.AbstractDay;
import com.spyatthehatch.util.ResourceReader;

/**
 * Day Twelve solutions.
 * 
 * @author Bill Everton
 * @version Advent 2021
 */
public class Day12 extends AbstractDay {
	private Map<String, Node> nodes;
	
	/**
	 * Puzzle resource.
	 */
	public static final String RESOURCE = "puzzles/2021/day12-list.txt";
	
	/**
	 * Constructor for Day Twelve puzzles.
	 */
	public Day12() {
		this.dayNum = "12";
		LOGGER.debug("Day " + this.dayNum + ": <Title>");
		this.year = "2021";
		
		final ResourceReader reader = new ResourceReader(RESOURCE);
		final List<String>rawList = reader.toList();
		this.nodes = new HashMap<String, Node>();
		
		for(final String s : rawList){
			String[] names = s.split("-");
			final String start = names[0];
			final String end = names[1];
			
			Node startNode = this.nodes.get(start);
			
			if(startNode == null){
				startNode = new Node(start);
			}
			
			Node endNode = this.nodes.get(end);
			
			if(endNode == null){
				endNode = new Node(end);
			}
			startNode.add(endNode);
			endNode.add(startNode);
			this.nodes.put(start, startNode);
			this.nodes.put(end, endNode);
		}
		
		System.out.println("Nodes has " + this.nodes.size() + " elements.");
		
		Iterator<Entry<String, Node>> it = this.nodes.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry<String, Node> e = (Map.Entry<String, Node>)it.next();
			Node n = (Node)e.getValue();
			System.out.println(n.toString());
		}
	}
	
	/**
	 * Puzzle one solution.
	 */
	public void puzzleOne() {
		int answer = 0;
		
		this.solutionOne = String.valueOf(answer);
	}
	
	/**
	 * Puzzle two solution.
	 */
	public void puzzleTwo() {
		int answer = 0;
		
		this.solutionTwo = String.valueOf(answer);
	}
	
	public class Node {
		public String name;
		public List<Node>nodes;
		
		public Node(final String name){
			this.name = name;
			this.nodes = new ArrayList<Node>();
		}
		
		public void add(final Node n){
			if(!this.nodes.contains(n)){
				this.nodes.add(n);
			}
		}
		
		@Override
		public String toString(){
			final StringBuilder sb = new StringBuilder();
			
			sb.append("\r\n" + "Node:" + this.name + ", child list size:" + this.nodes.size());
			for(Node n : this.nodes){
				sb.append("\r\n" + "     Child:" + n.name);
			}

			return sb.toString();
		}
	}
}