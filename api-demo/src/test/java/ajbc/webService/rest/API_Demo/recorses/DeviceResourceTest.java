package ajbc.webService.rest.API_Demo.recorses;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import jakarta.ws.rs.core.Response;

class DeviceResourceTest {
	private DeviceResource resource;

	@Test
	void testGetAllDevices() {
		Response acutal = resource.getAllDevices();
		assertEquals(Response.ok(), acutal.getStatus());
	}

	@Test
	void testGetDevicById() {
		fail("Not yet implemented");
	}

}