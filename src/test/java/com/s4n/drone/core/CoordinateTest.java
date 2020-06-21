package com.s4n.drone.core;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
public class CoordinateTest {
	
	@Test
    void shouldValidateGivenCoordinateAndDirection(TestInfo testInfo) {
		Coordinate coordinate = new Coordinate(2,-1, 'N');
        Assertions.assertEquals(2, coordinate.getPositionX());
        Assertions.assertEquals(-1, coordinate.getPositionY());
        Assertions.assertEquals('N', coordinate.getDirection());
    }
}
