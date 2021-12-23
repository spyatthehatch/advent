package com.spyatthehatch.objects.y2018;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Event object.
 * 
 * @author Bill Everton
 * @version Advent 2018
 */
public class Event implements Comparable<Event>{
	/**
	 * Logger.
	 */
	private static final Logger LOGGER = 
	   LoggerFactory.getLogger(Event.class);
	   
	/**
	 * Enum of actions possible for an event.
	 */
	public enum Action {START, AWAKE, ASLEEP};
	
	/**
	 * Date format.
	 */
	private static final String DATE_FORMAT = "[yyyy-MM-dd HH:mm";
	
	/**
	 * Guard ID for this event.
	 */
	private int guardId;
	
	/**
	 * Action taken for this event.
	 */
	private Action action;
	
	/**
	 * Event date time group.
	 */
	private Date dtg;
	
	/**
	 * Constructor.
	 * 
	 * @param s Raw string input for this event.
	 */
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
			this.guardId = Integer.valueOf(StringUtils.substringAfter(message[2],
				"#"));
		}
		
		else if(message[1].equals("falls")) {
			this.action = Action.ASLEEP;
		}
		
		else {
			this.action = Action.AWAKE;
		}
	}
	
	/**
	 * Set the Guard ID.
	 * @param id Guard ID.
	 */
	public void setGuardId(final int id) {
		this.guardId = id;
	}
	
	/**
	 * @return the guardId
	 */
	public int getGuardId() {
		return this.guardId;
	}

	/**
	 * @return the action
	 */
	public Action getAction() {
		return this.action;
	}

	/**
	 * @return the dtg
	 */
	public Date getDtg() {
		return this.dtg;
	}

	/**
	 * Used for sorting, by date.
	 */
	public int compareTo(final Event e) {
		return this.dtg.compareTo(e.getDtg());
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}