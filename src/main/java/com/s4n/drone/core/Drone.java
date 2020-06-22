package com.s4n.drone.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.Callable;
import java.util.stream.Stream;

import com.s4n.drone.enums.Orientation;
import com.s4n.drone.exceptions.DroneException;
import com.s4n.drone.filehandling.FileHandler;

public class Drone implements Callable<List<Coordinate>> {
	private int x;
	private int y;
	private char direction;
	private String file;
	private int deliveryCapacity;
	private int gridLimit;

	public Drone(String file, Coordinate coordinate, int deliveryCapacity, int gridLimit) {
		this.file = file;
		this.direction = coordinate.getDirection();
		this.x = coordinate.getPositionX();
		this.y = coordinate.getPositionY();
		this.deliveryCapacity = deliveryCapacity;
		this.gridLimit = gridLimit;
	}
	@Override
	public List<Coordinate> call() throws Exception {
		List<Coordinate> coordinate = new ArrayList<>();
		Stack<Coordinate> storeDroneLocation = new Stack<Coordinate>();

		synchronized (this) {
			Stream<String> stream = FileHandler.getFileLines(this.file);
			stream.limit(deliveryCapacity).forEach(line -> {
				Coordinate result = null;
				for (int i = 0; i < line.length(); i++) {
					char command = line.charAt(i);
					switch (command) {
						case 'D': turnRigth(); break;
						case 'I': turnLeft(); break;
						default: moveForward();
					}
					storeDroneLocation.push(new Coordinate.Builder().withPositioninX(x).withPositioninY(y)
							.withDirection(direction).build());
				}
				result = storeDroneLocation.pop();
				coordinate.add(result);
			});
		}

		return coordinate;
	}

	public void moveForward() {
		if(Math.abs(x) == gridLimit || Math.abs(y)==gridLimit) {
			throw new DroneException("===Error===limite del cuadrante alcanzado");
		}		
		if (direction == Orientation.NORTH.getOrientation())
			y++;
		else if (direction ==  Orientation.EAST.getOrientation())
			x++;
		else if (direction ==  Orientation.SOUTH.getOrientation())
			y--;
		else
			x--;
	}

	public void turnRigth() {
		if (direction ==  Orientation.NORTH.getOrientation()) {
			direction =  Orientation.EAST.getOrientation();
		} else if (direction ==  Orientation.EAST.getOrientation()) {
			direction =  Orientation.SOUTH.getOrientation();
		} else if (direction == Orientation.SOUTH.getOrientation()) {
			direction = Orientation.WEST.getOrientation();
		} else {
			direction =Orientation.NORTH.getOrientation();
		}
	}

	public void turnLeft() {
		if (direction == Orientation.NORTH.getOrientation()) {
			direction = Orientation.WEST.getOrientation();
		} else if (direction == Orientation.EAST.getOrientation()) {
			direction = Orientation.NORTH.getOrientation();
		} else if (direction == Orientation.SOUTH.getOrientation()) {
			direction = Orientation.EAST.getOrientation();
		} else {
			direction = Orientation.SOUTH.getOrientation();
		}
	}


}
