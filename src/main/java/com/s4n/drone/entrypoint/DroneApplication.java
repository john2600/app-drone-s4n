package com.s4n.drone.entrypoint;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.s4n.drone.service.DroneServiceHandler;

public class DroneApplication {
	
	public void starApp() {
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		DroneServiceHandler droneHandler = new DroneServiceHandler(executorService);
		PrepareDeliveryOrders prepareDeliveryOrderes = new PrepareDeliveryOrders();
		
		DeliveryExecutor deliveryExecutor = new DeliveryExecutor(prepareDeliveryOrderes, droneHandler);
		
		deliveryExecutor.prepareDeliveryDrone();
		deliveryExecutor.callDroneService();
		
		
	}
	public static void main(String[] args) {
		new DroneApplication().starApp();
	}

}
