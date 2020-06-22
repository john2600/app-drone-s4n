package com.s4n.drone.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.s4n.drone.core.Coordinate;
import com.s4n.drone.core.Drone;

@RunWith(JUnitPlatform.class)
public class DroneHandlerTest {
	
	private final static String FILE_NAME = "src/test/resources/in01.txt";
	private final static String FILE_OUTPUT = "src/main/resources/outbound/out01.txt";
	private final static String EXPECTED_CONTENT = "=== Reporte de Entregas===(1,4) Direccion ORIENTE(3,6) Direccion OCCIDENTE(-6,6) Direccion NORTE";
		
	@Test
	public void shouldTestDroneHandler() throws InterruptedException, ExecutionException, IOException {
		ExecutorService executorService = Executors.newFixedThreadPool(1);
		DroneServiceHandler droneHandler = new DroneServiceHandler(executorService);
		
		Drone drone = new Drone(FILE_NAME,
				new Coordinate.Builder().withPositioninX(0).withPositioninY(0).withDirection('N').build(),
				3, 10);
		
		List<Drone> droneList = new ArrayList<>();
		
		droneList.add(drone);
		droneHandler.setDrones(droneList);
		
		//when
		droneHandler.handle();
		
		//Then
		Stream<String> result = Files.lines(Paths.get(FILE_OUTPUT));
		StringBuilder report = new StringBuilder();
		result.forEach(x->{
			report.append(x);
		} );
		
		Assertions.assertEquals(EXPECTED_CONTENT, report.toString());
		result.close();
	}
	
	@AfterAll
    public static void clean(){
		
		Path filePath = Paths.get(FILE_OUTPUT);
		try {
			Files.delete(filePath);
		} catch (IOException e) {
			
		}
    }

}
