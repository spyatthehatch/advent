package com.spyatthehatch.objects;

/**
 * Acres object.
 * 
 * @author Bill Everton
 * @version Advent 2018
 */
public class Acres {
	/**
	 * Character denoting an open acre.
	 */
	public static final char OPEN = '.';
	
	/**
	 * Character denoting an acre of trees.
	 */
	public static final char TREES = '|';
	
	/**
	 * Character denoting an acres of lumberyard.
	 */
	public static final char LUMBERYARD = '#';
	
	/**
	 * The width of this acre map.
	 */
	private int width;
	
	/**
	 * The height of this acre map.
	 */
	private int height;
	
	/**
	 * Array of characters mapping the acres.
	 */
	private char[][] acres;
	
	/**
	 * Constructor.
	 * 
	 * @param map Array of characters showing the initial values of acres.
	 */
	public Acres(final char[][] map){
		this.width = map[0].length;
		this.height = map.length;
		this.acres = map;
	}
	
	/**
	 * Update the acres for one minute.
	 */
	public void update(){
		char[][] next = new char[this.width][this.height];
		
		for(int y=0; y<this.height; y++){
			for(int x=0; x<this.width; x++){
				next[x][y] = updateAcre(x, y);
			}
		}
		
		this.acres = next;
	}
	
	/**
	 * Get the resource value of the acres (lumberyards multiplied by trees).
	 * 
	 * @return Resource value.
	 */
	public int getValue(){
		int lumberyards = 0;
		int trees = 0;
		
		for(int y=0; y<this.height; y++){
			for(int x=0; x<this.width; x++){
				if(this.acres[x][y] == TREES){
					trees++;
				} else if(this.acres[x][y] == LUMBERYARD){
					lumberyards++;
				}
			}
		}
		
		return trees * lumberyards;
	}
	
	private char updateAcre(final int x, final int y){
		int trees = 0;
		int lumberyards = 0;
		
		if(x-1 >= 0 && y-1 >= 0){
			if(this.acres[x-1][y-1] == LUMBERYARD){
				lumberyards++;
			} else if (this.acres[x-1][y-1] == TREES){
				trees++;
			}
		}
		
		if(y-1 >= 0){
			if(this.acres[x][y-1] == LUMBERYARD){
				lumberyards++;
			} else if(this.acres[x][y-1] == TREES){
				trees++;
			}
		}
		
		if(x+1 < this.width && y-1 >= 0){
			if(this.acres[x+1][y-1] == LUMBERYARD){
				lumberyards++;
			} else if(this.acres[x+1][y-1] == TREES){
				trees++;
			}
		}
		
		if(x-1 >= 0){
			if(this.acres[x-1][y] == LUMBERYARD){
				lumberyards++;
			} else if(this.acres[x-1][y] == TREES){
				trees++;
			}
		}
		
		if(x+1 < this.width){
			if(this.acres[x+1][y] == LUMBERYARD){
				lumberyards++;
			} else if(this.acres[x+1][y] == TREES){
				trees++;
			}
		}
		
		if(x-1 >= 0 && y+1 < this.height){
			if(this.acres[x-1][y+1] == LUMBERYARD){
				lumberyards++;
			} else if(this.acres[x-1][y+1] == TREES){
				trees++;
			}
		}
		
		if(y+1 < this.height){
			if(this.acres[x][y+1] == LUMBERYARD){
				lumberyards++;
			} else if(this.acres[x][y+1] == TREES){
				trees++;
			}
		}
		
		if(x+1 < this.width && y+1 < this.height){
			if(this.acres[x+1][y+1] == LUMBERYARD){
				lumberyards++;
			} else if(this.acres[x+1][y+1] == TREES){
				trees++;
			}
		}
		
		if(this.acres[x][y] == OPEN){
			if(trees >= 3){
				return TREES;
			} else {
				return OPEN;
			}
		} else if(this.acres[x][y] == TREES){
			if(lumberyards >= 3){
				return LUMBERYARD;
			} else {
				return TREES;
			}
		} else {
			if(lumberyards >= 1 && trees >= 1){
				return LUMBERYARD;
			} else {
				return OPEN;
			}
		}
	}
}