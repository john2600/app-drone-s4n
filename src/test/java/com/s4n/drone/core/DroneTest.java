package com.s4n.drone.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.s4n.drone.enums.Orientation;
import com.s4n.drone.exceptions.DroneException;

@RunWith(JUnitPlatform.class)
public class DroneTest {

	private final static String FILE_NAME = "src/test/resources/mock.txt";
	private final static String FILE_NAME_BAD = "src/test/resources/badmock.txt";

	private final static int DRONE_CAPACITY = 3;
	private final static int GRID_LIMIT = 10;

	@Test
	public void shouldTestDroneLocation() throws Exception {
		// given
		Drone drone = new Drone(FILE_NAME,
				new Coordinate.Builder().withPositioninX(0).withPositioninY(0).withDirection('N').build(),
				DRONE_CAPACITY, GRID_LIMIT);

		// when
		List<Coordinate> result = drone.call();

		// then
		List<Coordinate> expected = new ArrayList<>();
		expected.add(new Coordinate.Builder().withPositioninX(-2).withPositioninY(4)
				.withDirection(Orientation.WEST.getOrientation()).build());

		expected.add(new Coordinate.Builder().withPositioninX(-1).withPositioninY(3)
				.withDirection(Orientation.SOUTH.getOrientation()).build());

		expected.add(new Coordinate.Builder().withPositioninX(-0).withPositioninY(0)
				.withDirection(Orientation.WEST.getOrientation()).build());

		Assertions.assertEquals(expected.get(0).getPositionX(), result.get(0).getPositionX());
		Assertions.assertEquals(expected.get(0).getPositionY(), result.get(0).getPositionY());
		Assertions.assertEquals(expected.get(0).getDirection(), result.get(0).getDirection());

		Assertions.assertEquals(expected.get(1).getPositionX(), result.get(1).getPositionX());
		Assertions.assertEquals(expected.get(1).getPositionY(), result.get(1).getPositionY());
		Assertions.assertEquals(expected.get(1).getDirection(), result.get(1).getDirection());

		Assertions.assertEquals(expected.get(2).getPositionX(), result.get(2).getPositionX());
		Assertions.assertEquals(expected.get(2).getPositionY(), result.get(2).getPositionY());
		Assertions.assertEquals(expected.get(2).getDirection(), result.get(2).getDirection());
	}

	@Test
	public void shouldThrownsAnExceptionDueGridLimit() throws Exception {
		// given
		Drone drone = new Drone(FILE_NAME_BAD,
				new Coordinate.Builder().withPositioninX(0).withPositioninY(0).withDirection('N').build(),
				DRONE_CAPACITY, GRID_LIMIT);

		DroneException exception = assertThrows(DroneException.class, () -> {
			drone.call();
		});
		assertEquals("===Error===limite del cuadrante alcanzado", exception.getMessage());

	}
	
	@Test
	public void shouldTestcapacity() throws Exception {
		// given
		int droneCapacity = 1;
		Drone drone = new Drone(FILE_NAME,
				new Coordinate.Builder().withPositioninX(0).withPositioninY(0).withDirection('N').build(),
				droneCapacity, GRID_LIMIT);

		// when
		List<Coordinate> result = drone.call();
		
		Assertions.assertEquals(droneCapacity, result.size());
	}
}
