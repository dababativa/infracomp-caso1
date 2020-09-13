package caso1;

import java.util.ArrayList;

public class Buffer {
	
	private int recursosLibres;
	private ArrayList<Mensaje> mensajes;
	
	public Buffer(int tamanho) {
		recursosLibres = tamanho;
		mensajes = new ArrayList<Mensaje>();
		System.out.println("Se creó un Buffer");
	}
	
	public void enviarMensaje(Mensaje mensaje) {
		if(recursosLibres<1) {
			try {
				System.out.println("Se va a esperar (Buffer)");
				wait();
				System.out.println("Se espero (Buffer)");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		recursosLibres--;
		mensajes.add(mensaje);
		System.out.println("Se va a añadir un mensaje al Buffer");
	}
	public void existo(){
		System.out.println("Existo");
	}
	public Mensaje retirarMensaje() {
		if(mensajes.isEmpty()) {
			System.out.println("El Buffer esta vacio nea");
			return null;
		}
		Mensaje message = mensajes.remove(0);
		recursosLibres++;
		System.out.println("Se fue un mensaje del Buffer");
		if(recursosLibres==1) {
			System.out.println("Se notifica que ahora hay espacio en el Buffer");
			notify();
		}
		return message;
	}
}
