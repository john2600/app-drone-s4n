package com.s4n.drone.enums;

public enum TypeTurn {
	RIGHT('D'),LEFT('I');
	
	private final char typeOfTurn;

	TypeTurn(char typeOfTurn) {
		this.typeOfTurn=typeOfTurn;
	}
	
	public char getTypeTurn() {
		return this.typeOfTurn;
	}
}
