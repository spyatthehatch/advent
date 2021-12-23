package com.spyatthehatch.objects.y2018.water;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spyatthehatch.objects.y2018.Point;

public class Ground {
	/**
	 * Logger.
	 */
	private static final Logger LOGGER = 
	   LoggerFactory.getLogger(Ground.class);
	
	public static final int WELL_X = 500;
	
	private SoilType[][] samples;
	
	private int xOffset = Integer.MAX_VALUE;;
	
	private int yOffset = Integer.MAX_VALUE;
	
	private int width;
	
	private int height;
	
	private int tiles;
	
	public Ground(List<Vein> veins){
		int maxX = Integer.MIN_VALUE;
		int maxY = Integer.MIN_VALUE;
		
		for(final Vein v : veins){
			final int startX = v.getMinX();
			final int endX = v.getMaxX();
			final int startY = v.getMinY();
			final int endY = v.getMaxY();
			
			if(startX < this.xOffset){
				this.xOffset = startX;
			}
			
			if(endX > maxX){
				maxX = endX;
			}
			
			if(startY < this.yOffset){
				this.yOffset = startY;
			}
			
			if(endY > maxY){
				maxY = endY;
			}
		}
		
		this.xOffset--;
		this.width = maxX - this.xOffset + 2;
		this.height = maxY - this.yOffset;
		
		LOGGER.trace("Bounds:" + this.xOffset + "," + this.yOffset + " to " +
			maxX + "," + maxY);
		LOGGER.trace("True width:" + this.width + ", offset:" + this.xOffset);
		LOGGER.trace("True height:" + this.height + ", offset:" + this.yOffset);
		
		this.samples = new SoilType[this.width + 1][this.height + 1];
		this.tiles = 0;
		
		for(int y=0; y <= this.height; y++){
			for(int x=0; x <= this.width; x++){
				this.samples[x][y] = SoilType.SAND;
			}
		}
		
		for(final Vein v : veins){
			final List<Point> points = v.getPoints();
			
			for(final Point p : points){
				this.samples[p.getX() - this.xOffset][p.getY() - this.yOffset] =
					SoilType.CLAY;
			}
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public int seep(){
		boolean done = false;
		int tiles = 0;
		int x = WELL_X - this.xOffset;
		int y = 0;
		
		do {
			switch(this.samples[x][y]){
				case SAND:
					this.samples[x][y] = SoilType.OVERFLOW;
					y++;
					break;
				case CLAY:
					
					break;
				case WATER:
					break;
				case OVERFLOW:
					break;
			}
			
			tiles++;
		} while (!done);
		
		return tiles;
	}
	
	/**
	 * 
	 */
	public void print(){
		for(int y=0; y <= this.height; y++){
			final StringBuilder sb = new StringBuilder();
			sb.append(y + this.yOffset);
			
			for(int x=0; x <= this.width; x++){
				sb.append(this.samples[x][y].getIcon());
			}
			
			LOGGER.trace(sb.toString());
		}
	}
}