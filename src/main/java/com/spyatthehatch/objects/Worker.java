package com.spyatthehatch.objects;

/**
 * Worker object.
 * 
 * @author Bill Everton
 * @version Advent 2018
 *
 */
public class Worker {
	/**
	 * Job this worker is working.
	 */
	private Step job;
	
	/**
	 * Time remaining on the current job.
	 */
	private int timeRemaining;

	/**
	 * Constructor.
	 */
	public Worker() {
		this.job = null;
		this.timeRemaining = 0;
	}
	
	/**
	 * @param job the job to set
	 */
	public void setJob(Step job) {
		this.job = job;
	}

	/**
	 * @param timeRemaining the timeRemaining to set
	 */
	public void setTimeRemaining(final int timeRemaining) {
		this.timeRemaining = timeRemaining;
	}

	/**
	 * @return the job
	 */
	public Step getJob() {
		return this.job;
	}

	/**
	 * Decrement the time.  Empty the worker job queue, if the job is completed.
	 * 
	 * @return True if the worker is ready to work.  False, otherwise.
	 */
	public boolean decrementTime() {
		this.timeRemaining--;
		if(this.timeRemaining <= 0) {
			this.job = null;
			return true;
		}
		return false;
	}
	
	/**
	 * @return the timeRemaining
	 */
	public int getTimeRemaining() {
		return this.timeRemaining;
	}
	
	/**
	 * @return True if the worker is ready to work.  False, otherwise.
	 */
	public boolean isReady() {
		return (this.job == null);
	}
}