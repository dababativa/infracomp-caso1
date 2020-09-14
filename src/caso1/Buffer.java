package caso1;

import java.util.ArrayList;

public class Buffer {
	
	private int recursosLibres;
	private ArrayList<Mensaje> mensajes;
	private int clientes;
	public Buffer(int tamanho, int pClientes) {
		recursosLibres = tamanho;
		clientes = pClientes;
		mensajes = new ArrayList<Mensaje>();
	}
	public boolean hayClientes(){
		return clientes>0;
	}
	public void quite(){
		clientes--;
	}
	public synchronized void enviarMensaje(Mensaje mensaje) {
		if(recursosLibres<1) {
			try {
				System.out.println("Se va a esperar (Buffer)");
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		recursosLibres--;
		mensajes.add(mensaje);
		System.out.println("Se añadio " + mensaje.identificar() + " al Buffer");
		mensajesActuales("añadidos");
	}
	public void mensajesActuales(String a){
		System.out.println("Hay " + mensajes.size() + " mensajes " + a);
	}
	public synchronized Mensaje retirarMensaje() {
		if(mensajes.isEmpty()) {
			System.out.println("El Buffer esta vacio nea");
			return null;
		}
		Mensaje message = mensajes.remove(0);
		recursosLibres++;
		System.out.println("Se fue un mensaje del Buffer " + message.identificar());
		if(recursosLibres==1) {
			System.out.println("Se notifica que ahora hay espacio en el Buffer");
			notify();
		}
		mensajesActuales("sacados");
		return message;
	}
}
