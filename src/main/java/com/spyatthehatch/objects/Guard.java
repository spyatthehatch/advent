package com.spyatthehatch.objects;

public class Guard {
	private int id;
	
	private int minutesAsleep;
	
	private int[] minutes;
	
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
		return id;
	}

	/**
	 * @return the minutesAsleep
	 */
	public int getMinutesAsleep() {
		return minutesAsleep;
	}
	
	public void addMinutesAsleep(final int start, final int stop) {
		for(int i = start; i < stop; i++) {
			this.minutes[i]++;
		}
		this.minutesAsleep += (stop - start);
	}
	
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
	
	public int getMinuteValue(final int i) {
		return this.minutes[i];
	}
}