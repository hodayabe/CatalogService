package ajbc.webService.rest.API_Demo.recorses;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import ajbc.webService.rest.API_Demo.DBservice.DBService;
import ajbc.webService.rest.API_Demo.models.Device;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("devices")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DeviceResource {

	DBService database = new DBService();

	@GET
	public Response getAllDevices() {
		List<Device> tempDeviceList = database.getAllDevices();
		return Response.status(Status.CREATED).entity(tempDeviceList).build();
	}

	@GET
	@Path("/{id}")
	public Response getDeviceById(@PathParam("id") UUID id) {
		Device tempDevice = database.getDeviceById(id);
		return Response.ok(tempDevice).build();
	}

	

}
