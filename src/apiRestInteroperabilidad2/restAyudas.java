package apiRestInteroperabilidad2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

public class restAyudas extends ServerResource{
	
	@Get("json")
	public Representation getAyudas() throws IOException {
		
		
		JSONObject data = readjson();
		Representation rep = new JsonRepresentation(data);
		return rep;
	}
	
	public JSONObject readjson() throws IOException {
		
		InputStream is = this.getClass().getResourceAsStream("becas.json");
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String content = "";
		String line = "";
		
		while((line=br.readLine())!= null) {
			content = content + line;
			
		}
		
		br.close();
		JSONObject data = new JSONObject(content);
		return data;
	}

}
