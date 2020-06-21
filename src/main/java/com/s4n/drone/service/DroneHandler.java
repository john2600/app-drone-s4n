package com.s4n.drone.service;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import com.s4n.drone.constants.Constants;
import com.s4n.drone.core.Coordinate;
import com.s4n.drone.core.Drone;
import com.s4n.drone.filehandling.FileHandler;

public class DroneHandler {
	private ExecutorService executorService;
	private List<Drone> drones;
	List<Future<List<Coordinate>>> result = new ArrayList<>();

	public DroneHandler(ExecutorService executorService) {
		this.executorService = executorService;
	}

	public void setDrones(List<Drone> drones) {
		this.drones = drones;
	}

	public void handle() throws InterruptedException, ExecutionException {
		
		for (int i = 0; i < this.drones.size(); i++) {
			result.add(executorService.submit(this.drones.get(i)));
		}

		for (int i = 0; i < result.size(); i++) {
			List<Coordinate> temp = result.get(i).get();
			Path newFilePath=  FileHandler.createFile(Constants.OUTPUT_FOLDER+ Constants.FILE_OUTPUT + i + ".txt");
			temp.stream().forEach(x -> {
				String fileName = x.getPositionX() + " - " + x.getPositionY() + " - " + x.getDirection() + "\n";
				FileHandler.fileWriter(newFilePath, fileName);
			});

			System.out.println("===");
		}
		executorService.shutdown();
	}
}
