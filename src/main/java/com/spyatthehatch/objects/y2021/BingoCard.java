package com.spyatthehatch.objects.y2021;

import java.util.HashMap;
import java.util.Map;

public class BingoCard {
	private Spot grid[][];
	private Map<Integer, Spot> byPosition;
	private Map<Integer, Spot> byValue;
	private boolean hasWon;
	
	public BingoCard(final int[][] grid){
		this.grid = new Spot[5][5];
		this.byPosition = new HashMap<Integer, Spot>();
		this.byValue = new HashMap<Integer, Spot>();
		this.hasWon = false;
		
		for(int y=0; y<5; y++){
			for(int x=0; x<5; x++){
				int position = y * 5 + x;
				final Spot s = new Spot(grid[x][y]); 
				this.grid[x][y] = s;
				this.byPosition.put(position, s);
				this.byValue.put(grid[x][y], s);
			}
		}
	}
	
	public boolean mark(final int value){
		Spot s = this.byValue.get(value);
		if(s == null){
			return false;
		} else {
			s.mark();
		}
		
		return winCondition();
	}
	
	public boolean winCondition(){
		for(int i=0; i<25; i += 5){
			if(this.byPosition.get(i).isMarked() && this.byPosition.get(i+1).isMarked() &&
				this.byPosition.get(i+2).isMarked() && this.byPosition.get(i+3).isMarked() &&
				this.byPosition.get(i+4).isMarked()){
				this.hasWon = true;
				return true;
			}
		}
		
		for(int i=0; i<5; i++){
			if(this.byPosition.get(i).isMarked() && this.byPosition.get(i+5).isMarked() &&
				this.byPosition.get(i+10).isMarked() && this.byPosition.get(i+15).isMarked() &&
				this.byPosition.get(i+20).isMarked()){
				this.hasWon = true;
				return true;
			}
		}
		
		return false;
	}
	
	public int calculate(){
		int sum = 0;
		for (int y=0; y<5; y++){
			for (int x=0; x<5; x++){
				if(!this.grid[x][y].isMarked()){
					sum += this.grid[x][y].getValue();
				}
			}
		}
		
		return sum;
	}
	
	public boolean hasWon(){
		return this.hasWon;
	}
	
	private class Spot {
		public boolean marked;
		public int value;
		
		public Spot(final int value){
			this.value = value;
			this.marked = false;
		}
		
		public void mark(){
			this.marked = true;
		}
		
		public boolean isMarked(){
			return this.marked;
		}
		
		public int getValue(){
			return this.value;
		}
	}
}
