package com.spyatthehatch.objects.y2018;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;
import javax.swing.JPanel;

/**
 * PixelPainter object.
 * 
 * @author Bill Everton
 * @version 2018 Advent
 */
public class PixelPainter extends JPanel {	
	/**
	 * Serial version.
	 */
	private static final long serialVersionUID = 1692597651156745181L;

	/**
	 * List of pixels.
	 */
	private List<Pixel> pixels;
	
	/**
	 * Constructor.
	 * 
	 * @param list List of pixels to manage.
	 */
	public PixelPainter(final List<Pixel> list){
		this.pixels = list;
	}
	
	/**
	 * Paint the pixels.
	 */
	public void paintComponent(final Graphics g){
		super.paintComponent(g);
		this.setBackground(Color.BLACK);
		
		final Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.red);
		
		final Dimension size = getSize();
		final int midWidth = size.width / 2;
		final int midHeight = size.height / 2;
		
		for(final Pixel p : this.pixels){
			final int x = p.getXPosition() + midWidth;
			final int y = p.getYPosition() + midHeight;
			
			g2d.drawRect(x, y, 1, 1);
		}
	}
}