package com.spyatthehatch.objects;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spyatthehatch.objects.y2018.MarbleGame;

/**
 * Marble unit tests.
 * 
 * @author Bill Everton
 * @version Advent 2018
 *
 */
public class MarbleGameTest {
	/**
	 * Logger.
	 */
	private static final Logger LOGGER = 
	   LoggerFactory.getLogger(MarbleGameTest.class);
	
	/**
	 * Executed before any unit test.
	 */
	@BeforeClass
	public static void beforeClass() {
		LOGGER.info("Start MarbleTest suite.");
	}
	
	/**
	 * Test that method gives provided results.
	 */
	@Test
	public void testCalculateHighScoreExpect8317() {
		final MarbleGame mg = new MarbleGame(10, 1618);
		assertEquals("FAIL: did not receive expected score.", 8317,
			mg.calculateHighScore());
		LOGGER.trace("PASS: testCalculateHighScoreExpect8317 complete.");
	}
	
	/**
	 * Test that method gives provided results.
	 */
	@Test
	public void testCalculateHighScoreExpect146373() {
		final MarbleGame mg = new MarbleGame(13, 7999);
		assertEquals("FAIL: did not receive expected score.", 146373,
			mg.calculateHighScore());
		LOGGER.trace("PASS: testCalculateHighScoreExpect146373 complete.");
	}
	
	/**
	 * Test that method gives provided results.
	 */
	@Test
	public void testCalculateHighScoreExpect2764() {
		final MarbleGame mg = new MarbleGame(17, 1104);
		assertEquals("FAIL: did not receive expected score.", 2764,
			mg.calculateHighScore());
		LOGGER.trace("PASS: testCalculateHighScoreExpect2764 complete.");
	}
	
	/**
	 * Test that method gives provided results.
	 */
	@Test
	public void testCalculateHighScoreExpect54718() {
		final MarbleGame mg = new MarbleGame(21, 6111);
		assertEquals("FAIL: did not receive expected score.", 54718,
			mg.calculateHighScore());
		LOGGER.trace("PASS: testCalculateHighScoreExpect54718 complete.");
	}
	
	/**
	 * Test that method gives provided results.
	 */
	@Test
	public void testCalculateHighScoreExpect37305() {
		final MarbleGame mg = new MarbleGame(30, 5807);
		assertEquals("FAIL: did not receive expected score.", 37305,
			mg.calculateHighScore());
		LOGGER.trace("PASS: testCalculateHighScoreExpect37305 complete.");
	}
	
	/**
	 * Test that method gives provided results.
	 */
	@AfterClass
	public static void afterClass() {
		LOGGER.info("MarbleTest suite complete.");
	}
}