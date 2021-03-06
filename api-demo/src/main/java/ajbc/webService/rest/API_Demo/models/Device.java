package ajbc.webService.rest.API_Demo.models;

import java.util.Random;

import ajbc.webService.rest.API_Demo.enums.Type;

public class Device extends Hardware {
	private double reading;

	public Device() {
		super();
	}

	public Device(Type type, String model, String manufacturer,double reading) {
		super(type,model, manufacturer);
		this.reading = reading;
	}

	public double getReading() {
		return reading;
	}

	public void setReading(double reading) {
		this.reading = reading;
	}

	public void simulateReading() {
		Random rand = new Random();
		setReading(rand.nextDouble());
	}

	@Override
	public String toString() {
		return "Device [ID=" + this.getID() + ", type=" + this.getType() + ", model=" + this.getModel()
			+", manufacturer=" + this.getManufacturer() +"reading=" + reading + "]";
		
	}

}
