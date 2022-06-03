package ajbc.webService.rest.API_Demo.DBservice;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

import ajbc.webService.rest.API_Demo.DB.DBMock;
import ajbc.webService.rest.API_Demo.enums.Type;
import ajbc.webService.rest.API_Demo.models.Device;
import ajbc.webService.rest.API_Demo.models.IOTThing;

public class DBServiceTest {
	private IOTThing thing;
	private Device device;
	private final Type type = Type.ACTUATOR;
	private String strType="ACTUATOR";
	private final String model = "mmodel";
	private final String manufacturer = "fff";
	private final double reading = 2;

	private String stringType="ACTUATOR";
	private DBMock db = DBMock.getInstance();
	private DBService dbService = new DBService();
	private final List<IOTThing> dbThings = db.getIotThings().values().stream().collect(Collectors.toList());
	private final List<Device> dbDevices = db.getDevices().values().stream().collect(Collectors.toList());

	@Test
	void testUpdateDB() {
		thing = new IOTThing(type, model, manufacturer, dbDevices);
		dbService.updateDB(thing);
		assertEquals(thing, db.getIotThings().get(thing.getID()));
	}

	@Test
	void testGetIOTThingByID() {
		IOTThing expectedThing = dbThings.get(0);
		assertEquals(expectedThing, dbService.getThingById(expectedThing.getID()));
	}

	@Test
	void testGetDeviceByID() {
		Device expectedDevice = dbDevices.get(0);
		assertEquals(expectedDevice, dbService.getDeviceById(expectedDevice.getID()));
	}

	@Test
	public void testGetAllIOTThing() {
		assertEquals(dbThings, dbService.getAllIOTThing());

	}

	@Test
	void testGetAllDevices() {
		assertEquals(dbDevices, dbService.getAllDevices());
	}

//	@Test
//	void testGetIOTThingsByProperties() {
//		List<IOTThing> things = new ArrayList<IOTThing>();
//		things.add();
//		dbService.updateDB(thing);
//		List<IOTThing> iotThingsReturn = dbService.getIotThingByproperties(strType, manufacturer, model);
//		assertArrayEquals(iotThingsReturn.toArray(), iotThings.toArray());
//	}
//
//
//	@Test
//	void testGetDevicesByProperties() {
//		//TODO
//		
//	}

}