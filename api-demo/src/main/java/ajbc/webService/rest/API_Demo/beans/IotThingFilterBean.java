package ajbc.webService.rest.API_Demo.beans;

import jakarta.ws.rs.QueryParam;

public class IotThingFilterBean {
	@QueryParam("type") String type;
	@QueryParam("modle") String modle;
	@QueryParam("manufacturer") String manufacturer;
	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getModle() {
		return modle;
	}
	public void setModle(String modle) {
		this.modle = modle;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	
	
	
	
	
	
	
}
