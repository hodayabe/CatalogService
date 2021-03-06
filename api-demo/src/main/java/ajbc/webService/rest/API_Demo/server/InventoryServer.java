package ajbc.webService.rest.API_Demo.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class InventoryServer extends Thread {
	private final int PORT;
	private ExecutorService executorService;
	
	
	public InventoryServer(int port) {
		this.PORT=port;
		executorService = Executors.newCachedThreadPool();
	}
	
	@Override
	public void run() {

		try (ServerSocket serverSocket = new ServerSocket(PORT);) {
			System.out.println("Inventory Server started on port " + PORT);

			while (true) {
				Socket clientSocket = serverSocket.accept();
				executorService.execute(new ServerSocketThread(clientSocket));
			}
		} catch (IOException e) {
			System.err.println("Failed to start server on port " + PORT);
			e.printStackTrace();
		}
	}
	
	public void kill() {

		try {
			executorService.shutdown();
			executorService.awaitTermination(2, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
