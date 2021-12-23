package com.spyatthehatch.objects.y2021;

public class Position implements Comparable<Position> {
	private Point p;
	private int risk;
	
	public Position (final int x, final int y, final int risk) {
		this.p = new Point(x, y);
		this.risk = risk;
	}
	
	public Position(final Point p, final int risk){
		this.p = p;
		this.risk = risk;
	}
	
	/**
	 * @return the x
	 */
	public int getX() {
		return this.p.getX();
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return this.p.getY();
	}

	/**
	 * @return the risk
	 */
	public int getRisk() {
		return risk;
	}

	@Override
	public String toString(){
		return "(" + this.p.getX() + "," + this.p.getY() + ") Risk:" + this.risk;
	}
	
	@Override
	public int compareTo (final Position p){
		return (this.risk < p.risk) ? -1 : ((this.risk == p.risk) ? 0 : 1);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((p == null) ? 0 : p.hashCode());
		result = prime * result + risk;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Position other = (Position) obj;
		if (p == null) {
			if (other.p != null)
				return false;
		} else if (!p.equals(other.p))
			return false;
		if (risk != other.risk)
			return false;
		return true;
	}
}