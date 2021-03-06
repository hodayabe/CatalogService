package ajbc.webService.rest.API_Demo.DB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import ajbc.webService.rest.API_Demo.enums.Type;
import ajbc.webService.rest.API_Demo.models.Device;
import ajbc.webService.rest.API_Demo.models.IOTThing;

public class DBMock {
	private static DBMock instance = null;
	private static Map<UUID,IOTThing> iotThings;
	private static Map<UUID,Device> devices;
	
	public static synchronized DBMock getInstance() {
		if(instance==null)
			instance = new DBMock();
		return instance;
	}
	
	private DBMock() {
		iotThings = new HashMap<UUID,IOTThing>();
		devices = new HashMap<UUID,Device>();
		seedThing();
		seedDevices();
	}

	private void seedThing() {
		List<Device> tempDevices=new ArrayList<Device>();
		tempDevices.add(new Device(Type.ACTUATOR,"model-11","aaa",1));
		tempDevices.add(new Device(Type.SENSOR,"model-12","bbb",3));
		tempDevices.add(new Device(Type.SENSOR,"model-12","ccc",7.6));

		
		List<IOTThing>thingsList = Arrays.asList(
			new IOTThing(Type.ACTUATOR,"model-78","SolarAdge",tempDevices));
		
		iotThings = thingsList.stream().collect(Collectors.toMap(IOTThing::getID, Function.identity()));
	}
	
	private void seedDevices() {
		List<Device> tempDevices=Arrays.asList(
		new Device(Type.ACTUATOR,"model-11","aaa",1)
		,new Device(Type.SENSOR,"model-12","bbb",3)
		,new Device(Type.SENSOR,"model-12","ccc",7.6));

		
		devices = tempDevices.stream().collect(Collectors.toMap(Device::getID, Function.identity()));
	}
	
	public Map<UUID, IOTThing> getIotThings() {
		return iotThings;
	}

	public Map<UUID, Device> getDevices() {
		return devices;
	}
	
	
	public synchronized void addIotThing(IOTThing thing) {
		iotThings.put(thing.getID(), thing);
	}
	//to Devices Map
		public synchronized void addDevice(Device device) {
			devices.put(device.getID(), device);
		}

	//From Devices Map
	public synchronized void removeDevice(Device device) {
		devices.remove(device.getID());
	}

	public synchronized void replaceThing(IOTThing thing) {
		iotThings.replace(thing.getID(), thing);
	}
	
}
