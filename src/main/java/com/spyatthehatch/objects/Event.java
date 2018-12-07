package com.spyatthehatch.objects;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Event implements Comparable<Event>{
	/**
	 * Logger.
	 */
	private static final Logger LOGGER = 
	   LoggerFactory.getLogger(Event.class);
	   
	private static final String DATE_FORMAT = "[yyyy-MM-dd HH:mm";
	
	public enum Action {START, AWAKE, ASLEEP};
	
	private int guardId;
	
	private Action action;
	
	private Date dtg;
	
	public Event (final String s) {
		final String[] event = s.split("]");
		
		try {
			this.dtg = new SimpleDateFormat(DATE_FORMAT).parse(event[0]);
		} catch (final ParseException e) {
			LOGGER.error(e.getMessage());
		}
		
		final String[] message = event[1].split(" ");
		
		if(message[1].equals("Guard")) {
			this.action = Action.START;
			this.guardId = Integer.valueOf(StringUtils.substringAfter(message[2], "#"));
		}
		
		else if(message[1].equals("falls")) {
			this.action = Action.ASLEEP;
		}
		
		else {
			this.action = Action.AWAKE;
		}
	}
	
	public void setGuardId(final int id) {
		this.guardId = id;
	}
	
	/**
	 * @return the guardId
	 */
	public int getGuardId() {
		return guardId;
	}

	/**
	 * @return the action
	 */
	public Action getAction() {
		return action;
	}

	/**
	 * @return the dtg
	 */
	public Date getDtg() {
		return dtg;
	}

	public int compareTo(final Event e) {
		return this.dtg.compareTo(e.getDtg());
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}