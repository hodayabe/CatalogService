package ajbc.webService.rest.API_Demo.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import com.google.gson.Gson;

import ajbc.webService.rest.API_Demo.DBservice.DBService;
import ajbc.webService.rest.API_Demo.models.IOTThing;

public class ServerSocketThread implements Runnable {

	private Socket clientSocket;
	private boolean stopped;

	public ServerSocketThread(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}

	@Override
	public void run() {

		try (BufferedReader bufferReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);) {

			System.out
					.println("Thing is connected " + clientSocket.getInetAddress() + " port " + clientSocket.getPort());

			// reading data
			Gson gson = new Gson();
			String line = bufferReader.readLine();
			IOTThing thing = gson.fromJson(line, IOTThing.class);

			DBService dbService = new DBService();
			if(thing==null)
				System.out.println("nulll***************");
			else {
				System.out.println("not null**** updating");
			dbService.updateDB(thing);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void kill() {
		this.stopped = true;
	}

}
