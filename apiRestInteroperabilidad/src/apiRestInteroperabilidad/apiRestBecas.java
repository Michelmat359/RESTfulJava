package apiRestInteroperabilidad;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

public class apiRestBecas extends Application{
	
	@Override
	public Restlet createInboundRoot() {
		Router router = new Router();
		router.attach("/becas/{ciudadano_dni}", restAyudaCiudadano.class);
		return router;
	}

}
