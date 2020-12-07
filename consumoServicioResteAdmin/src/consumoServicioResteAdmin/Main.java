package consumoServicioResteAdmin;

import java.io.IOException;
import java.util.Scanner;

import org.restlet.resource.ClientResource;

public class Main {
	
	public static void main(String  [] args) {
		
		Scanner entradaScanner = new Scanner(System.in);
		System.out.println("Bienvenido al programa de solicitud de ayuda de la asignatura e-Admin");
		System.out.println("Introduzca DNI sin letra");
		String entradaTeclado ="";
		entradaTeclado = entradaScanner.nextLine();
		System.out.println("****** Comprobando si existe becas concedidas asociadas al dni.... ******");
		doGet(entradaTeclado);
	}

	public static void doGet(String dni_ciudadano) {
		String url = "http://localhost:8081/becas/"+dni_ciudadano;
		ClientResource resource = new ClientResource(url);
		resource.get();
		if(resource.getStatus().isSuccess()) {
			try {
				String respuestaServicio = resource.getResponseEntity().getText();
				System.out.println(respuestaServicio);
			}catch(IOException e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("Problemas en el acceso al servicio");
		}
		
		
	}
}
