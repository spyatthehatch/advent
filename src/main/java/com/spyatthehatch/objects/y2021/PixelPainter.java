package com.spyatthehatch.objects.y2021;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Collection;

import javax.swing.JPanel;

/**
 * PixelPainter object.
 * 
 * @author Bill Everton
 * @version 2021 Advent
 */
public class PixelPainter extends JPanel {	
	/**
	 * Serial version.
	 */
	private static final long serialVersionUID = 1692597651156745181L;

	/**
	 * List of pixels.
	 */
	private Collection<Point> pixels;
	
	/**
	 * Constructor.
	 * 
	 * @param list List of pixels to manage.
	 */
	public PixelPainter(final Collection<Point> pixels){
		this.pixels = pixels;
	}
	
	/**
	 * Paint the pixels.
	 */
	public void paintComponent(final Graphics g){
		super.paintComponent(g);
		this.setBackground(Color.BLACK);
		
		final Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.red);
		
		for(final Point p : this.pixels){
			final int x = (p.getX());
			final int y = (p.getY());
			
			g2d.drawRect(x, y, 1, 1);
		}
	}
	
	/**
	 * 
	 * @param pixels
	 */
	public void setPixels(final Collection<Point> pixels){
		this.pixels = pixels;
	}
}