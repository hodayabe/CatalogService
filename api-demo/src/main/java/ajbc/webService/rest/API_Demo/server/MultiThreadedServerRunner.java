package ajbc.webService.rest.API_Demo.server;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class MultiThreadedServerRunner implements ServletContextListener {

	private final int PORT = 9060;
	InventoryServer server;
	
	public void contextInitialized(ServletContextEvent event) {
		 server = new InventoryServer(PORT);
		 server.start();
	}

	
	public void contextDestroyed(ServletContextEvent event) {
		server.kill();
	}

}
