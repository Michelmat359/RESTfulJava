package apiRestInteroperabilidad;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

public class restAyudaCiudadano extends ServerResource{

	@Get("json")
	public Representation consultarAyudaCiudadano() throws IOException {
		String ciudadano_dni = this.getAttribute("ciudadano_dni");
		JSONObject ayuda_ciudadano = leerAyudaCiudadano(ciudadano_dni);
		Representation rep = new JsonRepresentation(ayuda_ciudadano);
		return rep;
	}
	
	public JSONObject leerAyudaCiudadano(String ciudadano_dni) throws IOException {
		JSONObject datos = readjson();
		JSONArray arrayAcuerdos = datos.getJSONArray("acuerdos");
		JSONObject datosCiudadano = null;
		Integer dni = 0;
		Boolean concedida = false;
		Boolean encontrado = false;
		int i = 0;
		while(i<arrayAcuerdos.length() && !encontrado) {
			JSONObject objeto = arrayAcuerdos.getJSONObject(i);
			dni = objeto.getInt("dni");
			if(dni.toString().equals(ciudadano_dni)) {
				encontrado = true;
				concedida = objeto.getBoolean("concedida");
				datosCiudadano = construirJson(dni, concedida);
					
			}
			i++;
			
		}
		if(i ==arrayAcuerdos.length()) datosCiudadano = construirJson (Integer.parseInt(ciudadano_dni),concedida);
		
		return datosCiudadano;
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
	
	public JSONObject construirJson(Integer dni, boolean resultado) {
		String cadenadni = dni.toString();
		String cadenaresultado = "false";
		if(resultado == true) {
			cadenaresultado = "true";
		}
		String cadena = "{dni:"+cadenadni+", concedida:"+cadenaresultado +"}";
		JSONObject datos = new JSONObject(cadena);
		return datos;
	}
}
