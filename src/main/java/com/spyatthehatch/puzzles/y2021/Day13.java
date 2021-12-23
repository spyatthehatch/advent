package com.spyatthehatch.puzzles.y2021;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JFrame;

import com.spyatthehatch.objects.y2021.PixelPainter;
import com.spyatthehatch.objects.y2021.Point;
import com.spyatthehatch.puzzles.AbstractDay;
import com.spyatthehatch.util.ResourceReader;

/**
 * Day Thirteen solutions.
 * 
 * @author Bill Everton
 * @version Advent 2021
 */
public class Day13 extends AbstractDay {
	/**
	 * 
	 */
	public Set<Point> points;
	
	/**
	 * 
	 */
	public List<Fold> folds;
	
	/**
	 * 
	 */
	public static final int X_FACTOR = 2;
	
	/**
	 * 
	 */
	public static final int Y_FACTOR = 2;
	
	/**
	 * Puzzle resource.
	 */
	public static final String RESOURCE = "puzzles/2021/day13-list.txt";
	
	/**
	 * Constructor for Day Thirteen puzzles.
	 */
	public Day13() {
		this.dayNum = "13";
		LOGGER.debug("Day " + this.dayNum + ": Transparent Origami");
		this.year = "2021";
	}
	
	/**
	 * Puzzle one solution.
	 */
	public void puzzleOne() {
		init();
		List<Fold>instructions = new ArrayList<Fold>();
		instructions.add(this.folds.get(0));
		
		for(final Fold f : instructions){
			if(f.axis.equals("x")){
				this.points = processLeftFold(f.line);
			} else if(f.axis.equals("y")){
				this.points = processUpFold(f.line);
			} else {
				System.out.println("Unrecognized fold axis.");
			}
		}
		
		this.solutionOne = String.valueOf(this.points.size());
	}
	
	/**
	 * Puzzle two solution.
	 */
	public void puzzleTwo() {
		init();
		int xMax = Integer.MIN_VALUE;
		int yMax = Integer.MIN_VALUE;
		
		for(Point p : this.points){
			if(p.getX() > xMax){
				xMax = p.getX();
			}
			
			if(p.getY() > yMax){
				yMax = p.getY();
			}
		}
		
		System.out.println("Setting dimensions to " + xMax + " x " + yMax + ".");
		
		final PixelPainter pp = new PixelPainter(this.points);
		final JFrame frame = new JFrame("Advent Day 13");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(pp);
		frame.setSize(xMax, yMax);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		pp.repaint();
		pause(1000);
		
		for(final Fold f : this.folds){
			if(f.axis.equals("x")){
				this.points = processLeftFold(f.line);
			} else if(f.axis.equals("y")){
				this.points = processUpFold(f.line);
			} else {
				System.out.println("Unrecognized fold axis.");
			}
			
			pause(700);
			
			pp.setPixels(this.points);
			pp.repaint();
		}
		
		pause(1000);
		
		Set<Point> pixels = new LinkedHashSet<Point>();
		for(Point p : this.points){
			pixels.add(new Point(p.getX() * X_FACTOR, p.getY() * Y_FACTOR));
		}
		
		pp.setPixels(pixels);
		pp.repaint();

		this.solutionTwo = String.valueOf("HGAJBEHC");
	}
	
	/**
	 * 
	 * @param milliseconds
	 */
	public void pause(int milliseconds){
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 */
	public void init(){
		final ResourceReader reader = new ResourceReader(RESOURCE);
		final List<String>rawList = reader.toList();
		this.points = new LinkedHashSet<Point>();
		this.folds = new ArrayList<Fold>();
		
		
		for(int i=0; i<rawList.size(); i++){
			final String s = rawList.get(i);
			
			if(s.isEmpty()){
				System.out.println("Found the empty line!");
				continue;
			}
			
			if(s.contains(",")){
				final String[] coords = s.split(",");
				final Point p = new Point(Integer.valueOf(coords[0]), Integer.valueOf(coords[1]));
				this.points.add(p);
			}
			
			if(s.contains("fold along")){
				final String[] instructions = s.split(" ");
				final String line = instructions[2];
				Fold f = new Fold(line);
				this.folds.add(f);
			}
		}
		
		System.out.println("Points: " + this.points.size());
		System.out.println("Folds: " + this.folds.size());
	}
	
	/**
	 * 
	 * @param line
	 * @return
	 */
	public Set<Point> processLeftFold(final int line){
		Set<Point>dots = new LinkedHashSet<Point>();
		
		// First, copy the points that are left of the fold.
		for(final Point p : this.points){
			if(p.getX() <= line){
				dots.add(p);
			} else {
				int x = p.getX() - (2 * (p.getX() - line));
				Point dot = new Point(x, p.getY());
				
				if(!dots.contains(dot)){
					dots.add(dot);
				}
			}
		}
	
		return dots;
	}
	
	/**
	 * 
	 * @param line
	 * @return
	 */
	public Set<Point> processUpFold(final int line){
		Set<Point>dots = new LinkedHashSet<Point>();
		
		for(final Point p : this.points){
			if(p.getY() <= line){
				dots.add(p);
			} else {
				int y = p.getY() - (2 * (p.getY() - line));
				Point dot = new Point(p.getX(), y);
				
				if(!dots.contains(dot)){
					dots.add(dot);
				}
			}
		}
		
		return dots;
	}
	
	/**
	 * 
	 * @author Billy
	 *
	 */
	public class Fold {
		public String axis;
		public int line;
		
		public Fold(final String i){
			String[] instructions = i.split("=");
			this.axis = instructions[0];
			this.line = Integer.valueOf(instructions[1]);
		}
	}
}