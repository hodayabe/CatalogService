package ajbc.webService.rest.API_Demo.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import com.google.gson.Gson;

import ajbc.webService.rest.API_Demo.models.IOTThing;

public class InventoryReport implements Runnable {
	private IOTThing thing;
	private Socket clientSocket;

	public InventoryReport(IOTThing thing, Socket clientSocket) {
		this.thing = thing;
		this.clientSocket = clientSocket;

	}

	@Override
	public void run() {
		try (BufferedReader bufferReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);) {
			
			System.out.println("Connected to server");
			
			Gson gson = new Gson();
			String thingJson = gson.toJson(thing, thing.getClass());

			writer.println(thingJson);
			System.out.println("IOT thing sent to the server");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
