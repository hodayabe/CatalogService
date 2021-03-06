package ajbc.webService.rest.API_Demo.client;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import ajbc.webService.rest.API_Demo.enums.Type;
import ajbc.webService.rest.API_Demo.models.Device;
import ajbc.webService.rest.API_Demo.models.IOTThing;


public class InventoryClient {

	
	public static void main(String[] args) throws IOException, InterruptedException {
		final String SERVER_NAME = "localhost";
		final int SERVER_PORT = 9060, NUM_OF_REPORTS = 3,REPORTING_PERIOD_TIME=10; 
		
		IOTThing thing=initIotThing();

		ScheduledExecutorService executorService = Executors.newScheduledThreadPool(NUM_OF_REPORTS);
		for(int i=0; i<NUM_OF_REPORTS;i++) {
			try {
				thing.simulateInventoryChange();
				executorService.schedule(new InventoryReport(thing,new Socket(SERVER_NAME,SERVER_PORT)),REPORTING_PERIOD_TIME, TimeUnit.SECONDS);
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
			
			
		executorService.shutdown();
		executorService.awaitTermination(30, TimeUnit.SECONDS);
	}
	
	private static IOTThing initIotThing() {
		List<Device> tempDevices=new ArrayList<Device>();
		tempDevices.add(new Device(Type.ACTUATOR,"model-11","aaa",1));
		tempDevices.add(new Device(Type.SENSOR,"model-12","bbb",3));
		tempDevices.add(new Device(Type.SENSOR,"model-12","ccc",7.6));

		IOTThing tempIot=new IOTThing(Type.SENSOR,"model5555","lkjhg",tempDevices);
		return tempIot;
	}
	

}