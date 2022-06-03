package ajbc.webService.rest.API_Demo.models;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ajbc.webService.rest.API_Demo.enums.Type;

public class IOTThingTest extends Hardware {

	private final Type TYPE = Type.ACTUATOR;
	private final String MODEL = "modle4";
	private final String MANUFACTURER = "sol-4";
	private final List<Device> DEVICES = seedDevices();
	
	private IOTThing thing;

	
	public IOTThingTest() {
		thing = new IOTThing(TYPE,MODEL,MANUFACTURER,DEVICES);
	}
	
	private List<Device> seedDevices(){
		List<Device> devices = new ArrayList<Device>();
		devices.add(new Device(Type.ACTUATOR,"modle4","sol-4",5));
		devices.add(new Device(Type.SENSOR,"modle5","sol-5",7));

		return devices;
	}

	@Test
	void testCostractor() {
		assertEquals(MODEL, thing.getModel() );
		assertEquals(MANUFACTURER, thing.getManufacturer());
		assertEquals(TYPE, thing.getType());
		assertEquals(DEVICES, thing.getDevices());	
		
	}
	
	@Test
	void testSimulateInventoryChange() {
		thing.simulateInventoryChange();
		assertNotEquals(thing.getDevices(),DEVICES);
	}
	
	
	
}
