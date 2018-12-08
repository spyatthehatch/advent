package com.spyatthehatch.objects;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Step object.
 * 
 * @author Bill Everton
 * @version Advent 2018
 */
public class Step {
	/**
	 * Name of this step.
	 */
	private String name;
	
	/**
	 * List of other steps that must complete before this step can begin.
	 */
	private List<Step> waitList;
	
	/**
	 * List of all other steps that must complete before this step can begin.
	 */
	private Set<Step> dependencies;
	
	/**
	 * True if this job is complete.  False, otherwise.
	 */
	private boolean isComplete;
	
	/**
	 * Constructor.
	 * 
	 * @param name Name of this Step.
	 */
	public Step(final String name) {
		this.name = name;
		this.waitList = new ArrayList<Step>();
		this.dependencies = new HashSet<Step>();
		this.isComplete = false;
	}
	
	/**
	 * Add a Step to this Step object's wait list.
	 * @param s
	 */
	public void addToWaitList(final Step s) {
		this.waitList.add(s);
	}
	
	/**
	 * @return Name of this Step.
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * @return True if this Step is complete.  False, otherwise.
	 */
	public boolean isComplete() {
		return this.isComplete;
	}
	
	/**
	 * Set this Step as complete.
	 */
	public void setComplete() {
		this.isComplete = true;
	}
	
	/**
	 * @return The list of Steps that must be completed before this Step may
	 * begin.
	 */
	public List<Step> getWaitList() {
		return this.waitList;
	}

	/**
	 * @return The list of all Steps that must be completed before this Step may
	 * begin (includes transitive dependencies).
	 */
	public List<Step> getDependencies() {
		return new ArrayList<Step>(this.dependencies);
	}
	
	/**
	 * Build the dependencies list, recursively.
	 * @return  Transitive dependencies of this Step.
	 */
	public Set<Step> buildDependencies() {

		this.dependencies.addAll(this.waitList);
		
		for(Step s : this.waitList){
			this.dependencies.addAll(s.buildDependencies());
		}
		
		return this.dependencies;
	}
	
	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		sb.append("Step Name:" + this.name + ", WaitList:");
		for(Step s : this.waitList) {
			sb.append(s.getName());
		}
		
		sb.append(", Dep List:");
		
		for(Step s : this.dependencies){
			sb.append(s.getName());
		}
		
		return sb.toString();
	}
}