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

}
