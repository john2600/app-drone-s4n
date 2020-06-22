package com.s4n.drone.entrypoint;

import java.io.File;

import com.s4n.drone.constants.Constants;

public class PrepareDeliveryOrders {
	
	public File[] getDroneOrders() {
		File file = new File(Constants.INPUT_FOLDER);
		File[] listOfFiles = {};
		if (!file.isFile()) {
			listOfFiles = file.listFiles();
		}
		return listOfFiles;
	}
}
