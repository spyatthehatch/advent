package com.spyatthehatch.objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spyatthehatch.objects.y2018.Point;
import com.spyatthehatch.objects.y2019.Segment;

public class SegmentTest {
	/**
	 * Logger.
	 */
	private static final Logger LOGGER = 
	   LoggerFactory.getLogger(SegmentTest.class);
	
	private Segment a = new Segment(new Point(1, 3), new Point(1, 5));
	private Segment b = new Segment(new Point(3, 1), new Point(5, 1));
	private Segment c = new Segment(new Point(4, 2), new Point(4, 6));
	private Segment d = new Segment(new Point(2, 4), new Point(6, 4));
	private Segment e = new Segment(new Point(7, 5), new Point(7, 3));
	private Segment f = new Segment(new Point(16, 21), new Point(14, 21));
	private Segment g = new Segment(new Point(15, 22), new Point(15, 20));
	
	/**
	 * Executed before any unit test.
	 */
	@BeforeClass
	public static void beforeClass() {
		LOGGER.info("Start SegmentTest suite.");
	}
	
	@Test
	public void testOrientationExpectVertical(){
		assertEquals("FAIL: received incorrect orientation.", a.getOrientation(), Segment.Orientation.VERTICAL);
		LOGGER.trace("PASS: testOrientationExpectVertical complete.");
	}
	
	@Test
	public void testOrientationExpectHorizontal(){
		assertEquals("Fail: received incorrect orientation.", d.getOrientation(), Segment.Orientation.HORIZONTAL);
		LOGGER.trace("PASS: testOrientationExpectHorizontal complete.");
	}
	
	@Test
	public void testStartExpectLow(){
		assertEquals("Fail: received unexpected starting Y value.", e.getStart().getY(), 3);
		LOGGER.trace("PASS: testStartExpectLow complete.");
	}
	
	@Test
	public void testIntersectionExpectNull(){
		assertNull("Fail: did not receive null value.", a.intersection(b));
		LOGGER.trace("PASS: testIntersectionExpectNull complete.");
	}
	
	@Test
	public void testIntersectionExpectPoint(){
		assertEquals("Fail: did not receive expected point.", new Point(4,4), c.intersection(d));
		LOGGER.trace("PASS: testIntersectionExpectPoint complete.");
	}
	
	@Test
	public void testIntersectionBackwardExpectPoint() {
		assertEquals("Fail: did not receive expected point.", new Point(15, 21), f.intersection(g));
		LOGGER.trace("PASS: testIntersectionBackwardExpectPoint complete.");
	}
	
	@Test
	public void testIntersectionVerticalExpectPoint() {
		assertEquals("Fail: did not receive expected point.", new Point(15, 21), g.intersection(f));
		LOGGER.trace("PASS: testIntersectionVerticalExpectPoint complete.");
	}
	
	/**
	 * Test that method gives provided results.
	 */
	@AfterClass
	public static void afterClass() {
		LOGGER.info("SegmentTest suite complete.");
	}
}
