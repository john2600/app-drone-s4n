package com.s4n.drone.entrypoint;

import java.io.File;
import java.util.concurrent.ExecutionException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.s4n.drone.service.DroneServiceHandler;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class DeliveryExecutorTest {
	private static final String FOLDER = "src/test/resources";
	
	@Mock 
	PrepareDeliveryOrders prepareDeliveryOrders;
	@Mock 
	DroneServiceHandler droneHandler;
	
	@Test
	public void shouldCallDroneService() throws InterruptedException, ExecutionException {
		File file = new File(FOLDER);
		File[] listOfFiles = file.listFiles();
		
		DeliveryExecutor deliveryExecutor = new DeliveryExecutor(prepareDeliveryOrders, droneHandler);
		Mockito.when(prepareDeliveryOrders.getDroneOrders()).thenReturn(listOfFiles);	
		deliveryExecutor.prepareDeliveryDrone();
		deliveryExecutor.callDroneService();
		
		verify(droneHandler, times(1)).handle();
		
	}

}
