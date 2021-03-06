package ajbc.webService.rest.API_Demo.DBservice;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import ajbc.webService.rest.API_Demo.DB.DBMock;
import ajbc.webService.rest.API_Demo.enums.Type;
import ajbc.webService.rest.API_Demo.exception.MissingDataException;
import ajbc.webService.rest.API_Demo.models.Device;
import ajbc.webService.rest.API_Demo.models.IOTThing;

public class DBService {

	private DBMock db;
	private static Map<UUID, IOTThing> things;
	private static Map<UUID, Device> devices;

	public DBService() {
		db = DBMock.getInstance();
		things = db.getIotThings();
		devices = db.getDevices();
	}

	private void updateExistingThing(IOTThing thing) {
		List<Device> oldDevicesList = things.get(thing.getID()).getDevices();
		List<Device> newDevicesList = thing.getDevices();

		oldDevicesList.forEach(device -> {
			if (!newDevicesList.contains(device)) 
				devices.remove(device.getID());
		});

		newDevicesList.forEach(device -> {
			if (!oldDevicesList.contains(device)) 
				devices.put(device.getID(), device);
		});

		things.replace(thing.getID(), thing);
		db.replaceThing(thing);// on DBMock
		System.out.println("thing apdate");
	}
	
	

	public void updateDB(IOTThing thing) {
		if (!things.containsKey(thing.getID())) {
			// saving on DBService and on DBMock
			things.put(thing.getID(), thing);
			db.addIotThing(thing);
			thing.getDevices().forEach(device -> {
				devices.put(device.getID(), device);
				db.addDevice(device);
			});

			System.out.println("add new thing");
			return;
		}

		updateExistingThing(thing);
	}

	public Device getDeviceById(UUID id) throws MissingDataException {
		Device device = devices.get(id);
		if (device == null)
			throw new MissingDataException("id " + id + " is not a valid Device ID");
		return device;
	}

	public List<Device> getAllDevices() {
		return new ArrayList<Device>(devices.values());
	}

	public IOTThing getThingById(UUID id) throws MissingDataException {
		IOTThing thing = things.get(id);
		if (thing == null)
			throw new MissingDataException("id " + id + " is not a valid Thing ID");
		return thing;
	}

	public List<IOTThing> getAllIOTThing() {
		return new ArrayList<IOTThing>(things.values());
	}

	public List<IOTThing> getIotThingByproperties(String type, String manufacturer, String modle) {
		try {
			Type thingType = Type.valueOf(type.toUpperCase());
			List<IOTThing> thingList = things.values().stream().toList();
			for (IOTThing thing : thingList) {
				if (!thing.getType().equals(thingType) || !thing.getModel().equals(modle)
						|| !thing.getManufacturer().equals(manufacturer))
					thingList.remove(thing);
			}
			return thingList;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Device getDeviceBypropertiesAndPerentId(UUID id, String type, String modle, String manufacturer) {
		try {
			IOTThing thing = getThingById(id);
			List<Device> devicesList = thing.getDevices();

			Type deviceType = Type.valueOf(type.toUpperCase());
			for (Device device : devicesList) {
				if (device.getType().equals(deviceType) && device.getModel().equals(modle)
						&& device.getManufacturer().equals(manufacturer))
					return device;
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		return null;
	}

}