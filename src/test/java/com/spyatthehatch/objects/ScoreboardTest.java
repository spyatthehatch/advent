package com.spyatthehatch.objects;

import static org.junit.Assert.assertEquals;

import org.apache.commons.lang3.StringUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Scoreboard unit tests.
 * 
 * @author Bill Everton
 * @version Advent 2018
 */
public class ScoreboardTest {
	/**
	 * Logger.
	 */
	private static final Logger LOGGER = 
	   LoggerFactory.getLogger(ScoreboardTest.class);
	
	/**
	 * Given value for first recipe score.
	 */
	private static final int RECIPE_ONE = 3;
	
	/**
	 * Given value for second recipe score.
	 */
	private static final int RECIPE_TWO = 7;
	
	/**
	 * Executed before any unit test.
	 */
	@BeforeClass
	public static void beforeClass() {
		LOGGER.info("Start ScoreboardTest suite.");
	}
	
	/**
	 * Test combineScore method.
	 */
	@Test
	public void testCombineScoresExpect5158916779(){
		final Scoreboard scoreboard = new Scoreboard(RECIPE_ONE, RECIPE_TWO);
		final int recipeCount = 9;
		int size = Integer.MIN_VALUE;
		while (size < recipeCount + 10){
			size = scoreboard.combineRecipes();
		}
		
		final String scores = scoreboard.getScores(recipeCount, recipeCount + 10);
		assertEquals("FAIL: did not receive expected score.", "5158916779", scores);
		LOGGER.trace("PASS: testCombineScoresExpect5158916779 complete.");
	}
	
	/**
	 * Test combineScore method.
	 */
	@Test
	public void testCombineScoresExpect0124515891(){
		final Scoreboard scoreboard = new Scoreboard(RECIPE_ONE, RECIPE_TWO);
		final int recipeCount = 5;
		int size = Integer.MIN_VALUE;
		while (size < recipeCount + 10){
			size = scoreboard.combineRecipes();
		}
		
		final String scores = scoreboard.getScores(recipeCount, recipeCount + 10);
		assertEquals("FAIL: did not receive expected score.", "0124515891", scores);
		LOGGER.trace("PASS: testCombineScoresExpect0124515891 complete.");
	}
	
	/**
	 * Test combineScore method.
	 */
	@Test
	public void testCombineScoresExpect9251071085(){
		final Scoreboard scoreboard = new Scoreboard(RECIPE_ONE, RECIPE_TWO);
		final int recipeCount = 18;
		int size = Integer.MIN_VALUE;
		while (size < recipeCount + 10){
			size = scoreboard.combineRecipes();
		}
		
		final String scores = scoreboard.getScores(recipeCount, recipeCount + 10);
		assertEquals("FAIL: did not receive expected score.", "9251071085", scores);
		LOGGER.trace("PASS: testCombineScoresExpect9251071085 complete.");
	}
	
	/**
	 * Test combineScore method.
	 */
	@Test
	public void testCombineScoresExpect5941429882(){
		final Scoreboard scoreboard = new Scoreboard(RECIPE_ONE, RECIPE_TWO);
		final int recipeCount = 2018;
		int size = Integer.MIN_VALUE;
		while (size < recipeCount + 10){
			size = scoreboard.combineRecipes();
		}
		
		final String scores = scoreboard.getScores(recipeCount, recipeCount + 10);
		assertEquals("FAIL: did not receive expected score.", "5941429882", scores);
		LOGGER.trace("PASS: testCombineScoresExpect5941429882 complete.");
	}
	
	/**
	 * Test getScores method.
	 */
	@Test
	public void testGetScoresExpect9(){
		final Scoreboard scoreboard = new Scoreboard(RECIPE_ONE, RECIPE_TWO);
		boolean found = false;
		final String target = String.valueOf("51589");
		int size = Integer.MIN_VALUE;
		
		while(!found){
			size = scoreboard.combineRecipes();
			final String scores = scoreboard.getScores(size-10, size);
			if(StringUtils.contains(scores, target)){
				found = true;
			}
		}
		
		assertEquals("FAIL: did not receive expected size.", 9, size -
			target.length());
		LOGGER.trace("PASS: testGetScoresExpect9 complete.");
	}
	
	/**
	 * Test getScores method.
	 */
	@Test
	public void testGetScoresExpect5(){
		final Scoreboard scoreboard = new Scoreboard(RECIPE_ONE, RECIPE_TWO);
		boolean found = false;
		final String target = String.valueOf("01245");
		int size = Integer.MIN_VALUE;
		
		while(!found){
			size = scoreboard.combineRecipes();
			final String scores = scoreboard.getScores(size-10, size);
			if(StringUtils.contains(scores, target)){
				found = true;
			}
		}
		
		assertEquals("FAIL: did not receive expected size.", 5, size -
			target.length());
		LOGGER.trace("PASS: testGetScoresExpect5 complete.");
	}
	
	/**
	 * Test getScores method.
	 */
	@Test
	public void testGetScoresExpect18(){
		final Scoreboard scoreboard = new Scoreboard(RECIPE_ONE, RECIPE_TWO);
		boolean found = false;
		final String target = String.valueOf("92510");
		int size = Integer.MIN_VALUE;
		
		while(!found){
			size = scoreboard.combineRecipes();
			final String scores = scoreboard.getScores(size-10, size);
			if(StringUtils.contains(scores, target)){
				found = true;
			}
		}
		
		assertEquals("FAIL: did not receive expected size.", 18, size -
			target.length());
		LOGGER.trace("PASS: testGetScoresExpect18 complete.");
	}
	
	/**
	 * Test getScores method.
	 */
	@Test
	public void testGetScoresExpect2018(){
		final Scoreboard scoreboard = new Scoreboard(RECIPE_ONE, RECIPE_TWO);
		boolean found = false;
		final String target = String.valueOf("59414");
		int size = Integer.MIN_VALUE;
		
		while(!found){
			size = scoreboard.combineRecipes();
			final String scores = scoreboard.getScores(size-10, size);
			if(StringUtils.contains(scores, target)){
				found = true;
			}
		}
		
		assertEquals("FAIL: did not receive expected size.", 2018, size -
			target.length());
		LOGGER.trace("PASS: testGetScoresExpect2018 complete.");
	}
	
	/**
	 * Test that method gives provided results.
	 */
	@AfterClass
	public static void afterClass() {
		LOGGER.info("ScoreboardTest suite complete.");
	}
}