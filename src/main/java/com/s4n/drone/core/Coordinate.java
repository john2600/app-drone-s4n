package com.s4n.drone.core;

public class Coordinate {
	private int positionX;
	private int positionY;
	private char direction;

	public Coordinate(int x, int y, char d) {
		this.positionX = x;
		this.positionY = y;
		this.direction = d;
	}

	public int getPositionX() {
		return positionX;
	}

	public int getPositionY() {
		return positionY;
	}

	public char getDirection() {
		return direction;
	}
	
	@Override
	public String toString() {
		return "Coordinate [positionX=" + positionX + ", positionY=" + positionY + ", direction=" + direction + "]";
	}

	public static class Builder{
		private int x;
		private int y;
		private char d;
		
		public Builder withPositioninX(int x) {
			this.x=x;
			return this;
			
		}
		public Builder withPositioninY(int y) {
			this.y=y;
			return this;
		}
		public Builder withDirection(char d) {
			this.d=d;
			return this;
		}
		
		public Coordinate build() {
			Coordinate coordinate = new Coordinate(this.x, this.y, this.d);
			return coordinate;
		}	
	}
}
