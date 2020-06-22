package com.s4n.drone.service;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import com.s4n.drone.constants.Constants;
import com.s4n.drone.core.Coordinate;
import com.s4n.drone.core.Drone;
import com.s4n.drone.enums.Orientation;
import com.s4n.drone.filehandling.FileHandler;

public class DroneServiceHandler {
	static Map<Character,String> mapOrientation = new HashMap<>();
	
	private ExecutorService executorService;
	private List<Drone> drones;
	List<Future<List<Coordinate>>> result = new ArrayList<>();
	
	static  {
		mapOrientation.put(Orientation.NORTH.getOrientation(), Constants.NORTH);
		mapOrientation.put(Orientation.SOUTH.getOrientation(), Constants.SOUTH);
		mapOrientation.put(Orientation.EAST.getOrientation(), Constants.EAST);
		mapOrientation.put(Orientation.WEST.getOrientation(), Constants.WEST);
	}

	public DroneServiceHandler(ExecutorService executorService) {
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
			int index = i + 1;
			String sequence = (index < 10) ? "0":"";
			
			Path newFilePath = FileHandler.createFile(Constants.OUTPUT_FOLDER + Constants.FILE_OUTPUT + sequence + index +".txt");
			StringBuffer header = new StringBuffer("=== Reporte de Entregas===").append("\r\n\r\n");
			
			FileHandler.fileWriter(newFilePath, header.toString());
			temp.stream().forEach(x -> {
				StringBuffer sb = new StringBuffer("(").append(x.getPositionX()).append(",").append(x.getPositionY())
						.append(")").append(" Direccion ").append(mapOrientation.get(x.getDirection())).append("\r\n\r\n");
				FileHandler.fileWriter(newFilePath, sb.toString());
			});
		}
		executorService.shutdown();
	}
}
