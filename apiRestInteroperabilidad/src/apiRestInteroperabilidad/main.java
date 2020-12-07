package apiRestInteroperabilidad;

import org.restlet.Component;
import org.restlet.data.Protocol;

public class main {
	
	public static void main(String[] args) throws Exception {
		Component c = new Component();
		c.getServers().add(Protocol.HTTP, 8081);
		c.getDefaultHost().attach(new apiRestBecas());
		c.start();
		
	}
}