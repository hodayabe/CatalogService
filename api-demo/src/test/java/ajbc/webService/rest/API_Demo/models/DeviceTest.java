package ajbc.webService.rest.API_Demo.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;

import ajbc.webService.rest.API_Demo.enums.Type;
public class DeviceTest {
	
	private Device device;
	private Type type = Type.ACTUATOR;
	private String model = "newModel";
	private String manufacturer = "Yapan";
	private double reading = 3.1;
	
	public DeviceTest() {
		device = new Device(type, model, manufacturer, reading );
	}
	
	@Test
	public void checkConstractor() {
		assertEquals(device.getType(), type);
		assertEquals(device.getModel(), model);
		assertEquals(device.getManufacturer(), manufacturer);
		assertEquals(device.getReading(), reading ,reading);
	}
}