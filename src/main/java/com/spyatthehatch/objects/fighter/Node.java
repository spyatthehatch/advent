package com.spyatthehatch.objects.fighter;

import java.util.ArrayList;
import java.util.List;

public class Node {
	private Square location;
	
	private List<Node> neighbors;
	
	public Node(final Square loc){
		this.location = loc;
		this.neighbors = new ArrayList<Node>();
	}
	
	public void addNeighbor(final Node n){
		this.neighbors.add(n);
	}
	
	public List<Node> getNeighbors() {
		return this.neighbors;
	}
}