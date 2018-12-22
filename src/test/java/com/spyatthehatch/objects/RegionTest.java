package com.spyatthehatch.objects;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spyatthehatch.objects.Region.Type;

/**
 * Suite of unit tests for Region object.
 * 
 * @author Bill Everton
 * @version Advent 2018
 */
public class RegionTest{
	/**
	 * Logger.
	 */
	private static final Logger LOGGER = 
	   LoggerFactory.getLogger(RegionTest.class);
	
	/**
	 * Depth of cavern in tests.
	 */
	private static final int DEPTH = 510;
	
	/**
	 * Executed before any unit test.
	 */
	@BeforeClass
	public static void beforeClass() {
		LOGGER.info("Start RegionTest suite.");
	}
	
	/**
	 * Region test.
	 */
	@Test
	public void testOriginExpectRocky(){
		final Region region = new Region(0, 0, DEPTH, 0);
		assertTrue("FAIL: did not receive expected type.",
			region.getType() == Type.ROCKY);
		LOGGER.trace("PASS: testOriginExpectRocky complete.");
	}
	
	/**
	 * Region test.
	 */
	@Test
	public void test10ExpectWet(){
		final Region region = new Region(1, 0, DEPTH, Region.X_COEFFICIENT);
		assertTrue("FAIL: did not receive expected type.",
			region.getType() == Type.WET);
		LOGGER.trace("PASS: test10ExpectWet complete.");
	}
	
	/**
	 * Region test.
	 */
	@Test
	public void test10ErosionLevelExpect17317(){
		final Region region = new Region(1, 0, DEPTH, Region.X_COEFFICIENT);
		assertEquals("FAIL: did not receive expected erosion level.",
			region.getErosionLevel(), 17317);
		LOGGER.trace("PASS: test10ErosionLevelExpect17317 complete.");
	}
	
	/**
	 * Region test.
	 */
	@Test
	public void test01ExpectRocky(){
		final Region region = new Region(0, 1, DEPTH, Region.Y_COEFFICIENT);
		assertTrue("FAIL: did not receive expected type.",
			region.getType() == Type.ROCKY);
		LOGGER.trace("PASS: test01ExpectRocky complete.");
	}
	
	/**
	 * Region test.
	 */
	@Test
	public void test01ErosionLevelExpect8415(){
		final Region region = new Region(0, 1, DEPTH, Region.Y_COEFFICIENT);
		assertEquals("FAIL: did not receive expected erosion level.",
			8415, region.getErosionLevel());
		LOGGER.trace("PASS: test01ErosionLevelExpect8415 complete.");
	}
	
	/**
	 * Region test.
	 */
	@Test
	public void test11ExpectNarrow(){
		final Region region = new Region(0, 1, DEPTH, 8415 * 17317);
		assertTrue("FAIL: did not receive expected type.",
			region.getType() == Type.NARROW);
		LOGGER.trace("PASS: test11ExpectNarrow complete.");
	}
	
	/**
	 * Region test.
	 */
	@Test
	public void test11ErosionLevelExpect1805(){
		final Region region = new Region(1, 1, DEPTH, 8415 * 17317);
		assertEquals("FAIL: did not receive expected erosion level.",
			region.getErosionLevel(), 1805);
		LOGGER.trace("PASS: test11ErosionLevelExpect1805 complete.");
	}
	
	/**
	 * Region test.
	 */
	@Test
	public void testTargetExpectRocky(){
		final Region region = new Region(10, 10, DEPTH, 0);
		assertTrue("FAIL: did not receive expected type.",
				region.getType() == Type.ROCKY);
		LOGGER.trace("PASS: testTargetExpectRocky complete.");
	}
	
	/**
	 * Region test.
	 */
	@Test
	public void testTargetErosionLevelExpect510(){
		final Region region = new Region(1, 1, DEPTH, 0);
		assertEquals("FAIL: did not receive expected erosion level.",
			region.getErosionLevel(), 510);
		LOGGER.trace("PASS: testTargetErosionLevelExpect510 complete.");
	}
	
	/**
	 * Test that method gives provided results.
	 */
	@AfterClass
	public static void afterClass() {
		LOGGER.info("RegionTest suite complete.");
	}
}