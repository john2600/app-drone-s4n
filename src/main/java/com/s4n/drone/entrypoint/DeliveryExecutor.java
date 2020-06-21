package com.s4n.drone.entrypoint;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.s4n.drone.core.Coordinate;
import com.s4n.drone.core.Drone;
import com.s4n.drone.enums.Orientation;
import com.s4n.drone.service.DroneHandler;

public class DeliveryExecutor {
	
	List<Drone> drones = new ArrayList<>();
	private PrepareDeliveryOrders  prepareDeliveryOrders;
	private DroneHandler droneHandler;
	
	public DeliveryExecutor(final PrepareDeliveryOrders  prepareDeliveryOrders, final DroneHandler droneHandler) {
		this.prepareDeliveryOrders = prepareDeliveryOrders;
		this.droneHandler = droneHandler;
	}	
	public void prepareDeliveryDrone(){
		File[] orders = {}; 
		orders = this.prepareDeliveryOrders.setDroneOrders();
		
		for (int i = 0; i < orders.length; i++) {			
			String pathFile = orders[i].getAbsolutePath();
			Coordinate coordinate = new Coordinate.Builder()
					.withPositioninX(0)
					.withPositioninY(0)
					.withDirection(Orientation.NORTH.getOrientation())
					.build();
			
			Drone drone = new Drone(pathFile, coordinate, 3);
			drones.add(drone);
		}
	}
	
	public void callDroneService() {
		droneHandler.setDrones(drones);
		try {
			droneHandler.handle();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}
	

}
