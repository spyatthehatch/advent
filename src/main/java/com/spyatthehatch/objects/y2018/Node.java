package com.spyatthehatch.objects;

import java.util.ArrayList;
import java.util.List;

/**
 * Node object.
 * 
 * @author Bill Everton
 * @version Advent 2018
 */
public class Node {
	/**
	 * Count of nodes.
	 */
	private static int count = 0;
	
	/**
	 * ID.
	 */
	private int id;
	
	/**
	 * Count of child nodes.
	 */
	private int childCount;
	
	/**
	 * Count of metadata entries.
	 */
	private int metadataCount;
	
	/**
	 * List of child nodes.
	 */
	private List<Node> children;
	
	/**
	 * List of metadata entries.
	 */
	private List<Integer> metadata;
	
	/**
	 * Constructor.
	 * 
	 * @param childCount Count of child nodes.
	 * @param metadataCount Count of metadata entries.
	 */
	public Node(final int childCount, final int metadataCount) {
		this.childCount = childCount;
		this.metadataCount = metadataCount;
		this.children = new ArrayList<Node>();
		this.metadata = new ArrayList<Integer>();
		this.id = count++;
	}
	
	/**
	 * Add a child node.
	 * @param n Node to add.
	 */
	public void addChild(Node n) {
		this.children.add(n);
	}
	
	/**
	 * Add a metadata entry.
	 * 
	 * @param i Integer metadata entry.
	 */
	public void addMetadata(int i) {
		this.metadata.add(i);
	}
	
	/**
	 * Get the metadata list.
	 * 
	 * @return Metadata list.
	 */
	public List<Integer> getMetadata() {
		return this.metadata;
	}
	
	/**
	 * Get Node ID.
	 * 
	 * @return ID of this node.
	 */
	public int getId() {
		return this.id;
	}
	
	/**
	 * Get the child node list.
	 * 
	 * @return List of child nodes.
	 */
	public List<Node> getChildren() {
		return this.children;
	}
	
	/**
	 * Calculate the value of this node.  Since this is dependent on child
	 * nodes, this method calls itself recursively to find the node sum.
	 * 
	 * @return Node sum.
	 */
	public int calculateValue(){
		int sum = 0;
		
		if(this.children.isEmpty()){
			for(Integer data : this.metadata){
				sum += data;
			}
			return sum;
		}
		
		for(Integer data : this.metadata){
			int index = data - 1;
			if(index >=0 && index < this.childCount){
				sum += this.children.get(index).calculateValue();
			}
		}
		return sum;
	}
	
	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("Node id: " + this.id + ", ");
		sb.append("cCount: " + this.childCount + ", ");
		sb.append("mCount: " + this.metadataCount + ", ");
		
		sb.append("meta:" );
		for(Integer i : this.metadata) {
			sb.append(" " + i);
		}
		sb.append(", children:");
		
		for(Node n : this.children){
			sb.append(" " + n.getId());
		}
		
		return sb.toString();
	}
}