package com.s4n.drone.enums;

public enum Orientation {
	NORTH('N'),EAST('E'), SOUTH('S'),WEST('O'); 
    private final char orientation;

    Orientation(char orientation) {
        this.orientation = orientation;
    }
    
    public char getOrientation() {
        return this.orientation;
    }

}
