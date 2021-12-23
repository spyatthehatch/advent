package com.spyatthehatch.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Resource reader utility.
 * 
 * @author Bill Everton
 * @version Advent 2018
 */
public class ResourceReader {
	/**
	 * Logger.
	 */
	private static final Logger LOGGER = 
	   LoggerFactory.getLogger(ResourceReader.class);
			
	/**
	 * Class BufferedReader.
	 */
	private BufferedReader br = null;
	
	/**
	 * Constructor.
	 * 
	 * @param resource Resource file to read.
	 */
	public ResourceReader(final String resource) {
		LOGGER.trace("Reading file: " + resource + ".");
		final InputStream is = getClass().getClassLoader()
			.getResourceAsStream(resource);
		br = new BufferedReader(new InputStreamReader(is));
	}
	
	/**
	 * Get the file contents as an ArrayList of String objects.
	 * 
	 * @return List of String objects.
	 */
	public List<String> toList() {
		final List<String> list = new ArrayList<String>();
		
		String line = null;
		try {
			while((line = this.br.readLine()) != null) {
				list.add(line);
			}
		} catch (final IOException e) {
			LOGGER.warn("I/O error on read from resource.");
			throw new RuntimeException(e.getMessage());
		}
		
		LOGGER.trace("Read in " + list.size() + " elements.");
		return list;
	}
	
	/**
	 * Return a line from a file.
	 */
	public String toString() {
		String s = null;
		
		try {
			s = this.br.readLine();
		} catch (final IOException e) {
			LOGGER.warn("I/O error on read from resource.");
			throw new RuntimeException(e.getMessage());
		}
		
		return s;
	}
}