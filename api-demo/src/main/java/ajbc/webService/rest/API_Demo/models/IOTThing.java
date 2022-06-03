package ajbc.webService.rest.API_Demo.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ajbc.webService.rest.API_Demo.enums.Type;

public class IOTThing extends Hardware {
	private final int MAX_DEVICES=10;
	private List<Device> devices;
	

	public IOTThing() {
		super();
	}


	public IOTThing(Type type, String model, String manufacturer,List<Device> devices) {
		super(type, model, manufacturer);
		setDevices(devices);
	}

	public List<Device> getDevices() {
		return devices;
	}

	public void setDevices(List<Device> devices) {
		this.devices = devices;
	}

	public void addDevice(Device device) {
		devices.add(device);
	}
	
	public synchronized void simulateInventoryChange() {
		System.out.println("*******");
		Random rand=new Random();
		if(!devices.isEmpty()) {
			int removesNum=rand.nextInt(devices.size()-1);
			System.out.println("remone "+removesNum );
			for (int i = 0; i <removesNum; i++) {
				int index=rand.nextInt(devices.size()-1);
				devices.remove(index);
			}	
		}
		
		int addsNum=rand.nextInt(MAX_DEVICES-devices.size()-1);
		for (int i = 0; i <addsNum; i++) {
			devices.add(new Device(Type.SENSOR,"model","2.2.2000",1));
			System.out.println("add ");
		}
		
	}

	
	
	@Override
	public String toString() {
		return "IOTThing [ID=" + this.getID() + ", type=" + this.getType() + ", model=" + this.getModel()
		+ ", manufacturer=" + this.getManufacturer() +",devices=" + devices + "]";
	}


}
