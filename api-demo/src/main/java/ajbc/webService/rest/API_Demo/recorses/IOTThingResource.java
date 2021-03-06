package ajbc.webService.rest.API_Demo.recorses;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import ajbc.webService.rest.API_Demo.DBservice.DBService;
import ajbc.webService.rest.API_Demo.beans.IotThingFilterBean;
import ajbc.webService.rest.API_Demo.models.Device;
import ajbc.webService.rest.API_Demo.models.IOTThing;
import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("things")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class IOTThingResource {

	DBService database = new DBService();

	@GET
	public Response getAllIOTThing() {
		List<IOTThing> tempThingsList = database.getAllIOTThing();
		return Response.status(Status.CREATED).entity(tempThingsList).build();
	}

//	@GET
//	@Path("/{id}")
//	public Response getThingById(@PathParam("id") UUID id) {
//		IOTThing tempThing = database.getThingById(id);
//		return Response.ok(tempThing).build();
//	}

	@GET
	@Path("/filter")
	public Response getIotThingByproperties(@BeanParam IotThingFilterBean filterBean) {
		List<IOTThing> iotThings = database.getIotThingByproperties(filterBean.getType(), filterBean.getModle(),
				filterBean.getManufacturer());
		System.out.println(iotThings);
		return Response.ok(iotThings).build();
	}

	@GET
	@Path("/{id}")
	public Response getDeviceBypropertiesAndPerentId(@PathParam("id") UUID id,
			@BeanParam IotThingFilterBean filterBean) {

		if (filterBean.getType()==null && filterBean.getModle()==null
				&& filterBean.getManufacturer()==null) {
			IOTThing tempThing = database.getThingById(id);
			return Response.ok(tempThing).build();
		}
		Device tempDevice = database.getDeviceBypropertiesAndPerentId(id, filterBean.getType(), filterBean.getModle(),
				filterBean.getManufacturer());

		return Response.ok(tempDevice).build();
	}

}
