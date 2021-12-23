package com.spyatthehatch.objects.y2018;

/**
 * Guard object.
 * 
 * @author Bill Everton
 * @version Advent 2018
 */
public class Guard {
	/**
	 * ID of this Guard.
	 */
	private int id;
	
	/**
	 * Number of minutes this Guard was asleep.
	 */
	private int minutesAsleep;
	
	/**
	 * The number of days this Guard was asleep at the respective minute.
	 */
	private int[] minutes;
	
	/**
	 * Constructor.
	 * 
	 * @param ID of this Guard.
	 */
	public Guard(final int id) {
		this.id = id;
		this.minutesAsleep = 0;
		this.minutes = new int[60];
		
		for(int i=0; i<60; i++) {
			this.minutes[i] = 0;
		}
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * @return the minutesAsleep
	 */
	public int getMinutesAsleep() {
		return this.minutesAsleep;
	}
	
	/**
	 * Add minute range that this Guard was asleep.
	 * 
	 * @param start Start minute.
	 * @param stop Stop minute.
	 */
	public void addMinutesAsleep(final int start, final int stop) {
		for(int i = start; i < stop; i++) {
			this.minutes[i]++;
		}
		this.minutesAsleep += (stop - start);
	}
	
	/**
	 * @return The minute with the highest day count spent asleep.
	 */
	public int getSleepiestMinute() {
		int minute = 0;
		int value = 0;
		for(int i=0; i<this.minutes.length; i++) {
			if(this.minutes[i] > value) {
				minute = i;
				value = this.minutes[i];
			}
		}
		
		return minute;
	}
	
	/**
	 * @param Minute to query.
	 * @return The number of days that this Guard spent asleep on the respective
	 * minute.
	 */
	public int getMinuteValue(final int i) {
		return this.minutes[i];
	}
}