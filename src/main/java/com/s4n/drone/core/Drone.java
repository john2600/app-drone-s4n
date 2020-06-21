package com.s4n.drone.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.Callable;
import java.util.stream.Stream;

import com.s4n.drone.filehandling.FileHandler;

public class Drone implements Callable<List<Coordinate>> {
	private int x;
	private int y;
	private char direction;
	private String file;
	private int deliveryCapacity;

	public Drone(String file, Coordinate coordinate, int deliveryCapacity) {
		this.file = file;
		this.direction = coordinate.getDirection();
		this.x = coordinate.getPositionX();
		this.y = coordinate.getPositionY();
		this.deliveryCapacity = deliveryCapacity;
	}

	public void moveForward() {
		if (direction == 'N')
			y++;
		else if (direction == 'E')
			x++;
		else if (direction == 'S')
			y--;
		else
			x--;
	}

	public void turnRigth() {
		if (direction == 'N') {
			direction = 'E';
		} else if (direction == 'E') {
			direction = 'S';
		} else if (direction == 'S') {
			direction = 'O';
		} else {
			direction = 'N';
		}
	}

	public void turnLeft() {
		if (direction == 'N') {
			direction = 'O';
		} else if (direction == 'E') {
			direction = 'N';
		} else if (direction == 'S') {
			direction = 'E';
		} else {
			direction = 'S';
		}
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
					default: moveForward();}

					storeDroneLocation.push(new Coordinate.Builder().withPositioninX(x).withPositioninY(y)
							.withDirection(direction).build());
				}
				result = storeDroneLocation.pop();
				coordinate.add(result);
			});
		}

		return coordinate;
	}

}
